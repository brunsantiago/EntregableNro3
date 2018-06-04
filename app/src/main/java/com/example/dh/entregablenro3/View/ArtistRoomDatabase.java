package com.example.dh.entregablenro3.View;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.dh.entregablenro3.Model.DAO.DAOArtistRoom;
import com.example.dh.entregablenro3.Model.POJO.Artist;

@Database(entities = {Artist.class}, version = 1)
public abstract class ArtistRoomDatabase extends RoomDatabase{

        public abstract DAOArtistRoom artistRoomDao();

        private static ArtistRoomDatabase INSTANCE;

        public static ArtistRoomDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (ArtistRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                ArtistRoomDatabase.class, "artist_database")
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
