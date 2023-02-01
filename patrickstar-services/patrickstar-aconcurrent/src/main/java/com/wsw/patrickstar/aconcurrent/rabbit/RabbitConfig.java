package com.wsw.patrickstar.aconcurrent.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/7 14:21
 */
@Configuration
public class RabbitConfig {
    private static final String WSW_TEST_MESSAGE_EXCHANGE_NAME = "wsw_test_message_exchange";
    private static final String WSW_TEST_MESSAGE_QUEUE_NAME = "wsw_test_message_queue";
    private static final String WSW_TEST_MESSAGE_ROUTING_KEY = "wsw_test_message_routing_key";
    private static final String WSW_TEST_MESSAGE_EXCHANGE_DLQ_NAME = "wsw_test_message_dlq_exchange";
    private static final String WSW_TEST_MESSAGE_QUEUE_DLQ_NAME = "wsw_test_message_dlq_queue";

    /**
     * @Description: 声明Direct交换机 支持持久化
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:25
     */
    @Bean("directExchange1")
    public Exchange directExchange1() {
        return ExchangeBuilder.directExchange(WSW_TEST_MESSAGE_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * @Description: 声明一个队列 支持持久化
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:25
     */
    @Bean("directQueue1")
    public Queue directQueue1() {
        return QueueBuilder.durable(WSW_TEST_MESSAGE_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", WSW_TEST_MESSAGE_EXCHANGE_DLQ_NAME)
                .withArgument("x-dead-letter-routing-key", WSW_TEST_MESSAGE_QUEUE_DLQ_NAME).build();
    }

    /**
     * @Description: 通过绑定键 将指定队列绑定到一个指定的交换机
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:25
     */
    @Bean("directBinding1")
    public Binding directBinding1(@Qualifier("directQueue1") Queue queue, @Qualifier("directExchange1") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(WSW_TEST_MESSAGE_ROUTING_KEY).noargs();
    }

    /**
     * @Description: 死信交换机
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:24
     */
    @Bean("dlqDirectExchange1")
    public Exchange dlqDirectExchange1() {
        return ExchangeBuilder.directExchange(WSW_TEST_MESSAGE_EXCHANGE_DLQ_NAME).durable(true).build();
    }

    /**
     * @Description: 死信队列
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:24
     */
    @Bean("dlqDirectQueue1")
    public Queue dlqDirectQueue1() {
        return QueueBuilder.durable(WSW_TEST_MESSAGE_QUEUE_DLQ_NAME).build();
    }

    /**
     * @Description: 死信绑定
     * @Author: wangsongwen
     * @DateTime: 2021/10/14 11:24
     */
    @Bean("dlqDirectBinding1")
    public Binding dlqDirectBinding1(@Qualifier("dlqDirectQueue1") Queue queue, @Qualifier("dlqDirectExchange1") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(WSW_TEST_MESSAGE_QUEUE_DLQ_NAME).noargs();
    }

    @Bean(name = "consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(ConnectionFactory connectionFactory) {
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
