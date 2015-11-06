package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModifierDonneur extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_donneur);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        if (savedInstanceState == null) {
            ArrayList<String> donneur = new ArrayList<String>();
            donneur.add("Nancy");
            donneur.add("Zappellaz");
            donneur.add("Féminin");
            donneur.add("12.01.1989");
            donneur.add("Rue du pont 2");
            donneur.add("3960");
            donneur.add("Sierre");
            donneur.add("Sierre");
            donneur.add("+41795642538");
            donneur.add("A+");
            donneur.add("1");
            donneur.add("15.06.2015");
            remplirTableau(donneur);
        } else {
            ArrayList<String> currentData = savedInstanceState.getStringArrayList("liste");
            remplirTableau(currentData);

        }
    }

    public void onSaveInstanceState(Bundle outState){
        ArrayList<String> donnees = new ArrayList<String>();
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);

        for(int i= 0; i<12;i++){
            TextView textView = (TextView)findViewById(i);
            donnees.add(textView.getText().toString());
        }

        outState.putStringArrayList("liste", donnees);
        super.onSaveInstanceState(outState);
    }


    public void remplirTableau(ArrayList<String> donneur){

        ListView vueData;
        ListView vueInfo;
        //String[] donneur = {"Nancy","Zappellaz","féminin", "12.04.1989", "Rue de la Forge 15", "3966", "Chalais", "Sierre","+41795436584", "A+", "1", "15.06.2016"};
        String[] info = {"Prénom:", "Nom:", "Sexe:", "Naissance:", "Adresse:", "Npa:", "Lieu:", "Région:", "Téléphone:", "Groupe:", "Dons possibles:", "Disponible:"};

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

            tv2 = new EditText(this); // création cellule
            tv2.setId(i);
            tv2.setText(donneur.get(i)); // ajout du texte;
            tv2.setLayoutParams(params);

            //ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);

            //ajout de la ligne au tableau
            table.addView(row);

        }
    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, AfficherDonneur.class);
        startActivity(intent);
    }

    public void buttonOk(View view) {
        Intent intent = new Intent(this, AfficherDonneur.class);
        startActivity(intent);
    }

}
