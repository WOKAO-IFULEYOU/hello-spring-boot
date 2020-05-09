package com.beyond.hello.spring.boot.service;

import com.beyond.hello.spring.boot.entity.TbUser;
import com.beyond.hello.spring.boot.model.UserModel;
import com.beyond.hello.spring.boot.response.UserJpaResponse;
import com.beyond.hello.spring.boot.response.UserMybatisResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserJpaResponse userJpaResponse;
    @Autowired
    private UserMybatisResponse userMybatisResponse;

    /*
     * mybatis查询接口用
     */
    public List<UserModel> getUser (List<Integer> userIds){
        List<TbUser> userList = userMybatisResponse.getUser(userIds);
        List<UserModel> userModels = userList.stream()
                .map(user ->
                    {
                        UserModel userModel = new UserModel();
                        BeanUtils.copyProperties(user,userModel);
                        String creatDate = new SimpleDateFormat("yyyy-MM-dd").format(user.getCreatData());
                        String updateDate = new SimpleDateFormat("yyyy-MM-dd").format(user.getUpdetaData());
                        userModel.setCreatData(creatDate);
                        userModel.setUpdetaData(updateDate);
                        return userModel;
                    })
                .collect(Collectors.toList());
        return userModels;
    }

    /*
     * jpa查询接口用(分页)
     */
    public Page<UserModel> getPages (Pageable pageable){

        // 数据查询
        Page<TbUser> tbUsers = userJpaResponse.findAll(pageable);

        // 返回类型转换
        Page<UserModel> users = tbUsers.map(new Function<TbUser, UserModel>() {
            @Override
            public UserModel apply(TbUser tbUser) {
                UserModel userModel = new UserModel();
                BeanUtils.copyProperties(tbUser, userModel);
                String creatDate = new SimpleDateFormat("yyyy-MM-dd").format(tbUser.getCreatData());
                String updateDate = new SimpleDateFormat("yyyy-MM-dd").format(tbUser.getUpdetaData());
                userModel.setCreatData(creatDate);
                userModel.setUpdetaData(updateDate);
                return userModel;
            }
        });
        return users;
    }
}
