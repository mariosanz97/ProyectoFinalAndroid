package com.example.mario.appAdidas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class CestaAdapter extends ArrayAdapter {

    private Context context;
    private List<String> modelo;
    private List<String> precio;
    private List<String> foto;


    public CestaAdapter(Context context, List<String> modelo, List<String> precio, List<String> foto, List<String> id) {
        super(context, 0, modelo);
        this.modelo = modelo;
        this.context = context;
        this.precio = precio;
        this.foto = foto;
    }

    @Override
    public int getCount() {
        return modelo.size();
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.cesta_item_cell, parent, false);


        final MenuItemViewHolder holder = new MenuItemViewHolder();
        holder.foto = (ImageView) v.findViewById(R.id.foto);
        holder.modelo = (TextView) v.findViewById(R.id.nombre);
        holder.precio = (TextView) v.findViewById(R.id.precio);

        holder.modelo.setText(modelo.get(position));
        holder.precio.setText(precio.get(position) + " €");
        Picasso.with(context).load(foto.get(position)).into(holder.foto);


        return v;
    }
}
