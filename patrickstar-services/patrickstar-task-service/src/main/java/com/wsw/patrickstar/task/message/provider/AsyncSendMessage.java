//package com.wsw.patrickstar.task.message;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * @Author WangSongWen
// * @Date: Created in 16:59 2020/12/30
// * @Description:
// */
//@Slf4j
//@Component
//public class AsyncSendMessage {
//    @Resource
//    private RabbitMessageService messageService;
//
//    @Async("taskExecutor")
//    public void asyncSendMessage(String message) {
//        log.info("异步推送消息...");
//        messageService.sendMessage(message);
//    }
//}
