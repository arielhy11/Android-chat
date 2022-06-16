package com.example.android_chat.activities.api;

import com.example.android_chat.activities.entities.Contact;
import com.example.android_chat.activities.entities.Message;
import com.example.android_chat.activities.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceAPI {

 // ***users API***
 // get list of all users
 @GET("users")
 Call<List<User>> getUsers();

 // add user to the registered users
 @POST("users/{register}")
 Call<List<User>> addUser(@Path("register") String register, @Body User user);

 // ***contacts API***
 // get list of all contacts in the server
 @GET("contacts")
 Call<List<Contact>> getContacts();

 // get list of all contacts of specific user. "id" is the username
 @GET("contacts/{id}")
 Call<List<Contact>> getUserContacts(@Path("id") String id);

 // get details of specific contact. "user" is username and "id" is the contact name
 @GET("contacts/{user}/{id}")
 Call<Contact> getSpecificContact(@Path("user") String user, @Path("id") String id);

 // create new contact. "user" is username
 @POST("contacts/{user}")
 Call<Contact> postContact(@Path("user") String user, @Body Contact contact);

 // delete contact. "user" is username and "id" is the contact's name
 @DELETE("contacts/{user}/{id}")
 Call<Contact> deleteContact(@Path("user") String user, @Path("id") String id);

 // edit contact. "user" is username and "id" is the contact's name
 @PUT("contacts/{user}/{id}")
 Call<Contact> editContact(@Path("user") String user, @Path("id") String id, @Body Contact contact);

 // get list of messages between user and contact. "user" is username and "id" is the contact's name
 @GET("contacts/{user}/{id}/messages")
 Call<List<Message>> getMessages(@Path("user") String user, @Path("id") String id);

 // send new message between user and contact. "user" is username and "id" is the contact's name
 @POST("contacts/{user}/{id}/messages")
 Call<List<Message>> createMessage(@Path("user") String user, @Path("id") String id,
                                   @Body Message message);

}