package com.WeShowedUp.radharanipoojagallery.ui.developer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.WeShowedUp.radharanipoojagallery.R;

public class DeveloperFragment extends Fragment {

    private TextView emailAbhi, emailPranav, emailAman;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_developer, container, false);
        emailAbhi = root.findViewById(R.id.email_abhinav);
        emailAman = root.findViewById(R.id.email_aman);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailAbhi.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View view) {

                Log.i("Send email", "");
                String[] TO = {"abhinav.varshney_cs17@gla.ac.in"};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Abhinav");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                    Log.i("Finished sending email", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        emailAman.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View view) {
                Log.i("Send email", "");
                String[] TO = {"aman.singhal_cs17@gla.ac.in"};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Aman,");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                    Log.i("Finished sending email", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}