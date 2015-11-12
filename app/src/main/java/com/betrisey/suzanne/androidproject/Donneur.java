package com.betrisey.suzanne.androidproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import db.object.CDonneur;

public class Donneur extends AppCompatActivity {

    DonneurAdapter liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donneur);

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


        afficherParRegion("Sierre");

    }


    public void afficherParRegion(String region){

        //listview
        liste = new DonneurAdapter();

        LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 25, 25, 25);

        //Crée texteView Region
        TextView tv = new TextView(this);
        tv.setLayoutParams(params);
        tv.setPadding(15, 0, 0, 0);
        tv.setTextSize(20);
        tv.setTextColor(getResources().getColor(R.color.black));

        tv.setTypeface(null, Typeface.BOLD);
        tv.setText(region);

        ll.addView(tv);
        ListView lv = new ListView(this);
        lv.setAdapter(liste);

        ll.addView(lv);

        //Click sur la listView
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), AfficherDonneur.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donneur, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_nouveau_donneur:
                Intent intent = new Intent(this, NouveauDonneur.class);
                startActivity(intent);
                return true;
            case R.id.action_filtre_disponibilite:
                TextView text1 = (TextView) findViewById(R.id.textView_filtre);
                text1.setText("Filtre: disponibilité");
                return true;
            case R.id.action_filtre_nom:
                TextView text2 = (TextView) findViewById(R.id.textView_filtre);
                text2.setText("Filtre: nom");
                return true;
            case R.id.action_filtre_prenom:
                TextView text3 = (TextView) findViewById(R.id.textView_filtre);
                text3.setText("Filtre: prénom");
                return true;
            case R.id.action_filtre_naissance:
                TextView text4 = (TextView) findViewById(R.id.textView_filtre);
                text4.setText("Filtre: naissance");
                return true;
            case R.id.action_parametre:
                Intent intent2 = new Intent(this, Parametre.class);
                startActivity(intent2);
        }
        return (super.onOptionsItemSelected(item));


    }

    public void buttonAfficher(View view) {
        Intent intent = new Intent(this, AfficherDonneur.class);
        startActivity(intent);
    }

    public class DonneurAdapter extends BaseAdapter {

        List<CDonneur> liste = getDataForListView();

        public List<CDonneur> getDataForListView() {
            List<CDonneur> listDonneur = new ArrayList<CDonneur>();

            CDonneur d1 = new CDonneur("Michel", "Bulot", "Masculin", "15.02.1975", "Rue du chapito 8", "3966", "Chalais", "Sierre", "0797512365", "O+", 2, true);
            CDonneur d2 = new CDonneur("Myriame", "Zola", "Féminin", "28.05.1988", "Rue du Mimo 15", "3960", "Sierre", "Sierre", "0797512365", "A+", 3, false);
            CDonneur d3 = new CDonneur("Julio", "Delapo", "Masculin", "04.02.1942", "Route de Bubu 20", "3960", "Sierre", "Sierre", "0782165425", "B-", 1, true);
            CDonneur d4 = new CDonneur("Julia", "Delapo", "Féminin", "12.06.1940", "Route de Bubu 20", "3960", "Sierre", "Sierre", "0786425129", "O-", 0, false);
            CDonneur d5 = new CDonneur("Chapipo", "Bloubi", "Masculin", "03.03.1938", "Rue du Poulpe 6", "3966", "Réchy", "Sierre", "0775162308", "A-", 2, true);

            listDonneur.add(d1);
            listDonneur.add(d2);
            listDonneur.add(d3);
            listDonneur.add(d4);
            listDonneur.add(d5);


            return listDonneur;

        }

        @Override
        public int getCount() {
            return liste.size();
        }

        @Override
        public Object getItem(int position) {
            return liste.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) Donneur.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.liste_donneur, parent,false);
            }

            ImageView dispo = (ImageView) convertView.findViewById(R.id.imageViewDispo);
            TextView nom = (TextView)convertView.findViewById(R.id.textViewNom);
            TextView naissance = (TextView)convertView.findViewById(R.id.textViewNaissance);

            CDonneur i = liste.get(position);

            //Test si le donneur est dispo pour l'affichage de l'image
            if(i.getDisponibilite() == true){
                dispo.setImageDrawable(getResources().getDrawable(R.drawable.dispo_true));
            }else{
                dispo.setImageDrawable(getResources().getDrawable(R.drawable.dispo_false));
            }

            nom.setText(i.getNom() + " " +i.getPrenom());
            naissance.setText(i.getNaissance());

            return convertView;
        }
    }


}
