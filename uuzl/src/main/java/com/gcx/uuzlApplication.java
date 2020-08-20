package com.gcx;


import com.gcx.config.RibbonConfiguration;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.RateLimiterErrorHandler;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;


// 开启缓存
@EnableCaching
//@EnableDiscoveryClient
@EnableEurekaClient		// 让 Zuul 能够访问 Eureka Server 并注册自身
@SpringBootApplication
@EnableZuulProxy
@RibbonClients(value = {
        @RibbonClient(name = "consumer-server", configuration = RibbonConfiguration.class)
})
public class uuzlApplication {

//http://localhost:9000/consumer-server/order/11
    public static void main(String[] args) {

        SpringApplication.run(uuzlApplication.class, args);
    }
 //自定义key
    @Bean
    public RateLimitKeyGenerator ratelimitKeyGenerator(RateLimitProperties properties, RateLimitUtils rateLimitUtils) {
        return new DefaultRateLimitKeyGenerator(properties, rateLimitUtils) {
            @Override
            public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
                //super.key()为默认实现
                //keyPrefix+serviceId+(type1Key+...+typenKey)
                String key= super.key(request, route, policy) ;

                //":" + request.getMethod() 为自定义策略
                key += ":" + request.getMethod();

                //实现对key的重写，限流策略是以key为标识依据
                return key;
            }
        };




    }
    //自定义错误
    @Bean
    public RateLimiterErrorHandler rateLimitErrorHandler() {
        return new DefaultRateLimiterErrorHandler() {
            @Override
            public void handleSaveError(String key, Exception e) {
                // 处理存储key异常
            }

            @Override
            public void handleFetchError(String key, Exception e) {
                // 处理查询key异常
            }

            @Override
            public void handleError(String msg, Exception e) {
                // 处理异常信息
                System.out.println("=============handleError=====处理异常信息==============");
            }
        };
    }

}
