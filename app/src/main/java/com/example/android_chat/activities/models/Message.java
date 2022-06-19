package com.example.android_chat.activities.models;

import java.util.Date;

public class Message {
    private String message, time;//TODO change exactly to the same attribiutes in server
    private Date dateObject;
    private boolean isSender;

    public Message(String message, String time,/* Date dateObject,*/ boolean isSender) {
        this.message = message;
        this.time = time;
//        this.dateObject = dateObject;
        this.isSender = isSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDateObject() {
        return dateObject;
    }

    public void setDateObject(Date dateObject) {
        this.dateObject = dateObject;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }
}
