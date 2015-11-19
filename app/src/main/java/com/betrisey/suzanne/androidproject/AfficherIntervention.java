package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.ParseException;

import db.adapter.InterventionDataSource;
import db.object.CIntervention;

public class AfficherIntervention extends AppCompatActivity {

    private int id;
    InterventionDataSource ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_intervention);

        //ajouter icone a la barre d'action
        getSupportActionBar().setHomeButtonEnabled(true);


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

        ia = new InterventionDataSource(getApplicationContext());
        CIntervention i = null;
        try {
            i = ia.getInterventiononById(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create the text view
        TextView tw = (TextView) findViewById(R.id.textViewDate);
        tw.setText(String.valueOf(i.getDate()));
        tw = (TextView) findViewById(R.id.textViewQuantite);
        tw.setText(String.valueOf(i.getQuantite()));
        tw = (TextView) findViewById(R.id.textViewGroupe);
        tw.setText(i.getGroupe());
        tw = (TextView) findViewById(R.id.textViewDescription);
        tw.setText(i.getDescription());



    }

    public void buttonDelete(View view) {
        Intent intent = new Intent(this, Intervention.class);
        ia.deleteIntervention(id);
        startActivity(intent);
    }

    public void buttonEdit(View view) {
        Intent intent = new Intent(this, ModifierIntervention.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
