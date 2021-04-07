package com.micro.service.service2.mqListener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@RabbitListener(queues ="test1")
public class Test1Listener {
    @RabbitHandler
    public void process(Channel channel, Message message,String msg) {
        try {
            Long time=System.currentTimeMillis();
            String nowDate=new Date(time).toString();
            System.out.println(nowDate+Thread.currentThread().getName()+"Test1Listener1接收到msg====="+msg);
            Thread.sleep(3000);
            //   System.out.println("消息处理完成");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
