package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button preferanserBtn = findViewById(R.id.preferanserBtn);
        Button omSpilletBtn = findViewById(R.id.omSpilletBtn);
        Button startBtn = findViewById(R.id.startBtn);

        //navigere til "Preferanser" aktivitet
        preferanserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Preferanser.class);
                startActivity(intent);
            }
        });

        //navigere til "Om spillet" aktivitet
        omSpilletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OmSpill.class);
                startActivity(intent);
            }
        });

        //navigere til "Start" aktivitet
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StartSpill.class);
                startActivity(intent);

            }
        });
    }
}