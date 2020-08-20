package com.gcx.rabbitmq.config;


import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: 配置默认的交换机模式
 *
 * Direct Exchange是RabbitMQ默认的交换机模式，也是最简单的模式，根据key全文匹配去寻找队列。
 *
 * @author yanpenglei
 * @create 2017-10-25 0:09
 **/
@Configuration
public class RabbitDirectConfig {/*
    

    //@Bean("repeatTradeQueue") 配置转发队列（TTL队列中的消息过期后，转发目标队列，即Queue2）
  // @Bean("deadLetterQueue")  TTL消息队列 expiration字段或者x-message-ttl属性来设置时间，两者是
	//https://www.cnblogs.com/haoxinyue/p/6613706.html 
	
	1、exchange_delay_begin：缓冲队列exchange交换器，用于将消息转发至缓存消息队列 queue_delay_begin 。

	2、exchange_delay_done：死信（dead-letter）队列exchange交换器，用于将队列 queue_delay_begin 转发到死信队列。

	3、queue_delay_begin：缓冲消息队列，等待消息过期。

	4、queue_delay_done：死信消息队列，消费者能够真正消费信息。
	//=================================================
	Direct Exchange
            处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “abc”，则只有被标记为“abc”的消息才被转发，不会转发abc.def，也不会转发dog.ghi，只会转发abc。
	Fanout Exchange
	不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。很像子网广播，每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。
	Topic Exchange
           将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“abc.#”能够匹配到“abc.def.ghi”，但是“abc.*” 只会匹配到“abc.def”。
	Headers Exchanges
            不处理路由键。而是根据发送的消息内容中的headers属性进行匹配。在绑定Queue与Exchange时指定一组键值对；当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配；如果完全匹配则消息会路由到该队列，否则不会路由到该队列。headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。而fanout，direct，topic 的路由键都需要要字符串形式的。
            匹配规则x-match有下列两种类型：
       x-match = all ：表示所有的键值对都匹配才能接受到消息
        x-match = any ：表示只要有键值对匹配就能接受到消息
	
	*/
	//@Resource(name="delayMsgTwoTemplate")
    private AmqpTemplate delayMsgTwoTemplate;

    public void delayMsgTwo(String exchange, String routingKey, Object msg) {
        delayMsgTwoTemplate.convertAndSend(exchange, routingKey, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(10000));
                return message;
            }
        });
    }
	
	
    @Bean
    public Queue helloQueue() {
    	
        return new Queue("hello");
    }

    @Bean
    public Queue directQueue() {
        return new Queue("direct");
    }

    //-------------------配置默认的交换机模式，可以不需要配置以下-----------------------------------
    // @Bean("dlxExchange")  死信交换机 
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    //绑定一个key "direct"，当消息匹配到就会放到这个队列中
    //消息队列和交换机绑定
    @Bean
    Binding bindingExchangeDirectQueue(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("direct");
    }
    // 推荐使用 helloQueue（） 方法写法，这种方式在 Direct Exchange 模式 多此一举，没必要这样写
    //---------------------------------------------------------------------------------------------
}
