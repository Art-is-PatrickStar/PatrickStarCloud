//package com.wsw.patrickstar.task.message.consumer;
//
//import com.wsw.patrickstar.common.enums.TopicEnum;
//import com.wsw.patrickstar.common.utils.SerializeUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
///**
// * @Description:
// * @Author: wangsongwen
// * @Date: 2022/12/6 16:44
// */
//@Slf4j
//@Service
//public class KafkaTaskConsumer {
//    @KafkaListener(id = TopicEnum.TASK_TO_TASK_ID, topics = TopicEnum.TASK_TO_TASK_TOPIC)
//    public void taskConsumer(List<ConsumerRecord<String, byte[]>> consumerRecordList, Acknowledgment ack) {
//        if (CollectionUtils.isEmpty(consumerRecordList)) {
//            return;
//        }
//        List<String> taskMessages = parse(consumerRecordList);
//        try {
//            log.info("task messages: " + taskMessages.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        } finally {
//            ack.acknowledge();
//        }
//    }
//
//    private List<String> parse(List<ConsumerRecord<String, byte[]>> consumerRecordList) {
//        return consumerRecordList.stream().map(record -> SerializeUtils.deserialize(record.value(), String.class))
//                .filter(Objects::nonNull).collect(Collectors.toList());
//    }
//}
