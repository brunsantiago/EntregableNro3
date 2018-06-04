package com.example.dh.entregablenro3.Model.POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "artist_table")
public class Artist {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String artistId;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String nationality;
    @ColumnInfo
    private String Influenced_by;


    public Artist(){}

    public Artist(String artistId, String name, String nationality, String Influenced_by) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.Influenced_by = Influenced_by;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public void setInfluenced_by(String influenced_by) {
        Influenced_by = influenced_by;
    }
}
