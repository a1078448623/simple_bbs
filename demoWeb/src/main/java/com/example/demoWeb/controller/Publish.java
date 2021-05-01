package com.example.demoWeb.controller;


import com.example.demoWeb.dao.ArticleDao;
import com.example.demoWeb.domain.PublishArticleBean;
import com.example.demoWeb.domain.PublishTypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("publish")
public class Publish {

    @Autowired
    ArticleDao articleDao;

    @ResponseBody
    @RequestMapping("/homepublish")
    public boolean homePublish(PublishArticleBean publishArticleBean){

        return articleDao.addArticle(publishArticleBean);
    }

    @ResponseBody
    @RequestMapping("/setType")
    public boolean setType(String type, HttpSession session){

        session.setAttribute("current_publish_type",type);
        return true;
    }

    @ResponseBody
    @RequestMapping("/getType")
    public PublishTypeBean getType(HttpSession session){

        PublishTypeBean ptb=new PublishTypeBean();
        ptb.setReturntype((String) session.getAttribute("current_publish_type"));
        if(session.getAttribute("current_type_id")!=null) {
            ptb.setType_name(articleDao.getType(Integer.parseInt(String.valueOf(session.getAttribute("current_type_id")))));
            ptb.setType_id((String) session.getAttribute("current_type_id"));
        }
        return ptb;
    }
}
