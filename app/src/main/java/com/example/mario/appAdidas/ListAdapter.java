package com.example.mario.appAdidas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.appAdidas.Items.Pantalones;
import com.example.mario.appAdidas.Items.Sudaderas;
import com.example.mario.appAdidas.Items.Zapatillas;

import java.util.ArrayList;

/**
 * Created by seeketing on 22/3/17.
 */

public class ListAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<?> items;
    private int type;

    public ListAdapter(Context context, ArrayList<?> menuItems, int type) {
        super(context, 0, menuItems);
        this.items = menuItems;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount() {
        return items.size();
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.menu_ropa_cell, parent, false);
        ListAdapterHolder listAdapterHolder = new ListAdapterHolder();
        listAdapterHolder.image = (ImageView) v.findViewById(R.id.item_foto);
        listAdapterHolder.description = (TextView) v.findViewById(R.id.item_description);
        listAdapterHolder.modelo = (TextView) v.findViewById(R.id.item_modelo);
        listAdapterHolder.prize = (TextView) v.findViewById(R.id.item_precio);
        // 1 = zapatillas, 2 = pantalones, 3 = sudaderas
        if(type==1){
            Zapatillas zapatilla = (Zapatillas) items.get(position);
 //               listAdapterHolder.image.setImageDrawable(zapatilla.getFoto().getDrawable());
                listAdapterHolder.description.setText(zapatilla.getDesc());
                listAdapterHolder.modelo.setText(zapatilla.getModelo());
                listAdapterHolder.prize.setText(zapatilla.getPrecio()+"");
        } else if(type==2) {
            Pantalones pantalon = (Pantalones) items.get(position);
//                listAdapterHolder.image.setImageDrawable(pantalon.getFoto().getDrawable());
                listAdapterHolder.description.setText(pantalon.getDesc());
                listAdapterHolder.modelo.setText(pantalon.getModelo());
                listAdapterHolder.prize.setText(pantalon.getPrecio()+"");
        } else {
            Sudaderas sudadera = (Sudaderas) items.get(position);
  //              listAdapterHolder.image.setImageDrawable(sudadera.getFoto().getDrawable());
                listAdapterHolder.description.setText(sudadera.getDesc());
                listAdapterHolder.modelo.setText(sudadera.getModelo());
                listAdapterHolder.prize.setText(sudadera.getPrecio()+"");
        }
        return v;
    }
}
