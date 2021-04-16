package com.wsw.patrickstar.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rabbitmq.client.Channel;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.exception.TaskServiceException;
import com.wsw.patrickstar.feign.client.TaskClient;
import com.wsw.patrickstar.service.DataSyncService;
import com.wsw.patrickstar.service.ElasticService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @Author WangSongWen
 * @Date: Created in 16:15 2021/1/27
 * @Description: mysql与es数据同步主消息服务
 * <p>
 * 消息接收到后,根据taskId去查数据库,如果数据存在或已更改,则更新到es中,否则不同步
 */
@Service
@Slf4j
public class DataSyncServiceImpl implements DataSyncService {
    @Resource
    private ElasticService elasticService;
    @Resource
    private TaskClient taskClient;

    @Override
    @RabbitHandler
    @RabbitListener(queues = "task-queue")
    public void receiveMessage(Message message, Channel channel) throws Exception {
        try {
            Map<String, Object> messageMap = JSONObject.parseObject(message.getBody(), Map.class);
            if (MapUtils.isNotEmpty(messageMap)) {
                log.error("数据同步服务接收到的消息为空, 无法同步!");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
                return;
            }
            log.info("数据同步服务接收到了消息: " + JSONObject.toJSONString(messageMap));
            // 获取数据操作类型
            String operationType = MapUtils.getString(messageMap, "operationType");
            Long taskId = MapUtils.getLong(messageMap, "taskId");
            Task task, esTask = null;
            task = taskClient.selectTask(taskId);
            Optional<Task> taskOptional = elasticService.getEsTaskById(taskId);
            if (taskOptional.isPresent()) {
                esTask = taskOptional.get();
            }
            switch (operationType) {
                case "ADD":
                    // 数据库新增成功, 则新增至es
                    if (task != null) {
                        try {
                            elasticService.addEsTask(task);
                            log.info("新增数据同步至ElasticSearch成功!");
                        } catch (Exception e) {
                            log.error("新增数据同步至ElasticSearch失败! " + e.getMessage());
                            throw new TaskServiceException(e.getMessage(), e.getCause());
                        }
                    } else {
                        log.error("数据未成功新增至数据库,数据同步服务不执行!");
                        throw new TaskServiceException("数据未成功新增至数据库,数据同步服务不执行!");
                    }
                    break;
                case "DELETE":
                    // 数据库中已删除, es还存在, 则从es中删除
                    if (task == null && esTask != null) {
                        try {
                            elasticService.deleteEsTaskById(taskId);
                            log.info("删除数据同步至ElasticSearch成功!");
                        } catch (Exception e) {
                            log.error("删除数据同步至ElasticSearch失败! " + e.getMessage());
                            throw new TaskServiceException(e.getMessage(), e.getCause());
                        }
                    } else {
                        log.error("数据未成功从数据库中删除或不在es中,数据同步服务不执行!");
                        throw new TaskServiceException("数据未成功从数据库中删除或不在es中,数据同步服务不执行!");
                    }
                    break;
                case "UPDATE":
                    // 数据库中已更新, es未更新, 则更新es
                    if (!task.equals(esTask)) {
                        try {
                            elasticService.updateEsTask(task);
                            log.info("更新数据同步至ElasticSearch成功!");
                        } catch (Exception e) {
                            log.error("更新数据同步至ElasticSearch失败! " + e.getMessage());
                            throw new TaskServiceException(e.getMessage(), e.getCause());
                        }
                    } else {
                        log.error("数据未成功更新至数据库中,数据同步服务不执行!");
                        throw new TaskServiceException("数据未成功更新至数据库中,数据同步服务不执行!");
                    }
                    break;
                default:
                    break;
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            log.info("MySQL与ElasticSearch数据同步成功!");
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            log.error("MySQL与ElasticSearch数据同步失败! error: " + e.getMessage());
        }
    }
}
