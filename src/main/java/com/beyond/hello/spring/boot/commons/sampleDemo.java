package com.beyond.hello.spring.boot.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class sampleDemo {
    /*
     * file
     */
    public void fileDemo (){
        File file = new File("/wang/test/test.csv");  // CSV文件路径
        // 获取文件名
        String fileName = file.getName();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            String everyLine = "";
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
