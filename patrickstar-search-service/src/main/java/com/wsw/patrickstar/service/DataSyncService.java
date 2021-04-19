package com.wsw.patrickstar.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * @Author WangSongWen
 * @Date: Created in 16:13 2021/1/27
 * @Description:
 */
public interface DataSyncService {
    void receiveMessage(Message message, Channel channel) throws Exception;
}
