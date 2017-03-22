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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PaneActivity extends AppCompatActivity {
    private FragmentManager fm;
    private ZapatillasFragment zapatillasFragment;
    private SudaderasFragment sudaderasFragment;
    private PantalonesFragment pantalonesFragment;
    private FragmentTransaction transaction;
    // menu
    ListView listView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    ArrayAdapter<String> adapter;
    ArrayList<String> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pane);
        fm = getSupportFragmentManager();
        pantalonesFragment = (PantalonesFragment) fm.findFragmentById(R.id.pantalones);
        zapatillasFragment = (ZapatillasFragment) fm.findFragmentById(R.id.zapatillas);
        sudaderasFragment = (SudaderasFragment) fm.findFragmentById(R.id.sudaderas);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                Toast.makeText(PaneActivity.this, "Item: " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                Intent intent = new Intent();
                if(arg2==0){
                    intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else if(arg2>0) {
                    // aqui lo que hay que hacer es cambiar los datos que se muestra de x a x (de mujer a niño, etc)
                }


            }
        });
        // get data firebase


        // data

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
