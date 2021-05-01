package com.example.demoWeb.controller;


import com.example.demoWeb.dao.NewCommtsDao;
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
@RequestMapping("/message")
@ResponseBody
public class Message {

    @Autowired
    NewCommtsDao newCommtsDao;
    @RequestMapping("/getNew")
    public List<CommentsBean> getNewCommts(HttpSession session){

        if(session.getAttribute("user")==null) return null;

        int u_id = ((UserBean) session.getAttribute("user")).getU_id();
        List<CommentsBean> commts = newCommtsDao.getCommts(u_id);
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd hh:mm");
        for(CommentsBean cb:commts){
            cb.setFormate_time(sdf.format(cb.getCom_time()));
        }

        return commts;
    }
    @RequestMapping("/getReplyed")
    public List<CommentsBean> getReplyedCommts(HttpSession session){

        if(session.getAttribute("user")==null) return null;

        int u_id = ((UserBean) session.getAttribute("user")).getU_id();
        List<CommentsBean> commts = newCommtsDao.replyedCommts(u_id);
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd hh:mm");
        for(CommentsBean cb:commts){
            cb.setFormate_time(sdf.format(cb.getCom_time()));
        }

        return commts;
    }

    @RequestMapping("/setRead")
    public boolean setRead(String commt_id,HttpSession session){
        if("0".equals(commt_id)){
            return newCommtsDao.setRead(0,((UserBean)session.getAttribute("user")).getU_id());
        }
        else return newCommtsDao.setRead(Integer.parseInt(commt_id),0);

    }
}
