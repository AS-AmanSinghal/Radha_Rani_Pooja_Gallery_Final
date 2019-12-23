package com.WeShowedUp.radharanipoojagallery.Controller;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.WeShowedUp.radharanipoojagallery.R;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        imageView=findViewById(R.id.photo_image);
        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);
    }
}
