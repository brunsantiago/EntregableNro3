package com.example.dh.entregablenro3.Controller;

import com.example.dh.entregablenro3.Model.DAO.DAOArtist;
import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.View.ResultListener;


public class ArtistController {

    public ArtistController() {
    }

    public void getArtist(final ResultListener<Artist> escuchadorDeLaVista, final String artistId) {

            DAOArtist daoArtist = new DAOArtist();
            daoArtist.obtenerArtista(new ResultListener<Artist>() {
                @Override
                public void finish(Artist resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            },artistId);
    }

}
