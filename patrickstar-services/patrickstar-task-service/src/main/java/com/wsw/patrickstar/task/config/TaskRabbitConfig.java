package com.wsw.patrickstar.task.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/12/6 16:07
 */
@Configuration
public class TaskRabbitConfig {
    @Value("${task.exchange}")
    private String TASK_EXCHANGE;
    @Value("${task.queue}")
    private String TASK_QUEUE;
    @Value("${task.routing.key}")
    private String TASK_ROUTING_KEY;
    @Value("${task.dlq.exchange}")
    private String TASK_DLQ_EXCHANGE;
    @Value("${task.dlq.queue}")
    private String TASK_DLQ_QUEUE;

    /**
     * @Description: 声明Direct交换机 支持持久化
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:25
     */
    @Bean("taskExchange")
    public Exchange taskExchange() {
        return ExchangeBuilder.directExchange(TASK_EXCHANGE).durable(true).build();
    }

    /**
     * @Description: 声明一个队列 支持持久化
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:25
     */
    @Bean("taskQueue")
    public Queue taskQueue() {
        return QueueBuilder.durable(TASK_QUEUE)
                .withArgument("x-dead-letter-exchange", TASK_DLQ_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", TASK_DLQ_QUEUE).build();
    }

    /**
     * @Description: 通过绑定键 将指定队列绑定到一个指定的交换机
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:25
     */
    @Bean("taskBinding")
    public Binding taskBinding(@Qualifier("taskQueue") Queue queue, @Qualifier("taskExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TASK_ROUTING_KEY).noargs();
    }

    /**
     * @Description: 死信交换机
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:24
     */
    @Bean("taskDlqExchange")
    public Exchange taskDlqExchange() {
        return ExchangeBuilder.directExchange(TASK_DLQ_EXCHANGE).durable(true).build();
    }

    /**
     * @Description: 死信队列
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:24
     */
    @Bean("taskDlqQueue")
    public Queue taskDlqQueue() {
        return QueueBuilder.durable(TASK_DLQ_QUEUE).build();
    }

    /**
     * @Description: 死信绑定
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:24
     */
    @Bean("taskDlqBinding")
    public Binding taskDlqBinding(@Qualifier("taskDlqQueue") Queue queue, @Qualifier("taskDlqExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TASK_DLQ_QUEUE).noargs();
    }

    @Bean(name = "taskConsumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory taskConsumerBatchContainerFactory(ConnectionFactory connectionFactory) {
        //创建 SimpleRabbitListenerContainerFactory 对象
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //额外添加批量消费的属性
        factory.setBatchListener(true);
        factory.setConsumerBatchEnabled(true);
        //监听器一次批量处理的消息数量
        factory.setBatchSize(30);
        //手动确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(5);
        factory.setMaxConcurrentConsumers(10);
        factory.setPrefetchCount(200);
        return factory;
    }
}
