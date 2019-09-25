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
    private String phone;
    private float neck;
    private float chest;
    private float waist;
    private float hip;
    private float seat;
    private float shirtLength;
    private float halfShoulder;
    private float shoulderWidth;
    private float armLength;
    private float biceps;
    private float wrist;
    private float inseam;
    private float coatSleeveLength;
    private float jacketLength;
    private float thigh;
    private float aboveKnee;
    private float belowKnee;
    private float calf;
    private float ankle;
    private float crotchToKnee;
    private float kneeToCalf;
    private float calfToAnkle;
    private float waistToAnkle;
    private boolean favorite;
    @ColumnInfo(name = "date_record")
    private Date dateRecord;

    @Ignore
    public Profile() {
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(Date dateRecord) {
        this.dateRecord = dateRecord;
    }

    public float getNeck() {
        return neck;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public float getSeat() {
        return seat;
    }

    public void setSeat(float seat) {
        this.seat = seat;
    }

    public float getShirtLength() {
        return shirtLength;
    }

    public void setShirtLength(float shirtLength) {
        this.shirtLength = shirtLength;
    }

    public float getHalfShoulder() {
        return halfShoulder;
    }

    public void setHalfShoulder(float halfShoulder) {
        this.halfShoulder = halfShoulder;
    }

    public float getShoulderWidth() {
        return shoulderWidth;
    }

    public void setShoulderWidth(float shoulderWidth) {
        this.shoulderWidth = shoulderWidth;
    }

    public float getArmLength() {
        return armLength;
    }

    public void setArmLength(float armLength) {
        this.armLength = armLength;
    }

    public float getBiceps() {
        return biceps;
    }

    public void setBiceps(float biceps) {
        this.biceps = biceps;
    }

    public float getWrist() {
        return wrist;
    }

    public void setWrist(float wrist) {
        this.wrist = wrist;
    }

    public float getInseam() {
        return inseam;
    }

    public void setInseam(float inseam) {
        this.inseam = inseam;
    }

    public float getCoatSleeveLength() {
        return coatSleeveLength;
    }

    public void setCoatSleeveLength(float coatSleeveLength) {
        this.coatSleeveLength = coatSleeveLength;
    }

    public float getJacketLength() {
        return jacketLength;
    }

    public void setJacketLength(float jacketLength) {
        this.jacketLength = jacketLength;
    }

    public float getThigh() {
        return thigh;
    }

    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    public float getAboveKnee() {
        return aboveKnee;
    }

    public void setAboveKnee(float aboveKnee) {
        this.aboveKnee = aboveKnee;
    }

    public float getBelowKnee() {
        return belowKnee;
    }

    public void setBelowKnee(float belowKnee) {
        this.belowKnee = belowKnee;
    }

    public float getCalf() {
        return calf;
    }

    public void setCalf(float calf) {
        this.calf = calf;
    }

    public float getAnkle() {
        return ankle;
    }

    public void setAnkle(float ankle) {
        this.ankle = ankle;
    }

    public float getCrotchToKnee() {
        return crotchToKnee;
    }

    public void setCrotchToKnee(float crotchToKnee) {
        this.crotchToKnee = crotchToKnee;
    }

    public float getKneeToCalf() {
        return kneeToCalf;
    }

    public void setKneeToCalf(float kneeToCalf) {
        this.kneeToCalf = kneeToCalf;
    }

    public float getCalfToAnkle() {
        return calfToAnkle;
    }

    public void setCalfToAnkle(float calfToAnkle) {
        this.calfToAnkle = calfToAnkle;
    }

    public float getWaistToAnkle() {
        return waistToAnkle;
    }

    public void setWaistToAnkle(float waistToAnkle) {
        this.waistToAnkle = waistToAnkle;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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
