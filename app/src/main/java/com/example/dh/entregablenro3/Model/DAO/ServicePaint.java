package com.example.dh.entregablenro3.Model.DAO;

import com.example.dh.entregablenro3.Model.POJO.ContenedorDePaints;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicePaint {

    @GET("/bins/x858r")
    Call<ContenedorDePaints> getPaints();


}
