package com.WeShowedUp.radharanipoojagallery.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.WeShowedUp.radharanipoojagallery.Module.SharedPrefManager;
import com.WeShowedUp.radharanipoojagallery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    TextView name, email;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_my_photo,
                R.id.nav_feed, R.id.nav_developer,
                R.id.nav_upload, R.id.nav_coupons)
                .setDrawerLayout(drawer)
                .build();
        View headerView = navigationView.getHeaderView(0);
        email = headerView.findViewById(R.id.header_mobile);
        email.setText(getIntent().getStringExtra("phone"));
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        BottomNavigationView navigationViewB = findViewById(R.id.nav_view_bottom);
        NavController navControllerB = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navControllerB, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationViewB, navControllerB);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        int id = item.getItemId();
        switch (id) {
            case R.id.action_log_out:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                sharedPrefManager.putString(this, "phone", "");
                sharedPrefManager.putString(this, "password", "");
                startActivity(intent);
                finish();
                break;
            case R.id.action_change_password:
                Snackbar.make(getWindow().getDecorView(), "Coming Soon", Snackbar.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
