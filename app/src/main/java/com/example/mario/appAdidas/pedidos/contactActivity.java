package com.example.mario.appAdidas.pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.appAdidas.HombresF.HombresActivity;
import com.example.mario.appAdidas.HomeActivity;
import com.example.mario.appAdidas.Localizacion;
import com.example.mario.appAdidas.LoginActivity;
import com.example.mario.appAdidas.MenuItemAdapter;
import com.example.mario.appAdidas.MujeresF.MujeresActivity;
import com.example.mario.appAdidas.NinosF.NinosActivity;
import com.example.mario.appAdidas.PerfilActivity;
import com.example.mario.appAdidas.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class contactActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewuserEmail;
    private Button btnLoguot;
    private Button btnConfig;
    ImageView img;

    // listapedidos
    ListView listapedidos;

    // menu
    ListView listView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    ArrayAdapter<String> adapter;

    ArrayList<String> menuItems;
    ArrayList<String> modelo = new ArrayList<>();
    ArrayList<String> precio = new ArrayList<>();
    ArrayList<String> foto = new ArrayList<>();

    ArrayAdapter adapter2;

    //STORAGE
    public static final String PREFS = "person";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        listapedidos = (ListView) findViewById(R.id.listapedidos);

        //PEDIDOS



        //PEDIDOS


// menu


        listView = (ListView) findViewById(R.id.list_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        btnConfig = (Button) findViewById(R.id.config);

        listView.setDivider(null);
        listView.setDividerHeight(0);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        //adapter = new MenuItemAdapter(this, menuItems);
        //listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(contactActivity.this, "Item: " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();


                if (menuItems.get(arg2).toString().equals("Hombres")) {
                    Intent intent = new Intent(contactActivity.this, HombresActivity.class);
                    startActivity(intent);
                    //finish();

                }  else if (menuItems.get(arg2).toString().equals("Mi Perfil")) {
                    Intent intent = new Intent(contactActivity.this, PerfilActivity.class);
                    startActivity(intent);
                    //finish();
                }else if (menuItems.get(arg2).toString().equals("Mujeres")) {
                    Intent intent = new Intent(contactActivity.this, MujeresActivity.class);
                    startActivity(intent);
                    //finish();
                }  else if (menuItems.get(arg2).toString().equals("Home")) {
                    Intent intent = new Intent(contactActivity.this, HomeActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Niños")) {
                    Intent intent = new Intent(contactActivity.this, NinosActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Encuentranos")) {
                    Intent intent = new Intent(contactActivity.this, Localizacion.class);
                    startActivity(intent);
                    finish();
                } else if (menuItems.get(arg2).toString().equals("Log out")) {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(contactActivity.this, LoginActivity.class));
                }


                //Intent intent = new Intent(MainActivity.this, AboutmeActivity.class);
                //startActivity(intent);
                //finish();
            }
        });


    }


    @Override
    public void onClick(View v) {
        if (v == btnLoguot) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
