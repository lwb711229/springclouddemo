package com.gcx.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class dead {
    /*
	
	public static void main(){
		//第一步：设置TTL产生死信，有两种方式Per-Message TTL和 Queue TTL，第一种可以针对每一条消息设置一个过期时间使用于大多数场景，
		第二种针对队列设置过期时间、适用于一次性延时任务的场景
		byte[] messageBodyBytes = "Hello, world!".getBytes();
		AMQP.BasicProperties properties = new AMQP.BasicProperties();
		
		properties.setExpiration("60000");//设置消息的过期时间为60秒
		channel.basicPublish("my-exchange", "routing-key", properties, messageBodyBytes);
		//这条消息发送到相应的队列之后，如果60秒内没有被消费，则变为死信
		
	}
	
	public jieshou(){
		
		channel.exchangeDeclare("some.exchange.name", "direct");
		//声明一个队列，当myqueue队列中有死信产生时，会转发到交换器some.exchange.name
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-dead-letter-exchange", "some.exchange.name");
		 
		//如果设置死信会以路由键some-routing-key转发到some.exchange.name，如果没设默认为消息发送到本队列时用的routing key
		//args.put("x-dead-letter-routing-key", "some-routing-key");
		channel.queueDeclare("myqueue", false, false, false, args);

	}
 */
//============================================================================

	/**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     *
     * @return the exchange
     */
    @Bean("deadLetterExchange")
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange("DL_EXCHANGE").durable(true).build();
    }

    /**
     * 声明一个死信队列.
     * x-dead-letter-exchange   对应  死信交换机
     * x-dead-letter-routing-key  对应 死信队列
     *
     * @return the queue
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    声明  死信交换机
      //  args.put(DEAD_LETTER_QUEUE_KEY, "DL_EXCHANGE");
//       x-dead-letter-routing-key    声明 死信路由键
      //  args.put(DEAD_LETTER_ROUTING_KEY, "KEY_R");
        return QueueBuilder.durable("DL_QUEUE").withArguments(args).build();
    }

    /**
     * 定义死信队列转发队列.
     *
     * @return the queue
     */
    @Bean("redirectQueue")
    public Queue redirectQueue() {
        return QueueBuilder.durable("REDIRECT_QUEUE").build();
    }

    /**
     * 死信路由通过 DL_KEY 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding deadLetterBinding() {
        return new Binding("DL_QUEUE", Binding.DestinationType.QUEUE, "DL_EXCHANGE", "DL_KEY", null);

    }

    /**
     * 死信路由通过 KEY_R 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding() {
        return new Binding("REDIRECT_QUEUE", Binding.DestinationType.QUEUE, "DL_EXCHANGE", "KEY_R", null);
    }



}