package com.example.mario.appAdidas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
                listAdapterHolder.image = transferImage("zapatillas/" + zapatilla.getFoto());
                listAdapterHolder.description.setText(zapatilla.getDescripcion());
                listAdapterHolder.modelo.setText(zapatilla.getModelo());
                listAdapterHolder.prize.setText(zapatilla.getPrecio() + "");
        } else if(type==2) {
            Pantalones pantalon = (Pantalones) items.get(position);
                listAdapterHolder.image = transferImage("pantalones/" + pantalon.getFoto());
                listAdapterHolder.description.setText(pantalon.getDescripcion());
                listAdapterHolder.modelo.setText(pantalon.getModelo());
                listAdapterHolder.prize.setText(pantalon.getPrecio() + "");
        } else {
            Sudaderas sudadera = (Sudaderas) items.get(position);
                listAdapterHolder.image = transferImage("sudaderas/" + sudadera.getFoto());
                listAdapterHolder.description.setText(sudadera.getDescripcion());
                listAdapterHolder.modelo.setText(sudadera.getModelo());
                listAdapterHolder.prize.setText(sudadera.getPrecio() + "");
        }
        return v;
    }

    public ImageView transferImage(String foto) {
        ImageView imageView=null;
        String photoPath = foto;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://login-4ca8d.appspot.com");
        storageRef.child(photoPath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                ImageView imageView = new ImageView(getContext());
                InputStream is = null;
                try {
                    is = context.getContentResolver().openInputStream(uri);
                    Bitmap bm = BitmapFactory.decodeStream(is);
                    imageView.setImageBitmap(bm);
                    is.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("---------> There is a image's path in database that doesn't match with any image in the storage <---------");
            }
        });
        return imageView;
    }
}