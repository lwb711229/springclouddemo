package com.gcx;

//import org.mybatis.spring.annotation.MapperScan;



import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



// 开启缓存
@EnableCaching
@EnableDiscoveryClient

//@FeignClient(name= "producer-server", fallback = HelloRemoteHystrix.class) 内部
@EnableFeignClients

@SpringBootApplication

// @HystrixCommand(fallbackMethod = "helloError") 任何调用
@EnableHystrix

@EnableZuulProxy





//org.mybatis.spring.annotation.MapperScan
//应该是 tk.mybatis.spring.annotation.MapperScan; 通用扫描
@MapperScan(basePackages = "com.gcx.dao")
public class ConsumerServerApplication {

   //@Bean 应用在方法上，用来将方法返回值设为为bean
    @Bean(name = "restTemplate")
  // @LoadBalanced  //@LoadBalanced实现负载均衡
  public RestTemplate restTemplate() {
     System.out.println("==================restTemplate==============");
        return new RestTemplate();
    }





    public static void main(String[] args) {

        SpringApplication.run(ConsumerServerApplication.class, args);
    }
}
