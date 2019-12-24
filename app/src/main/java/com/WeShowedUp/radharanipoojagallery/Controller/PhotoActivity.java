package com.WeShowedUp.radharanipoojagallery.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.WeShowedUp.radharanipoojagallery.General.RetrofitClass;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.GenerateCouponResponse.GenerateCouponResponse;
import com.WeShowedUp.radharanipoojagallery.Response.GetCouponResponse.GetCouponResponse;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        TextView name = findViewById(R.id.name_myphoto);
        ImageView image = findViewById(R.id.image_myphoto);
        TextView price = findViewById(R.id.price_myphoto);
        TextView date = findViewById(R.id.date_myphoto);
        TextView like = findViewById(R.id.likes_myphoto);
        TextView share = findViewById(R.id.share_myphoto);
        button = findViewById(R.id.coupon_btn);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Photo");
        name.setText(getIntent().getStringExtra("message"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(image);
        price.setText(getIntent().getStringExtra("price"));
        like.setText(getIntent().getStringExtra("likes"));
        share.setText(getIntent().getStringExtra("shares"));
        date.setText(getIntent().getStringExtra("date"));
        String status = getIntent().getStringExtra("status");

        if (status != null)
            if (status.equals("0")) {
                button.setBackgroundColor(Color.RED);
                button.setText("UnVerified");
                button.setEnabled(false);
            } else {
                if (status.equals("1")) {
                    button.setBackgroundColor(Color.GREEN);
                    button.setText("Verified");
                    button.setEnabled(true);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {
                                RetrofitClass retrofitClass = new RetrofitClass();
                                Call<GenerateCouponResponse> call = retrofitClass.retrofit().generatecoupen(getIntent().getStringExtra("phone"), getIntent().getStringExtra("post_id"));
                                call.enqueue(new Callback<GenerateCouponResponse>() {
                                    @Override
                                    public void onResponse(Call<GenerateCouponResponse> call, Response<GenerateCouponResponse> response) {
                                        button.setBackgroundColor(Color.BLUE);
                                        button.setText("Coupon Generated");
                                        button.setEnabled(true);
                                    }

                                    @Override
                                    public void onFailure(Call<GenerateCouponResponse> call, Throwable t) {
                                        hideSoftKey();
                                        Snackbar.make(getWindow().getDecorView(), "Something Wents Wrong", Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } else {
                    if (status.equals("2")) {
                        button.setBackgroundColor(Color.BLUE);
                        button.setText("Coupon Generated");
                        button.setEnabled(true);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    RetrofitClass retrofitClass = new RetrofitClass();
                                    Call<GetCouponResponse> call = retrofitClass.retrofit().getcoupon(getIntent().getStringExtra("post_id"));
                                    call.enqueue(new Callback<GetCouponResponse>() {
                                        @Override
                                        public void onResponse(Call<GetCouponResponse> call, Response<GetCouponResponse> response) {
                                            if (response.body() != null) {
                                                if (response.body().getFlag()) {
                                                    Intent intent = new Intent(PhotoActivity.this, CouponActivity.class);
                                                    intent.putExtra("status", String.valueOf(response.body().getData().getStatus()));
                                                    intent.putExtra("amount", String.valueOf(response.body().getData().getAmount()));
                                                    intent.putExtra("start", response.body().getData().getStart());
                                                    intent.putExtra("end", response.body().getData().getEnd());
                                                    intent.putExtra("post_id", getIntent().getStringExtra("post_id"));
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<GetCouponResponse> call, Throwable t) {
                                            hideSoftKey();
                                            Snackbar.make(getWindow().getDecorView(), "Try Again", Snackbar.LENGTH_SHORT).show();
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                //new Verified(status);
                            }
                        });
                    } else {
                        button.setBackgroundColor(Color.GRAY);
                        button.setText("Coupon Redeemed");
                        button.setEnabled(false);
                    }
                }
            }

    }

    private void hideSoftKey() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null)
            if (imm.isAcceptingText()) { // verify if the soft keyboard is open
                imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
            }
    }
}
