package com.wsw.patrickstar.rabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/7 14:35
 */
@Slf4j
@Component
public class RabbitBatchConsumer {
    @RabbitListener(queues = "task_queue", containerFactory = "consumerBatchContainerFactory")
    public void message(List<Message> messages, Channel channel) throws Exception {
        try {
            List<String> messageBodys = messages.stream().map(e -> new String(e.getBody())).collect(Collectors.toList());
            log.info("成功接受到消息：" + JSON.toJSONString(messageBodys));
            log.info("消息长度：" + messages.size());
            for (Message message : messages) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
            log.info("消息处理成功");
        } catch (Exception e) {
            log.info("消息处理失败");
            for (Message message : messages) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
        }
    }
}
