package com.freshappbooks.mycontactswithroomexample.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String surName;

    @Ignore
    public Contact(long id, String name, String surName) {
        this.id = id;
        this.name = name;
        this.surName = surName;
    }

    public Contact(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
