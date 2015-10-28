package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AfficherDonneur extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_donneur);

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

        ListView vueData;
        ListView vueInfo;
        String[] donneur = {"féminin", "12.04.1989", "Rue de la Forge 15", "3966", "Chalais", "Sierre","+41795436584", "A+", "1", "15.06.2016"};
        String[] info = {"Sexe:", "Naissance:", "Adresse:", "Npa:", "Lieu:", "Région:", "Téléphone:", "Groupe:", "Dons possibles:", "Disponible:"};

        //Tableau
        TableLayout table=(TableLayout) findViewById(R.id.tableLayout);
        TableRow row;
        TextView tv1,tv2;

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.width=0;
        params.weight = 0.5f;

        for(int i = 0;i<info.length;i++){
            row = new TableRow(this); //Création d'une nouvelle ligne
            row.setWeightSum(1f);

            tv1 = new TextView(this); // création cellule
            tv1.setText(info[i]); // ajout du texte
            tv1.setLayoutParams(params);

            tv2 = new TextView(this); // création cellule
            tv2.setText(donneur[i]); // ajout du texte;
            tv2.setLayoutParams(params);


            //ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);

            //ajout de la ligne au tableau
            table.addView(row);

        }


    }


    public void buttonDonDeSang(View view) {
        Intent intent = new Intent(this, DonDeSang.class);
        startActivity(intent);
    }

    public void buttonEdit(View view) {
        Intent intent = new Intent(this, ModifierDonneur.class);
        startActivity(intent);
    }
}
