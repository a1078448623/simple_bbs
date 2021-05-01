package com.example.demoWeb.dao;

import com.example.demoWeb.domain.AddCommentBean;
import com.example.demoWeb.domain.ArtitleDetailBean;
import com.example.demoWeb.domain.CommentsBean;
import com.example.demoWeb.domain.RecommendBean;

import java.util.List;

public interface ComplexDao {

    List<RecommendBean> getRecoms();
    List<CommentsBean> getCommts(int art_id);
    ArtitleDetailBean getDetails(int art_id);
    boolean addCommts(AddCommentBean addCommentBean);
    int getNums(int art_id);
    boolean updateCommts(int art_id);
    boolean updateCollects(int art_id,String dowhat);
    List<RecommendBean> serch(String content,int type_id);
//    boolean hasNoCheckReply(int u_id,)
}
