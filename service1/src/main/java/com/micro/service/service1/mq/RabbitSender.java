package com.micro.service.service1.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * mq 发送调用
 *
 * @author Spark
 **/
@Component
public class RabbitSender {
	
	private static  final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

	
	@Resource
	private RabbitTemplate rabbitTemplate;

	private Message getMessage(Object object){
		MessageProperties messageProperties = new MessageProperties();
		return new SimpleMessageConverter().toMessage(object,messageProperties);
	}

	private Message getMessage(Object object,String expire){
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setExpiration(expire);
		return new SimpleMessageConverter().toMessage(object,messageProperties);
	}

	/**
	 * 不建议使用这个，尽量用 object
	 * 默认交换机 direct 模式 一对一
	 * 向指定 队列 发送消息信息
	 *
	 * @param queueName  路由键
	 * @param message  string类型
	 */
	@Deprecated
	public void send(String queueName, String message){

		try {
			rabbitTemplate.send(queueName, getMessage(message));
		} catch (Exception e) {
			logger.error("消息发送失败1",e);
		}
	}
	public void send(String queueName, String message,String expire){

		try {
			rabbitTemplate.send(queueName, getMessage(message,expire));

		} catch (Exception e) {
			logger.error("消息发送失败1",e);
		}
	}

	/**
	 * 向指定 队列 发送消息信息
	 *
	 * 默认交换机 direct 模式 一对一
	 * @param queueName 路由键
	 * @param obj  对象
	 */
	public void send(String queueName, Object obj) {

		try {
			rabbitTemplate.send(queueName, getMessage(obj));
		} catch (Exception e) {
			logger.error("消息发送失败2", e);
		}
	}



	/**
	 * 消息广播 fanout模式 支持一个生产者对应多个消费者
	 *
	 * @param exchange 交换机
	 * @param obj 消息体
	 */
	public void sendFanout(String exchange, Object obj){

		try {
			rabbitTemplate.send(exchange,null, getMessage(obj));
		} catch (Exception e) {
			logger.error("消息发送失败3",e);
		}
	}


	public void sendTopic(String exchange,String routingKey, Object obj){

		try {
			rabbitTemplate.send(exchange,routingKey, getMessage(obj));
		} catch (Exception e) {
			logger.error("消息发送失败4",e);
		}
	}
}
