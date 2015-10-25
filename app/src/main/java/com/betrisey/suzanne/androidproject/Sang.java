package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

public class Sang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sang);

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
        getMenuInflater().inflate(R.menu.menu_sang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_filtre_groupe_sanguin:
                return true;
            case R.id.action_filtre_statut:
                return true;
            case R.id.action_filtre_date:
                return true;
            case R.id.action_parametre:
                Intent intent = new Intent(this, Parametre.class);
                startActivity(intent);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void buttonAfficher(View view) {
        Intent intent = new Intent(this, AffichagePochetteSang.class);
        startActivity(intent);
    }
}
