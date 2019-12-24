package com.WeShowedUp.radharanipoojagallery.Controller;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.WeShowedUp.radharanipoojagallery.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Objects;

public class CouponActivity extends AppCompatActivity {

    ImageView qrcode;
    TextView amount, start, end, status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        amount = findViewById(R.id.amount_coupon_act);
        start = findViewById(R.id.start_coupon_act);
        end = findViewById(R.id.end_coupon_act);
        status = findViewById(R.id.status_coupon_act);
        qrcode = findViewById(R.id.qrcode_coupon_act);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Coupon");
        String Status = getIntent().getStringExtra("status");
        amount.setText(getIntent().getStringExtra("amount"));
        start.setText(getIntent().getStringExtra("start"));
        end.setText(getIntent().getStringExtra("end"));
        if (Status != null)
            if (Status.equals("0")) {
                status.setText("Redeem");

                QRCodeWriter writer = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = writer.encode(Objects.requireNonNull(getIntent().getStringExtra("post_id")), BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    System.out.println("QR CODE COMPLETED !!!");
                    qrcode.setImageBitmap(bmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                status.setText("Redeemed");
        }
    }
}
