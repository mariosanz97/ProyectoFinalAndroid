package com.example.mario.appAdidas.DetallesRopaActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.appAdidas.DataHolder;
import com.example.mario.appAdidas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetallesActivity extends AppCompatActivity {


    ImageView img;
    TextView modelo;
    int cont;
    Button cesta;
    Button pay;
    Button btnshare;
    public static final String PREFS = "person";

    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);


        img = (ImageView) findViewById(R.id.url);
        modelo = (TextView) findViewById(R.id.modelo);
        cesta = (Button) findViewById(R.id.cesta);
        pay = (Button) findViewById(R.id.pay);
        btnshare = (Button) findViewById(R.id.btnshare);
        sp = (Spinner) findViewById(R.id.sp);
        Intent intent = getIntent();


        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("S");
        spinnerArray.add("M");
        spinnerArray.add("L");
        spinnerArray.add("XL");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.sp);
        sItems.setAdapter(adapter);


        final String modeloo = intent.getStringExtra("modelo");
        final String url = intent.getStringExtra("url");
        final int precio = intent.getIntExtra("precio", 0);

        //System.out.println(precio + "...........");


        modelo.setText(modeloo);
        Picasso.with(this).load(url).into(img);

        final String idX = DataHolder.intance.id;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myref = database.getReference("perfiles");
        Query query = myref.child(idX).child("cesta");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                DataHolder.intance.cont = (int) snapshot.getChildrenCount();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    DataHolder.intance.cont = Integer.parseInt(snap.getKey())+1;
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        cesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        DataHolder.intance.cont = (int) dataSnapshot.child(idX).child("cesta").getChildrenCount();
                        for (DataSnapshot snap: dataSnapshot.child(idX).child("cesta").getChildren()) {
                            DataHolder.intance.cont = Integer.parseInt(snap.getKey())+1;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                if (DataHolder.intance.cont == 0) {
                    myref.child(idX).child("cesta").child(0 + "").setValue("Null&&null&&null");
                    myref.child(idX).child("cesta").child(DataHolder.intance.cont + 1 + "").setValue(modeloo + "&&" + precio + "&&" + url);

                } else {
                    myref.child(idX).child("cesta").child(DataHolder.intance.cont + "").setValue(modeloo + "&&" + precio + "&&" + url);
                }

                Toast.makeText(DetallesActivity.this, "Añadido a la cesta ", Toast.LENGTH_SHORT).show();
            }
        });


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri bmpUri = null;
                try {
                    bmpUri = getLocalBitmapUri(img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bmpUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    DetallesActivity.this.startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                }
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/your_paypal"));
                startActivity(browserIntent);
            }
        });


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
        File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        FileOutputStream out = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
        out.close();
        bmpUri = Uri.fromFile(file);

        return bmpUri;
    }

}
