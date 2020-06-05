package com.beyond.hello.spring.boot.controller;

import com.beyond.hello.spring.boot.model.UserModel;
import com.beyond.hello.spring.boot.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UploadController {

    @Autowired
    private UserService userService;

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // 画面没传文件，或者文件内容为空
        if (file.isEmpty()) {
            model.addAttribute("message", "not file");
            model.addAttribute("status", false);
        } else {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("上传的后缀名为：" + suffixName);

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<UserModel> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(UserModel.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<UserModel> inputUsers = csvToBean.parse();

                List<Integer> inputIds = inputUsers.stream().map(UserModel::getId).collect(Collectors.toList());

                List<UserModel> users = userService.getUser(inputIds);

                if(CollectionUtils.isEmpty(users)) {
                    // save users list on model
                    model.addAttribute("message", "upload stop");
                    model.addAttribute("status", false);
                }
                // save users list on model
                model.addAttribute("message", "upload ok");
                model.addAttribute("status", true);


            } catch (Exception ex) {
                model.addAttribute("message", "upload error");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }
}
