package com.WeShowedUp.radharanipoojagallery.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.WeShowedUp.radharanipoojagallery.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {

    EditText username,password,conf_password;
    Button button;
    static int PReCode=1;
    static int REQUESTCODE=1;
    Uri contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Objects.requireNonNull(getSupportActionBar()).hide();
        username=findViewById(R.id.registration_mobile_number);
        password=findViewById(R.id.registration_password);
        conf_password=findViewById(R.id.registration_conf_password);
        button=findViewById(R.id.registration_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (Build.VERSION.SDK_INT>=22)
                {
                    checkResequestForPermission();
                }
                else
                {
                    ContactList();
                }
            }
        });
    }

    private void ContactList()
    {
        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }

    private void checkResequestForPermission()
    {
        if (ContextCompat.checkSelfPermission(RegistrationActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this,Manifest.permission.READ_CONTACTS))
            {
                Snackbar.make(getWindow().getDecorView(),"Accept the Contact Permission",Snackbar.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(RegistrationActivity.this,new String[]{Manifest.permission.READ_CONTACTS},PReCode);
            }
        }
        else
        {
            ContactList();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==REQUESTCODE && requestCode==RESULT_OK && data!=null)
        {
            contact=data.getData();
            Toast.makeText(RegistrationActivity.this,"ACCEPT",Toast.LENGTH_SHORT).show();
        }
    }
}