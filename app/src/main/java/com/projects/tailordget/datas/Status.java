package com.projects.tailordget.datas;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Status {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Status() {
    }

    @Ignore
    public Status(String name) {
        this.name = name;
    }
}
