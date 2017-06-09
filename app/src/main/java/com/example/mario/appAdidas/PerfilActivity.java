package com.example.mario.appAdidas;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.appAdidas.HombresF.HombresActivity;
import com.example.mario.appAdidas.MujeresF.MujeresActivity;
import com.example.mario.appAdidas.NinosF.NinosActivity;
import com.example.mario.appAdidas.pedidos.contactActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PerfilActivity extends AppCompatActivity implements View.OnClickListener {

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
    ArrayList<String> idd = new ArrayList<>();
    ArrayList<String> precio = new ArrayList<>();
    ArrayList<String> foto = new ArrayList<>();

    ArrayAdapter adapter2;
    Button pay;


    //STORAGE
    public static final String PREFS = "person";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        listapedidos = (ListView) findViewById(R.id.listapedidos);
        pay = (Button) findViewById(R.id.pay);

        //Cesta


        //ActionBar Text
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("Mi Perfil");


        //Adding values
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("perfiles");


        SharedPreferences sp = getSharedPreferences(PREFS, 0);
        //final String idX = sp.getString("idFirebase", "nothing");
        final String idX = DataHolder.intance.id;

        myref.child(idX).child("cesta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {

                    if (child != null) {
                        String ropa = (String) child.getValue();
                        String ropa1[] = ropa.split("&&");

                        idd.add(child.getKey());
                        modelo.add(ropa1[0]);
                        precio.add(ropa1[1]);
                        foto.add(ropa1[2]);
                    }


                }

                if (modelo.size() != 0) {
                    idd.remove(0);
                    modelo.remove(0);
                    precio.remove(0);
                    foto.remove(0);
                }


                adapter2 = new CestaAdapter(PerfilActivity.this, modelo, precio, foto, idd);


                listapedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myref = database.getReference("perfiles");
                        myref.child(DataHolder.intance.id).child("cesta").child(idd.get(position)).removeValue();


                        modelo.remove(position);
                        precio.remove(position);
                        foto.remove(position);
                        idd.remove(position);

                        adapter2.clear();


                        adapter2.notifyDataSetChanged();
                        finish();
                        startActivity(getIntent());

                    }
                });


                listapedidos.setAdapter(adapter2);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //cesta

        pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/your_paypal"));
                startActivity(browserIntent);
            }
        });
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(PerfilActivity.this, "Item: " + menuItems.get(arg2), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();

                if (menuItems.get(arg2).toString().equals("Hombres")) {
                    Intent intent = new Intent(PerfilActivity.this, HombresActivity.class);
                    startActivity(intent);
                    //finish();

                } else if (menuItems.get(arg2).toString().equals("Home")) {
                    Intent intent = new Intent(PerfilActivity.this, HomeActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Mujeres")) {
                    Intent intent = new Intent(PerfilActivity.this, MujeresActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Niños")) {
                    Intent intent = new Intent(PerfilActivity.this, NinosActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Encuentranos")) {
                    Intent intent = new Intent(PerfilActivity.this, Localizacion.class);
                    startActivity(intent);
                    finish();
                } else if (menuItems.get(arg2).toString().equals("Contactanos")) {
                    Intent intent = new Intent(PerfilActivity.this, contactActivity.class);
                    startActivity(intent);
                    //finish();
                } else if (menuItems.get(arg2).toString().equals("Log out")) {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(PerfilActivity.this, LoginActivity.class));
                }


                //Intent intent = new Intent(MainActivity.this, AboutmeActivity.class);
                //startActivity(intent);
                //finish();
            }
        });

        // /menu


        // config

        btnConfig.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, configActivity.class);
                startActivity(intent);
            }
        });


        //

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


// /login


        // get data firebase


        // /data


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
