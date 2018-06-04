package com.example.dh.entregablenro3.View;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.dh.entregablenro3.Model.POJO.Artist;

import java.util.List;

class DatabaseInitializer {

    private static List<Artist> listaDeArtistas;

    public static void populateSync(@NonNull final ArtistRoomDatabase db) {
        populateWithTestData(db);
    }

    private static Artist addArtist(final ArtistRoomDatabase db, final String id, final String name, String nationality, String influenced) {
        Artist unArtista = new Artist();
        unArtista.setArtistId(id);
        unArtista.setName(name);
        unArtista.setNationality(nationality);
        unArtista.setInfluenced_by(influenced);
        db.artistRoomDao().insert(unArtista);
        return unArtista;
    }


    private static void populateWithTestData(ArtistRoomDatabase db) {

        db.artistRoomDao().deleteAll();

        addArtist(db, "1", "Santiago", "Argentina", "Picasso");
        addArtist(db, "2", "Miguel", "Mexico", "Martha");
        addArtist(db, "3", "Angel", "Bolivia", "Chopan");
        addArtist(db, "4", "Raul", "Colombia", "Rabmblent");
        addArtist(db, "5", "Agustin", "EEUU", "Mirko");

    }

    public static void populateAsync(final ArtistRoomDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ArtistRoomDatabase mDb;

        PopulateDbAsync(ArtistRoomDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }

    private static List<Artist> fetchData(ArtistRoomDatabase mDb) {

        return mDb.artistRoomDao().getAllArtist();
    }

    public static List<Artist> fetchDataDbAsync(Context context) {

        FetchDataDbAsync task = new FetchDataDbAsync();
        task.execute(context);
        return task.doInBackground();
    }

    private static class FetchDataDbAsync extends AsyncTask<Context, Void, List<Artist>> {

        @Override
        protected List<Artist> doInBackground(Context... contexts) {
            listaDeArtistas = ArtistRoomDatabase.getDatabase(contexts[0]).artistRoomDao().getAllArtist();
            return listaDeArtistas;
        }

    }
}