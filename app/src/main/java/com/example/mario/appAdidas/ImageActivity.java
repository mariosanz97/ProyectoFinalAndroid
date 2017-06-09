package com.example.mario.appAdidas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    ImageView selectedImage;
    ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);

        selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
        imgbtn = (ImageButton) findViewById(R.id.imgbtn);


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent(); // get Intent which we set from Previous Activity

        selectedImage.setImageResource(intent.getIntExtra("image", 0)); // get image from Intent and set it in ImageView
    }
}
