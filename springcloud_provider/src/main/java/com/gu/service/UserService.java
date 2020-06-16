package com.gu.service;

import com.gu.mapper.UserMapper;
import com.gu.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Users queryUserById(String id){
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void deleteUserById(String id){
        this.userMapper.deleteByPrimaryKey(id);
    }

    public List<Users> queryListUser(){
        return this.userMapper.selectAll();
    }

}
