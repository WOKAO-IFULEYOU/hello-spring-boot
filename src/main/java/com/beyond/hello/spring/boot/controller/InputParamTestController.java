package com.beyond.hello.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InputParamTestController {
    //ユーザidをクリックしてから、次のページに遷移します。
    @RequestMapping("/next/{id}")
    public String handleLink(@PathVariable String id, Model model){
        model.addAttribute("userid", id);
        return "second";
    }
}
