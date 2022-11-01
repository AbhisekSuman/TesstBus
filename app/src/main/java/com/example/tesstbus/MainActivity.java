 package com.example.tesstbus;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
    Button bus1;
    Button bus2;
    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent foregroundintent = new Intent(this,Foreground.class);
        Intent Locationintent  = new Intent(this,Bus1Map.class);


//        if (Build.VERSION.SDK_INT >= 0){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(foregroundintent);
        }
        else {
            Toast.makeText(this, "12", Toast.LENGTH_SHORT).show();
            stopService(foregroundintent);
        }
//        }

        bus1 = findViewById(R.id.bus1);
        bus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 =new Intent(MainActivity.this,Bus1Map.class);
                startActivity(i1);
            }
        });


        bus2 = findViewById(R.id.bus2);
        bus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 =new Intent(MainActivity.this,Bus2Map.class);
                startActivity(i2);
            }
        });

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(foregroundintent);
                stopService(Locationintent);
            }
        });
    }
}