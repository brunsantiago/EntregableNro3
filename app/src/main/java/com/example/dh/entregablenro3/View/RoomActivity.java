package com.example.dh.entregablenro3.View;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.R;

import java.util.List;
import java.util.Locale;


public class RoomActivity extends AppCompatActivity {

    private ArtistRoomDatabase mDb;
    private TextView textViewArtistas;
    private List<Artist> listaDeArtistas;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        textViewArtistas = findViewById(R.id.textViewArtistas);

        context = getApplicationContext();

        ArtistRoomDatabase.destroyInstance();

        mDb = ArtistRoomDatabase.getDatabase(getApplicationContext());

        //createDb();

        fetchDataDbAsync();

    }


    public void createDb() {
        DatabaseInitializer.populateAsync(mDb);
    }

    public void fetchDataDbAsync() {
        FetchDataDbAsync task = new FetchDataDbAsync();
        task.execute();
    }

    private class FetchDataDbAsync extends AsyncTask<Void, Void, List<Artist>> {

        @Override
        protected List<Artist> doInBackground(Void... voids) {
            listaDeArtistas = ArtistRoomDatabase.getDatabase(getApplicationContext()).artistRoomDao().getAllArtist();
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

}
