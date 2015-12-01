package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import db.adapter.DonneurDataSource;
import db.adapter.InterventionDataSource;
import db.adapter.SangDataSource;
import db.object.CIntervention;
import db.object.CSang;

public class Home extends AppCompatActivity {

    List<CSang> listSang;
    List<CIntervention> listIntervention;
    SangDataSource sa;
    InterventionDataSource ia;
    Date now;

    // Ouvre la fenetre donneur
    public void openDonneur(View view) {
        Intent intent = new Intent(this, Donneur.class);
        startActivity(intent);
    }

    // Ouvre la fenetre intervention
    public void openIntervention(View view) {
        Intent intent = new Intent(this, Intervention.class);
        startActivity(intent);
    }

    // Ouvre la fenetre banque du sang
    public void openSang(View view) {
        Intent intent = new Intent(this, Sang.class);
        startActivity(intent);
    }

    // Ouvre la fenetre parametre
    public void openParametre(View view) {
        Intent intent = new Intent(this, Parametre.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Date now = new Date();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(day==1 && month==0){
            DonneurDataSource da = new DonneurDataSource(getApplicationContext());
            try {
                da.initializeDons();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        sa = new SangDataSource(this);
        ia = new InterventionDataSource(this);

        try {
            listSang = sa.getAllSangs();
            listIntervention = ia.getAllInterventions();
            now = changeIntoDate(year + "." + month + "." + day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int k=0;k<listIntervention.size(); k++)
        {
            if(listIntervention.get(k).getDate().before(now)){
                try {
                    List<CSang> sangs = sa.getAllSangsByIntervention(listIntervention.get(k).getId());
                    for(int j=0; j<sangs.size(); j++)
                    {
                        sangs.get(j).setStatut("utilisé");
                        sa.updateSang(sangs.get(j));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        for(int i=0;i<listSang.size(); i++)
        {
            if(listSang.get(i).getPeremption().before(now)){
                try {
                    listSang.get(i).setStatut("inutilisable");
                    sa.updateSang(listSang.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public Date changeIntoDate(String s) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.FRENCH);
        Date date = format.parse(s);
        return date;
    }
}
