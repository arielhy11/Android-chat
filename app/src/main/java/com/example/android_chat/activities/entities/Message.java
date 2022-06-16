package com.example.android_chat.activities.entities;

public class Message {

    private int id;

    private String content;

    private String created;

    // true or false
    private Boolean sent;

    public Message(int id, String content, String created, Boolean sent) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
    }

    public Message(String content) {
        this.id = 0;
        this.content = content;
        this.created = null;
        this.sent = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}
