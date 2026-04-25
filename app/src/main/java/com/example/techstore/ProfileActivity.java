package com.example.techstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView txtProfileName, txtProfileEmail, txtProfilePhone;
    ImageButton btnProfileMap, btnProfilePhone, btnProfileFacebook, btnProfileInstagram;
    Button btnLogoutProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtProfileName      = findViewById(R.id.txtProfileName);
        txtProfileEmail     = findViewById(R.id.txtProfileEmail);
        txtProfilePhone     = findViewById(R.id.txtProfilePhone);
        btnProfileMap       = findViewById(R.id.btnProfileMap);
        btnProfilePhone     = findViewById(R.id.btnProfilePhone);
        btnProfileFacebook  = findViewById(R.id.btnProfileFacebook);
        btnProfileInstagram = findViewById(R.id.btnProfileInstagram);
        btnLogoutProfile    = findViewById(R.id.btnLogoutProfile);
        Button btnBackProfile = findViewById(R.id.btnBackProfile);
        btnBackProfile.setOnClickListener(v -> finish());


        SharedPreferences prefs = getSharedPreferences("TechStorePrefs", MODE_PRIVATE);
        txtProfileName.setText("Name: "  + prefs.getString("firstName","") + " " + prefs.getString("lastName",""));
        txtProfileEmail.setText("Email: " + prefs.getString("email",""));
        txtProfilePhone.setText("Phone: " + prefs.getString("phone",""));

        btnProfileMap.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=Ain+Benian+Alger+Algerie"))));

        btnProfilePhone.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:0794081373")))); // ← ton numéro

        btnProfileFacebook.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/VotrePage"))));

        btnProfileInstagram.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/slayer_ry"))));

        btnLogoutProfile.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Sign Out")
                        .setMessage("Do you really want to sign out?")
                        .setPositiveButton("Yes", (d, w) -> {
                            startActivity(new Intent(this, LoginActivity.class));
                            finishAffinity();
                        })
                        .setNegativeButton("Cancel", null)
                        .show());
    }


}