package com.betrisey.suzanne.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class AfficherDonneur extends AppCompatActivity {
    ListView vueData;
    ListView vueInfo;
    private String[] donneur = {"féminin", "12.04.1989", "Rue de la Forge 15", "3966", "Chalais", "Sierre","+41795436584", "A+", "1", "15.06.2016"};
    private String[] info = {"Sexe:", "Naissance:", "Adresse:", "Npa:", "Lieu:", "Région:", "Téléphone:", "Groupe:", "Dons possibles:", "Disponible:"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_donneur);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
/*
        ArrayAdapter<String> adapterInfo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
        vueInfo = (ListView) findViewById(R.id.listInfo);
        vueInfo.setAdapter(adapterInfo);


        donneur.add("féminin");
        donneur.add("12.04.1989");
        donneur.add("Rue de la Forge 15");
        donneur.add("3966");
        donneur.add("Chalais");
        donneur.add("Sierre");
        donneur.add("+4179561235");
        donneur.add("A+");
        donneur.add("1");
        donneur.add("15.06.2016");


        ArrayAdapter<String> adapterData = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, donneur);
        vueData = (ListView) findViewById(R.id.listDonneur);
        vueData.setAdapter(adapterData);*/

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
            tv2.setText(donneur[i]); // ajout du texte;
            tv1.setHeight(100);

            //ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);

            //ajout de la ligne au tableau
            table.addView(row);

        }


    }
}
