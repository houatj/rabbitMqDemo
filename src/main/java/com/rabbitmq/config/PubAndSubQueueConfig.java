package com.rabbitmq.config;

import com.rabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 发布订阅配置
 * @author: Tj
 * @create: 2020-06-16 11:41
 **/

@Configuration
public class PubAndSubQueueConfig {


    /**
     *  创建订阅模式交换机 （扇形交换机）
     *  @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMQConstant.FANOUT_EXCHANGE,true, false);
    }

    /**
     * 发布订阅队列1
     * @return
     */
    @Bean
    public Queue PubAndSubQueue1() {
        return new Queue(RabbitMQConstant.PUB_SUB_QUEUE1, true);
    }

    /**
     * 发布订阅队列2
     * @return
     */
    @Bean
    public Queue PubAndSubQueue2() {
        return new Queue(RabbitMQConstant.PUB_SUB_QUEUE2, true);
    }

    /**
     * 订阅模式队列1绑定交换机
     * @return
     */
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(PubAndSubQueue1()).to(fanoutExchange());
    }

    /**
     * 订阅模式队列2绑定交换机
     * @return
     */
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(PubAndSubQueue2()).to(fanoutExchange());
    }





}
