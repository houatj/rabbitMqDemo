package com.rabbitmq.consumer.sub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: 发布订阅消费者1
 * @author: Tj
 * @create: 2020-06-16 09:59
 **/
@Component
public class PubAndSubConsumer1 {

    @RabbitListener(queues = RabbitMQConstant.PUB_SUB_QUEUE1)
    public void process(Message message, Channel channel) throws IOException {
        try {
            String msg = new String(message.getBody());
            System.out.println("发布订阅消费者1，消费消息：" + msg);
            //业务逻辑
            // 确认收到消息，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            /**
             * message.getMessageProperties().getRedelivered()
             * 获取消息是否之前就被消费过
             */
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                System.out.println("消息即将再次返回队列处理");
                // requeue为是否重新回到队列，true重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }


}
