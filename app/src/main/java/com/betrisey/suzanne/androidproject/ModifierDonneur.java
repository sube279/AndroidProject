package com.betrisey.suzanne.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ModifierDonneur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_donneur);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
