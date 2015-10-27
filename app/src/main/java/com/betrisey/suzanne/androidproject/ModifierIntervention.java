package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ModifierIntervention extends AppCompatActivity {
    private String date;
    private String quantite;
    private String groupe;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_intervention);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Get the message from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date = extras.getString("date");
            quantite = extras.getString("quantite");
            groupe = extras.getString("groupe");
            description = extras.getString("description");
        }

        // Create the text view
        TextView tw = (TextView) findViewById(R.id.textViewDate);
        tw.setText(date);
        tw = (TextView) findViewById(R.id.textViewQuantite);
        tw.setText(quantite);
        tw = (TextView) findViewById(R.id.textViewGroupe);
        tw.setText(groupe);
        tw = (TextView) findViewById(R.id.textViewDescription);
        tw.setText(description);
    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, AfficherIntervention.class);
        startActivity(intent);
    }

    public void buttonOk(View view) {
        Intent intent = new Intent(this, AfficherIntervention.class);
        startActivity(intent);
    }
}
