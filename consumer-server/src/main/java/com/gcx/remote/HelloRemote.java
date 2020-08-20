package com.gcx.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by summer on 2017/5/11.
 */
//需要应用开启@EnableFeignClients
@FeignClient(name= "producer-server", fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
    //如果熔断失败调用 "producer-server"  "/hello" 方法 否则 HelloRemoteHystrix 实现的hello 方法
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);

}
