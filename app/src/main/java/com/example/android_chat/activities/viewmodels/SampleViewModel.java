package com.example.android_chat.activities.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.activities.entities.User;

import java.util.List;

public class SampleViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;
    private MutableLiveData<List<Contact>> contacts;

    public MutableLiveData<List<User>> getUsers() {
    if (users == null) {
        users = new MutableLiveData<>();
        }
    return users;
    }

    public MutableLiveData<List<Contact>> getContacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<>();
        }
        return contacts;
    }

}