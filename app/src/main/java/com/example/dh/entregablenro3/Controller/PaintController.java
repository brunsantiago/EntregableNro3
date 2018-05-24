package com.example.dh.entregablenro3.Controller;



import com.example.dh.entregablenro3.Model.DAO.DAOPaintRetrofit;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.ResultListener;

import java.util.List;

public class PaintController {

    public void obtenerPaints(final ResultListener<List<Paint>> escuchadorDeLaVista) {

        if(hayInternet()) {

            DAOPaintRetrofit daoPaintRetrofit = new DAOPaintRetrofit();
            daoPaintRetrofit.obtenerPaintsDeInternetAsincronico(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            });
        }

    }


    public Boolean hayInternet(){
        return true;
    }

}
