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
@RequestMapping("/recom")
public class RecommendArt {

    @Autowired
    private ComplexDao complexDao;
    @RequestMapping("/getRecArt")
    @ResponseBody
    public List<RecommendBean> getRecArt(){
        SimpleDateFormat sdf=new SimpleDateFormat("MM dd");
        List<RecommendBean> recoms = complexDao.getRecoms();
        for(RecommendBean rb:recoms){
            rb.setFormate_time(sdf.format(rb.getCre_time()));
        }

        return recoms;
    }
    @ResponseBody
    @RequestMapping("/sendId")
    public Boolean sendId(String art_id, HttpSession session){
        if(art_id!=null) session.setAttribute("current_art_id",art_id);
        return true;
    }
}
