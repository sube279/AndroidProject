package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.Calendar;

public class NouveauDonneur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_donneur);

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

        //spinner jours
        String[] jours={"1", "2", "3", "4","5","6","7","8","9","10","11","12","13","14","15"
        ,"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter<String> stringArrayAdapter=
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,jours);
        Spinner spinner =
                (Spinner)  findViewById(R.id.spinnerJour);
        spinner.setAdapter(stringArrayAdapter);

        String[] mois={"janvier", "février", "mars", "avril","mai","juin","juillet","août","septembre",
                "octobre","novembre","décembre"};
        ArrayAdapter<String> stringArrayAdapter2=
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,mois);
        Spinner spinner2 =
                (Spinner)  findViewById(R.id.spinnerMois);
        spinner2.setAdapter(stringArrayAdapter2);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        Integer[] annees = new Integer[101];
        for(int i = 0; i<=100; i++){
            annees[i] = year-i;
        }

        ArrayAdapter<Integer> stringArrayAdapter4=
                new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,annees);
        Spinner spinner4 =
                (Spinner)  findViewById(R.id.spinnerAnnee);
        spinner4.setAdapter(stringArrayAdapter4);


        String[] groupe={"A+", "A-", "B+", "B-","AB+","AB-","O+","O-"};
        ArrayAdapter<String> stringArrayAdapter3=
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,groupe);
        Spinner spinner3 =
                (Spinner)  findViewById(R.id.spinnerGroupes);
        spinner3.setAdapter(stringArrayAdapter3);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nouveau_donneur, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, Donneur.class);
        startActivity(intent);
    }

    public void buttonOk(View view) {
        Intent intent = new Intent(this, Donneur.class);
        startActivity(intent);
    }
}
