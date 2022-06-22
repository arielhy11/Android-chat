package com.example.android_chat.activities.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_chat.activities.activities.ChatActivity;
import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.databinding.UserContainerBinding;

import java.util.List;

public class ContactsAdapters extends RecyclerView.Adapter<com.example.android_chat.activities.adapters.ContactsAdapters.UserViewHolder> {

    private final List<Contact> contacts;
    Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myEdit;

    public ContactsAdapters(Context context, List<Contact> contacts) {
        super();
        this.contacts = contacts;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
    }

    @NonNull
    @Override
    public com.example.android_chat.activities.adapters.ContactsAdapters.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserContainerBinding userContainerBinding = UserContainerBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
        );
        return new com.example.android_chat.activities.adapters.ContactsAdapters.UserViewHolder(userContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.android_chat.activities.adapters.ContactsAdapters.UserViewHolder holder,
                                 int position) {
        holder.setContact(contacts.get(position));
        holder.userContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myEdit.putString("contactName", contacts.get(holder.getAdapterPosition()).getId());
                myEdit.putString("contactServer", contacts.get(holder.getAdapterPosition()).getServer());
                myEdit.apply();
                Intent intent = new Intent(context, ChatActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    //INNER CLASS
    class UserViewHolder extends RecyclerView.ViewHolder {

        UserContainerBinding binding;
        ConstraintLayout userContainer;

        UserViewHolder(UserContainerBinding userContainerBinding) {
            super(userContainerBinding.getRoot());
            binding = userContainerBinding;
            userContainer = binding.userContainer;
        }

        void setContact(Contact contact) {
            binding.textOfName.setText(contact.getId());
            binding.lastMessage.setText(contact.getLast());
        }

    }
}
