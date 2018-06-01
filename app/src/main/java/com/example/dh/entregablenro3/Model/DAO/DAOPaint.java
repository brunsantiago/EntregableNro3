package com.example.dh.entregablenro3.Model.DAO;

import android.widget.Toast;

import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.View.PaintsRVAdapter;
import com.example.dh.entregablenro3.View.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class DAOPaint {

    private List<Paint> listaDePaints;

    public DAOPaint(){
        this.listaDePaints = new ArrayList<>();
    }

    public void obtenerPaints(final ResultListener<List<Paint>> escuchadorDelControlador){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("dbpaints").child("paints");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Paint unPaint = snapshot.getValue(Paint.class);
                    listaDePaints.add(unPaint);
                }
                escuchadorDelControlador.finish(listaDePaints);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Toast.makeText(getContext(), "Fallo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void obtenerImages(final ResultListener<StorageReference> escuchadorDelControlador, String imagePath){

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference();
        reference = reference.child(imagePath);
        escuchadorDelControlador.finish(reference);

    }

}
