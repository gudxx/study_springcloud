package com.gu.controller;


import com.gu.pojo.Users;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("consumer/user")
@DefaultProperties(defaultFallback="fallMessage")       //全局的熔断方法   不能有参数，返回值类型要和熔断方法类型一致
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    /*@Autowired
    private DiscoveryClient discoveryClient;*/

    @GetMapping
    @ResponseBody
    @HystrixCommand     //可以设置参数fallbackMethod       局部的熔断方法，参数和返回值和熔断方法都要一直 。
    public String queryUserById(@RequestParam("id") String id){

        /*List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD_PROVIDER");
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();
        int port = instance.getPort();*/
        return this.restTemplate.getForObject("http://study-provider/user/"+id,String.class);
    }

    public String fallMessage(){
        return "连接失败！";
    }

}
