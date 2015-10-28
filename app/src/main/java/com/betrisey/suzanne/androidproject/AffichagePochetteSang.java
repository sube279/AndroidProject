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

public class AffichagePochetteSang extends AppCompatActivity {

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

        TextView textView = (TextView)findViewById(R.id.textNum);
        textView.setText("N° 1445623");
        remplirTableau();
    }

    public void remplirTableau(){
        ListView vueData;
        ListView vueInfo;
        String[] data = {"0054893", "01.10.2015", "09.11.2015", "Sion", "O-", "Commandé", "12.10.2015"};
        String[] info = {"ID Donneur:", "Date don:", "Péremption:", "Région:", "Groupe:", "Statut:", "Intervention:"};

        //Tableau
        TableLayout table=(TableLayout) findViewById(R.id.tableLayout);
        TableRow row;
        TextView tv1,tv2;

        for(int i = 0;i<info.length;i++){
            row = new TableRow(this); //Création d'une nouvelle ligne

            tv1 = new TextView(this); // création cellule
            tv1.setText(info[i]); // ajout du texte
            tv1.setWidth(380);
            tv1.setHeight(100);

            tv2 = new TextView(this); // création cellule
            tv2.setText(data[i]); // ajout du texte;
            tv1.setHeight(100);

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
}
