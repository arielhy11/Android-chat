package com.example.android_chat.activities.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_chat.activities.entities.User;

import java.util.List;

public class SampleViewModel extends ViewModel {

        private MutableLiveData<List<User>> users;

        public MutableLiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            }
        return users;
        }
}