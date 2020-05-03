package com.beyond.hello.spring.boot.service;

import com.beyond.hello.spring.boot.entity.TbUser;
import com.beyond.hello.spring.boot.model.UserModel;
import com.beyond.hello.spring.boot.response.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserResponse userResponse;

    public List<UserModel> getUser (List<Integer> userIds){
        List<TbUser> userList = userResponse.getUser(userIds);
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
}
