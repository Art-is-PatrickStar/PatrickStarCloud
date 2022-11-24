package com.wsw.patrickstar.kafka.service.controller;

import com.wsw.patrickstar.common.enums.TopicEnum;
import com.wsw.patrickstar.kafka.service.entity.TaskMessage;
import com.wsw.patrickstar.kafka.service.service.KafkaMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 10:15
 */
@Slf4j
@RestController
public class SendMsgController {
    @Resource
    private KafkaMessageService kafkaMessageService;

    @GetMapping("/send")
    public void sendMsg() {
        kafkaMessageService.sendMessage(TopicEnum.TASK_TO_TASK, new TaskMessage(1L, "wsw"));
    }
}
