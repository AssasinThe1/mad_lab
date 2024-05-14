package com.example.m2_first_experiment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomePageActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "1";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        Button logoutButton = findViewById(R.id.logoutButton);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // If user is signed in, show the user's email in the welcome message
            welcomeTextView.setText("Welcome, " + currentUser.getEmail() + "!");
        }

        logoutButton.setOnClickListener(view -> {
            mAuth.signOut();
            // After logging out, navigate back to the login screen
            Intent intent = new Intent(HomePageActivity.this, SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Call this to finish the current activity
        });
    }

    // Method to navigate to the Top Navigation Bar Activity
    public void navigateToTopNavBar(View view) {
        Intent intent = new Intent(this, TopNavBarActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the Bottom Navigation Bar Activity
    public void navigateToBottomNavBar(View view) {
        Intent intent = new Intent(this, BottomNavBarActivity.class);
        startActivity(intent);
    }

    // Placeholder method for sending a notification
    public void sendNotification(View view) {
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.notification_icon) // replace with your own drawable resource
                .setContentTitle("Notification Title")
                .setContentText("This is a sample notification message.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    // Placeholder method for the second notification button
    public void logout(View view) {
        // Logic for what should happen when "Logout" button is clicked
        // This method can be used to log out the user
        mAuth.signOut();

    }
}
