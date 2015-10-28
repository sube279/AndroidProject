package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ModifierDonneur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_donneur);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        remplirTableau();
    }

    public void remplirTableau(){

        ListView vueData;
        ListView vueInfo;
        String[] donneur = {"Nancy","Zappellaz","féminin", "12.04.1989", "Rue de la Forge 15", "3966", "Chalais", "Sierre","+41795436584", "A+", "1", "15.06.2016"};
        String[] info = {"Prénom:", "Nom:", "Sexe:", "Naissance:", "Adresse:", "Npa:", "Lieu:", "Région:", "Téléphone:", "Groupe:", "Dons possibles:", "Disponible:"};

        //Tableau
        TableLayout table=(TableLayout) findViewById(R.id.tableLayout);
        TableRow row;
        TextView tv1,tv2;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        for(int i = 0;i<info.length;i++){
            row = new TableRow(this); //Création d'une nouvelle ligne

            tv1 = new TextView(this); // création cellule
            tv1.setText(info[i]); // ajout du texte
            tv1.setWidth(380);
            tv1.setHeight(120);

            tv2 = new EditText(this); // création cellule
            tv2.setText(donneur[i]); // ajout du texte;
            tv2.setHeight(120);

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
