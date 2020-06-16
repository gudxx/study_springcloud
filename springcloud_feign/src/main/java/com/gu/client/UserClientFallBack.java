package com.gu.client;

import com.gu.pojo.Users;
import org.springframework.stereotype.Component;


@Component
public class UserClientFallBack implements UserClient{
    @Override
    public Users queryUserById(String id) {
        Users users = new Users();
        users.setUsername("错误！！！！！");
        return users;
    }
}
