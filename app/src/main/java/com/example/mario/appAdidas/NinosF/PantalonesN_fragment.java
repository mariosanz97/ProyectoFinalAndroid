package com.example.mario.appAdidas.NinosF;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mario.appAdidas.R;
import com.example.mario.appAdidas.RopaApp.Ropa;
import com.example.mario.appAdidas.RopaApp.RopaItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PantalonesN_fragment extends Fragment {


    ListView listaropa;

    ArrayList<String> modelo;

    //lista
    ArrayList<Ropa> ropa;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    RopaItemAdapter ropaItemAdapter;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pantalones_ninos, container, false);

        //ActionBar Text
        ActionBar myActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        myActionBar.setTitle("Pantalones");



        listaropa = (ListView) v.findViewById(R.id.ropalista);


        progressDialog = new ProgressDialog(getContext());


        progressDialog.setMessage("Cargando...");
        progressDialog.show();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("ropa");


        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ropa = new ArrayList<Ropa>();
                modelo = new ArrayList<String>();

                int cont = 0;

                for (DataSnapshot child : dataSnapshot.child("pantalones").getChildren()) {


                    System.out.println("-----sidiModelo-----> " + child.getValue(Ropa.class).getModelo() + "----PRECIO--->" + child.getValue(Ropa.class).getPrecio() + "----DES--->" + child.getValue(Ropa.class).getDescripcion());

                    if (child.getValue(Ropa.class).getPara().equals("Niños")) {
                        int precio = child.getValue(Ropa.class).getPrecio();
                        modelo.add(child.getValue(Ropa.class).getModelo());
                        int existencias = child.getValue(Ropa.class).getExistencias();
                        String para = child.getValue(Ropa.class).getPara();
                        String descripcion = child.getValue(Ropa.class).getDescripcion();
                        String foto = child.getValue(Ropa.class).getFoto();
                        System.out.println("img::::::::::::::::::::::::::::::" + foto);


                        Ropa ropaClass = new Ropa(precio, modelo.get(cont), para, descripcion, existencias, foto);
                        //Ropa ropaClass = new Ropa(modelo,precio);

                        ropa.add(ropaClass);
                        cont++;
                    }
                }

                ropaItemAdapter = new RopaItemAdapter(getContext(), ropa);
                listaropa.setAdapter(ropaItemAdapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;

    }

    public interface OnFragmentInteractionListener {
    }
}
