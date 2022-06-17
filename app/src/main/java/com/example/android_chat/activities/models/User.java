package com.example.android_chat.activities.models;

import java.io.Serializable;

public class User implements Serializable {
    public int id = 0;
    public static int lastId = 0;
    private String image;
    private String nickName;
    private String password;
    private String name;

    public String getImage() {
        return image;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
