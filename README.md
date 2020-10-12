Spring中的事件总线机制

AnthonyJing 2020-09-20 23:12:26   27   收藏
分类专栏： Spring EventBus 文章标签： spring 设计模式 事件模块
版权
Spring中的事件总线机制
写在前面
Spring事件机制
EventBus事件总线
引入依赖
定义消息实体
定义事件监听类
定义事件配置EventBusConfig
定义事件处理类EventHandler
定义事件发布
写在前面
前一段时间公司组织培训DDD领域驱动设计相关的课程，虽然一直以来都在用传统的三层架构设计软件，但DDD的兴起作为软件设计新的潮流我们也要跟紧步伐，虽然里面有些概念比较抽象难以理解，但是讲到领域事件相关章节的时候还是有一点理解。
DDD说领域内使用事件总线进行消息通知，同时消息可以单独使用数据进行存储，领域之间用消息队列进行通知,这样可以分清责任，进行业务解耦，由此可以想到之前使用三层架构中service中如果涉及到其他的业务逻辑是需要注入其他的service进行调用，所以加一个事件总线也可以使得责任更加清晰，今天就来看看SpringBoot如何实现一个事件总线的。

Spring事件机制
下图是Spring的事件驱动模型，其主要的几个基类为 事件源EventObject 、监听者EventListener、发布者（Spring）ApplicationEventPublisher
在这里插入图片描述
可以看到消息发布者实现ApplicationEventPublisher接口并使用publishEvent进行消息发布，消息发布后到ApplicationEvent事件中心，那么事件消费者通过实现ApplicationListener接口，会在onApplicationEvent方法中收到事件发布的消息。

现在用代码实现一下三个核心类

//事件中心
public class MyContextEvent extends ApplicationEvent {
    public MyContextEvent(Object source) {
        super(source);
        System.out.println("事件中心收到消息->"+source.toString());
    }
}

//事件监听者
public class MyContextListener implements ApplicationListener<MyContextEvent> {
    @Override
    public void onApplicationEvent(MyContextEvent myContextEvent) {
        System.out.println("监听者收到消息....");
    }
}


public static void main(String[] args) {
        //获取IOC容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册监听者
        context.register(MyContextListener.class);
        //刷新容器
        context.refresh();
        //事件发布
        context.publishEvent(new MyContextEvent("开始发布消息 ...."));
        SpringApplication.run(EventbusDemoApplication.class, args);

    }

实现结果
在这里插入图片描述

EventBus事件总线
引入依赖
<dependency>
            <groupId>org.greenrobot</groupId>
            <artifactId>eventbus</artifactId>
            <version>3.1.1</version>
        </dependency>

定义消息实体
public class Message {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}


定义事件监听类
@Component
public class EventListener {
    @Subscribe
    public void onMessageEvent(Message event) {
        System.out.println(MessageFormat.format("收到消息:code:{0},msg:{1}", event.getCode(),event.getMsg()));
    }
}

定义事件配置EventBusConfig
@Configuration
public class EventBusConfig {

    @Bean
    public EventBus eventBus(){
        return new EventBus();
    }

}

定义事件处理类EventHandler
@Component
public class EventBusHandler {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private EventListener eventListener;

    @PostConstruct
    public void init() {
        eventBus.register(eventListener);
    }

    @PreDestroy
    public void destroy() {
        eventBus.unregister(eventListener);
    }

    public void eventPost(Message message){
        eventBus.post(message);
    }
}


定义事件发布
@RestController
public class EventPublishController {

    @Resource
    private EventBusHandler eventBusHandler;

    @GetMapping("/publish")
    public void publishEvent() {
        System.out.println("开始发布消息。。。");
        eventBusHandler.eventPost(new Message(1,"消息发布"));
    }
}

此时启动Springboot访问http://localhost:9000/publish便会发布消息