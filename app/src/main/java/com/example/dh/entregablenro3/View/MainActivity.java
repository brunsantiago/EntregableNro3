package com.example.dh.entregablenro3.View;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh.entregablenro3.Model.POJO.ContenedorDePaints;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainFragment.Notificable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        Fragment unFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedorMainFragment,unFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void recibirPaintClickeado(List<Paint> listaDePaints, int posicion) {
        ContenedorDePaints contenedorDePaints = new ContenedorDePaints(listaDePaints,posicion);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CONTENEDOR_DE_PAINTS",contenedorDePaints);
        Intent intent = new Intent(this,DetalleActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show();
        loginActivity();
    }

    private void loginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
