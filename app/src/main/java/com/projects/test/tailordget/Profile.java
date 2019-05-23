package com.projects.test.tailordget;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Profile {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String sexe;
    @ColumnInfo(name = "date_record")
    private Date dateRecord;

    public Profile(int id, String name, String sexe, Date dateRecord) {
        this.id = id;
        this.name = name;
        this.sexe = sexe;
        this.dateRecord = dateRecord;
    }

    @Ignore
    public Profile(String name, String sexe, Date dateRecord) {
        this.name = name;
        this.sexe = sexe;
        this.dateRecord = dateRecord;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(Date dateRecord) {
        this.dateRecord = dateRecord;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sexe='" + sexe + '\'' +
                ", dateRecord='" + dateRecord + '\'' +
                '}';
    }
}
