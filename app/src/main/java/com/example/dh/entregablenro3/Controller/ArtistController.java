package com.example.dh.entregablenro3.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.dh.entregablenro3.Model.DAO.DAOArtist;
import com.example.dh.entregablenro3.Model.DAO.DAOArtistRoomManager;
import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.View.ResultListener;

import java.util.List;


public class ArtistController {

    public ArtistController() {
    }

    public void getArtist(final ResultListener<List<Artist>> escuchadorDeLaVista, final String artistId, final Context context) {

        if (isNetDisponible(context)) {
            DAOArtist daoArtist = new DAOArtist();
            daoArtist.obtenerArtista(new ResultListener<List<Artist>>() {
                @Override
                public void finish(List<Artist> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                    actualizarBaseDeDatos(context, resultado);
                }
            }, artistId);
        } else {
            DAOArtistRoomManager daoArtistRoomManager = new DAOArtistRoomManager(context);
            escuchadorDeLaVista.finish(daoArtistRoomManager.extraerDatabase());
        }
    }

    private void actualizarBaseDeDatos(Context context, List<Artist> listaDeArtistas){
        DAOArtistRoomManager daoArtistRoomManager = new DAOArtistRoomManager(context,listaDeArtistas);
        daoArtistRoomManager.cargarDatabase();
    }

    private static boolean isNetDisponible(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected());
    }

}
