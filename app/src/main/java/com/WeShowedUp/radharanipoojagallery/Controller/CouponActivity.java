package com.WeShowedUp.radharanipoojagallery.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.WeShowedUp.radharanipoojagallery.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Objects;

public class CouponActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        imageView=findViewById(R.id.qrcode);

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
            Log.d("printhoja", "onResponse: "+bmp);
            System.out.println("QR CODE COMPLETED !!!");
            imageView.setImageBitmap(bmp);
            Toast.makeText(getApplicationContext(),bmp.toString(),Toast.LENGTH_SHORT).show();
            System.out.println("PRINT "+bmp);
        }
        catch (Exception e)
        {
        }
    }
}
