package com.projects.tailordget.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.projects.tailordget.datas.Command;

import java.util.List;

@Dao
public interface CommandDAO {

    @Query("SELECT * FROM command ORDER BY id DESC")
    List<Command> loadAllCommands();

    @Insert
    void insertCommand(Command command);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCommand(Command command);

    @Delete
    void deleteCommand(Command command);

    @Query("SELECT * FROM command WHERE id = :id")
    Command loadCommandById(int id);
}
