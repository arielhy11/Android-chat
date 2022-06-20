package com.example.android_chat.activities.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int fakeId;

    private String id;

    private String password;

    private String name;

    private String profilePic;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /*public User(String id, String password, String name, String profilePic) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profilePic = profilePic;
    }*/

    public int getFakeId() {
        return fakeId;
    }

    public void setFakeId(int fakeId) {
        this.fakeId = fakeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
