package com.betrisey.suzanne.androidproject;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import db.adapter.InterventionDataSource;
import db.adapter.SangDataSource;
import db.object.CIntervention;
import db.object.CSang;

public class Sang extends AppCompatActivity {

    SangAdapter liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sang);

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

        SangDataSource sa = new SangDataSource(getApplicationContext());

        //listview
        try {
            liste = new SangAdapter(this.getApplicationContext());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(liste);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CSang s = liste.getSang(position);
                sendSang(s.getId());
            }
        });


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
                TextView text = (TextView) findViewById(R.id.textView_filtre);
                text.setText("Filtre: groupe sanguin");
                return true;
            case R.id.action_filtre_statut:
                TextView text2 = (TextView) findViewById(R.id.textView_filtre);
                text2.setText("Filtre: statut");
                return true;
            case R.id.action_filtre_date:
                TextView text3 = (TextView) findViewById(R.id.textView_filtre);
                text3.setText("Filtre: date de péremption");
                return true;
            case R.id.action_parametre:
                Intent intent = new Intent(this, Parametre.class);
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
            listSang = sa.getAllSangs();


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
