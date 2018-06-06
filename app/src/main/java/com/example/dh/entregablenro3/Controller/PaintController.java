package com.example.dh.entregablenro3.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.dh.entregablenro3.Model.DAO.DAOPaint;
import com.example.dh.entregablenro3.Model.DAO.DAOPaintRoomManager;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.View.ResultListener;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class PaintController {

    public PaintController() {
    }

    public void getPaints(final ResultListener<List<Paint>> escuchadorDeLaVista, final Context context) {

        if (isNetDisponible(context)) {
            DAOPaint daoPaint = new DAOPaint();
            daoPaint.obtenerPaints(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                    actualizarBaseDeDatos(context, resultado);
                }
            });
        } else {
            DAOPaintRoomManager daoPaintRoomManager = new DAOPaintRoomManager(context);
            escuchadorDeLaVista.finish(daoPaintRoomManager.extraerDatabase());
        }
    }

        public void obtenerImages(final ResultListener<StorageReference> escuchadorDeLaVista, String imagePath){
            DAOPaint daoPaint = new DAOPaint();
            daoPaint.obtenerImages(new ResultListener<StorageReference>() {
                @Override
                public void finish(StorageReference resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            }, imagePath);
        }

        private void actualizarBaseDeDatos (Context context, List < Paint > listaDePaints){
            DAOPaintRoomManager daoPaintRoomManager = new DAOPaintRoomManager(context, listaDePaints);
            daoPaintRoomManager.cargarDatabase();
        }

        private static boolean isNetDisponible (Context context){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
            return (actNetInfo != null && actNetInfo.isConnected());
        }
    }
