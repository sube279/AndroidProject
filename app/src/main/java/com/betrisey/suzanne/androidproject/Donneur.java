package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class Donneur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donneur);

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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donneur, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_nouveau_donneur:
                Intent intent = new Intent(this, NouveauDonneur.class);
                startActivity(intent);
                return true;
            case R.id.action_filtre_disponibilite:
                return true;
            case R.id.action_filtre_nom:
                return true;
            case R.id.action_filtre_prenom:
                return true;
            case R.id.action_filtre_naissance:
                return true;
            case R.id.action_parametre:
                Intent intent2 = new Intent(this, Parametre.class);
                startActivity(intent2);
        }
        return (super.onOptionsItemSelected(item));


    }




}
