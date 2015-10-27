package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

import java.lang.reflect.Field;

public class AfficherIntervention extends AppCompatActivity {

    private String date;
    private String quantite;
    private String groupe;
    private String description;

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

    public void buttonDelete(View view) {
        Intent intent = new Intent(this, Intervention.class);
        startActivity(intent);
    }

    public void buttonEdit(View view) {
        Intent intent = new Intent(this, ModifierIntervention.class);
        intent.putExtra("date", date);
        intent.putExtra("quantite", quantite);
        intent.putExtra("groupe", groupe);
        intent.putExtra("description", description);
        startActivity(intent);
    }
}
