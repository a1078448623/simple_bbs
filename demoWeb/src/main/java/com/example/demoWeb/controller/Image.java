package com.example.demoWeb.controller;


import com.example.demoWeb.dao.UserDao;
import com.example.demoWeb.domain.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/upload")
public class Image {

    @Autowired
    UserDao userDao;


    @RequestMapping("/image")
    public String uploadimage(@RequestParam("headimage") MultipartFile multipartFile, HttpSession session) throws IOException {

        Date date = new Date();
        long time = date.getTime();
        if(!multipartFile.isEmpty()){
            String filename = multipartFile.getOriginalFilename();
            String vUrl=String.valueOf(time)+filename;
            multipartFile.transferTo(new File("E:\\JavaCode\\demoWeb\\src\\main\\webapp\\img\\"
            +vUrl));
            int u_id = ((UserBean) session.getAttribute("user")).getU_id();
            boolean b = userDao.storeImage(u_id, vUrl);
            UserBean user = userDao.login((UserBean) session.getAttribute("user"));
            session.setAttribute("user",user);
            //System.out.println(vUrl);

        }

        return "redirect:../index.html";
    }

    @ResponseBody
    @RequestMapping("/getURL")
    public String getURL(HttpSession session){

        return  ((UserBean)session.getAttribute("user")).getHimage();
    }
}
