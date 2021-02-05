package com.wsw.patrickstar.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 15:31 2021/2/5
 * @Description:
 */
public interface DeadLetterQueueService {
    void deadLetterMessage(Message message, Channel channel, Map<String, Object> messageMap) throws Exception;
}
