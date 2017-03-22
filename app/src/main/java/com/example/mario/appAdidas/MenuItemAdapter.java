package com.example.mario.appAdidas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MenuItemAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> items;

    public MenuItemAdapter(Context context, List<String> menuItems) {
        super(context, 0, menuItems);
        this.items = menuItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }


    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.menu_item_cell, parent, false);
        MenuItemViewHolder holder = new MenuItemViewHolder();
            holder.tvNombre = (TextView) v.findViewById(R.id.itemName);
            holder.tvNombre.setText(items.get(position));
            holder.foto = (ImageView) v.findViewById(R.id.itemPhoto);
        if(items.get(position).equals("Mi Perfil")) {
            holder.foto.setImageResource(R.mipmap.icon_profile);
        } else if(items.get(position).equals("Hombre")) {
            holder.foto.setImageResource(R.mipmap.icon_hombre);
        } else if(items.get(position).equals("Mujeres")) {
            holder.foto.setImageResource(R.mipmap.icon_mujer);
        } else if(items.get(position).equals("Niños")) {
            holder.foto.setImageResource(R.mipmap.icon_ninos);
        }
        return v;
    }
}
