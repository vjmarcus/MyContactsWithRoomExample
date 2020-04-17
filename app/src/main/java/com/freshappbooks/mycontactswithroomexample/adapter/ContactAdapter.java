package com.freshappbooks.mycontactswithroomexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freshappbooks.mycontactswithroomexample.R;
import com.freshappbooks.mycontactswithroomexample.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> listOfContacts;

    public ContactAdapter(List<Contact> listOfContacts) {
        this.listOfContacts = listOfContacts;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = listOfContacts.get(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewSurName.setText(contact.getSurName());
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
