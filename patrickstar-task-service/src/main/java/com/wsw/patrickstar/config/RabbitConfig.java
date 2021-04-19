package com.wsw.patrickstar.config;

import com.wsw.patrickstar.api.Rabbit;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 17:18 2020/10/30
 * @Description: rabbit配置类
 */
@Configuration
public class RabbitConfig {
    // task队列
    @Bean
    Queue taskQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("directQueue",true,true,false);
        //一般设置一下队列的持久化就好,其余两个就是默认false
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Rabbit.DEAD_LETTER_EXCHANGE); // 绑定死信交换机
        return QueueBuilder.durable(Rabbit.TASK_QUEUE).withArguments(args).build();
    }

    // 死信队列
    @Bean
    Queue deadLetterQueue() {
        return new Queue(Rabbit.DEAD_LETTER_QUEUE, true);
    }

    // task交换器
    @Bean
    DirectExchange taskExchange() {
        return new DirectExchange(Rabbit.TASK_EXCHANGE, true, false);
    }

    // 死信队列交换器
    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(Rabbit.DEAD_LETTER_EXCHANGE, true, false);
    }

    // 将task队列绑定到task交换机
    @Bean
    Binding bindTaskQueue() {
        return BindingBuilder.bind(taskQueue()).to(taskExchange()).with(Rabbit.ROUTING_KEY);
    }

    // 将ta死信队列绑定到死信交换机
    @Bean
    Binding binddeadLetterQueue() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(Rabbit.ROUTING_KEY);
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

}
