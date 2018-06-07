package com.example.dh.entregablenro3.View;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dh.entregablenro3.Model.POJO.Mensaje;
import com.example.dh.entregablenro3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ChatFragmentActivo extends Fragment {

    RecyclerView recyclerViewChat;
    FloatingActionButton botonEnviarMensaje;
    EditText textoMensaje;
    ChatRVAdapter chatAdapter;
    String nombreUsuario;

    FirebaseDatabase database;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;

    public ChatFragmentActivo() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_fragment_activo, container, false);

        nombreUsuario = getArguments().getString("NOMBRE");

        recyclerViewChat = view.findViewById(R.id.recyclerViewChat);
        botonEnviarMensaje = view.findViewById(R.id.botonEnviarMensaje);
        textoMensaje = view.findViewById(R.id.textoMensaje);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("chat");

        chatAdapter = new ChatRVAdapter(getContext());
        recyclerViewChat = view.findViewById(R.id.recyclerViewChat);
        recyclerViewChat.setHasFixedSize(true);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerViewChat.setAdapter(chatAdapter);


        botonEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Mensaje(nombreUsuario, obtenerTiempo(),textoMensaje.getText().toString(),currentUser.getUid()));
                textoMensaje.setText("");
            }
        });

        chatAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                setScrollBar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mensaje mensaje = dataSnapshot.getValue(Mensaje.class);
                chatAdapter.agregarMensaje(mensaje);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        hideKeyboard();

        return view;
    }

    private void setScrollBar(){
        recyclerViewChat.scrollToPosition(chatAdapter.getItemCount()-1);
    }

    private void hideKeyboard() {
    // Check if no view has focus
        View view = getActivity().getCurrentFocus();
        if (view != null) {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public String obtenerTiempo(){
        final TimePicker timePicker = new TimePicker(getContext());
        final Integer hora = timePicker.getHour();
        final Integer minuto = timePicker.getMinute();
        if (Integer.toString(minuto).length()==1) {
            String concatenarMinuto = "0" + minuto;
            return hora+":0"+minuto;
        }
        return hora+":"+minuto;
    }

}
