package com.projects.tailordget.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.projects.tailordget.datas.Type;

import java.util.List;

@Dao
public interface TypeDAO {

    @Query("SELECT * FROM type ORDER BY id DESC")
    List<Type> loadAllTypes();

    @Insert
    void insertType(Type type);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateType(Type type);

    @Delete
    void deleteType(Type type);

    @Query("SELECT * FROM type WHERE id = :id")
    Type loadTypeById(int id);

    @Query("DELETE FROM type")
    void deleteAllType();
}
