package com.example.demoWeb.controller;


import com.example.demoWeb.dao.ArticleDao;
import com.example.demoWeb.dao.ComplexDao;
import com.example.demoWeb.domain.ArticleBean;
import com.example.demoWeb.domain.ArtitleDetailBean;
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
@RequestMapping("/allarts")

public class AllArticles {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ComplexDao complexDao;

    @ResponseBody
    @RequestMapping("/getArts")
    public List<ArticleBean> getArts(HttpSession session){
        String type= (String) session.getAttribute("current_type_id");
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd");
        List<ArticleBean> allArts = articleDao.findAllArts(Integer.parseInt(type));
        if(allArts==null) return null;
       for(ArticleBean ab:allArts){
           ab.setNickname(articleDao.findNickName(ab.getAuth_id()));
           ab.setContent("");
           ab.setFormate_date(sdf.format(ab.getCre_time()));
       }
        return allArts;
    }
    @ResponseBody
    @RequestMapping("/sendId")
    public Boolean sendId(String art_id, String type_id,String fromWhere_id,HttpSession httpSession){
        if(art_id!=null)httpSession.setAttribute("current_art_id",art_id);
        if(type_id!=null)httpSession.setAttribute("current_type_id",type_id);
        if(fromWhere_id!=null) httpSession.setAttribute("from_where_id",fromWhere_id);
        return true;
    }
    @ResponseBody
    @RequestMapping("/getreadArt")
    public ArtitleDetailBean getreadArt(HttpSession httpSession){
        String currentArtId= (String) httpSession.getAttribute("current_art_id");
        int art_id=Integer.parseInt(currentArtId);

        ArtitleDetailBean singelArt = complexDao.getDetails(art_id);
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
        singelArt.setFormate_date(sdf.format(singelArt.getCre_time()));

        return singelArt;
    }

    @RequestMapping("/getTypeName")
    @ResponseBody
    public String getTypeName(HttpSession session){
        String type_id= (String) session.getAttribute("current_type_id");
        return articleDao.getType(Integer.parseInt(type_id));
    }
    @ResponseBody
    @RequestMapping("/getFromType")
    public String getFromType(HttpSession session){
        return (String) session.getAttribute("from_where_id");
    }

    @ResponseBody
    @RequestMapping("/getMyArts")
    public List<RecommendBean> getMyArts(HttpSession session){
        int u_id = ((UserBean) session.getAttribute("user")).getU_id();
        List<RecommendBean> myArts = articleDao.getMyArts(u_id);
        if(myArts!=null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            for (RecommendBean rb:myArts){
                rb.setFormate_time(sdf.format(rb.getCre_time()));
            }
            return myArts;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/getTId")
    public String getTypeID(HttpSession session){

        return (String) session.getAttribute("current_type_id");
    }
}


