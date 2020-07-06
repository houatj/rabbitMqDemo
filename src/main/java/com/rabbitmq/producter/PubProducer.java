package com.rabbitmq.producter;

import com.rabbitmq.constant.RabbitMQConstant;
import com.rabbitmq.mqsend.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class PubProducer {
    @Autowired
    private Send send;

    /**
     * 简单队列发送消息
     * @param msg
     * @return
     */
    @GetMapping("/simpleQueue")
    public String simpleQueueSendMsg(String msg) {
        send.simpleSend(RabbitMQConstant.SIMPLE_QUEUE, msg);
        return "success";
    }

    /**
     * 简单队列其实和工作队列是一样的 只是一个是多个消费者 一个是单个消费者
     *
     * 工作队列有两种工作方式：轮询分发(默认)、公平分发即当某个消费者没有消费完成之前不用再分发消息
     *
     * spring.rabbitmq.listener.simple.prefetch=1
     * 消费者每次从队列获取的消息数量。此属性当不设置时为：轮询分发，设置为1为：公平分发
     *
     *
     * 工作队列发送消息
     * @param msg
     * @return
     */
    @GetMapping("/workQueue")
    public String workQueueSendMsg(String msg) {
        send.workSend(RabbitMQConstant.WORK_QUEUE, msg);
        return "success";
    }

    /**
     * 发布订阅发送消息
     *
     * 绑定发布订阅 扇形交换机的每个队列都会受到消息， 每个队列的消费者， 只有一个消费者能拿到队列的消息
     * @param msg
     * @return
     */
    @GetMapping("/pubAndSubQueue")
    public String pubAndSubQueueSendMsg(String msg) {
        send.pubAndSubSend(RabbitMQConstant.FANOUT_EXCHANGE, msg);
        return "success";
    }

    /**
     * 路由模式发送消息
     *
     * 消息根据routingKey 路由到指定配置好的队列中 一个队列 可以绑定多个routingKey
     * @param msg
     * @param routingKey routingKey1  routingKey2 routingKey3
     * @return
     */
    @GetMapping("/routingQueue")
    public String routingSendMsg(String msg, String routingKey) {
        send.routingSend(RabbitMQConstant.DIRECT_EXCHANGE, routingKey, msg);
        return "success";
    }


    /**
     * topic 模式发送消息
     *
     * 路由模式的延伸， 通过topic 匹配
     * @param msg
     * @param routingKey
     * @return
     */
    @GetMapping("/topicQueue")
    public String topicSendMsg(String msg, String routingKey) {
        send.topicSend(RabbitMQConstant.TOPIC_EXCHANGE, routingKey, msg);
        return "success";
    }

    /**
     * 产生死信队列
     * @param msg
     * @param routingKey consumerBeadKey
     * @return
     */
    @GetMapping("/addBeadQueue")
    public String addBeadQueue(String msg, String routingKey) {
        send.beadSend(RabbitMQConstant.CONSUMER_EXCHANGE, routingKey, msg);
        return "success";
    }


}
