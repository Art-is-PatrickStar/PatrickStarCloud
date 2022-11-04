package com.wsw.patrickstar.task.message;

import com.wsw.patrickstar.task.entity.TaskMessage;
import com.wsw.patrickstar.common.enums.TopicEnum;
import com.wsw.patrickstar.common.utils.SerializeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: wangsongwen
 * @Date: 2021/11/17 8:54
 * @Description: kafka发送消息
 */
@Slf4j
@Service
public class KafkaMessageService {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaMessageService(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TopicEnum topicEum, TaskMessage message) {
        if (message == null) {
            log.error("发送数据为空, topicId:{}, topicDesc:{}", topicEum.getTopicId(), topicEum.getTopicDesc());
            return;
        }
        ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(topicEum.getTopicId(),
                UUID.randomUUID().toString(), SerializeUtils.serialize2JsonByte(message));
        CompletableFuture<SendResult<String, byte[]>> completable = future.completable();
        completable.whenCompleteAsync((n, e) -> {
            if (null != e) {
                log.error("发送异常: " + e.getMessage());
            } else {
                log.info("发送成功");
            }
            kafkaTemplate.flush();
        });
    }
}
