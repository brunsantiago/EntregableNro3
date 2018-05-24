package com.example.dh.entregablenro3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MainFragment extends Fragment {


    PaintsRVAdapter unAdapterDeRecetas;
    List<Paint> listaDePaints;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        cargarRecetas();

        RecyclerView recyclerViewPaints = (RecyclerView) view.findViewById(R.id.recyclerViewPaints);

        recyclerViewPaints.setHasFixedSize(true);

        recyclerViewPaints.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        unAdapterDeRecetas = new PaintsRVAdapter(getContext(),listaDePaints, new PaintsRVAdapter.NotificableDelClickRecycler() {
            @Override
            public void notificarClick(Receta unaReceta) {
                notificable.recibirRecetaClickeada(unaReceta.getRecetaId());
            }


        return view;
    }

}
