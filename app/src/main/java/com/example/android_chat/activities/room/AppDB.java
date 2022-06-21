package com.example.android_chat.activities.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.activities.entities.User;

@Database(entities = {User.class, Contact.class}, version = 2)
public abstract class AppDB extends RoomDatabase{

    public abstract UsersDao usersDao();

    public abstract ContactsDao contactsDao();
}