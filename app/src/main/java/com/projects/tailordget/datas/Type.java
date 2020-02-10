package com.projects.tailordget.datas;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity()
public class Type {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Type() {
    }

    @Ignore
    public Type(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
