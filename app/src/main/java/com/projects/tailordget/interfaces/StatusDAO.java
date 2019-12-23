package com.projects.tailordget.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.projects.tailordget.datas.Status;

import java.util.List;

@Dao
public interface StatusDAO {

    @Query("SELECT * FROM status ORDER BY id DESC")
    List<Status> loadAllStatuss();

    @Insert
    void insertStatus(Status status);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateStatus(Status status);

    @Delete
    void deleteStatus(Status status);

    @Query("SELECT * FROM status WHERE id = :id")
    Status loadStatusById(int id);
}
