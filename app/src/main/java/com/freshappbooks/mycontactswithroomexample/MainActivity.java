package com.freshappbooks.mycontactswithroomexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.freshappbooks.mycontactswithroomexample.adapter.ContactAdapter;
import com.freshappbooks.mycontactswithroomexample.data.ContactDatabase;
import com.freshappbooks.mycontactswithroomexample.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    ContactAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private ContactDatabase database;
    private List<Contact> listOfContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(getApplicationContext(), ContactDatabase.class, "contactsDb")
                .build();
        loadContacts();


        fab = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recycler_view);

        adapter = new ContactAdapter(listOfContacts);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAndEditContact(false, null, -1);
            }
        });

    }

    private void addAndEditContact(final boolean isUpdate, final Contact contact, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.add_edit_contact, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        TextView textView = view.findViewById(R.id.textView_label);
        final EditText editTextName = view.findViewById(R.id.editText_name);
        final EditText editTextSurName = view.findViewById(R.id.editText_surname);
        textView.setText(!isUpdate ? "Добавить контакт" : "Обновить контакт");
        if (isUpdate && contact!= null) {
            editTextName.setText(contact.getName());
            editTextSurName.setText(contact.getSurName());
        }
        builder.setCancelable(false).setPositiveButton(isUpdate ? "Обновить" : "Создать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(editTextName.getText().toString())){
                    Toast.makeText(MainActivity.this, "Введите Имя", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(editTextSurName.getText().toString())){
                    Toast.makeText(MainActivity.this, "Введите Фамилию", Toast.LENGTH_SHORT).show();
                } else {
                    if (isUpdate && contact != null) {
                        updateContact(editTextName.getText().toString(), editTextSurName.getText().toString(), position);
                    } else {
                        addContact(editTextName.getText().toString(), editTextSurName.getText().toString());
                    }
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void loadContacts() {
        listOfContacts = new ArrayList<>();
        new GetAllContactsAsyncTask().execute();
    }
    private void deleteContact(Contact contact){
        new DeleteContactAsyncTask().execute(contact);
    }
    private void addContact(String name, String surName){
        Contact contact = new Contact(name, surName);
        new AddContactAsyncTask().execute(contact);
    }
    private void updateContact(String name, String surName, int position) {
        Contact contact = listOfContacts.get(position);
        contact.setName(name);
        contact.setSurName(surName);
        new UpdateContactAsyncTask().execute(contact);
        listOfContacts.set(position, contact);
    }

    private class GetAllContactsAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            listOfContacts.addAll(database.getContactsDao().getAllContacts());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.setListOfContacts(listOfContacts);
        }
    }
    private class DeleteContactAsyncTask extends AsyncTask<Contact, Void, Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            database.getContactsDao().deleteContact(contacts[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadContacts();
        }
    }
    private class AddContactAsyncTask extends AsyncTask<Contact, Void, Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            database.getContactsDao().addContact(contacts[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadContacts();
        }
    }
    private class UpdateContactAsyncTask extends AsyncTask<Contact, Void, Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            database.getContactsDao().updateContact(contacts[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadContacts();
        }
    }
}
