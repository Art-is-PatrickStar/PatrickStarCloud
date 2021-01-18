package com.wsw.patrickstar.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.wsw.patrickstar.mapper.RecepienterMapper;
import com.wsw.patrickstar.service.RecepienterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2020/11/20
 * @Description:
 */
@Service
@Slf4j
public class RecepienterServiceImpl implements RecepienterService {
    @Resource
    private RecepienterMapper recepienterMapper;

    @RabbitHandler
    @RabbitListener(queues = "queueTask")
    public void receiveMessage(Message message, Channel channel, Map<String, Object> messageMap) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("summercloud-task-service接收到了消息: " + objectMapper.writeValueAsString(messageMap));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            Long taskId = MapUtils.getLong(messageMap, "taskId");
            String taskName = MapUtils.getString(messageMap, "taskName");
            String recepientName = MapUtils.getString(messageMap, "recepientName");
            String remark = MapUtils.getString(messageMap, "remark");
            if (null != taskId && StringUtils.isNotBlank(taskName) && StringUtils.isNotBlank(recepientName)) {
                int result = insert(taskId, taskName, recepientName, remark);
                if (result >= 1) {
                    log.info("summercloud-task-service插入数据成功!");
                }
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

    @Override
    public int insert(Long taskId, String taskName, String name, String remark) {
        return recepienterMapper.insert(taskId, taskName, name, remark);
    }
}
