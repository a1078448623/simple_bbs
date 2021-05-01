package com.example.demoWeb.controller;

import com.example.demoWeb.dao.CollectDao;
import com.example.demoWeb.dao.ComplexDao;
import com.example.demoWeb.domain.RecommendBean;
import com.example.demoWeb.domain.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/collect")
public class Collect {

    @Autowired
    CollectDao collectDao;
    
    @Autowired
    ComplexDao complexDao;
    
    @ResponseBody
    @RequestMapping("/isCollected")
    public boolean isCollected(HttpSession session){

        int u_id = ((UserBean) session.getAttribute("user")).getU_id();
        int art_id=Integer.parseInt((String) session.getAttribute("current_art_id"));
        return collectDao.hasCollect(u_id,art_id);

    }
    
    @ResponseBody
    @RequestMapping("/addCollect")
    public boolean addCollect(HttpSession session){
        int u_id = ((UserBean) session.getAttribute("user")).getU_id();
        int art_id=Integer.parseInt((String) session.getAttribute("current_art_id"));
        boolean a = collectDao.addCollect(u_id, art_id);
        boolean b = complexDao.updateCollects(art_id, "add");
        return a&b;
    }
    @ResponseBody
    @RequestMapping("/removeCollect")
    public boolean removeCollect(HttpSession session){
        int u_id = ((UserBean) session.getAttribute("user")).getU_id();
        int art_id=Integer.parseInt((String) session.getAttribute("current_art_id"));
        boolean a = collectDao.removeCollect(u_id, art_id);
        boolean b = complexDao.updateCollects(art_id, "sub");
        return a&b;
    }

    @ResponseBody
    @RequestMapping("/getCollects")
    public List<RecommendBean> getCollects(HttpSession session){
        int u_id = ((UserBean) session.getAttribute("user")).getU_id();
        List<RecommendBean> collectArts = collectDao.getCollectArts(u_id);
        if(collectArts!=null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            for(RecommendBean rb:collectArts){
                rb.setFormate_time(sdf.format(rb.getCre_time()));
            }
        }
        return collectArts;
    }
}
