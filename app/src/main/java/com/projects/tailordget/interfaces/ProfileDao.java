package com.projects.tailordget.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.projects.tailordget.datas.Profile;

import java.util.List;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM profile ORDER BY id DESC")
    List<Profile> loadAllProfiles();

    @Insert
    void insertProfile(Profile profile);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProfile(Profile profile);

    @Delete
    void deleteProfile(Profile profile);

    @Query("SELECT * FROM profile WHERE id = :id")
    Profile loadProfileById(int id);
}
