package com.example.dh.entregablenro3.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.Model.POJO.Paint;

import java.util.List;


@Dao
public interface DAOPaintRoom {

    @Insert
    void insert(Paint unPaint);

    @Query("DELETE FROM paint_table")
    void deleteAll();

    @Query("SELECT * FROM paint_table")
    List<Paint> getAllPaint();

}
