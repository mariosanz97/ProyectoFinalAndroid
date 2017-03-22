package com.example.mario.appAdidas;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.appAdidas.Items.Pantalones;
import com.example.mario.appAdidas.Items.Sudaderas;
import com.example.mario.appAdidas.Items.Zapatillas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textViewuserEmail;
    private Button btnLoguot;
    // menu
    private ListView listView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
                // Toast.makeText(ProfileActivity.this, "Categoria " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                if(arg2==0){
                    // nada porque es donde esta ahora el menu (profile)
                } else if(arg2>0) {
                    Intent intent = new Intent(getApplicationContext(), PaneActivity.class);
                    // Intermediario i = new Intermediario(arg2);
                    startActivity(intent);
                    finish();
                }
            }
        });
        // menu
        // login
        firebaseAuth = firebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewuserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewuserEmail.setText("welcome, " + user.getEmail());
        btnLoguot = (Button) findViewById(R.id.logout);
        btnLoguot.setOnClickListener(this);
        firebaseAuth = firebaseAuth.getInstance();
        // login


        // get data firebase


        // data

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
