package com.wsw.patrickstar.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.wsw.patrickstar.exception.TaskServiceException;
import com.wsw.patrickstar.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 16:32 2021/3/1
 * @Description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CheckDeadLetterMessage {
    @Resource
    private MailService mailService;

    @RabbitHandler
    @RabbitListener(queues = "dead-letter-queue")
    public void checkDeadLetterMessageHandler(Message message, Channel channel, Map<String, Object> messageMap) throws TaskServiceException {
        try {
            if (MapUtils.isNotEmpty(messageMap)) {
                ObjectMapper objectMapper = new ObjectMapper();
                String messageString = objectMapper.writeValueAsString(messageMap);
                log.info("死信队列接收到的消息: " + messageString);
                // 对进入死信队列的消息进行处理或补偿
                Long taskId = MapUtils.getLong(messageMap, "taskId");
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("任务死信队列接收到消息，请重新处理!");
                simpleMailMessage.setFrom("2544894086@qq.com");
                simpleMailMessage.setTo("2544894086@qq.com");
                simpleMailMessage.setCc("2544894086@qq.com");
                simpleMailMessage.setBcc("2544894086@qq.com");
                simpleMailMessage.setSentDate(new Date());
                simpleMailMessage.setText("失败的任务ID: " + taskId);
                mailService.sendMail(simpleMailMessage);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TaskServiceException(e.getMessage(), e.getCause());
        }
    }
}
