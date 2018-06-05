package com.example.dh.entregablenro3.Model.DAO;




import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.View.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DAOArtist {

    private static final String DBARTIST = "dbartist" ;

    private List<Artist> listaDeArtistas;

    public DAOArtist() {
    this.listaDeArtistas = new ArrayList<>();
    }

    public void obtenerArtista(final ResultListener<List<Artist>> escuchadorDelControlador, final String artistId){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child(DBARTIST).child("artist");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Artist unArtista = snapshot.getValue(Artist.class);
                        listaDeArtistas.add(unArtista);
                    }
                    escuchadorDelControlador.finish(listaDeArtistas);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    //Toast.makeText(DetalleActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
                }
            });
        }

}


