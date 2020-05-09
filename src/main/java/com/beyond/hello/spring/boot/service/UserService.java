package com.beyond.hello.spring.boot.service;

import com.beyond.hello.spring.boot.entity.TbUser;
import com.beyond.hello.spring.boot.model.UserModel;
import com.beyond.hello.spring.boot.response.UserResponse;
import com.beyond.hello.spring.boot.response.UserResponseImpl;
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
    private UserResponse userResponse;
    @Autowired
    private UserResponseImpl userResponseImpl;

    public List<UserModel> getUser (List<Integer> userIds){
        List<TbUser> userList = userResponseImpl.getUser(userIds);
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

    public List<UserModel> getPageUsers (int start, int count) {
        List<TbUser> pageUsers = userResponseImpl.getPageUsers(start, count);
        List<UserModel> userModels = pageUsers.stream()
                .map(pageUser ->
                {
                    UserModel userModel = new UserModel();
                    BeanUtils.copyProperties(pageUser, userModel);
                    String creatDate = new SimpleDateFormat("yyyy-MM-dd").format(pageUser.getCreatData());
                    String updateDate = new SimpleDateFormat("yyyy-MM-dd").format(pageUser.getUpdetaData());
                    userModel.setCreatData(creatDate);
                    userModel.setUpdetaData(updateDate);
                    return userModel;
                })
                .collect(Collectors.toList());
        return userModels;
    }

    public Page<UserModel> getPages (Pageable pageable){

        Page<TbUser> tbUsers = userResponse.findAll(pageable);

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
