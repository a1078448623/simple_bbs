package com.example.demoWeb.controller;

import com.example.demoWeb.dao.ComplexDao;
import com.example.demoWeb.domain.RecommendBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/serch")
public class Serch {

    @Autowired
    ComplexDao complexDao;
    @RequestMapping("/sechInHome")
    public List<RecommendBean> serchInHome(String content, HttpSession session){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<RecommendBean> serch = complexDao.serch(content, 0);
        System.out.println(content);
        if(serch!=null)
        for(RecommendBean rb:serch){
            rb.setFormate_time(sdf.format(rb.getCre_time()));
        }
        System.out.println(serch);
        return serch;
    }

    @RequestMapping("/serchInCommunity")
    public List<RecommendBean> serchInCommunity(String content,HttpSession session){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        List<RecommendBean> serch = complexDao.serch(content, Integer.parseInt((String) session.getAttribute("current_type_id")));
        System.out.println(content);
        if(serch!=null)
            for(RecommendBean rb:serch){
                rb.setFormate_time(sdf.format(rb.getCre_time()));
            }
        System.out.println(serch);
        return serch;
    }
}
