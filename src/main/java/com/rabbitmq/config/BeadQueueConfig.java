package com.rabbitmq.config;

import com.rabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 死信队列配置
 * @author: Tj
 * @create: 2020-06-16 11:44
 **/


@Configuration
public class BeadQueueConfig {

    /**
     * 消息持久化
     * 设置消息持久化必须先设置队列持久化，要不然队列不持久化，消息持久化，队列都不存在了，消息存在还有什么意义。
     * 消息持久化需要将交换机持久化、队列持久化、消息持久化，才能最终达到持久化的目的
     */



    /**
     * 创建死信交换机,跟普通交换机一样,只是死信交换机只用来接收过期的消息 或者消费失败 并且配置了死信队列的 队列
     * @return
     */
    @Bean
    public DirectExchange beadExchange() {
        return new DirectExchange(RabbitMQConstant.DEAD_EXCHANGE, true, false);
    }

    /**
     * 创建死信队列,该队列没有消费者,消息会设置过期时间,消息过期后会发送到死信交换机,在由死信交换机转发至处理该消息的队列中
     * @return
     */
    @Bean
    public Queue BeadQueue() {
        return new Queue(RabbitMQConstant.DEAD_QUEUE, true);
    }

    /**
     * 将死信队列与死信交换机绑定,key1
     *
     * @return
     */
    @Bean
    public Binding beadQueueBinding() {
        return BindingBuilder.bind(BeadQueue()).to(beadExchange()).with(RabbitMQConstant.DEAD_ROUTING_KEY);
    }




    @Bean
    public DirectExchange consumerExchange() {
        return new DirectExchange(RabbitMQConstant.CONSUMER_EXCHANGE, true, false);
    }


    /**
     * 产生私信队列消息的 队列
     *
     */
    @Bean
    public Queue consumerBeadQueue() {
        // 队列持久
        Map<String, Object> arguments = new HashMap<>(2);
        // 死信路由到死信交换器DLX
        arguments.put("x-dead-letter-exchange", RabbitMQConstant.DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", RabbitMQConstant.DEAD_ROUTING_KEY);
        return new Queue(RabbitMQConstant.CONSUMER_BEAD_QUEUE, true, false, false, arguments);

    }

    /**
     *
     *创建 产生死信队列信息的  队列
     * @return
     */
    @Bean
    public Binding consumerBeadQueueBinding() {
        return BindingBuilder.bind(consumerBeadQueue()).to(consumerExchange()).with(RabbitMQConstant.CONSUMER_BEAD_KEY);
    }





}
