package com.wsw.patrickstar.message;

import com.wsw.patrickstar.entity.TaskMessage;
import com.wsw.patrickstar.enums.TopicEnum;
import com.wsw.patrickstar.utils.SerializeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: wangsongwen
 * @Date: 2021/11/17 9:36
 * @Description: TODO
 */
@Slf4j
@Service
public class TaskMessageConsumer {

    @KafkaListener(id = TopicEnum.TASK_TO_TASK_ID, topics = TopicEnum.TASK_TO_TASK_TOPIC)
    public void taskConsumer(List<ConsumerRecord<String, byte[]>> consumerRecordList, Acknowledgment ack) {
        if (CollectionUtils.isEmpty(consumerRecordList)) {
            return;
        }
        List<TaskMessage> taskMessageList = parse(consumerRecordList);
        try {
            log.info("task messages: " + taskMessageList.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            ack.acknowledge();
        }
    }

    private List<TaskMessage> parse(List<ConsumerRecord<String, byte[]>> consumerRecordList) {
        return consumerRecordList.stream().map(record -> SerializeUtils.deserialize(record.value(), TaskMessage.class))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }
}
