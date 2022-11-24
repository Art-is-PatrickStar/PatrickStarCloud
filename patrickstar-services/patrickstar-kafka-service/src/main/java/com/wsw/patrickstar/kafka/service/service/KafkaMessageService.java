package com.wsw.patrickstar.kafka.service.service;

import com.wsw.patrickstar.common.enums.TopicEnum;
import com.wsw.patrickstar.kafka.service.entity.TaskMessage;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/16 10:05
 */
public interface KafkaMessageService {
    void sendMessage(TopicEnum topicEum, TaskMessage message);
}
