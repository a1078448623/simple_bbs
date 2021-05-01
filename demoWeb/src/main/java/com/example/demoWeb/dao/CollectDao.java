package com.example.demoWeb.dao;

import com.example.demoWeb.domain.RecommendBean;

import java.util.List;

public interface CollectDao {

    boolean hasCollect(int u_id,int art_id);
    boolean addCollect(int u_id,int art_id);
    boolean removeCollect(int u_id,int art_id);
    List<RecommendBean> getCollectArts(int u_id);
}
