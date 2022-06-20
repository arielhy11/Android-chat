package com.example.android_chat.activities.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_chat.activities.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract UserDao userDao();
}
