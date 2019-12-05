package com.WeShowedUp.radharanipoojagallery.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.WeShowedUp.radharanipoojagallery.MainActivity;
import com.WeShowedUp.radharanipoojagallery.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText username,pass;
    TextView forgot_password;
    LinearLayout registration;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        button=findViewById(R.id.login_btn);
        username=findViewById(R.id.login_mobile_number);
        pass=findViewById(R.id.login_password);
        progressBar=findViewById(R.id.progressbar);
        forgot_password=findViewById(R.id.login_forgotpassword);
        registration=findViewById(R.id.login_registration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                button.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                loginValidation(String.valueOf(username.getText()).trim(),String.valueOf(pass.getText()).trim());
            }
        });


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Snackbar.make(getWindow().getDecorView(),"Forgot password",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void loginValidation(String mobile, String password)
    {
        if(mobile.isEmpty() && password.isEmpty())
        {
            hideSoftKey();
            progressBar.setVisibility(View.INVISIBLE);
            Snackbar.make(getWindow().getDecorView(),"Enter Mobile Number/Password",Snackbar.LENGTH_SHORT).show();
            button.setEnabled(true);
        }
        else
        {
            progressBar.setVisibility(View.INVISIBLE);
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("mobile",mobile);
            startActivity(intent);
            finish();
        }
    }

    private void hideSoftKey()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(imm!=null)
            if (imm.isAcceptingText())
            { // verify if the soft keyboard is open
                imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
            }
    }
}
