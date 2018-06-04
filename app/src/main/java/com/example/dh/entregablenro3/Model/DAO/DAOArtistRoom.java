package com.example.dh.entregablenro3.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dh.entregablenro3.Model.POJO.Artist;

import java.util.List;


@Dao
public interface DAOArtistRoom {

    @Insert
    void insert(Artist unArtista);

    @Query("DELETE FROM artist_table")
    void deleteAll();

    @Query("SELECT * FROM artist_table")
    List<Artist> getAllArtist();

}
