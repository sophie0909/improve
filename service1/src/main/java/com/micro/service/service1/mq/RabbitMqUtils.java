package com.micro.service.service1.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitMqUtils {

    /**
     * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return  new RabbitTemplate(connectionFactory);
    }

    @Bean
    TopicExchange topic_excExchange() {
        return new TopicExchange("topic_exc");
    }
    @Bean
    FanoutExchange fanout_tExchange() {
        return new FanoutExchange("fanout_t");
    }

    @Bean
    public Queue test1queue() {
        return new Queue("test1", true);
    }

    @Bean
    public Queue test21queue() {
        return new Queue("test21", true);
    }

    @Bean
    public Queue test22queue() {
        return new Queue("test22", true);
    }
    @Bean
    public Queue test23queue() {
        return new Queue("test23", true);
    }

    @Bean
    public Queue test31queue() {
        return new Queue("test31", true);
    }

    @Bean
    public Queue test32queue() {
        return new Queue("test32", true);
    }

    @Bean
    public Queue test33queue() {
        return new Queue("test33", true);
    }
    @Bean
    Binding fanoutBinding21Exchange() {
        return BindingBuilder.bind(test21queue()).to(fanout_tExchange());
    }
    @Bean
    Binding fanoutBinding22Exchange() {
        return BindingBuilder.bind(test22queue()).to(fanout_tExchange());
    }
    @Bean
    Binding fanoutBinding23Exchange() {
        return BindingBuilder.bind(test23queue()).to(fanout_tExchange());
    }

    @Bean
     Binding binding31queue() {
        return BindingBuilder.bind(test31queue()).to(topic_excExchange()).with("*");
    }

    @Bean
   Binding binding32queue() {
        return BindingBuilder.bind(test32queue()).to(topic_excExchange()).with("test32");
    }

    @Bean
    Binding binding33queue() {
        return BindingBuilder.bind(test33queue()).to(topic_excExchange()).with("test33");
    }

    ///////////////////////////////////////////----死信队列----////////////////////////////////////////////////////////


    private static String dlxExchange="dlxExchange";

    private static String dlxQueue="dlxQueue";

    private static String dlxRoutingKey="dlxRoutingKey";
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchange);
    }

    /**
     * 声明死信队列
     *
     * @return Queue
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(dlxQueue);
    }

    /**
     * 绑定死信队列到死信交换机
     *
     * @return Binding
     */
    @Bean
    public Binding bindingDlxQueue() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with(dlxRoutingKey);
    }
    /**
     * 声明队列
     *
     * @return Queue
     */
    @Bean
    public Queue test4Queue() {
        // 队列绑定我们的死信交换机
        Map<String, Object> arguments = new HashMap<>(2);
        arguments.put("x-dead-letter-exchange", dlxExchange);
        arguments.put("x-dead-letter-routing-key", dlxRoutingKey);
        arguments.put("x-max-length",3);
        return new Queue("test4", true, false, false, arguments);
    }
}
