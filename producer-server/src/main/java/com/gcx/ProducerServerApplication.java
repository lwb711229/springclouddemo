package com.gcx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


// 开启缓存
@EnableCaching


@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient


public class ProducerServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProducerServerApplication.class, args);
    }
}
