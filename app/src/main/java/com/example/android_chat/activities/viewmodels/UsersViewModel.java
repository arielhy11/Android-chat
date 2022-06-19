/*
package com.example.android_chat.activities.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_chat.activities.entities.User;

import java.util.List;

public class UsersViewModel extends ViewModel {

    UsersRepository usersRepository;

    private LiveData<List<User>> usersList;

    public UsersViewModel(){
        usersRepository = new UsersRepository();
        usersList = usersRepository.getAll();
    }

    public LiveData<List<User>> getUsersList() {
        return usersList;
    }

   public void add(User user){ usersRepository.add(user); }

    public void delete(User user){ usersRepository.delete(user); }

    public void reload(){ usersRepository.reload(); }
}
*/
