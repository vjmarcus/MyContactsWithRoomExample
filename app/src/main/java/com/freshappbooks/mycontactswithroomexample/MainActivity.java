package com.freshappbooks.mycontactswithroomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.os.Bundle;

import com.freshappbooks.mycontactswithroomexample.adapter.ContactAdapter;
import com.freshappbooks.mycontactswithroomexample.data.ContactDatabase;
import com.freshappbooks.mycontactswithroomexample.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private ContactDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(getApplicationContext(), ContactDatabase.class, "contactsDb")
                .allowMainThreadQueries().build();

        database.getContactsDao().addContact(new Contact("Valya", "Petrov"));
        database.getContactsDao().addContact(new Contact("Petya", "Sidorov"));

        fab = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recycler_view);
        List<Contact> listOfContacts = new ArrayList<>();
        listOfContacts.addAll(database.getContactsDao().getAllContacts());
        adapter = new ContactAdapter(listOfContacts);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }
}
