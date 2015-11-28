package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import db.adapter.SangDataSource;
import db.object.CSang;

public class AffichagePochetteSang extends AppCompatActivity {

   CSang sang;
    SangDataSource sa;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_pochette_sang);

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

        sa = new SangDataSource(getApplicationContext());
        try {
            sang = sa.getSangById(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView)findViewById(R.id.textNum);
        textView.setText("N° " + sang.getId());
        try {
            remplirTableau();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void remplirTableau() throws ParseException {
        ListView vueData;
        ListView vueInfo;
        String[] data = {String.valueOf(sang.getDonneur()), changeIntoString(sang.getDateDon()), changeIntoString(sang.getPeremption()), sang.getRegion(), sang.getGroupe(), sang.getStatut()};
        String[] info = {"ID Donneur:", "Date don:", "Péremption:", "Région:", "Groupe:", "Statut:"};

        //Tableau
        TableLayout table=(TableLayout) findViewById(R.id.tableLayout);
        TableRow row;
        TextView tv1,tv2;

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.width=0;
        params.bottomMargin = 20;
        params.weight = 0.5f;

        for(int i = 0;i<info.length;i++){
            row = new TableRow(this); //Création d'une nouvelle ligne
            row.setWeightSum(1f);

            tv1 = new TextView(this); // création cellule
            tv1.setText(info[i]); // ajout du texte
            tv1.setLayoutParams(params);

            tv2 = new TextView(this); // création cellule
            tv2.setText(data[i]); // ajout du texte;
            tv2.setLayoutParams(params);

            //ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);

            //ajout de la ligne au tableau
            table.addView(row);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pochette, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_statut:
                Intent intent2 = new Intent(this, ChoixStatut.class);
                startActivity(intent2);
                return true;
            case R.id.action_transfert:
                Intent intent = new Intent(this, Transfert.class);
                startActivity(intent);
                return true;
            }
        return (super.onOptionsItemSelected(item));
    }

    public String changeIntoString(Date d) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.FRENCH);
        String s = df.format(d);
        return s;
    }
}
