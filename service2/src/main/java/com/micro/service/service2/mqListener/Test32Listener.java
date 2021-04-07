package com.micro.service.service2.mqListener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues ="test32")
public class Test32Listener {
    @RabbitHandler
    public void process(Channel channel, Message message,String msg) {
        try {
            System.out.println("Listener32接收到msg====="+msg);
            Thread.sleep(5000);
            System.out.println("消息处理完成");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
