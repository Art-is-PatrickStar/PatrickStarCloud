package com.wsw.patrickstar.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * @Author WangSongWen
 * @Date: Created in 16:39 2020/12/8
 * @Description:
 */
@Service
@Slf4j
public class MessageService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Map<String, Object> message) {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("summerCloudExchange", "", message, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息ID: " + (correlationData != null ? correlationData.getId() : null));
        if (ack) {
            log.info("消息发送到exchange成功: correlationData:{}", correlationData);
        } else {
            log.error("消息发送到exchange失败: correlationData:{}, cause:{}", correlationData, cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息从exchange发送到queue失败: message:{},从交换机exchange:{},以路由键routingKey:{}," + "未找到匹配队列,replyCode:{},replyText:{}",
                message, exchange, routingKey, replyCode, replyText);
    }
}
