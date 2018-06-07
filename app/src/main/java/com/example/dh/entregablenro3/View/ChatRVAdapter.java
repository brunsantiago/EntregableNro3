package com.example.dh.entregablenro3.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dh.entregablenro3.Model.POJO.Mensaje;
import com.example.dh.entregablenro3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ChatRVAdapter extends RecyclerView.Adapter{

    private List<Mensaje> listaDeMensajes;
    private Context context;
    private FirebaseUser currentUser;

    public ChatRVAdapter(Context context) {
        this.listaDeMensajes = new ArrayList<>();
        this.context = context;
    }

    public void agregarMensaje(Mensaje mensaje){
        listaDeMensajes.add(mensaje);
        notifyItemInserted(listaDeMensajes.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        View viewCelda = LayoutInflater.from(context).inflate(R.layout.card_view_mensajes,parent,false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(viewCelda);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Mensaje unMensaje = listaDeMensajes.get(position);

        ChatViewHolder chatViewHolder = (ChatViewHolder) holder;

        chatViewHolder.cargarViewHolder(unMensaje);

    }

    @Override
    public int getItemCount() {
        return listaDeMensajes.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private TextView hora;
        private TextView mensaje;
        private LinearLayout globoLayout;

        private ChatViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreUser);
            hora = itemView.findViewById(R.id.horaMensaje);
            mensaje = itemView.findViewById(R.id.textoDelMensaje);
            globoLayout = itemView.findViewById(R.id.globoLayout);
        }

        public void cargarViewHolder(Mensaje unMensaje){

            nombre.setText(unMensaje.getNombreUserMensaje());
            hora.setText(unMensaje.getHoraMensaje().toString());
            mensaje.setText(unMensaje.getTextoMensaje());
            if(currentUser.getUid().equals(unMensaje.getUserId())){
                globoLayout.setBackgroundResource(R.drawable.in_message_bg);
            } else {
                globoLayout.setBackgroundResource(R.drawable.out_message_bg);
            }

        }
    }
}
