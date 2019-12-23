package com.WeShowedUp.radharanipoojagallery.Controller;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.WeShowedUp.radharanipoojagallery.General.RetrofitClass;
import com.WeShowedUp.radharanipoojagallery.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class PhotoActivity extends AppCompatActivity {

    private ImageView image;
    private TextView name,price,date,verified,like,share;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        name=findViewById(R.id.name_myphoto);
        image=findViewById(R.id.image_myphoto);
        price=findViewById(R.id.price_myphoto);
        date=findViewById(R.id.date_myphoto);
        like=findViewById(R.id.likes_myphoto);
        share=findViewById(R.id.share_myphoto);
        button=findViewById(R.id.coupon_btn);

        name.setText(getIntent().getStringExtra("message"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(image);
        price.setText(getIntent().getStringExtra("price"));
        like.setText(getIntent().getStringExtra("likes"));
        share.setText(getIntent().getStringExtra("shares"));
        date.setText(getIntent().getStringExtra("date"));
        String status=getIntent().getStringExtra("status");

        if (status!=null)
        if (status.equals("0"))
        {
            button.setBackgroundColor(Color.RED);
            button.setText("UnVerified");
            button.setEnabled(false);
        }
        else
        {
            if (status.equals("1"))
            {
                button.setBackgroundColor(Color.GREEN);
                button.setText("Verified");
                button.setEnabled(true);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        new Verified();
                    }
                });

            }
            else
            {
                if (status.equals("2"))
                {
                    button.setBackgroundColor(Color.BLUE);
                    button.setText("Coupon Generated");
                    button.setEnabled(true);
                }
                else
                {
                    button.setBackgroundColor(Color.GRAY);
                    button.setText("Coupon Redeemed");
                    button.setEnabled(false);
                }
            }
        }

    }


    public class Verified extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                RetrofitClass retrofitClass=new RetrofitClass();
                //Call<>
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
