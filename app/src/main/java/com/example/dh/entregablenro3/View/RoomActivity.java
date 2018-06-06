package com.example.dh.entregablenro3.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dh.entregablenro3.Model.DAO.DAOPaintRoomManager;
import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;

import java.util.List;
import java.util.Locale;


public class RoomActivity extends AppCompatActivity {

    private AppRoomDatabase mDb;
    private TextView textViewArtistas,textViewPinturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        textViewArtistas = findViewById(R.id.textViewArtistas);
        textViewPinturas = findViewById(R.id.textViewPaints);

        fetchDataDbAsync();

        mostrarDbAsync();

        //Toast.makeText(this, "Lista Extraida"+traerLista(), Toast.LENGTH_SHORT).show();

    }

    public void fetchDataDbAsync() {
        FetchDataDbAsync task = new FetchDataDbAsync();
        task.execute();
    }

    public void mostrarDbAsync() {
        mostrarPaintsDbAsync task = new mostrarPaintsDbAsync();
        task.execute();
    }

    private class FetchDataDbAsync extends AsyncTask<Void, Void, List<Artist>> {

        @Override
        protected List<Artist> doInBackground(Void... voids) {
            List<Artist> listaDeArtistas = AppRoomDatabase.getDatabase(getApplicationContext()).artistRoomDao().getAllArtist();
            return listaDeArtistas;
        }

        @Override
        protected void onPostExecute(List<Artist> artists) {
            fetchData(artists);
        }

        private void fetchData(List<Artist> artistas) {

            StringBuilder sb = new StringBuilder();

            for (Artist unArtista : artistas) {
                sb.append(String.format(Locale.US,
                        "%s, %s, %s \n", unArtista.getArtistId(), unArtista.getName(), unArtista.getNationality()));
            }
            textViewArtistas.setText(sb);
        }
    }

    private class mostrarPaintsDbAsync extends AsyncTask<Void, Void, List<Paint>> {

        @Override
        protected List<Paint> doInBackground(Void... voids) {
            List<Paint> listaDePaints = AppRoomDatabase.getDatabase(getApplicationContext()).paintRoomDao().getAllPaint();
            return listaDePaints;
        }

        @Override
        protected void onPostExecute(List<Paint> paints) {
            fetchData(paints);
        }

        private void fetchData(List<Paint> paints) {

            StringBuilder sb = new StringBuilder();

            for (Paint unPaint : paints) {
                sb.append(String.format(Locale.US,
                        "%s, %s, %s \n", unPaint.getArtistId(), unPaint.getName(), unPaint.getImage()));
            }
            textViewPinturas.setText(sb);
        }
    }

    private void mainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        mainActivity();
    }

}
