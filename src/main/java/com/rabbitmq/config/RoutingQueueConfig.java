package com.rabbitmq.config;

import com.rabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 路由模式配置
 * @author: Tj
 * @create: 2020-06-16 11:42
 **/

@Configuration
public class RoutingQueueConfig {

    /**
     * 创建路由模式交换机 （直连交换机）
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConstant.DIRECT_EXCHANGE,true, false);
    }


    /**
     * 路由队列1
     * @return
     */
    @Bean
    public Queue routingQueue1() {
        return new Queue(RabbitMQConstant.ROUTING_QUEUE1, true);
    }

    /**
     * 路由队列2
     * @return
     */
    @Bean
    public Queue routingQueue2() {
        return new Queue(RabbitMQConstant.ROUTING_QUEUE2, true);
    }

    /**
     * 路由队列3
     * @return
     */
    @Bean
    public Queue routingQueue3() {
        return new Queue(RabbitMQConstant.ROUTING_QUEUE3, true);
    }

    /**
     * 路由模式队列1绑定交换机,通过key1发送
     * @return
     */
    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(routingQueue1()).to(directExchange()).with(RabbitMQConstant.ROUTING_KEY1);
    }

    /**
     * 路由模式队列2绑定交换机,通过key2发送
     * @return
     */
    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(routingQueue2()).to(directExchange()).with(RabbitMQConstant.ROUTING_KEY2);
    }

    /**
     * 路由模式队列2绑定交换机,通过key3发送
     * @return
     */
    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(routingQueue3()).to(directExchange()).with(RabbitMQConstant.ROUTING_KEY3);
    }

}
