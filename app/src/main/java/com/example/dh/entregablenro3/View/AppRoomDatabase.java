package com.example.dh.entregablenro3.View;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.dh.entregablenro3.Model.DAO.DAOArtistRoom;
import com.example.dh.entregablenro3.Model.DAO.DAOPaintRoom;
import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.Model.POJO.Paint;

@Database(entities = {Artist.class,Paint.class}, version = 1,exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

        public final static String DATABASE_NAME = "app_database";

        public abstract DAOArtistRoom artistRoomDao();

        public abstract DAOPaintRoom paintRoomDao();

        private static AppRoomDatabase INSTANCE;

        public static AppRoomDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (AppRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppRoomDatabase.class, DATABASE_NAME)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

        public static void destroyInstance() {
        INSTANCE = null;
    }

}
