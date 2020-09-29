package com.gcx.rabbitmq.topic;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;


/**
 * 描述: 接收者
 *
 * @author: yanpenglei
 * @create: 2017/10/23 14:15
 */
@Component
@RabbitListener(queues = "topic.ymq")
public class TopicReceiver3  {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException  {
       
    	System.out.println("接收者 TopicReceiver3," + message);

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive("topic.ymq");
        int num = declareOk.getMessageCount();
        if(num > 100) {
            //当队列数超过100的时候，进行操作
        }
      


        // 手动返回未确认消息,requeue 为true时,重新入队

        //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);

    	
    	// channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); //确定该消息已成功消费
        // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//
      //  log.error("消息已重复处理失败,拒绝再次接收...");
       // channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息

      //  log.error("消息即将再次返回队列处理...");

      //  channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//channel.basicQos(1);和channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);是配套使用，只有在channel.basicQos被使用的时候channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false)才起到作用。
//

    	/*
    	channel.basicQos(1); //保证公平分发 
    	 boolean durable = true;  
    	//声明交换机  
       // channel.exchangeDeclare(Send.EXCHANGE_NAME, "direct", durable); //按照routingKey过滤  
    	 //声明队列  
         String queueName = channel.queueDeclare("topic.ymq", true, true, false, null).getQueue();  
    	 QueueingConsumer consumer = new QueueingConsumer(channel);  
    	 
    	 //队列可以多次绑定，绑定不同的交换机或者路由key  
         //channel.queueBind(queueName, Send.EXCHANGE_NAME, routingKey);  
         
         //将消费者和队列关联  
         channel.basicConsume(queueName, false, consumer); // 设置为false表面手动确认消息消费  
         
       //将消费者和队列关联  
         channel.basicConsume(queueName, false, consumer); // 设置为false表面手动确认消息消费  
         
         while (true) {  
             Delivery delivery = consumer.nextDelivery();  
             String msg = new String(delivery.getBody());  
             String key = delivery.getEnvelope().getRoutingKey();  
   
             System.out.println("  Received '" + key + "':'" + msg + "'");  
             System.out.println(" Handle message");  
             TimeUnit.SECONDS.sleep(3); //mock handle message  
             channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); //确定该消息已成功消费  
         }  
         */
         
    	
    	// channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); //确定该消息已成功消费 
    	// channel.confirmSelect(); //Enables publisher acknowledgements on this channel  
    	
    	
    	//channel.confirmSelect();
    	//channel.basicQos(1);
       // channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
           channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
         //    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);

        // 重新发送消息到队尾
       /* channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
                message.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
                JSON.toJSONBytes(msg));
*/

       // channel.confirmSelect();
      /*  channel.addConfimrListener(new ConfirmListener() {
   
             @Override  
             public void handleNack(long deliveryTag, boolean multiple) throws IOException {  
                 System.out.println("[handleNack] :" + deliveryTag + "," + multiple);  
   
             }  
   
             @Override  
             public void handleAck(long deliveryTag, boolean multiple) throws IOException {  
                 System.out.println("[handleAck] :" + deliveryTag + "," + multiple);  
             }  
         });  
    	*/

       /* Consumer consumer = new DefaultConsumer(channel){
            public  void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body){};

        };*/
    }

	


}
