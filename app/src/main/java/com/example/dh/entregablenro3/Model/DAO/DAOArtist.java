package com.example.dh.entregablenro3.Model.DAO;




import com.example.dh.entregablenro3.Model.POJO.Artist;
import com.example.dh.entregablenro3.View.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DAOArtist {

    private static final String DBARTIST = "dbartist" ;
    private Artist artistaAMostrar;

    public DAOArtist() {
    this.artistaAMostrar = new Artist();
    }

    public void obtenerArtista(final ResultListener<Artist> escuchadorDelControlador, final String artistId){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child(DBARTIST).child("artist");

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
                    if (artistaAMostrar == null) {return;}
                    escuchadorDelControlador.finish(artistaAMostrar);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    //Toast.makeText(DetalleActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
                }
            });
        }

}


