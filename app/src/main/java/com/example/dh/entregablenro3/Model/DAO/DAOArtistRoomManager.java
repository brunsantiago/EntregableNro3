package com.example.dh.entregablenro3.Model.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.View.AppRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DAOArtistRoomManager {

    private static AppRoomDatabase mDb;
    private static List<Artist> listaDeArtistas;

    public DAOArtistRoomManager(Context context){
        mDb = AppRoomDatabase.getDatabase(context);
    }

    public DAOArtistRoomManager(Context context, List<Artist> listaDeArtistas){
        mDb = AppRoomDatabase.getDatabase(context);
        this.listaDeArtistas = listaDeArtistas;
    }

    public void cargarDatabase() {
        CargarDbAsync task = new CargarDbAsync();
        task.execute();
    }

    public List<Artist> extraerDatabase() {
        MostrarArtistDbAsync task = new MostrarArtistDbAsync();
        task.execute();
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
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

    private static class MostrarArtistDbAsync extends AsyncTask<Void, Void, List<Artist>> {
        @Override
        protected List<Artist> doInBackground(Void... voids) {
            return AppRoomDatabase.getDatabase(getApplicationContext()).artistRoomDao().getAllArtist();
        }
    }


}
