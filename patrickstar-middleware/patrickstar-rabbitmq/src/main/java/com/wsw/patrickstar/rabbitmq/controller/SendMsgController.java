package com.wsw.patrickstar.rabbitmq.controller;

import com.wsw.patrickstar.rabbitmq.provider.RabbitProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/14 23:37
 */
@Slf4j
@RestController
public class SendMsgController {
    @Autowired
    private RabbitProvider rabbitProvider;

    @PostMapping("/sendMsg")
    public void sendMsg(@RequestParam String exchangeName, @RequestParam String routingKey) {
        for (int i = 0; i < 10000; i++) {
            rabbitProvider.sendMessage(exchangeName, routingKey, i + "a");
        }
        log.info("消息发送完成");
    }
}