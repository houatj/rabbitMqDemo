package com.rabbitmq.config;

import com.rabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: topic配置
 * @author: Tj
 * @create: 2020-06-16 11:43
 **/

@Configuration
public class TopicQueueConfig {

    /**
     * 创建主题模式交换机 （topic交换机）
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE,true, false);
    }

    /**
     * topic队列1
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue(RabbitMQConstant.TOPIC_QUEUE1, true);
    }

    /**
     * topic队列2
     * @return
     */
    @Bean
    public Queue topicQueue2() {
        return new Queue(RabbitMQConstant.TOPIC_QUEUE2, true);
    }

    /**
     * topic队列3
     * @return
     */
    @Bean
    public Queue topicQueue3() {
        return new Queue(RabbitMQConstant.TOPIC_QUEUE3, true);
    }

    /**
     * 主题模式队列1绑定交换机
     * 符号“#”匹配一个或多个词，符号“*”匹配一个词。比如“hello.#”能够匹配到“hello.123.456”，但是“hello.*”只能匹配到“hello.123”
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(RabbitMQConstant.TOPIC_ROUTING_KEY1);
    }

    /**
     * 主题模式队列1绑定交换机
     * 符号“#”匹配一个或多个词，符号“*”匹配一个词。比如“hello.#”能够匹配到“hello.123.456”，但是“hello.*”只能匹配到“hello.123”
     * @return
     */
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(RabbitMQConstant.TOPIC_ROUTING_KEY2);
    }

    /**
     * 主题模式队列1绑定交换机
     * 符号“#”匹配一个或多个词，符号“*”匹配一个词。比如“hello.#”能够匹配到“hello.123.456”，但是“hello.*”只能匹配到“hello.123”
     * @return
     */
    @Bean
    public Binding topicBinding3() {
        return BindingBuilder.bind(topicQueue3()).to(topicExchange()).with(RabbitMQConstant.TOPIC_ROUTING_KEY);
    }




}
