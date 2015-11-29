package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;
import java.text.ParseException;

import db.adapter.SangDataSource;
import db.object.CSang;

public class ChoixStatut extends AppCompatActivity {

    CSang sang;
    SangDataSource sa;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_statut);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //pour le menu (marche pas sur toutes les versions d'android sinon)
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }

        // Get the message from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        sa = new SangDataSource(getApplicationContext());
        try {
            sang = sa.getSangById(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, AffichagePochetteSang.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void buttonOk(View view) {
        Intent intent = new Intent(this, AffichagePochetteSang.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
