package com.beyond.hello.spring.boot.controller;

import com.beyond.hello.spring.boot.commons.Pager;
import com.beyond.hello.spring.boot.model.UserModel;
import com.beyond.hello.spring.boot.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class LoadController {

    // 分页栏显示的个数,不能是偶数
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    private UserService userService;

    //ajax
    @PostMapping("/demotest")
    public ResponseEntity<?> demo(){
        System.out.println("demo");
        return ResponseEntity.ok("success");
    }

    //ユーザidをクリックしてから、次のページに遷移します。
    @RequestMapping("/next/{id}")
    public String handleLink(@PathVariable String id, Model model){
        model.addAttribute("userid", id);
        return "second";
    }

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

    @RequestMapping(value = "page-file", method = RequestMethod.GET)
    public ModelAndView getPageUsers (@RequestParam("pageSize") Optional<Integer> pageSize,
                                      @RequestParam("page") Optional<Integer>  page,
                                      Model model){

        /*
         * 方法1：ModelAndView型也可以,返回ModelAndView型
         * 使用 ModelAndView
         * ModelAndView.addAttribute
         */

        ModelAndView modelAndView = new ModelAndView("page-file");

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<UserModel> users = userService.getPages(PageRequest.of(evalPage, evalPageSize));

        if(users.getSize() == 0 || CollectionUtils.isEmpty(users.getContent())) {
            model.addAttribute("status", false);
            model.addAttribute("message", "not ok");
        }

        model.addAttribute("status", true);
        model.addAttribute("message", "ok");

        Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("users", users);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

        return modelAndView;

        /*
        * 方法2：返回页面名
        * 使用Model
        * Model.addAttribute
        */
//        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
//
//        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get()-1;
//
//        Page<UserModel> users = userService.getPages(PageRequest.of(evalPage, evalPageSize));
//
//        if(users.getSize() == 0 || CollectionUtils.isEmpty(users.getContent())) {
//            model.addAttribute("status", false);
//            model.addAttribute("message", "not ok");
//        }
//
//        model.addAttribute("status", true);
//        model.addAttribute("message", "ok");
//
//        Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
//
//        model.addAttribute("users", users);
//        model.addAttribute("selectedPageSize", evalPageSize);
//        model.addAttribute("pageSizes", PAGE_SIZES);
//        model.addAttribute("pager", pager);
//
//        return "page-file";
    }
}
