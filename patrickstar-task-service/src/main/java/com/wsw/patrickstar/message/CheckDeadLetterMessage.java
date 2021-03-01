package com.wsw.patrickstar.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.wsw.patrickstar.entity.Task;
import com.wsw.patrickstar.exception.TaskServiceException;
import com.wsw.patrickstar.service.TaskService;
import com.xxl.job.core.context.XxlJobHelper;
import org.apache.commons.collections4.MapUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 16:32 2021/3/1
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CheckDeadLetterMessage {
    @Resource
    private AsyncSendMessage asyncSendMessage;
    @Resource
    private TaskService taskService;

    @RabbitHandler
    @RabbitListener(queues = "dead-letter-queue")
    public void checkDeadLetterMessageHandler(Message message, Channel channel, Map<String, Object> messageMap) throws TaskServiceException {
        try {
            if (MapUtils.isNotEmpty(messageMap)) {
                ObjectMapper objectMapper = new ObjectMapper();
                String messageString = objectMapper.writeValueAsString(messageMap);
                XxlJobHelper.log("死信队列接收到的消息: " + messageString);
                // 对进入死信队列的消息进行处理或补偿
                Long taskId = MapUtils.getLong(messageMap, "taskId");
                String operationType = MapUtils.getString(messageMap, "operationType");
                Task task = taskService.selectTaskById(taskId);
                if (task != null) {
                    Map<String, Object> reMessageMap = new HashMap<>();
                    reMessageMap.put("operationType", operationType);
                    reMessageMap.put("taskId", taskId);
                    System.out.println(reMessageMap);
                    //asyncSendMessage.asyncSendMessage(reMessageMap);
                }
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TaskServiceException(e.getMessage(), e.getCause());
        }
    }
}
