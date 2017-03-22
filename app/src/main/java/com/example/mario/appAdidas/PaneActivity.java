package com.example.mario.appAdidas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.appAdidas.Fragments.PantalonesFragment;
import com.example.mario.appAdidas.Fragments.ZapatillasFragment;
import com.example.mario.appAdidas.Fragments.SudaderasFragment;
import com.example.mario.appAdidas.Items.Pantalones;
import com.example.mario.appAdidas.Items.Sudaderas;
import com.example.mario.appAdidas.Items.Zapatillas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

public class PaneActivity extends AppCompatActivity {
    private FragmentManager fm;
    private ZapatillasFragment zapatillasFragment;
    private SudaderasFragment sudaderasFragment;
    private PantalonesFragment pantalonesFragment;
    private FragmentTransaction transaction;
    private ListView list_zapas;
    private ListView list_suda;
    private ListView list_pants;
    private ArrayList<Zapatillas> zapatillas;
    private ArrayList<Pantalones> pantalones;
    private ArrayList<Sudaderas> sudaderas;
    // menu
    private ListView listView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pane);
        // fragments
        fm = getSupportFragmentManager();
        pantalonesFragment = (PantalonesFragment) fm.findFragmentById(R.id.pantalones);
        zapatillasFragment = (ZapatillasFragment) fm.findFragmentById(R.id.zapatillas);
        sudaderasFragment = (SudaderasFragment) fm.findFragmentById(R.id.sudaderas);
        // menu de navegacion
        list_zapas = (ListView) zapatillasFragment.getView().findViewById(R.id.list_zapatillas);
        list_pants = (ListView) pantalonesFragment.getView().findViewById(R.id.list_pantalones);
        list_suda = (ListView) sudaderasFragment.getView().findViewById(R.id.list_sudaderas);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // listener del menu de navegacion (cambia las vistas del tipo de ropa)
                switch (item.getItemId()) {
                    case R.id.navigation_pantalones:
                        cambiaFragment(0);
                        return true;
                    case R.id.navigation_sudaderas:
                        cambiaFragment(1);
                        return true;
                    case R.id.navigation_zapatillas:
                        cambiaFragment(2);
                        return true;
                }
                return false;
            }

        });
        cambiaFragment(0);
        // menu
        listView = (ListView) findViewById(R.id.list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menuItems = new ArrayList<String>();
        menuItems.add("Mi Perfil");
        menuItems.add("Hombre");
        menuItems.add("Mujeres");
        menuItems.add("Niños");
        adapter = new MenuItemAdapter(this, menuItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(PaneActivity.this, "Categoría " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                // 0 = profile, 1 = hombre, 2 = mujeres, 3 = niños
                if(arg2==0){
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else if(arg2>0) {
                    // **** hay que setear en el create la creacion de objetos de pantalones,
                    // **** ( es el default nada mas entrar )
                    // // aqui lo que se hace es cambiar los datos que se muestra a - hombre,
                    // // mujer o niño - (dentro de la categoria de la ropa)
                    /*
                    if(arg2==1){

                    } else if(arg2 ==2){
                        for (Zapatillas zap: zapatillas) {
                            // list_zapas add las que sean de = "Mujer"
                        }
                    } else if(arg2==3) {

                    }
                    */
                }
            }
        });
        // get data firebase - setear listas de prendas con sus datos

        // data - ?

    }

    public void cambiaFragment(int fragment) {
        transaction = fm.beginTransaction();
        transaction.hide(zapatillasFragment);
        transaction.hide(pantalonesFragment);
        transaction.hide(sudaderasFragment);
        if (fragment == 0) {
            transaction.show(pantalonesFragment);
        } else if (fragment == 1) {
            transaction.show(sudaderasFragment);
        } else if (fragment == 2) {
            transaction.show(zapatillasFragment);
        }
        transaction.commitNow();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
