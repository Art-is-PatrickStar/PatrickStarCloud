package com.wsw.patrickstar.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 16:13 2021/1/27
 * @Description:
 */
public interface DataSyncService {
    void receiveMessage(Message message, Channel channel, Map<String, Object> messageMap) throws IOException;
}
