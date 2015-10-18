package com.betrisey.suzanne.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DonDeSang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_de_sang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
