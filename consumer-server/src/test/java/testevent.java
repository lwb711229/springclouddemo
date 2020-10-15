
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class testevent {

	public static void main(String[] args) {
        //获取IOC容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册监听者
        context.register(MyContextListener.class);
        //刷新容器
        context.refresh();
        //事件发布
        context.publishEvent(new MyContextEvent("开始发布消息 ...."));
      //  SpringApplication.run(EventbusDemoApplication.class, args);

    }
}
