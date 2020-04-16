package com.freshappbooks.mycontactswithroomexample.data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.freshappbooks.mycontactswithroomexample.model.Contact;

import java.util.List;

public interface DAO  {
    @Insert
    public void addContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

    @Update
    void updateContact(Contact contact);

    @Query("select * from contacts")
    public List<Contact> getAllContacts();

    @Query("select * from contacts where id ==:id ")
    public Contact getContact(int id);
}