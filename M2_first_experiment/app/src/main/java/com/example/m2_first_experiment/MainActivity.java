package com.example.m2_first_experiment;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signInButton = findViewById(R.id.signInButton);
        Button registerButton = findViewById(R.id.registerButton);

        signInButton.setOnClickListener(v -> {
            // Intent to navigate to SignInActivity
            Intent signInIntent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(signInIntent);
        });

        registerButton.setOnClickListener(v -> {
            // Intent to navigate to RegistrationActivity
            Intent registerIntent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(registerIntent);
        });
    }
}
