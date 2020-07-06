package com.rabbitmq.config;

import com.rabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 工作队列配置
 * @author: Tj
 * @create: 2020-06-16 11:40
 **/


@Configuration
public class WorkQueueConfig {

    /**
     * 创建工作队列
     * @return
     */
    @Bean
    public Queue workQueue() {
        //队列持久化
        return new Queue(RabbitMQConstant.WORK_QUEUE, true);
    }

}
