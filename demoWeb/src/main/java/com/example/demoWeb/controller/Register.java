package com.example.demoWeb.controller;


import com.example.demoWeb.dao.UserDao;
import com.example.demoWeb.domain.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class Register {

    @Autowired
    UserDao userDao;
    @RequestMapping("usernamever")
    @ResponseBody
    public boolean usernameReapt(String username){

        return userDao.nameReapt(username);
    }

    @RequestMapping("/nicknamever")
    @ResponseBody
    public boolean nicknameReapt(String nickname){
        return userDao.nicknameReapt(nickname);
    }

    @RequestMapping("/submit")
    @ResponseBody
    public boolean submitInfo(UserBean userBean, HttpSession session){
        UserBean user = userDao.addUser(userBean);
        if(user!=null){
            session.setAttribute("user",user);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/changename")
    public String changeName(String new_name,HttpSession session){
        int u_id = ((UserBean) session.getAttribute("user")).getU_id();

        boolean b = userDao.changeNmae(u_id, new_name);
        UserBean user = (UserBean) session.getAttribute("user");
        UserBean newuser = userDao.login(user);
        session.setAttribute("user",newuser);
        return newuser.getNickname();
    }
}
