package com.example.dh.entregablenro3.Model.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.View.ArtistRoomDatabase;

import java.util.List;

public class DAOArtistRoomManager {

    private static ArtistRoomDatabase mDb;
    private static List<Artist> listaDeArtistas;

    public DAOArtistRoomManager(Context context, List<Artist> listaDeArtistas){
        mDb = ArtistRoomDatabase.getDatabase(context);
        this.listaDeArtistas = listaDeArtistas;
    }

    public void cargarDatabase() {
        CargarDbAsync task = new CargarDbAsync();
        task.execute();
    }

    private static void cargarListaEnDatabase() {
        mDb.artistRoomDao().deleteAll();
        for (Artist unArtista : listaDeArtistas){
            mDb.artistRoomDao().insert(unArtista);
        }
    }

    private static class CargarDbAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(final Void... params) {
            cargarListaEnDatabase();
            return null;
        }
    }


}
