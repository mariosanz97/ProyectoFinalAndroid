package com.example.mario.appAdidas.NinosF;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mario.appAdidas.HombresF.HombresActivity;
import com.example.mario.appAdidas.HombresF.PantalonesH_fragment;
import com.example.mario.appAdidas.HombresF.SudaderasH_fragment;
import com.example.mario.appAdidas.HombresF.ZapatillasH_fragment;
import com.example.mario.appAdidas.HomeActivity;
import com.example.mario.appAdidas.Localizacion;
import com.example.mario.appAdidas.LoginActivity;
import com.example.mario.appAdidas.MenuItemAdapter;
import com.example.mario.appAdidas.PerfilActivity;
import com.example.mario.appAdidas.R;
import com.example.mario.appAdidas.pedidos.contactActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class NinosActivity extends AppCompatActivity implements PantalonesH_fragment.OnFragmentInteractionListener, SudaderasH_fragment.OnFragmentInteractionListener, ZapatillasH_fragment.OnFragmentInteractionListener {

    private TextView infoTextView;
    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth firebaseAuth;

    public static final String PREFS = "person";

    //menu
    ListView listView;
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ArrayAdapter<String> adapter;
    ArrayList<String> menuItems;
    ArrayList<String> listaSesiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninos);


        //menu
        listView = (ListView) findViewById(R.id.list_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        listView.setDivider(null);
        listView.setDividerHeight(0);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().

                setDisplayHomeAsUpEnabled(true);

        menuItems = new ArrayList<String>();
        menuItems.add("Home");
        menuItems.add("Mi Perfil");
        menuItems.add("Hombres");
        menuItems.add("Mujeres");
        menuItems.add("Niños");
        menuItems.add("Contactanos");
        menuItems.add("Encuentranos");
        menuItems.add("Log out");


        adapter = new MenuItemAdapter(this, menuItems);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(ProfileActivity.this, "Item: " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();

                if (menuItems.get(arg2).toString().equals("Mi Perfil")) {
                    Intent intent = new Intent(NinosActivity.this, PerfilActivity.class);
                    startActivity(intent);
                    //finish();

                } else if (menuItems.get(arg2).toString().equals("Hombres")) {
                    Intent intent = new Intent(NinosActivity.this, HombresActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Home")) {
                    Intent intent = new Intent(NinosActivity.this, HomeActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Contactanos")) {
                    Intent intent = new Intent(NinosActivity.this, contactActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Encuentranos")) {
                    Intent intent = new Intent(NinosActivity.this, Localizacion.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Log out")) {
                    finish();
                    startActivity(new Intent(NinosActivity.this, LoginActivity.class));
                }

            }
        });

        ///menu


        PantalonesN_fragment fragmento1 = new PantalonesN_fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, fragmento1);

        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.replace(R.id.contenedor, fragmento1);
        transition.commit();


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Pantalones) {

                    PantalonesN_fragment fragmento1 = new PantalonesN_fragment();

                    getSupportFragmentManager().beginTransaction().add(R.id.contenedor, fragmento1);

                    FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
                    transition.replace(R.id.contenedor, fragmento1);
                    transition.commit();


                } else if (item.getItemId() == R.id.Sudaderas) {

                    SudaderasN_fragment fragmento2 = new SudaderasN_fragment();
                    FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();
                    transition1.replace(R.id.contenedor, fragmento2);
                    transition1.commit();

                } else if (item.getItemId() == R.id.Zapatillas) {

                    ZapatillasN_fragment fragmento3 = new ZapatillasN_fragment();
                    FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();
                    transition1.replace(R.id.contenedor, fragmento3);
                    transition1.commit();

                }

                return false;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
