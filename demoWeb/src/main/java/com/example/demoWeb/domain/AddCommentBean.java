package com.example.demoWeb.domain;

public class AddCommentBean {
    private String to_art_id;
    private String re_auth_id;
    private String content;
    private int floor;
    private int reply_floor;

    public String getTo_art_id() {
        return to_art_id;
    }

    public void setTo_art_id(String to_art_id) {
        this.to_art_id = to_art_id;
    }

    public String getRe_auth_id() {
        return re_auth_id;
    }

    public void setRe_auth_id(String re_auth_id) {
        this.re_auth_id = re_auth_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
