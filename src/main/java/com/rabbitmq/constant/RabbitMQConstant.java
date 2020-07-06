package com.rabbitmq.constant;

/**
 * @description:
 * @author: Tj
 * @create: 2020-06-15 19:34
 **/

public class RabbitMQConstant {


    /**
     * 简单队列 点对点模式 只有一个消费者
     */
    public static final String SIMPLE_QUEUE = "simpleQueue";

    /**
     * 工作队列 有多个消费者
     */
    public static final String WORK_QUEUE = "work";




    /**
     *发布订阅模式交换机
     */
    public static final String FANOUT_EXCHANGE = "pubAndSubExchange";


    /**
     * 发布订阅队列1
     */
    public static final String PUB_SUB_QUEUE1 = "pubAndSubQueue1";


    /**
     * 发布订阅队列2
     */
    public static final String PUB_SUB_QUEUE2 = "pubAndSubQueue2";



    /**
     * 路由交换机
     */
    public static final String DIRECT_EXCHANGE = "routingExchange";

    /**
     * 路由队列1
     */
    public static final String ROUTING_QUEUE1 = "routingQueue1";

    /**
     * 路由队列2
     */
    public static final String ROUTING_QUEUE2 = "routingQueue2";
    /**
     * 路由队列3
     */
    public static final String ROUTING_QUEUE3 = "routingQueue3";
    /**
     *路由模式 routingKey1
     */
    public static final String ROUTING_KEY1 = "routingKey1";
    /**
     *路由模式 routingKey2
     */
    public static final String ROUTING_KEY2 = "routingKey2";
    /**
     *路由模式 routingKey2
     */
    public static final String ROUTING_KEY3 = "routingKey3";





    /**
     * topic 交换机
     */
    public static final String TOPIC_EXCHANGE = "topicExchange";
    /**
     * topic队列1
     */
    public static final String TOPIC_QUEUE1 = "topicQueue1";

    /**
     * topic队列2
     */
    public static final String TOPIC_QUEUE2 = "topicQueue2";
    /**
     * topic队列3
     */
    public static final String TOPIC_QUEUE3 = "topicQueue3";

    /**
     * topic routingKey1
     */
    public static final String TOPIC_ROUTING_KEY1 = "topicRoutingKey1.#";
    /**
     *topic routingKey2
     */
    public static final String TOPIC_ROUTING_KEY2 = "topicRoutingKey2.#";
    /**
     *topic routingKey
     */
    public static final String TOPIC_ROUTING_KEY = "topicRoutingKey.#";


    /**
     * 死信交换机
     */
    public static final String DEAD_EXCHANGE = "deadExchange";


    /**
     * 死信队列routingKey
     */
    public static final String DEAD_ROUTING_KEY = "deadRoutingKey";

    /**
     * 死信队列
     */
    public static final String DEAD_QUEUE = "deadQueue";

    /**
     * 产生死信队列的交换机
     */
    public static final String CONSUMER_EXCHANGE = "consumerExchange";

    /**
     * 处理死信队列的 消费队列
     */
    public static final String CONSUMER_BEAD_QUEUE = "consumerDeadQueue";


    /**
     * 产生死信队列 的队列routingKey
     */

    public static final String CONSUMER_BEAD_KEY="consumerBeadKey";


}
