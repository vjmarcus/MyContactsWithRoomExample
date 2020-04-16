package com.freshappbooks.mycontactswithroomexample.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.freshappbooks.mycontactswithroomexample.model.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase  {
    public abstract DAO getContactsDao();
}
