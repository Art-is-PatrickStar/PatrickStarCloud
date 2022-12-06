package com.wsw.patrickstar.kafka.provider;

import cn.hutool.core.collection.CollectionUtil;
import com.wsw.patrickstar.common.enums.TopicEnum;
import com.wsw.patrickstar.common.utils.SerializeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/6 16:39
 */
@Slf4j
@Service
public class KafkaProvider {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProvider(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TopicEnum topicEum, List<String> messages) {
        if (CollectionUtil.isEmpty(messages)) {
            log.error("发送数据为空, topicId:{}, topicDesc:{}", topicEum.getTopicId(), topicEum.getTopicDesc());
            return;
        }
        ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send(topicEum.getTopicId(),
                UUID.randomUUID().toString(), SerializeUtils.serialize2JsonByte(messages));
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
