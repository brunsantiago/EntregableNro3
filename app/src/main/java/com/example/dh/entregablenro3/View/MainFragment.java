package com.example.dh.entregablenro3.View;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh.entregablenro3.Controller.PaintController;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;
import com.example.dh.entregablenro3.ResultListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerViewPaints;
    List<Paint> listaDePaints;
    private Notificable notificable;


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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference();

        cargarPaints();

        recyclerViewPaints = view.findViewById(R.id.recyclerViewPaints);
        recyclerViewPaints.setHasFixedSize(true);
        recyclerViewPaints.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        return view;
    }

    public void cargarPaints(){

        PaintController paintController = new PaintController();
        paintController.obtenerPaints(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                listaDePaints = resultado;
                PaintsRVAdapter unAdapterDePaints = new PaintsRVAdapter(getContext(),listaDePaints, new PaintsRVAdapter.NotificableDelClickRecycler() {
                    @Override
                    public void notificarClick(int posicion) {
                        notificable.recibirPaintClickeado(listaDePaints,posicion);
                    }
                });
                recyclerViewPaints.setAdapter(unAdapterDePaints);
        }});
    }

    public interface Notificable{
        void recibirPaintClickeado(List<Paint> listaDePaints, int posicion);
    }

}
