
package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(TAG, " method onCreate: ");
        System.out.println("onCreate");
        Log.i(TAG,"onCreate");
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
//        Log.d(TAG, " method onStart: ");
        System.out.println("onStart");
        Log.i(TAG,"onStart");
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Log.d(TAG, " method onRestart: ");
        System.out.println("onRestart");
        Log.i(TAG,"Restart");
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onPause() {
        super.onPause();
//        Log.d(TAG, " method onPause: ");
        System.out.println("onPause");
        Log.i(TAG,"onPause");
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.d(TAG, " method onStop: ");
        System.out.println("onStop");
        Log.i(TAG,"onStop");
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d(TAG, " method onResume: ");
        Log.i(TAG,"onResume");
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, " method onDestroy: ");
        Log.i(TAG,"onDestroy");
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();

    }
}