package com.freshappbooks.mycontactswithroomexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freshappbooks.mycontactswithroomexample.MainActivity;
import com.freshappbooks.mycontactswithroomexample.R;
import com.freshappbooks.mycontactswithroomexample.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> listOfContacts;
    private MainActivity activity;

    public ContactAdapter(List<Contact> listOfContacts, MainActivity activity) {
        this.listOfContacts = listOfContacts;
        this.activity = activity;
        this.notifyDataSetChanged();
    }

    public void setListOfContacts(List<Contact> listOfContacts) {
        this.listOfContacts = listOfContacts;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Contact contact = listOfContacts.get(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewSurName.setText(contact.getSurName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addAndEditContact(true, contact, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfContacts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName, textViewSurName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_name);
            textViewSurName = itemView.findViewById(R.id.textView_surname);
        }
    }
}
