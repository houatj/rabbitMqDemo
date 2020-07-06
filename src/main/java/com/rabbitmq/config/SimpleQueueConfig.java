package com.rabbitmq.config;

import com.rabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 简单队列配置
 * @author: Tj
 * @create: 2020-06-16 11:39
 **/


@Configuration
public class SimpleQueueConfig {

    /**
     * 创建简单队列(点对点) 只有一个消费者
     * @return
     */
    @Bean
    public Queue simpleQueue() {
        //队列持久化
        return new Queue(RabbitMQConstant.SIMPLE_QUEUE, true);
    }

}
