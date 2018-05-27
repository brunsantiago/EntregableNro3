package com.example.dh.entregablenro3.View;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class PaintsRVAdapter extends RecyclerView.Adapter{

    private List<Paint> listaDePaints;
    private NotificableDelClickRecycler notificable;
    private Context context;

    private FirebaseStorage storage;
    private StorageReference reference;

    public PaintsRVAdapter(Context context, List<Paint> listaDeRecetas, NotificableDelClickRecycler notificable) {
        this.context = context;
        this.listaDePaints = listaDeRecetas;
        this.notificable = notificable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();

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
        private ImageView imageViewPaint;

        public PaintViewHolder(View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            imageViewPaint = itemView.findViewById(R.id.imageViewPaint);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notificable.notificarClick(getAdapterPosition());
                }
            });
        }

        public void cargarPaint(Paint unPaint){
            textViewTitulo.setText(unPaint.getName());

            reference = storage.getReference().child(unPaint.getImage());

            GlideApp.with(context).load(reference).into(imageViewPaint);

            /*
            try {
                final File archivo = File.createTempFile(unPaint.getName(), "png");
                reference.getFile(archivo).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Picasso.get().load(archivo.getAbsoluteFile()).into(imageViewPaint);
                    }
                });
            } catch (Exception e) {}
            */
        }
    }

    private void cargarImagenDescargadaDelStorage(String imagePath, final ImageView imageView) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference();
        reference = reference.child(imagePath);

        try {
            final File archivo = File.createTempFile("imagenTemporal", "jpg");
            reference.getFile(archivo).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Picasso.get().load(archivo.getAbsoluteFile()).into(imageView);
                }
            });
        } catch (Exception e) {}
    }

    public interface  NotificableDelClickRecycler{
        void notificarClick(int posicion);
    }
}

