package com.wsw.patrickstar.rabbitmq.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/7 14:39
 */
@Slf4j
@Component
public class RabbitProvider implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.error("消息发送到exchange失败: correlationData:{}, cause:{}", correlationData, cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息从exchange发送到queue失败: message:{},从交换机exchange:{},以路由键routingKey:{}," + "未找到匹配队列,replyCode:{},replyText:{}",
                message, exchange, routingKey, replyCode, replyText);
    }

    public void sendMessage(String exchangeName, String routingKey, String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, correlationData);
    }
}
