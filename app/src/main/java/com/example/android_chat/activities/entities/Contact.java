package com.example.android_chat.activities.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int fakeId;

    private String id;

    private String name;

    private String server;

    private String last;

    private String lastDate;

    //private List<Message> messages;

    private String userName;

    public Contact(String id, String name, String server, String userName) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    /*public List<Message> getMessages() {
        return messages;
    }*/

    /*public void setMessages(List<Message> messages) {
        this.messages = messages;
    }*/

    public int getFakeId() {
        return fakeId;
    }

    public void setFakeId(int fakeId) {
        this.fakeId = fakeId;
    }
}

