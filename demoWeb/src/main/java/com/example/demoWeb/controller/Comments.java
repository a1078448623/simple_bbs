package com.example.demoWeb.controller;

import com.example.demoWeb.dao.ComplexDao;
import com.example.demoWeb.domain.AddCommentBean;
import com.example.demoWeb.domain.CommentsBean;
import com.example.demoWeb.domain.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("commts")
public class Comments {

    @Autowired
    private ComplexDao complexDao;


    @ResponseBody
    @RequestMapping("getCommts")
    public List<CommentsBean> getCommts(HttpSession session){
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
        String art_id = (String) session.getAttribute("current_art_id");
        List<CommentsBean> commts = complexDao.getCommts(Integer.parseInt(art_id));
        if(commts==null) return null;
        for(CommentsBean cb:commts){
            cb.setFormate_time(sdf.format(cb.getCom_time()));
        }

        return commts;
    }

    @RequestMapping("/addComment")
    @ResponseBody
    public boolean addComment(AddCommentBean addCommentBean,HttpSession session){
        addCommentBean.setTo_art_id((String) session.getAttribute("current_art_id"));
        addCommentBean.setRe_auth_id(String.valueOf(((UserBean) session.getAttribute("user")).getU_id()));
        boolean b = complexDao.addCommts(addCommentBean);
        if(b) {
            boolean b1 = complexDao.updateCommts(Integer.parseInt((String) session.getAttribute("current_art_id")));
        }
        return b;
    }

    @RequestMapping("/getnums")
    @ResponseBody
    public int getnums(HttpSession session){
         return complexDao.getNums(Integer.parseInt((String) session.getAttribute("current_art_id")));
    }
}
