package com.micro.service.service2.mqListener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

@Component
//@RabbitListener(queues ="test1")
public class Test1Listener0 {
    @RabbitHandler
    public void process(Channel channel, Message message,String msg) {
        try {
            LinkedBlockingQueue linkedBlockingQueue=new LinkedBlockingQueue();
            linkedBlockingQueue.add(message);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                    10000, TimeUnit.MILLISECONDS, linkedBlockingQueue, new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                }
            });

            System.out.println(Thread.currentThread().getName()+"Test1Listener0接收到msg====="+msg);
         //   Thread.sleep(5000);
            System.out.println("消息处理完成");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
