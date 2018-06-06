package com.example.dh.entregablenro3.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh.entregablenro3.Controller.ArtistController;
import com.example.dh.entregablenro3.Controller.PaintController;
import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.Model.POJO.ContenedorDePaints;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;
import com.google.firebase.storage.StorageReference;

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

    getArtist(getApplicationContext(),unPaint.getArtistId());
    cargarImagenDescargadaDelStorage(unPaint.getImage());

    }

    public void getArtist(Context context, final String artistId){

        ArtistController artistController = new ArtistController();
        artistController.getArtist(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> resultado) {
                for(Artist unArtista : resultado){
                    if(unArtista.getArtistId().equals(artistId)){
                        nameArtist.setText(unArtista.getName());
                        nationalityArtist.setText(unArtista.getNationality());
                        influencedByArtist.setText(unArtista.getInfluenced_by());
                    }
                }
            }
        },artistId,context);
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

