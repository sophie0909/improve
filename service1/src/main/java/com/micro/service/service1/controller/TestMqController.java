package com.micro.service.service1.controller;

import com.micro.service.service1.mq.RabbitSender;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;

@RequestMapping("/mq")
@RestController
public class TestMqController {
    @Autowired
    private RabbitSender rabbitSender;
    @RequestMapping("/send")
    public Object sendMsg(){
        for (int i = 0; i <10000 ; i++) {
            rabbitSender.send("test1","测试消息"+i);
        }
        return "sucesss";
    }

    @RequestMapping("/sendFanoutMsg")
    public Object sendFanoutMsg(){
        rabbitSender.sendFanout("fanout_t","fanout消息");
        return "sucesss";
    }

    @RequestMapping("/sendTopicMsg")
    public Object sendTopicMsg(){
        rabbitSender.sendTopic("topic_exc","test","topic消息");
        rabbitSender.sendTopic("topic_exc","test3","test3topic消息");
        rabbitSender.sendTopic("topic_exc","test31","test31topic消息");
        rabbitSender.sendTopic("topic_exc","test32","test32topic消息");
        rabbitSender.sendTopic("topic_exc","test33","test33topic消息");
        return "sucesss";
    }
    @Resource
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息时设置 消息的过期时间
     * @return
     */
    @RequestMapping("/sendFailMsg")
    public Object sendFailMsg(){
//        rabbitTemplate.convertAndSend("","test4", "业务消息，请处理......", new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration("3000");
//                return message;
//            }
//        });
        rabbitSender.send("test4","业务消息，请处理......","3000");
        return "sucesss";
    }



//    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
//        // 实现要点之允许加锁失败节点限制（N-(N/2+1)）
//        int failedLocksLimit = failedLocksLimit();
//        List<RLock> acquiredLocks = new ArrayList<RLock>(locks.size());
//        // 实现要点之遍历所有节点通过EVAL命令执行lua加锁
//        for (ListIterator<RLock> iterator = locks.listIterator(); iterator.hasNext();) {
//            RLock lock = iterator.next();
//            boolean lockAcquired;
//            try {
//                // 对节点尝试加锁
//                lockAcquired = lock.tryLock(awaitTime, newLeaseTime, TimeUnit.MILLISECONDS);
//            } catch (RedisConnectionClosedException|RedisResponseTimeoutException e) {
//                // 如果抛出这类异常，为了防止加锁成功，但是响应失败，需要解锁
//                unlockInner(Arrays.asList(lock));
//                lockAcquired = false;
//            } catch (Exception e) {
//                // 抛出异常表示获取锁失败
//                lockAcquired = false;
//            }
//            if (lockAcquired) {
//                // 成功获取锁集合
//                acquiredLocks.add(lock);
//            } else {
//                // 如果达到了允许加锁失败节点限制，那么break，即此次Redlock加锁失败
//                if (locks.size() - acquiredLocks.size() == failedLocksLimit()) {
//                    break;
//                }
//
//            }
//
//        }
//
//        return true;
//
//    }
    public static void main(String[] args) {
        System.out.println("start");
        Integer a=1;
        Integer b=2;
        if(a==2&&
        b==2){
            System.out.println("error1");
        }

        if(a==2&
                b==2){
            System.out.println("error2");
        }

        if(a==1||
                b==2){
            System.out.println("error3");
        }

        if(a==1|
                b==2){
            System.out.println("error3");
        }
    }

}
