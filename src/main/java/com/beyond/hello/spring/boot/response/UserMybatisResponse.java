package com.beyond.hello.spring.boot.response;

import com.beyond.hello.spring.boot.entity.TbUser;
import com.beyond.hello.spring.boot.entity.TbUserExample;
import com.beyond.hello.spring.boot.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 呼叫mybatis查询语句用
 */
@Repository
public class UserMybatisResponse {
    @Autowired
    private TbUserMapper tbUserMapper;

    public List<TbUser> getUser (List<Integer> userIds){
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.createCriteria().andIdIn(userIds);
        List<TbUser> userList = tbUserMapper.selectByExample(tbUserExample);
        return userList;
    }
}
