package com.example.dh.entregablenro3.View;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dh.entregablenro3.Model.DAO.DAOArtistRoom;
import com.example.dh.entregablenro3.Model.POJO.Artist;

import java.util.List;

public class ArtistRepository {

    private DAOArtistRoom mDAOArtistRoom;
    private LiveData<List<Artist>> mAllArtist;

    ArtistRepository(Application application) {
        ArtistRoomDatabase db = ArtistRoomDatabase.getDatabase(application);
        mDAOArtistRoom = db.artistRoomDao();
        //mAllArtist = mDAOArtistRoom.getAllArtist();
    }

    LiveData<List<Artist>> getAllArtist() {
        return mAllArtist;
    }


    public void insert(Artist unArtista) {
        new insertAsyncTask(mDAOArtistRoom).execute(unArtista);
    }

    private static class insertAsyncTask extends AsyncTask<Artist,Void,Void> {

        private DAOArtistRoom mAsyncTaskDao;

        insertAsyncTask(DAOArtistRoom dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Artist... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}