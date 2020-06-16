package com.gu.client;


import com.gu.pojo.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "study-provider",fallback = UserClientFallBack.class)
public interface UserClient {

    @GetMapping("user/{id}")
    @ResponseBody
    public Users queryUserById(@PathVariable("id") String id);

}
