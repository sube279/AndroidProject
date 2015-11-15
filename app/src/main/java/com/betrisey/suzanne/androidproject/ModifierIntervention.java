package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import db.adapter.InterventionDataSource;
import db.object.CIntervention;

public class ModifierIntervention extends AppCompatActivity {
    private int id;
    public CIntervention i;
    public InterventionDataSource ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_intervention);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Get the message from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        ia = new InterventionDataSource(getApplicationContext());
        i = ia.getInterventiononById(id);

        // Create the text view
        EditText tw = (EditText) findViewById(R.id.textViewDate);
        tw.setText(i.getDate());
        tw = (EditText) findViewById(R.id.textViewQuantite);
        tw.setText(String.valueOf(i.getQuantite()));
        tw = (EditText) findViewById(R.id.textViewGroupe);
        tw.setText(i.getGroupe());
        tw = (EditText) findViewById(R.id.textViewDescription);
        tw.setText(i.getDescription());
    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, AfficherIntervention.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void buttonOk(View view) {

        EditText et = (EditText) findViewById (R.id.textViewDate);
        i.setDate(et.getText().toString());
        et = (EditText) findViewById (R.id.textViewDescription);
        i.setDescription(et.getText().toString());
        et = (EditText) findViewById (R.id.textViewQuantite);
        i.setQuantite(Integer.parseInt(et.getText().toString()));
        et = (EditText) findViewById (R.id.textViewGroupe);
        i.setGroupe(et.getText().toString());

        ia.updateIntervention(i);

        Intent intent = new Intent(this, AfficherIntervention.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
