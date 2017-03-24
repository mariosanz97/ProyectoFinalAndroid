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
import android.widget.ListView;
import android.widget.Toast;
import com.example.mario.appAdidas.Fragments.PantalonesFragment;
import com.example.mario.appAdidas.Fragments.ZapatillasFragment;
import com.example.mario.appAdidas.Fragments.SudaderasFragment;
import com.example.mario.appAdidas.Items.Pantalones;
import com.example.mario.appAdidas.Items.Sudaderas;
import com.example.mario.appAdidas.Items.Zapatillas;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PaneActivity extends AppCompatActivity {
    private FragmentManager fm;
    private ZapatillasFragment zapatillasFragment;
    private SudaderasFragment sudaderasFragment;
    private PantalonesFragment pantalonesFragment;
    private FragmentTransaction transaction;
    private ListView list_zapas;
    private ListView list_suda;
    private ListView list_pants;
    private DataClothes ropa;

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
        ropa = new DataClothes();
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
                // Toast.makeText(PaneActivity.this, "Categoría " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                // 0 = profile, 1 = hombre, 2 = mujeres, 3 = niños
                if(arg2==0){
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else if(arg2>0) {
                    // aqui lo que se hace es cambiar los datos que se muestran a - hombre,
                    // mujer, niño - (dentro de la categoria de la ropa)
                    ArrayList<Zapatillas> zapatillas_type = new ArrayList<Zapatillas>();
                    ArrayList<Pantalones> pantalones_type = new ArrayList<Pantalones>();
                    ArrayList<Sudaderas> sudaderas_type = new ArrayList<Sudaderas>();
                    if(arg2==1){
                        // set ropa hombre
                        for (Zapatillas zap: ropa.zapatillas) {
                            if (zap.getPara().equals("Hombre")) {
                                zapatillas_type.add(zap);
                            }
                        }
                        for (Sudaderas sud: ropa.sudaderas) {
                            if (sud.getPara().equals("Hombre")) {
                                sudaderas_type.add(sud);
                            }
                        }
                        for (Pantalones pant: ropa.pantalones) {
                            if (pant.getPara().equals("Hombre")) {
                                pantalones_type.add(pant);
                            }
                        }
                    } else if(arg2 ==2){
                        // set ropa mujer
                        for (Zapatillas zap: ropa.zapatillas) {
                            if (zap.getPara().equals("Mujer")) {
                                zapatillas_type.add(zap);
                            }
                        }
                        for (Sudaderas sud: ropa.sudaderas) {
                            if (sud.getPara().equals("Mujer")) {
                                sudaderas_type.add(sud);
                            }
                        }
                        for (Pantalones pant: ropa.pantalones) {
                            if (pant.getPara().equals("Mujer")) {
                                pantalones_type.add(pant);
                            }
                        }
                    } else if(arg2==3) {
                        // set ropa niño
                        for (Zapatillas zap: ropa.zapatillas) {
                            if (zap.getPara().equals("Niños")) {
                                zapatillas_type.add(zap);
                            }
                        }
                        for (Sudaderas sud: ropa.sudaderas) {
                            if (sud.getPara().equals("Niños")) {
                                sudaderas_type.add(sud);
                            }
                        }
                        for (Pantalones pant: ropa.pantalones) {
                            if (pant.getPara().equals("Niños")) {
                                pantalones_type.add(pant);
                            }
                        }
                    }
                    ArrayAdapter<Zapatillas> adapter_zapas = new ListAdapter(arg1.getContext(), zapatillas_type, 1);
                    ArrayAdapter<Pantalones> adapter_pantalones = new ListAdapter(arg1.getContext(), pantalones_type, 2);
                    ArrayAdapter<Sudaderas> adapter_sudaderas = new ListAdapter(arg1.getContext(), sudaderas_type, 3);
                    list_zapas.setAdapter(adapter_zapas);
                    list_suda.setAdapter(adapter_sudaderas);
                    list_pants.setAdapter(adapter_pantalones);
                }
            }
        });
        // get data firebase
        setRopa();
        /*
        ropa.pantalones.add(new Pantalones(2,null,"sdas1",2,"Hombre","DSFSDFFAFADDASDDASDSDASDASASADSA"));
        ropa.pantalones.add(new Pantalones(1,null,"sdas2",1,"Hombre","DSFSDFFAFADDASDDASDSDASDASASADSA"));
        ropa.pantalones.add(new Pantalones(3,null,"sdas1",3,"Mujer","DSFSDFFAFADDASDDASDSDASDASASADSA"));
        ropa.pantalones.add(new Pantalones(4,null,"sdas2",4,"Mujer","DSFSDFFAFADDASDDASDSDASDASASADSA"));
        ropa.pantalones.add(new Pantalones(5,null,"sdas1",5,"Niños","DSFSDFFAFADDASDDASDSDASDASASADSA"));
        ropa.pantalones.add(new Pantalones(6,null,"sdas2",6,"Niños","DSFSDFFAFADDASDDASDSDASDASASADSA"));
        */

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

    public void setRopa(){
        // setear ropa
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("ropa");
        // pantalones
        myref.child("pantalones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    System.out.println("----------> Object Pantalon from DATABASE:"+child.getValue().toString());
                    // pillar foto de storage con el nombre que tiene el campo 'foto' del pantalon
                   // String foto = child.getValue(Pantalones.class).getFoto();
                    final Pantalones pantalon = new Pantalones();
                    pantalon.setDescripcion(child.getValue(Pantalones.class).getDescripcion());
                    pantalon.setExistencias(child.getValue(Pantalones.class).getExistencias());
                    pantalon.setModelo(child.getValue(Pantalones.class).getModelo());
                    pantalon.setPara(child.getValue(Pantalones.class).getPara());
                    pantalon.setPrecio(child.getValue(Pantalones.class).getPrecio());
                    pantalon.setFoto(child.getValue(Pantalones.class).getFoto());
                    System.out.println("----------> Result Object Pantalon"+pantalon.toString());
                    ropa.pantalones.add(pantalon);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // sudaderas

        // zapatillas

    }

    public void inicializar(int firstSelected) {
        ArrayList<Zapatillas> zapatillas_type = new ArrayList<Zapatillas>();
        ArrayList<Pantalones> pantalones_type = new ArrayList<Pantalones>();
        ArrayList<Sudaderas> sudaderas_type = new ArrayList<Sudaderas>();
        if(firstSelected==1){
            // set ropa hombre
            for (Zapatillas zap: ropa.getZapatillas()) {
                if (zap.getPara().equals("Hombre")) {
                    zapatillas_type.add(zap);
                }
            }
            for (Sudaderas sud: ropa.getSudaderas()) {
                if (sud.getPara().equals("Hombre")) {
                    sudaderas_type.add(sud);
                }
            }
            for (Pantalones pant: ropa.getPantalones()) {
                if (pant.getPara().equals("Hombre")) {
                    pantalones_type.add(pant);
                }
            }
        } else if(firstSelected ==2){
            // set ropa mujer
            for (Zapatillas zap: ropa.getZapatillas()) {
                if (zap.getPara().equals("Mujer")) {
                    zapatillas_type.add(zap);
                }
            }
            for (Sudaderas sud: ropa.getSudaderas()) {
                if (sud.getPara().equals("Mujer")) {
                    sudaderas_type.add(sud);
                }
            }
            for (Pantalones pant: ropa.getPantalones()) {
                if (pant.getPara().equals("Mujer")) {
                    pantalones_type.add(pant);
                }
            }
        } else if(firstSelected==3) {
            // set ropa niño
            for (Zapatillas zap: ropa.getZapatillas()) {
                if (zap.getPara().equals("Niños")) {
                    zapatillas_type.add(zap);
                }
            }
            for (Sudaderas sud: ropa.getSudaderas()) {
                if (sud.getPara().equals("Niños")) {
                    sudaderas_type.add(sud);
                }
            }
            for (Pantalones pant: ropa.getPantalones()) {
                if (pant.getPara().equals("Niños")) {
                    pantalones_type.add(pant);
                }
            }
        }

        ArrayAdapter<Zapatillas> adapter_zapas = new ListAdapter(this, zapatillas_type, 1);
        ArrayAdapter<Pantalones> adapter_pantalones = new ListAdapter(this, pantalones_type, 2);
        ArrayAdapter<Sudaderas> adapter_sudaderas = new ListAdapter(this, sudaderas_type, 3);
        list_zapas.setAdapter(adapter_zapas);
        list_suda.setAdapter(adapter_sudaderas);
        list_pants.setAdapter(adapter_pantalones);
    }
}
