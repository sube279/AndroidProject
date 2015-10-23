package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ModifierDonneur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_donneur);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, AfficherDonneur.class);
        startActivity(intent);
    }

    public void buttonOk(View view) {
        Intent intent = new Intent(this, AfficherDonneur.class);
        startActivity(intent);
    }

}
