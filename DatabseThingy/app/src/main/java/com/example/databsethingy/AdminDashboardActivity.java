package com.example.databsethingy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    private EditText etUsername, etNewPasswordChange, etOldUsername, etNewUsernameChange,etNewName, etNewUsername, etNewPhone, etNewPassword;;
    private Button btnCreateUser, btnChangePassword, btnChangeUsername;
    private UserDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);

        etNewName = findViewById(R.id.et_new_name);
        etNewUsername = findViewById(R.id.et_new_username);
        etNewPhone = findViewById(R.id.et_new_phone);
        etNewPassword = findViewById(R.id.et_new_password);
        etUsername = findViewById(R.id.et_username);
        etNewPasswordChange = findViewById(R.id.et_new_password_change);
        etOldUsername = findViewById(R.id.et_old_username);
        etNewUsernameChange = findViewById(R.id.et_new_username_change);

        btnCreateUser = findViewById(R.id.btn_create_user);
        btnChangePassword = findViewById(R.id.btn_change_password);
        btnChangeUsername = findViewById(R.id.btn_change_username);

        databaseHelper = new UserDatabaseHelper(this);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etNewName.getText().toString().trim();
                String newUsername = etNewUsername.getText().toString().trim();
                String newPhone = etNewPhone.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();

                databaseHelper.createUser(newName, newUsername, newPhone, newPassword);
                Toast.makeText(AdminDashboardActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String newPassword = etNewPasswordChange.getText().toString().trim();

                databaseHelper.changePassword(username, newPassword);
                Toast.makeText(AdminDashboardActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldUsername = etOldUsername.getText().toString().trim();
                String newUsername = etNewUsernameChange.getText().toString().trim();

                databaseHelper.changeUsername(oldUsername, newUsername);
                Toast.makeText(AdminDashboardActivity.this, "Username changed successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}