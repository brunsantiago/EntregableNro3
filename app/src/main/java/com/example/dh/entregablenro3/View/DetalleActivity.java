package com.example.dh.entregablenro3.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dh.entregablenro3.Controller.ArtistController;
import com.example.dh.entregablenro3.Controller.PaintController;
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

    private ImageView imagePaint;
    private TextView titlePaint;
    private TextView nameArtist;
    private TextView nationalityArtist;
    private TextView influencedByArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

    imagePaint = findViewById(R.id.imagePaint);
    titlePaint = findViewById(R.id.titlePaint);
    nameArtist = findViewById(R.id.nameArtist);
    nationalityArtist = findViewById(R.id.nationalityArtist);
    influencedByArtist = findViewById(R.id.influencedByArtist);

    Bundle bundle = getIntent().getExtras();
    ContenedorDePaints contenedorDePaints = (ContenedorDePaints) bundle.getSerializable("CONTENEDOR_DE_PAINTS");
    Paint unPaint = contenedorDePaints.getPaints().get(contenedorDePaints.getPosicion());

    titlePaint.setText(unPaint.getName());

    getArtist(unPaint.getArtistId());
    cargarImagenDescargadaDelStorage(unPaint.getImage());

    }

    public void getArtist(String artistId){

        ArtistController artistController = new ArtistController();
        artistController.getArtist(new ResultListener<Artist>() {
            @Override
            public void finish(Artist resultado) {
                nameArtist.setText(resultado.getName());
                nationalityArtist.setText(resultado.getNationality());
                influencedByArtist.setText(resultado.getInfluenced_by());
            }
        },artistId);

    }

    private void cargarImagenDescargadaDelStorage(String imagePath) {

        PaintController paintController = new PaintController();
        paintController.obtenerImages(new ResultListener<StorageReference>() {
            @Override
            public void finish(StorageReference resultado) {
                GlideApp.with(DetalleActivity.this).load(resultado).into(imagePaint);
            }
        },imagePath);
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

