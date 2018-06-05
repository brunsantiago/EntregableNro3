package com.example.dh.entregablenro3.Controller;

import android.content.Context;

import com.example.dh.entregablenro3.Model.DAO.DAOArtist;
import com.example.dh.entregablenro3.Model.DAO.DAOArtistRoomManager;
import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.View.ResultListener;

import java.util.List;


public class ArtistController {

    public ArtistController() {
    }

    public void getArtist(final ResultListener<List<Artist>> escuchadorDeLaVista, final String artistId, final Context context) {

            DAOArtist daoArtist = new DAOArtist();
            daoArtist.obtenerArtista(new ResultListener<List<Artist>>() {
                @Override
                public void finish(List<Artist> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                    actualizarBaseDeDatos(context,resultado);
                }
            },artistId);
    }

    public void actualizarBaseDeDatos(Context context, List<Artist> listaDeArtistas){

        DAOArtistRoomManager daoArtistRoomManager = new DAOArtistRoomManager(context,listaDeArtistas);
        daoArtistRoomManager.cargarDatabase();

    }


}
