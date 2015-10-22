package com.betrisey.suzanne.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AfficherDonneur extends AppCompatActivity {
    ListView vueData;
    ListView vueInfo;
    private ArrayList<String> donneur = new ArrayList<String>();
    private String[] info = {"Sexe:", "Naissance:", "Adresse:", "Npa:", "Lieu:", "Région:", "Téléphone:", "Groupe:", "Dons possibles:", "Disponible:"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_donneur);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
        vueData.setAdapter(adapterData);


    }
}
