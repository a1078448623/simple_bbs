package com.example.demoWeb.dao;

import com.example.demoWeb.domain.ArticleBean;
import com.example.demoWeb.domain.PublishArticleBean;
import com.example.demoWeb.domain.RecommendBean;

import java.util.List;

public interface ArticleDao {

    public abstract List<ArticleBean> findAllArts(int type);
    String findAuthor(int art_id);
    ArticleBean findSingelArt(int art_id);
    String findNickName(int u_id);
    String getType(int type_id);
    boolean addArticle(PublishArticleBean pab);
    List<RecommendBean> getMyArts(int u_id);
}
