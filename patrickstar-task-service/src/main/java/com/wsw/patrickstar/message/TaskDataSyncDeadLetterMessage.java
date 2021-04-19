package com.wsw.patrickstar.message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rabbitmq.client.Channel;
import com.wsw.patrickstar.api.Rabbit;
import com.wsw.patrickstar.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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
public class TaskDataSyncDeadLetterMessage {
    @Resource
    private MailService mailService;

    @RabbitHandler
    @RabbitListener(queues = Rabbit.DEAD_LETTER_QUEUE)
    public void checkDeadLetterMessageHandler(Message message, Channel channel) throws Exception {
        try {
            String messageBody = new String(message.getBody());
            Map<String, Object> messageMap = JSONObject.parseObject(messageBody, new TypeReference<Map<String, Object>>() {});
            if (MapUtils.isNotEmpty(messageMap)) {
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
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            log.info("死信队列发送异常邮件成功!");
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            log.error("死信队列发送异常邮件失败: " + e.getMessage());
        }
    }
}
