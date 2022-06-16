package com.example.android_chat.activities.models;

import java.io.Serializable;

public class User implements Serializable {
    private String image;
    private String email;
    private String port;
    private String name;

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getPort() {
        return port;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPort(String port) {
        this.port = port;
    }


}
