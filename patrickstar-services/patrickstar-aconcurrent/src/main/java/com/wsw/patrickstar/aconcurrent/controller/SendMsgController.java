package com.wsw.patrickstar.aconcurrent.controller;

import com.wsw.patrickstar.aconcurrent.rabbit.RabbitProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/7 14:48
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
