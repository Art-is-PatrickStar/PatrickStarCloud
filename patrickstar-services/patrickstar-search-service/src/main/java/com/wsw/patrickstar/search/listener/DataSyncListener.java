//package com.wsw.patrickstar.search.listener;
//
//import cn.hutool.core.map.MapUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.TypeReference;
//import com.rabbitmq.client.Channel;
//import com.wsw.patrickstar.api.model.dto.TaskDTO;
//import com.wsw.patrickstar.api.response.Result;
//import com.wsw.patrickstar.api.service.TaskCloudService;
//import com.wsw.patrickstar.common.constant.RabbitConstants;
//import com.wsw.patrickstar.search.service.ElasticService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
///**
// * @Author WangSongWen
// * @Date: Created in 16:15 2021/1/27
// * @Description: mysql与es数据同步主消息服务
// * <p>
// * 消息接收到后,根据taskId去查数据库,如果数据存在或已更改,则更新到es中,否则不同步
// */
//@Slf4j
//@Service
//public class DataSyncListener {
//    @Resource
//    private ElasticService elasticService;
//    @Resource
//    private TaskCloudService taskCloudService;
//
//    @RabbitHandler
//    @RabbitListener(queues = RabbitConstants.TASK_QUEUE)
//    public void onMessage(Message message, Channel channel) throws Exception {
//        try {
//            String messageBody = new String(message.getBody());
//            Map<String, Object> messageMap = JSONObject.parseObject(messageBody, new TypeReference<Map<String, Object>>() {
//            });
//            if (MapUtil.isEmpty(messageMap)) {
//                log.error("数据同步服务接收到的消息为空, 无法同步!");
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//                return;
//            }
//            log.info("数据同步服务接收到了消息: " + JSONObject.toJSONString(messageMap));
//            // 获取数据操作类型
//            String operationType = MapUtil.getStr(messageMap, "operationType");
//            Long taskId = MapUtil.getLong(messageMap, "taskId");
//            TaskDTO taskDTO = null, esTask;
//            Result<TaskDTO> result = taskCloudService.selectTaskByTaskId(taskId);
//            if (result.getStatus() == 200 && result.getData() != null) {
//                taskDTO = result.getData();
//            }
//            esTask = elasticService.getEsTaskById(taskId);
//            switch (operationType) {
//                case "ADD":
//                    // 数据库新增成功, 则新增至es
//                    if (taskDTO != null) {
//                        try {
//                            elasticService.addEsTask(taskDTO);
//                            log.info("新增数据同步至ElasticSearch成功!");
//                        } catch (Exception e) {
//                            log.error("新增数据同步至ElasticSearch失败! " + e.getMessage());
//                            throw new Exception("新增数据同步至ElasticSearch失败");
//                        }
//                    } else {
//                        log.error("数据未成功新增至数据库,数据同步服务不执行!");
//                        throw new Exception("数据未成功新增至数据库,数据同步服务不执行!");
//                    }
//                    break;
//                case "DELETE":
//                    // 数据库中已删除, es还存在, 则从es中删除
//                    if (taskDTO == null && esTask != null) {
//                        try {
//                            elasticService.deleteEsTaskById(taskId);
//                            log.info("删除数据同步至ElasticSearch成功!");
//                        } catch (Exception e) {
//                            log.error("删除数据同步至ElasticSearch失败! " + e.getMessage());
//                            throw new Exception("删除数据同步至ElasticSearch失败");
//                        }
//                    } else {
//                        log.error("数据未成功从数据库中删除或不在es中,数据同步服务不执行!");
//                        throw new Exception("数据未成功从数据库中删除或不在es中,数据同步服务不执行!");
//                    }
//                    break;
//                case "UPDATE":
//                    // 数据库中已更新, es未更新, 则更新es
//                    if (!taskDTO.equals(esTask)) {
//                        try {
//                            elasticService.updateEsTask(taskDTO);
//                            log.info("更新数据同步至ElasticSearch成功!");
//                        } catch (Exception e) {
//                            log.error("更新数据同步至ElasticSearch失败! " + e.getMessage());
//                            throw new Exception("更新数据同步至ElasticSearch失败");
//                        }
//                    } else {
//                        log.error("数据未成功更新至数据库中,数据同步服务不执行!");
//                        throw new Exception("数据未成功更新至数据库中,数据同步服务不执行!");
//                    }
//                    break;
//                default:
//                    break;
//            }
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//            log.info("MySQL与ElasticSearch数据同步成功!");
//        } catch (Exception e) {
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//            log.error("MySQL与ElasticSearch数据同步失败! error: " + e.getMessage());
//        }
//    }
//}
