package com.example.dh.entregablenro3.View;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dh.entregablenro3.R;

public class ChatFragment extends Fragment {

    private EditText textoNombre;
    private TextView botonContinuar;
    private NotificableDelActivity notificableDelActivity;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificableDelActivity = (NotificableDelActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        textoNombre = view.findViewById(R.id.textoNombre);
        botonContinuar = view.findViewById(R.id.botonContinuar);
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificableDelActivity.notificarNombre(textoNombre.getText().toString());
            }
        });

        return view;
    }

    public interface NotificableDelActivity {
        void notificarNombre(String nombre);
    }

}
