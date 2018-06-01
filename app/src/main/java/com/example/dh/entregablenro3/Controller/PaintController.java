package com.example.dh.entregablenro3.Controller;

import com.example.dh.entregablenro3.Model.DAO.DAOPaint;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.View.ResultListener;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class PaintController {

    public PaintController() {
    }

    public void obtenerPaints(final ResultListener<List<Paint>> escuchadorDeLaVista){

        DAOPaint daoPaint = new DAOPaint();
        daoPaint.obtenerPaints(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

    public void obtenerImages(final ResultListener<StorageReference> escuchadorDeLaVista,String imagePath){
        DAOPaint daoPaint = new DAOPaint();
        daoPaint.obtenerImages(new ResultListener<StorageReference>() {
            @Override
            public void finish(StorageReference resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        },imagePath);
    }
}
