package com.xin.model;

public class Hobby {
    ////新增的字段
    //private Kind kind;
    //private User user;

    private String username;

    private String kindid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getKindid() {
        return kindid;
    }

    public void setKindid(String kindid) {
        this.kindid = kindid == null ? null : kindid.trim();
    }

}