package com.betrisey.suzanne.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import java.util.Locale;

public class Parametre extends AppCompatActivity {

    Activity activity;
    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        //ajouter icone a la barre d'action
        getSupportActionBar().setHomeButtonEnabled(true);

        activity = this;

        Resources res = getResources();
        Configuration conf = res.getConfiguration();

        String lang = conf.locale.getLanguage();
        lang = lang.substring(0,2);



        RadioButton radioButton;

        if(lang.equals("de"))
        {
            radioButton = (RadioButton) findViewById(R.id.radioAllemand);
            radioButton.setChecked(true);
        }
        else
        {
            radioButton = (RadioButton) findViewById(R.id.radioFrancais);
            radioButton.setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parametre, menu);
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

    public void buttonOk (View view){

        RadioButton rb;

        rb = (RadioButton) findViewById(R.id.radioFrancais);

        if(rb.isChecked())
        {
            setLocale("fr");
        }
        else
        {
            setLocale("de");
        }

        activity.finish();

    }


    public void buttonAnnuler (View view){
        activity.finish();
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}
