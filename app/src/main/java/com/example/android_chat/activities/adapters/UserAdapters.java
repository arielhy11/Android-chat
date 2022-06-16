package com.example.android_chat.activities.adapters;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_chat.activities.models.User;
import com.example.android_chat.databinding.UserContainerBinding;

import java.util.List;

public class UserAdapters extends RecyclerView.Adapter<UserAdapters.UserViewHolder>{

    private final List<User> users;

    public UserAdapters(List<User> users) {
        super();
        this.users = users;
    }


    private Bitmap getUserImage(String image){
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserContainerBinding userContainerBinding = UserContainerBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
        );
        return new UserViewHolder(userContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //INNER CLASS
    class UserViewHolder extends RecyclerView.ViewHolder{

        UserContainerBinding binding;

        UserViewHolder(UserContainerBinding userContainerBinding){
            super(userContainerBinding.getRoot());
            binding = userContainerBinding;
        }

        void setUser(User user){
//            binding.profilePic.setImageBitmap(getUserImage(user.getImage()));
            binding.textOfName.setText(user.getName());
        }

    }


}
