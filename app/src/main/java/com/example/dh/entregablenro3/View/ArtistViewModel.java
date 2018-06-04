package com.example.dh.entregablenro3.View;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.dh.entregablenro3.Model.POJO.Artist;

import java.util.List;

public class ArtistViewModel extends AndroidViewModel {

    private ArtistRepository mRepository;

    private LiveData<List<Artist>> mAllWords;

    public ArtistViewModel (Application application) {
        super(application);
        mRepository = new ArtistRepository(application);
        mAllWords = mRepository.getAllArtist();
    }

    LiveData<List<Artist>> getAllArtist() { return mAllWords; }

    public void insert(Artist unArtista) { mRepository.insert(unArtista); }
}