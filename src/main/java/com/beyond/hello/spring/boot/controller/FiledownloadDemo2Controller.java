package com.beyond.hello.spring.boot.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/download")
public class FiledownloadDemo2Controller {

    @RequestMapping(value = "demo2", method=RequestMethod.GET)
    public void csvdownload(HttpServletResponse response) {
        response.addHeader("Content-Disposition", "attachment; filename=\"sample.csv\"");
        response.setContentType("text/csv; charset=shift_jis");
        try (ServletOutputStream out = response.getOutputStream()) {
            for (int i = 0; i < 100; i++) {
                // ここから
                String csv = "\"data1\",\"data2\"\n";
                out.write(csv.getBytes("shift_jis"));
                // ここまでをデータ取得しながらデータ出力する
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
