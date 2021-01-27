package com.wsw.patrickstar.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.feign.client.TaskClient;
import com.wsw.patrickstar.service.DataSyncService;
import com.wsw.patrickstar.service.ElasticService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

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
    @RabbitListener(queues = "queueTask")
    public void receiveMessage(Message message, Channel channel, Map<String, Object> messageMap) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("数据同步服务接收到了消息: " + objectMapper.writeValueAsString(messageMap));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            // 获取数据操作类型
            String operationType = MapUtils.getString(messageMap, "operationType");
            Long taskId = MapUtils.getLong(messageMap, "taskId");
            Task task;
            switch (operationType) {
                case "ADD":
                    task = taskClient.selectTask(taskId);
                    if (task != null) {
                        try {
                            elasticService.addEsTask(task);
                            log.info("新增数据同步至ElasticSearch成功!");
                        } catch (Exception e) {
                            log.error("新增数据同步至ElasticSearch失败! " + e.getMessage());
                        }
                    }
                    break;
                case "DELETE":
                    if (taskId != null) {
                        try {
                            elasticService.deleteEsTaskById(taskId);
                            log.info("删除数据同步至ElasticSearch成功!");
                        } catch (Exception e) {
                            log.error("删除数据同步至ElasticSearch失败! " + e.getMessage());
                        }
                    }
                    break;
                case "UPDATE":
                    task = taskClient.selectTask(taskId);
                    if (task != null) {
                        try {
                            elasticService.updateEsTask(task);
                            log.info("更新数据同步至ElasticSearch成功!");
                        } catch (Exception e) {
                            log.error("更新数据同步至ElasticSearch失败! " + e.getMessage());
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.info("消息已重复处理失败,拒绝再次接收");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.info("消息即将再次返回队列处理");
                // requeue为是否重新回到队列，true重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            log.error(e.getMessage());
        }
    }
}
