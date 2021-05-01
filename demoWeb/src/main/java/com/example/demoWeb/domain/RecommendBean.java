package com.example.demoWeb.domain;

import java.util.Date;

public class RecommendBean {

    private int art_id;
    private String nickname;
    private String t_name;
    private String title;
    private int likes;
    private int comments;
    private Date cre_time;
    private String formate_time;

    public String getFormate_time() {
        return formate_time;
    }

    public void setFormate_time(String formate_time) {
        this.formate_time = formate_time;
    }

    public int getArt_id() {
        return art_id;
    }

    public void setArt_id(int art_id) {
        this.art_id = art_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Date getCre_time() {
        return cre_time;
    }

    public void setCre_time(Date cre_time) {
        this.cre_time = cre_time;
    }
}
