package com.betrisey.suzanne.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AjouterIntervention extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_intervention);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
