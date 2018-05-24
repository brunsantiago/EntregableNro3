package com.example.dh.entregablenro3.Model.DAO;


import com.example.dh.entregablenro3.Model.POJO.ContenedorDePaints;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DAOPaintRetrofit {

    private String baseURL;
    private Retrofit retrofit;
    private ServicePaint service;

    public DAOPaintRetrofit(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
    }


    public void obtenerPaintsDeInternetAsincronico(final ResultListener<List<Paint>> escuchadorDelControlador){

        ServicePaint servicePaint = retrofit.create(ServicePaint.class);
        Call<ContenedorDePaints> llamada = servicePaint.getPaints();

        llamada.enqueue(new Callback<ContenedorDePaints>() {
            @Override
            public void onResponse(Call<ContenedorDePaints> call, Response<ContenedorDePaints> response) {
                escuchadorDelControlador.finish(response.body().getPaints());
            }

            @Override
            public void onFailure(Call<ContenedorDePaints> call, Throwable t) {
                escuchadorDelControlador.finish(new ArrayList<Paint>());
            }
        });
    }




}
