import org.springframework.context.ApplicationListener;

//事件监听者
public class MyContextListener implements ApplicationListener<MyContextEvent> {
    @Override
    public void onApplicationEvent(MyContextEvent myContextEvent) {
        System.out.println("监听者收到消息....");
    }
}
