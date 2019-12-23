package com.WeShowedUp.radharanipoojagallery.Controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.WeShowedUp.radharanipoojagallery.General.RetrofitClass;
import com.WeShowedUp.radharanipoojagallery.Module.SharedPrefManager;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.LoginResponse.LoginResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText username, pass;
    TextView forgot_password;
    LinearLayout registration;
    ProgressBar progressBar;
    CheckBox checkBox;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        button = findViewById(R.id.login_btn);
        username = findViewById(R.id.login_mobile_number);
        pass = findViewById(R.id.login_password);
        progressBar = findViewById(R.id.progressbar);
        forgot_password = findViewById(R.id.login_forgotpassword);
        registration = findViewById(R.id.login_registration);
        sharedPrefManager= new SharedPrefManager(this);
        checkBox = findViewById(R.id.login_rememberme);
        hideSoftKey();

        if (sharedPrefManager.getString("phone").isEmpty() && sharedPrefManager.getString("password").isEmpty()) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button.setEnabled(false);
                    progressBar.setVisibility(View.VISIBLE);
                    loginValidation(String.valueOf(username.getText()).trim(), String.valueOf(pass.getText()).trim());
                }
            });
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("phone", sharedPrefManager.getString("phone"));
            startActivity(intent);
            finish();
        }


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getWindow().getDecorView(), "Forgot password", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void loginValidation(String mobile, String password) {
        if (mobile.isEmpty() && password.isEmpty()) {
            hideSoftKey();
            progressBar.setVisibility(View.INVISIBLE);
            Snackbar.make(getWindow().getDecorView(), "Enter Mobile Number/Password", Snackbar.LENGTH_SHORT).show();
            button.setEnabled(true);
        } else
            {
              new Login(mobile, password).execute();

            //login(mobile, password);
        }
    }

    public class Login extends AsyncTask<Void,Void,Void>
    {
        private String mobile;
        private String password;

        public Login(String mobile, String password)
        {
            this.mobile = mobile;
            this.password= password;
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            Log.d("Mobile", "doInBackground: "+mobile);
            Log.d("Password", "doInBackground: "+password);
            try
            {
                RetrofitClass retrofitClass = new RetrofitClass();
                Call<LoginResponse> call = retrofitClass.retrofit().login(mobile, password);
                call.enqueue(new Callback<LoginResponse>()
                {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (response.body() != null) {
                            if (response.body().getFlag()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("phone", mobile);
                                if (checkBox.isChecked()) {
                                    sharedPrefManager.putString(LoginActivity.this, "phone", String.valueOf(response.body().getData().getPhone()).trim());
                                    sharedPrefManager.putString(LoginActivity.this, "password", String.valueOf(password));
                                }
                                startActivity(intent);
                                finish();
                            } else {
                                hideSoftKey();
                                button.setEnabled(true);
                                Snackbar.make(getWindow().getDecorView(), "User Does not Exists", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setEnabled(true);
                        hideSoftKey();
                        Snackbar.make(getWindow().getDecorView(), "Bad Connection Try Again!!", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
            catch (Exception e)
            {
                hideSoftKey();
                Snackbar.make(getWindow().getDecorView(),"Try Again",Snackbar.LENGTH_SHORT).show();
            }

            return null;
        }
    }
    private void login(final String mobile, final String password) {
    }

    private void hideSoftKey() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null)
            if (imm.isAcceptingText()) { // verify if the soft keyboard is open
                imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
            }
    }
}
