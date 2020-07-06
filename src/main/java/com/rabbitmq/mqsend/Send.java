package com.rabbitmq.mqsend;

import com.rabbitmq.constant.RabbitMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @消息队列发送工具类
 * @Autor zxf
 * @Date 2019/8/15
 */
@Component
public class Send implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private  static final Logger log = LoggerFactory.getLogger(Send.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public Send(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发布/订阅模式发送
     * @param json
     */
    public void pubAndSubSend(String fanoutExchange, String json) {
        Message message = this.setMessage(json);
        //在fanoutExchange中在绑定Q到X上时，会自动把Q的名字当作bindingKey。
        this.rabbitTemplate.convertAndSend(fanoutExchange, "", message);
    }

    /**
     * 简单模式发送
     * @param json
     */
    public void simpleSend(String simpleQueue, String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(simpleQueue, message);
    }

    /**
     * work模式发送
     * @param json
     */
    public void workSend(String workQueue, String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(workQueue, message);
    }

    /**
     * 路由模式发送
     *  @param routingKey
     * @param json
     */
    public void routingSend(String routingExchange, String routingKey, String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(routingExchange, routingKey, message);
    }

    /**
     * 主题模式发送
     *
     * @param routingKey
     * @param json
     */
    public void topicSend(String topicExchange, String routingKey, String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    }

    /**
     * 死信模式发送,用于定时任务处理
     *  @param routingKey
     * @param json
     */
    public void beadSend(String deadExchange, String routingKey, String json) {
        Message message = this.setMessage(json);
        this.rabbitTemplate.convertAndSend(deadExchange, routingKey, message);
    }

    /**
     * 设置消息参数
     * @param json
     * @return
     */
    private Message setMessage(String json){
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(json.getBytes(), messageProperties);
        //消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return message;
    }

    /**
     * 消息确认  是否成功发送到交换机中
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送确认成功");
        } else {
            log.info("消息发送失败:" + cause);
        }
    }

    /**
     * 消息发送失败回传 是否发送到具体队列中   失败才会调用
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode + ",replyText:"
                + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
        try {
            Thread.sleep(10000L);
            // TODO 重新发送消息至队列,此处应写一套重发机制,重发多少次结束,否则如果消息如果一直发送失败,则会一直发下去!
            this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
