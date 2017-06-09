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


public class MenuItemAdapter extends ArrayAdapter {

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


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.menu_item_cell, parent, false);

        MenuItemViewHolder holder = new MenuItemViewHolder();
        holder.tvNombre = (TextView) v.findViewById(R.id.itemName);
        holder.tvNombre.setText(items.get(position));

        holder.img = (ImageView) v.findViewById(R.id.img);
        if (items.get(position).equals("Mi Perfil")) {
            holder.img.setImageResource(R.mipmap.user);
        } else if (items.get(position).equals("Hombres")) {
            holder.img.setImageResource(R.mipmap.man);
        }  else if (items.get(position).equals("Home")) {
            holder.img.setImageResource(R.mipmap.home);
        }else if (items.get(position).equals("Mujeres")) {
            holder.img.setImageResource(R.mipmap.women);
        } else if (items.get(position).equals("Niños")) {
            holder.img.setImageResource(R.mipmap.veves);
        } else if (items.get(position).equals("Log out")) {
            holder.img.setImageResource(R.mipmap.logout);
        }else if (items.get(position).equals("Encuentranos")) {
            holder.img.setImageResource(R.mipmap.find);
        }else if (items.get(position).equals("Contactanos")) {
            holder.img.setImageResource(R.mipmap.cn);
        }
        return v;
    }
}
