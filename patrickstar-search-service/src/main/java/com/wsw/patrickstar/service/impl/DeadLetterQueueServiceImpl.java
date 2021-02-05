package com.wsw.patrickstar.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.wsw.patrickstar.service.DeadLetterQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 15:30 2021/2/5
 * @Description: 死信队列消息处理
 */
@Service
@Slf4j
public class DeadLetterQueueServiceImpl implements DeadLetterQueueService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RabbitHandler
    @RabbitListener(queues = "dead-letter-queue")
    public void deadLetterMessage(Message message, Channel channel, Map<String, Object> messageMap) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String messageString = objectMapper.writeValueAsString(messageMap);
        System.out.println("死信队列接收到的消息: " + messageString);
    }
}
