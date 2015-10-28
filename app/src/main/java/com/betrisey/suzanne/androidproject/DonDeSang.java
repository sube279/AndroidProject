package com.betrisey.suzanne.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DonDeSang extends AppCompatActivity {
    private boolean utilOui;
    private boolean malOui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_de_sang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        utilOui = true;
        malOui = false;

        remplirTableau();

    }

    public void buttonUtilOui(View view){
        ImageButton imgBtnOui = (ImageButton)findViewById(R.id.btnUtilOui);
        ImageButton imgBtnNon = (ImageButton)findViewById(R.id.btnUtilNon);

        utilOui = true;

        changeImage(imgBtnOui, imgBtnNon, utilOui);
    }

    public void buttonUtilNon(View view){
        ImageButton imgBtnOui = (ImageButton)findViewById(R.id.btnUtilOui);
        ImageButton imgBtnNon = (ImageButton)findViewById(R.id.btnUtilNon);

        utilOui = false;

        changeImage(imgBtnOui, imgBtnNon, utilOui);
    }

    public void buttonMalOui(View view){
        ImageButton imgBtnOui = (ImageButton)findViewById(R.id.btnMalOui);
        ImageButton imgBtnNon = (ImageButton)findViewById(R.id.btnMalNon);

        malOui = true;

        changeImage(imgBtnOui, imgBtnNon, malOui);
    }

    public void buttonMalNon(View view){
        ImageButton imgBtnOui = (ImageButton)findViewById(R.id.btnMalOui);
        ImageButton imgBtnNon = (ImageButton)findViewById(R.id.btnMalNon);

        malOui = false;

        changeImage(imgBtnOui, imgBtnNon, malOui);
    }

    public void changeImage(ImageButton imgBtnOui, ImageButton imgBtnNon, boolean oui){
        if(oui == true){
            imgBtnOui.setImageDrawable(getResources().getDrawable(R.drawable.btn_oui_act));
            imgBtnNon.setImageDrawable(getResources().getDrawable(R.drawable.btn_non_des));
        }else{
            imgBtnOui.setImageDrawable(getResources().getDrawable(R.drawable.btn_oui_des));
            imgBtnNon.setImageDrawable(getResources().getDrawable(R.drawable.btn_non_act));
        }
    }

    public void remplirTableau(){
        ListView vueData;
        ListView vueInfo;
        String[] donneur = {"1445623", "Suzanne Bétrisey", "27.09.1990","o-", "01.10.2015", "Sion"};
        String[] info = {"Numéro:", "Donneur:", "", "Groupe:", "Date don:", "Stockage:"};

        TableLayout table=(TableLayout) findViewById(R.id.tableLayout);
        TableRow row;
        TextView tv1,tv2;

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.width=0;
        params.bottomMargin = 20;
        params.weight = 0.5f;

        for(int i = 0;i<info.length;i++) {
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
}
