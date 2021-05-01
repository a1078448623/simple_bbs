package com.example.demoWeb.controller;

import com.example.demoWeb.dao.UserDao;
import com.example.demoWeb.domain.ResponBean;
import com.example.demoWeb.domain.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class Login {

    @Autowired
    private UserDao userDao;
    @RequestMapping("/confirm")
    @ResponseBody
    public ResponBean loginConfirm(UserBean user, HttpSession session) {

        ResponBean rb=new ResponBean();
        UserBean loginuser=userDao.login(user);
        if(loginuser==null){
            rb.setSeverMsg("用户名或密码错误");rb.setLoginFlag(false);
        }else {
            session.setAttribute("user",loginuser);
            rb.setLoginFlag(true);
        }
        return rb;
    }

    @ResponseBody
    @RequestMapping("/isLogged")
    public UserBean isLogged(HttpSession session){
        //if(session.getAttribute("user")==null) return null;
        return (UserBean) session.getAttribute("user");
    }

    @ResponseBody
    @RequestMapping("/logout")
    public boolean logOut(HttpSession session){
        session.removeAttribute("user");
        return true;
    }

}
