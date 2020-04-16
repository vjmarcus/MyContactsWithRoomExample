package com.freshappbooks.mycontactswithroomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.freshappbooks.mycontactswithroomexample.adapter.ContactAdapter;
import com.freshappbooks.mycontactswithroomexample.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recycler_view);
        List<Contact> listOfContacts = new ArrayList<>();
        listOfContacts.add(new Contact("Valya", "Petrov"));
        listOfContacts.add(new Contact("Petya", "Sidorov"));
        adapter = new ContactAdapter(listOfContacts);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }
}
