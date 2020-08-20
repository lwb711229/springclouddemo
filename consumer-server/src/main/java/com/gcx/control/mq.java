package com.gcx.control;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.gcx.model.Gcxuser;
import com.gcx.rabbitmq.config.Mqutil;
import com.gcx.util.MyResult;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class mq {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private Mqutil mqutil;

    @RequestMapping("/sendMessageTest")
    public void sendMessageTest() {
        String context = "此消息在，配置转发消息模式队列下， 有 TopicReceiver1 TopicReceiver2 TopicReceiver3 可以收到";

        String routeKey = "topic.message";

        String exchange = "topicExchange";

        context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

        System.out.println("sendMessageTest : " + context);

       // this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        mqutil.sendMessage(exchange, routeKey, context);
    }
    @RequestMapping("/sendMessagesTest")
    public void sendMessagesTest() {


        String context = "此消息在，配置转发消息模式队列下，有  TopicReceiver2 TopicReceiver3 可以收到";

        String routeKey = "topic.message.s";

        String exchange = "topicExchange";

        context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

        System.out.println("sendMessagesTest : " + context);

        this.rabbitTemplate.convertAndSend(exchange, routeKey, context);

    }
    @RequestMapping("/sendYmqTest")
    public MyResult<Object> sendYmqTest() {

        String context = "此消息在，配置转发消息模式队列下，有 TopicReceiver3 可以收到";

        String routeKey = "topic.ymq";

        String exchange = "topicExchange";

        context = "context:" + exchange + ",routeKey:" + routeKey + ",context:" + context;

        System.out.println("sendYmqTest : " + context);

       // this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
        mqutil.sendMessage(exchange, routeKey, context);

        return MyResult.ok(context);

    }



    @RequestMapping("/esTest")
    public void esTest() {

        {

            JSONObject student1 = new JSONObject();
            student1.put("name","杨旭你好");
            student1.put("address","山东烟台");
            student1.put("age",22);
            student1.put("date","1995-04-12");
            JSONObject student2 = new JSONObject();
            student2.put("name","张小花1");
            student2.put("address","山东烟台你好");
            student2.put("age",24);
            student2.put("date","1996-07-24");
            //1、使用JSONObject
            // JSONObject json = JSONObject.fromObject(stu);
            //2、使用JSONArray
            //   JSONArray array=JSONArray.fromObject(stu);






            // System.out.println("sendMessageTest : " + context);


        }
    }


    }
