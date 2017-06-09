package com.example.mario.appAdidas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.VideoView;

import com.example.mario.appAdidas.HombresF.HombresActivity;
import com.example.mario.appAdidas.MujeresF.MujeresActivity;
import com.example.mario.appAdidas.NinosF.NinosActivity;
import com.example.mario.appAdidas.pedidos.contactActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    // menu
    ListView listView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;

    ArrayList<String> menuItems;
    ArrayList<String> menuItems2;

    GridView gridView;

    int logos[] = {R.drawable.model4, R.drawable.model3, R.drawable.model4, R.drawable.model3, R.drawable.model4, R.drawable.model3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Home");



        final VideoView videoview = (VideoView) findViewById(R.id.videoview);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback;
        videoview.setVideoURI(Uri.parse(path));
        videoview.start();


        // menu
        listView = (ListView) findViewById(R.id.list_view2);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout2);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                drawerLayout.closeDrawers();
                if (menuItems.get(arg2).toString().equals("Hombres")) {
                    Intent intent = new Intent(HomeActivity.this, HombresActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Mi Perfil")) {
                    Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Mujeres")) {
                    Intent intent = new Intent(HomeActivity.this, MujeresActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Niños")) {
                    Intent intent = new Intent(HomeActivity.this, NinosActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Contactanos")) {
                    Intent intent = new Intent(HomeActivity.this, contactActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Encuentranos")) {
                    Intent intent = new Intent(HomeActivity.this, Localizacion.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Log out")) {
                    finish();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }


                //Intent intent = new Intent(MainActivity.this, AboutmeActivity.class);
                //startActivity(intent);
                //finish();
            }
        });

        // /menu



    }



    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
