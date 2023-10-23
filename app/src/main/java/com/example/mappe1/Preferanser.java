package com.example.mappe1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class Preferanser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferanser);

        Button regnestykker5 = findViewById(R.id.regnestykker5);
        Button regnestykker10 = findViewById(R.id.regnestykker10);
        Button regnestykker15 = findViewById(R.id.regnestykker15);
        Button tysk = findViewById(R.id.tysk);
        Button norsk = findViewById(R.id.norsk);

        //Bytte språk til tysk
        tysk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("de-DE");
                AppCompatDelegate.setApplicationLocales(appLocale);
                recreate();
            }
        });

        //Bytte språk til norsk
        norsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleListCompat appLocale = LocaleListCompat.forLanguageTags("no-NO");
                AppCompatDelegate.setApplicationLocales(appLocale);
            }
        });


        //Velge hvor mange regnestykker man vil ha
        regnestykker5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lagrePreferanser(5);
                startSpillMedValgteRegnestykker(5);
            }
        });

        regnestykker10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lagrePreferanser(10);
                startSpillMedValgteRegnestykker(10);
            }
        });

        regnestykker15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lagrePreferanser(15);
                startSpillMedValgteRegnestykker(15);
            }
        });
    }

    //metode for å lagre valgte preferanser i SharedPreferances
    private void lagrePreferanser(int count) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("valgt_preferanse", count);
        editor.apply();
    }

    //Metode for å starte "Start Spill" aktivitet
    private void startSpillMedValgteRegnestykker(int count) {
        Intent startSpillIntent = new Intent(this, StartSpill.class);
        startSpillIntent.putExtra("valgt_preferanse", count);
        startActivity(startSpillIntent);
    }
}


            
