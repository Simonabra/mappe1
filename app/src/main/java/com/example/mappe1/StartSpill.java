package com.example.mappe1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import androidx.appcompat.app.AlertDialog;

public class StartSpill extends AppCompatActivity {
    private List<String> regnestykkerListe;
    private List<String> riktigeSvarListe;
    private List<Integer> visteRegnestykker = new ArrayList<>();
    private String[] regnestykkerAddisjon;
    private String[] faktiskeSvar;

    //implementerer indeksvariabel
    private int currentIndex = 0;
    private int valgtPreferanse;
    private int antallRiktigeSvar = 0;

    EditText input;
    StringBuilder userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);

        input = (EditText) findViewById(R.id.inputSvar);
        userInput = new StringBuilder();

        valgtPreferanse = getIntent().getIntExtra("valgt_preferanse", 5);

        //henter array regnestykker og riktige svar
        regnestykkerAddisjon = getResources().getStringArray(R.array.regnestykker);
        faktiskeSvar = getResources().getStringArray(R.array.riktigeSvar);

        //konventerer til en list
        regnestykkerListe = new ArrayList<>(Arrays.asList(regnestykkerAddisjon));
        riktigeSvarListe = new ArrayList<>(Arrays.asList(faktiskeSvar));

        visNeste();

        Button visNesteBtn = findViewById(R.id.visNesteBtn);
        visNesteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visNeste();
            }
        });
        //håndterer alle knappene som skal vises etter bruker har tastet in svar ved å trykke på knappene
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("1");
                input.setText(userInput.toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("2");
                input.setText(userInput.toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("3");
                input.setText(userInput.toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("4");
                input.setText(userInput.toString());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("5");
                input.setText(userInput.toString());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("6");
                input.setText(userInput.toString());
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("7");
                input.setText(userInput.toString());
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("8");
                input.setText(userInput.toString());
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("9");
                input.setText(userInput.toString());
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                userInput.append("0");
                input.setText(userInput.toString());
            }
        });

        Button slettBtn = findViewById(R.id.slettBtn);
        slettBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userInput.length() > 0){
                    userInput.deleteCharAt(userInput.length() - 1);
                    input.setText(userInput.toString());
                }
            }
        });

        Button enterBtn = findViewById(R.id.enterBtn);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sjekkSvar();
            }
        });

    }
    //Nytt regnstykke blir hentet fra listen ved hjelp av indeksen, indeksen økes for å få neste regnstykke
    private void visNeste() {
        if (currentIndex < valgtPreferanse && currentIndex < regnestykkerAddisjon.length) {
                String regnestykke = regnestykkerAddisjon[currentIndex];
                String riktigeSvar = faktiskeSvar[currentIndex];

                TextView nesteRegnestykket = findViewById(R.id.nesteRegnestykket);
                nesteRegnestykket.setText(regnestykke);
                currentIndex++;
                EditText userInput = findViewById(R.id.inputSvar);
                userInput.setText("");

            } else {
                visSluttMelding();
            }
        }

        //sørge for at samme regnestykke ikke skal komme opp flere ganger i en sesjon
        private int hentUniktR () {
            Random random = new Random();
            int indeks = -1;
            do {
                if (regnestykkerListe.isEmpty()) {
                    break;
                }
                indeks = random.nextInt(regnestykkerListe.size());
            } while (visteRegnestykker.contains(indeks));
            visteRegnestykker.add(indeks);
            return indeks;
        }

        private void visSluttMelding () {
            TextView nesteRegnestykket = findViewById(R.id.nesteRegnestykket);
            nesteRegnestykket.setText("Spillet er ferdig!");
        }

        private void sjekkSvar () {
            String brukerSvar = input.getText().toString().trim();
            String riktigSvar = riktigeSvarListe.get(currentIndex - 1);

            Log.d("debug", "Bruker svar:" + brukerSvar);
            Log.d("debug", "riktig svar:" + riktigSvar);

            if (brukerSvar.equals(riktigSvar)) {
                antallRiktigeSvar++;
                Toast.makeText(this, "Riktig! Bra jobbet!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Feil. Prøv igjen!", Toast.LENGTH_SHORT).show();

            }
        }
    }



