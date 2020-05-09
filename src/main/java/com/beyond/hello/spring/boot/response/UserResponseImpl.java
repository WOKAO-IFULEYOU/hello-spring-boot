package com.beyond.hello.spring.boot.response;

import com.beyond.hello.spring.boot.entity.TbUser;
import com.beyond.hello.spring.boot.entity.TbUserExample;
import com.beyond.hello.spring.boot.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserResponseImpl {
    @Autowired
    private TbUserMapper tbUserMapper;

    public List<TbUser> getUser (List<Integer> userIds){
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.createCriteria().andIdIn(userIds);
        List<TbUser> userList = tbUserMapper.selectByExample(tbUserExample);
        return userList;
    }

    public List<TbUser> getPageUsers (int start, int count){
//        int start = 2;
//        int count = 12;
        List<TbUser> userList = tbUserMapper.selectUsers(start, count);
        return userList;
    }
}
