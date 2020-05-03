package com.beyond.hello.spring.boot.controller;

import com.beyond.hello.spring.boot.model.UserModel;
import com.beyond.hello.spring.boot.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoadController {

    @Autowired
    private UserService userService;

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "not file");
            model.addAttribute("status", false);
        } else {

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

                // save users list on model
                model.addAttribute("users", users);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "file error");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }
}
