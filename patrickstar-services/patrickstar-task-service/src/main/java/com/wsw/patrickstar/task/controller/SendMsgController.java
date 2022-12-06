package com.wsw.patrickstar.task.controller;

import com.wsw.patrickstar.common.enums.TopicEnum;
//import com.wsw.patrickstar.kafka.provider.KafkaProvider;
import com.wsw.patrickstar.rabbitmq.provider.RabbitProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 10:15
 */
@Slf4j
@RestController
public class SendMsgController {
//    @Resource
//    private KafkaProvider kafkaProvider;
    @Resource
    private RabbitProvider rabbitProvider;

//    @GetMapping("/sendKafkaMsg")
//    public void sendKafkaMsg() {
//        List<String> taskMessages = new ArrayList<>();
//        taskMessages.add("wsw");
//        taskMessages.add("sws");
//        kafkaProvider.sendMessage(TopicEnum.TASK_TO_TASK, taskMessages);
//    }

    @PostMapping("/sendRabbitMsg")
    public void sendRabbitMsg(@RequestParam String exchangeName, @RequestParam String routingKey) {
        for (int i = 0; i < 10000; i++) {
            rabbitProvider.sendMessage(exchangeName, routingKey, i + "a");
        }
        log.info("消息发送完成");
    }
}
