package com.example.demoWeb.domain;

import java.util.Date;

public class CommentsBean {

    private int id;
    private int to_art_id;
    private String nickname;
    private String content;
    private Date com_time;
    private int floor;
    private int reply_floor;
    private String formate_time;
    private String himage;
    private int reply_checked;
    private int to_commt_id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTo_art_id() {
        return to_art_id;
    }

    public void setTo_art_id(int to_art_id) {
        this.to_art_id = to_art_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCom_time() {
        return com_time;
    }

    public void setCom_time(Date com_time) {
        this.com_time = com_time;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getReply_floor() {
        return reply_floor;
    }

    public void setReply_floor(int reply_floor) {
        this.reply_floor = reply_floor;
    }

    public String getFormate_time() {
        return formate_time;
    }

    public void setFormate_time(String formate_time) {
        this.formate_time = formate_time;
    }

    public String getHimage() {
        return himage;
    }

    public void setHimage(String himage) {
        this.himage = himage;
    }

    public int getReply_checked() {
        return reply_checked;
    }

    public void setReply_checked(int reply_checked) {
        this.reply_checked = reply_checked;
    }

    public int getTo_commt_id() {
        return to_commt_id;
    }

    public void setTo_commt_id(int to_commt_id) {
        this.to_commt_id = to_commt_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
