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

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import db.adapter.InterventionDataSource;
import db.adapter.SangDataSource;
import db.object.CIntervention;
import db.object.CSang;

public class Sang extends AppCompatActivity {

    SangAdapter liste;
    String region;
    String filtre = "groupe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sang);

        //ajouter icone a la barre d'action
        getSupportActionBar().setHomeButtonEnabled(true);

        // récupère le filtre passé dans l'intent (choix fait par le menu)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            filtre = extras.getString("filtre");
        }

        //afficher le bon titre de filtre
        setFiltreTitle();

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

        afficher();

    }

    private void setFiltreTitle(){
        TextView text = (TextView) findViewById(R.id.textView_filtre);

        switch(filtre){
            case("groupe"):
                text.setText(getResources().getString(R.string.filtreGroupe));
                break;
            case("statut"):
                text.setText(getResources().getString(R.string.filtreStatut));
                break;
            case("date"):
                text.setText(getResources().getString(R.string.filtreDate));
                break;
        }
    }

    public void afficher(){

        try {
            //Récupère la liste des régions dans les ressources: spinners.xml
            List<String> listRegions = Arrays.asList(getResources().getStringArray(R.array.region));

            for(int i = 0; i < listRegions.size(); i++){
                region = listRegions.get(i);
                afficherParRegion(region);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void afficherParRegion(String region) throws ParseException {


        final ArrayList<CSang> listeSang = new ArrayList<CSang>();

        //listview
        liste = new SangAdapter(this.getApplicationContext());

        //Ne pas afficher si une région n'a pas de pochette
        if(liste.getCount()!=0) {
            //Pour récupérer toutes les pochettes de chaque région
            for (int i = 0; i < liste.getCount(); i++) {
                listeSang.add(liste.getSang(i));
            }

            LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(25, 25, 25, 25);

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

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CSang s = listeSang.get(position);
                    sendSang(s.getId());
                }
            });
        }
    }

    public void sendSang(int id){
        Intent intent = new Intent(this, AffichagePochetteSang.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_filtre_groupe_sanguin:
                Intent intent = new Intent(this, Sang.class);
                filtre = "groupe";
                intent.putExtra("filtre", filtre);
                startActivity(intent);
                return true;
            case R.id.action_filtre_statut:
                intent = new Intent(this, Sang.class);
                filtre = "statut";
                intent.putExtra("filtre", filtre);
                startActivity(intent);
                return true;
            case R.id.action_filtre_date:
                intent = new Intent(this, Sang.class);
                filtre = "date";
                intent.putExtra("filtre", filtre);
                startActivity(intent);
                return true;
            case R.id.action_parametre:
                intent = new Intent(this, Parametre.class);
                startActivity(intent);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void buttonAfficher(View view) {
        Intent intent = new Intent(this, AffichagePochetteSang.class);
        startActivity(intent);
    }

    public class SangAdapter extends BaseAdapter {

        SangDataSource sa;
        List<CSang> liste;

        public SangAdapter (Context context) throws ParseException {
            sa = new SangDataSource(context);
            liste = getDataForListView();
        }


        public List<CSang> getDataForListView() throws ParseException {
            List<CSang> listSang;
            listSang = sa.getAllSangsByGroupe(region);

            switch(filtre){
                case("groupe"):
                    listSang = sa.getAllSangsByGroupe(region);
                    break;
                case("statut"):
                    listSang = sa.getAllSangsByStatut(region);
                    break;
                case("date"):
                    listSang = sa.getAllSangsByDate(region);
                    break;
            }

            return listSang;

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
                LayoutInflater inflater = (LayoutInflater) Sang.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.liste_sang, parent,false);
            }

            TextView statut = (TextView)convertView.findViewById(R.id.textViewStatut);
            TextView peremption = (TextView)convertView.findViewById(R.id.textViewPeremption);
            ImageView groupe = (ImageView) convertView.findViewById(R.id.imageViewGroupe);

            CSang s = liste.get(position);

            statut.setText(s.getStatut());
            try {
                peremption.setText(changeIntoString(s.getPeremption()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(s.getGroupe().equals("A+"))
            groupe.setImageDrawable(getResources().getDrawable(R.drawable.a_plus));
            if(s.getGroupe().equals("A-"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.a_moins));
            if(s.getGroupe().equals("B+"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.b_plus));
            if(s.getGroupe().equals("B-"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.b_moins));
            if(s.getGroupe().equals("AB+"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.ab_plus));
            if(s.getGroupe().equals("AB-"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.ab_moins));
            if(s.getGroupe().equals("O+"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.o_plus));
            if(s.getGroupe().equals("O-"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.o_moins));



            return convertView;
        }

        public CSang getSang(int position)
        {
            return liste.get(position);
        }
    }

    public String changeIntoString(Date d) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.FRENCH);
        String s = df.format(d);
        return s;
    }

    public Date changeIntoDate(String s) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.FRENCH);
        Date date = format.parse(s);
        return date;
    }


}
