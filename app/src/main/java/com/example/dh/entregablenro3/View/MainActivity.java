package com.example.dh.entregablenro3.View;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh.entregablenro3.Model.POJO.ContenedorDePaints;
import com.example.dh.entregablenro3.Model.POJO.Paint;
import com.example.dh.entregablenro3.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;


public class MainActivity extends AppCompatActivity implements MainFragment.Notificable {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mToggle;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigationView);

        drawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        NavigationViewListener navigationViewListener = new NavigationViewListener();
        View hView = navigationView.getHeaderView(0);
        TextView correo = hView.findViewById(R.id.correoUser);
        if (mAuth.getCurrentUser() != null) {correo.setText(mAuth.getCurrentUser().getEmail());}
        Menu menu = navigationView.getMenu();
        MenuItem nav_login = menu.findItem(R.id.loginActivity);
        nav_login.setTitle("Logout");
        navigationView.setNavigationItemSelectedListener(navigationViewListener);

        Fragment unFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedorMainFragment,unFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class NavigationViewListener implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            if(item.getItemId() == R.id.pinturasActivity){
                loginActivity();
            }
            else if(item.getItemId() == R.id.loginActivity){
                signOut();
            }
            drawerLayout.closeDrawers();
            return true;
        }

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
        Toast.makeText(this, "Sesion cerrada con Exito", Toast.LENGTH_SHORT).show();
        loginActivity();
    }

    private void loginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
