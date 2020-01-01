package com.projects.tailordget.datas;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private Integer profile;
    private Integer type;
    private float price;
    private String details;
    private String images;
    private Integer status;
    @ColumnInfo(name = "date_record")
    private Date dateRecord;
    @ColumnInfo(name = "date_delivery")
    private Date dateDelivery;
    @ColumnInfo(name = "date_update")
    private Date dateUpdate;

    public Order(int id, String title, Integer profile, Integer type) {
        this.id = id;
        this.title = title;
        this.profile = profile;
        this.type = type;
    }

    @Ignore
    public Order() {
    }

    @Ignore
    public Order(String title, Integer profile, Integer type) {
        this.title = title;
        this.profile = profile;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(Date dateRecord) {
        this.dateRecord = dateRecord;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", profile=" + profile +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
