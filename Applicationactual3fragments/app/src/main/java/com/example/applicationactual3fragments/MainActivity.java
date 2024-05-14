package com.example.applicationactual3fragments;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.applicationactual3fragments.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment to the activity's container layout
            FirstFragment firstFragment = new FirstFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment)
                    .commit();
        }
    }

    public void goToSecondFragment(Bundle bundle) {
        // Create fragment and give it an argument for the selected article
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, secondFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void goBackToFirstFragment() {
        // This will take the user back to the FirstFragment
        getSupportFragmentManager().popBackStack();
    }
}
