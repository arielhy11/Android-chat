package com.example.android_chat.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_chat.activities.activities.ChatActivity;
import com.example.android_chat.activities.models.User;
import com.example.android_chat.databinding.UserContainerBinding;

import java.util.List;

public class UserAdapters extends RecyclerView.Adapter<UserAdapters.UserViewHolder>{

    private final List<User> users;
    Context context;

    public UserAdapters(Context context, List<User> users) {
        super();
        this.users = users;
        this.context = context;
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
        holder.userContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, ChatActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //INNER CLASS
    class UserViewHolder extends RecyclerView.ViewHolder{

        UserContainerBinding binding;
        ConstraintLayout userContainer;

        UserViewHolder(UserContainerBinding userContainerBinding){
            super(userContainerBinding.getRoot());
            binding = userContainerBinding;
            userContainer = binding.userContainer;
        }

        void setUser(User user){
//            binding.profilePic.setImageBitmap(getUserImage(user.getImage()));
            binding.textOfName.setText(user.getName());
        }

    }


}
