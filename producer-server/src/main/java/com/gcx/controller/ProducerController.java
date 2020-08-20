package com.gcx.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerController {

    @Autowired
    private  EurekaClient disenc;

    @GetMapping("/user/{id}")

    public  String user(@PathVariable long id){
        InstanceInfo  instance = disenc.getNextServerFromEureka("PRODUCER-SERVER",false);
        //   return  "server  "+id ;
       return  instance.getHomePageUrl();
    }
/*

    @GetMapping("/user")
    public  String user(){
        InstanceInfo  instance = disenc.getNextServerFromEureka("PRODUCER-SERVER",false);
        return  "server";
       // return  instance.getHomePageUrl();
    }
*/

    @GetMapping("/info")
    public  String info(){
        InstanceInfo  instance = disenc.getNextServerFromEureka("PRODUCER-SERVER",false);
        return  instance.getHomePageUrl();
    }

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first messge";
    }
}
