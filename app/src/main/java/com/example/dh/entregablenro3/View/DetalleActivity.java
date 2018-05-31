package com.example.dh.entregablenro3.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.Model.POJO.ContenedorDePaints;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    private static final String ARTIST = "artists" ;
    private ImageView imagePaint;
    private TextView titlePaint;
    private TextView name;
    private TextView nationality;
    private TextView influencedBy;
    private FirebaseDatabase database;
    private Artist artistaAMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

    database = FirebaseDatabase.getInstance();


    imagePaint = findViewById(R.id.imagePaint);
    titlePaint = findViewById(R.id.titlePaint);
    name = findViewById(R.id.name);
    nationality = findViewById(R.id.nationality);
    influencedBy = findViewById(R.id.influencedBy);

    Bundle bundle = getIntent().getExtras();
    ContenedorDePaints contenedorDePaints = (ContenedorDePaints) bundle.getSerializable("CONTENEDOR_DE_PAINTS");
    Paint unPaint = contenedorDePaints.getPaints().get(contenedorDePaints.getPosicion());

    titlePaint.setText(unPaint.getName());

    getArtista(unPaint.getArtistId());

    cargarImagenDescargadaDelStorage(unPaint.getImage());

    }

    private void getArtista(final String artistId){

        DatabaseReference reference = database.getReference().child("dbartist").child("artist");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Artist unArtista = snapshot.getValue(Artist.class);
                    if (unArtista.getArtistId().equals(artistId)){
                        artistaAMostrar = unArtista;
                        break;
                    }
                }
                name.setText(artistaAMostrar.getName());
                nationality.setText(artistaAMostrar.getNationality());
                influencedBy.setText(artistaAMostrar.getInfluenced_by());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(DetalleActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void cargarImagenDescargadaDelStorage(String imagePath) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference();
        reference = reference.child(imagePath);

        GlideApp.with(this).load(reference).into(imagePaint);

    }

}

