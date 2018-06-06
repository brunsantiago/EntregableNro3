package com.example.dh.entregablenro3.Model.DAO;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.View.AppRoomDatabase;
import com.example.dh.entregablenro3.View.ResultListener;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DAOPaintRoomManager {

    private static AppRoomDatabase mDb;
    private static List<Paint> listaDePaints;

    public DAOPaintRoomManager(Context context){
        mDb = AppRoomDatabase.getDatabase(context);
    }

    public DAOPaintRoomManager(Context context, List<Paint> listaDePaints){
        mDb = AppRoomDatabase.getDatabase(context);
        this.listaDePaints = listaDePaints;
    }

    public void cargarDatabase() {
        CargarDbAsync task = new CargarDbAsync();
        task.execute();
    }

    public List<Paint> extraerDatabase() {
        MostrarPaintsDbAsync task = new MostrarPaintsDbAsync();
        task.execute();
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void cargarListaEnDatabase() {
        mDb.paintRoomDao().deleteAll();
        for (Paint unPaint : listaDePaints){
            mDb.paintRoomDao().insert(unPaint);
        }
    }

    private static class CargarDbAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(final Void... params) {
            cargarListaEnDatabase();
            return null;
        }
    }

    private static class MostrarPaintsDbAsync extends AsyncTask<Void, Void, List<Paint>> {
        @Override
        protected List<Paint> doInBackground(Void... voids) {
            return AppRoomDatabase.getDatabase(getApplicationContext()).paintRoomDao().getAllPaint();
        }
    }

}
