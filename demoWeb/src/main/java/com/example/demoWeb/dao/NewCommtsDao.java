package com.example.demoWeb.dao;

import com.example.demoWeb.domain.CommentsBean;

import java.util.List;

public interface NewCommtsDao {

    List<CommentsBean> getCommts(int u_id);
    List<CommentsBean> replyedCommts(int u_id);
    boolean setRead(int commt_id,int u_id);
}
