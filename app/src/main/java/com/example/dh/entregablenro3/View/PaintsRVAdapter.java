package com.example.dh.entregablenro3.View;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;

import java.util.List;

public class PaintsRVAdapter extends RecyclerView.Adapter{

    private List<Paint> listaDePaints;
    private NotificableDelClickRecycler notificable;
    private Context context;

    public PaintsRVAdapter(Context context, List<Paint> listaDeRecetas, NotificableDelClickRecycler notificable) {
        this.context = context;
        this.listaDePaints = listaDeRecetas;
        this.notificable = notificable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_paint,parent,false);
        PaintViewHolder paintViewHolder = new PaintViewHolder(viewDeLaCelda);
        return paintViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Paint unPaint = listaDePaints.get(position);
        PaintViewHolder paintViewHolder = (PaintViewHolder) holder;
        paintViewHolder.cargarPaint(unPaint);
    }

    @Override
    public int getItemCount() {
        return listaDePaints.size();
    }


    private class PaintViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitulo;
        private FrameLayout fondoLayoutRV;

        public PaintViewHolder(View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            fondoLayoutRV = itemView.findViewById(R.id.fondoLayoutRV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notificable.notificarClick(getAdapterPosition());
                }
            });
        }

        public void cargarPaint(Paint unPaint){
            textViewTitulo.setText(unPaint.getName());
            fondoLayoutRV.setBackgroundResource(R.drawable.picasso);
        }
    }

    public interface  NotificableDelClickRecycler{
        void notificarClick(int posicion);
    }
}

