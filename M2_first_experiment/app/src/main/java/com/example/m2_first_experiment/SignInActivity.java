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

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in); // Set the correct layout file

        mAuth = FirebaseAuth.getInstance();

        final EditText emailEditText = findViewById(R.id.emailEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Log.d("SignIn", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                // TODO: Navigate to the next screen or update UI
                                Toast.makeText(SignInActivity.this, "Sign in successful.",
                                        Toast.LENGTH_SHORT).show();
                                Intent homePageIntent = new Intent(SignInActivity.this, HomePageActivity.class);
                                startActivity(homePageIntent);
                            } else {
                                Log.w("SignIn", "signInWithEmail:failure", task.getException());
                                Toast.makeText(SignInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(SignInActivity.this, "Email and password cannot be empty.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
