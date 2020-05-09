package com.beyond.hello.spring.boot.controller;

import com.beyond.hello.spring.boot.commons.Pager;
import com.beyond.hello.spring.boot.model.UserModel;
import com.beyond.hello.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PagingController {

    // 分页栏显示的个数,不能是偶数
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    // 每页希望显示的件数
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    private UserService userService;

    /*
     * 分页
     * @parama pageSize 每页显示件数
     * @parama page 要显示第几页的页数
     *
     */
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
