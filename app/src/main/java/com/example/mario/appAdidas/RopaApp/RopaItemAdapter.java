package com.example.mario.appAdidas.RopaApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mario.appAdidas.DetallesRopaActivity.DetallesActivity;
import com.example.mario.appAdidas.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class RopaItemAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<Ropa> ropa;

    public RopaItemAdapter(Context context, ArrayList<Ropa> ropa) {
        super(context, 0);
        this.ropa = ropa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ropa.size();
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.celda_ropa, parent, false);

        final RopaItemViewHolder holder = new RopaItemViewHolder();

        holder.modelo = (TextView) v.findViewById(R.id.modelo);
        holder.modelo.setText(ropa.get(position).getModelo());

        holder.precio = (TextView) v.findViewById(R.id.preciop);
        holder.precio.setText(String.valueOf(ropa.get(position).getPrecio()) + " €");


        holder.foto = (ImageView) v.findViewById(R.id.imageropa);
        holder.btnshare = (Button) v.findViewById(R.id.btnshare);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Picasso.with(context).load(ropa.get(position).getFoto()).into(holder.foto);


        //compartir
        holder.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri bmpUri = null;
                try {
                    bmpUri = getLocalBitmapUri(holder.foto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bmpUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    context.startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                }
            }
        });

        holder.lyropa = (LinearLayout) v.findViewById(R.id.lyropa);


        holder.lyropa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(context, DetallesActivity.class);
                intent.putExtra("modelo", ropa.get(position).getModelo());
                intent.putExtra("url", ropa.get(position).getFoto());
                intent.putExtra("precio", ropa.get(position).getPrecio());
                context.startActivity(intent);
            }
        });




/*
        holder.para = (TextView) v.findViewById(R.id.para);
        holder.para.setText(ropa.get(position).getPara());

        holder.existencias = (TextView) v.findViewById(R.id.existencias);
        holder.existencias.setText(ropa.get(position).getExistencias());

        holder.descripcion = (TextView) v.findViewById(R.id.descripcion);
        holder.descripcion.setText(ropa.get(position).getDescripcion());
*/
        return v;

    }


    public Uri getLocalBitmapUri(ImageView imageView) throws IOException {
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        Uri bmpUri = null;
        File file = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        FileOutputStream out = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
        out.close();
        bmpUri = Uri.fromFile(file);

        return bmpUri;
    }


}
