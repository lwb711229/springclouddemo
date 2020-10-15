import org.springframework.context.ApplicationEvent;

//事件中心
public class MyContextEvent extends ApplicationEvent {
    public MyContextEvent(Object source) {
        super(source);
        System.out.println("事件中心收到消息->"+source.toString());
    }
}