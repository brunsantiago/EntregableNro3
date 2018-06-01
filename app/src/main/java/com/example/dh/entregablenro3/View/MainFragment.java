package com.example.dh.entregablenro3.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dh.entregablenro3.Controller.PaintController;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerViewPaints;
    List<Paint> listaDePaints;
    private Notificable notificable;
    private FirebaseDatabase database;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notificable = (Notificable) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        database = FirebaseDatabase.getInstance();
        listaDePaints = new ArrayList<>();

        getPaints();

        recyclerViewPaints = view.findViewById(R.id.recyclerViewPaints);
        recyclerViewPaints.setHasFixedSize(true);
        recyclerViewPaints.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        return view;
    }

    public void getPaints(){

        PaintController paintController = new PaintController();
        paintController.obtenerPaints(new ResultListener<List<Paint>>() {
            @Override
            public void finish(final List<Paint> resultado) {
                PaintsRVAdapter unAdapterDePaints = new PaintsRVAdapter(getContext(),resultado, new PaintsRVAdapter.NotificableDelClickRecycler() {
                    @Override
                    public void notificarClick(int posicion) {
                        notificable.recibirPaintClickeado(resultado,posicion);
                    }
                });
                recyclerViewPaints.setAdapter(unAdapterDePaints);
            }
        });
    }

    public interface Notificable{
        void recibirPaintClickeado(List<Paint> listaDePaints, int posicion);
    }

}
