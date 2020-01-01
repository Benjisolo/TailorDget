package com.projects.tailordget.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.projects.tailordget.datas.Order;

import java.util.List;

@Dao
public interface OrderDAO {

    @Query("SELECT * FROM `order` ORDER BY id DESC")
    List<Order> loadAllOrders();

    @Insert
    void insertOrder(Order order);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Query("SELECT * FROM `order` WHERE id = :id")
    Order loadOrderById(int id);
}
