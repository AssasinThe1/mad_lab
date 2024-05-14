package com.example.m2_first_experiment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Set the correct layout file

        mAuth = FirebaseAuth.getInstance();

        final EditText emailEditText = findViewById(R.id.editTextEmail);
        final EditText passwordEditText = findViewById(R.id.editTextPassword);
        final EditText confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "Passwords do not match.",
                    Toast.LENGTH_SHORT).show();
        }
        Button registerButton = findViewById(R.id.buttonRegister);

        registerButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Log.d("Registration", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                // TODO: Navigate to the next screen or update UI
                                Toast.makeText(RegistrationActivity.this, "Registration successful.",
                                        Toast.LENGTH_SHORT).show();
                                Intent signInIntent = new Intent(RegistrationActivity.this, SignInActivity.class);
                                startActivity(signInIntent);
                            } else {
                                Log.w("Registration", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(RegistrationActivity.this, "Email and password cannot be empty.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}