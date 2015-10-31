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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

        //listview
        liste = new SangAdapter();

        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(liste);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CSang s = liste.getSang(position);
                sendSang(s);
            }
        });
    }

    public void sendSang(CSang s){
        Intent intent = new Intent(this, AffichagePochetteSang.class);
        intent.putExtra("id", s.getId());
        intent.putExtra("donneur", s.getDonneur());
        intent.putExtra("dateDon", s.getDateDon());
        intent.putExtra("peremption", s.getPeremption());
        intent.putExtra("region", s.getRegion());
        intent.putExtra("groupe", s.getGroupe());
        intent.putExtra("statut", s.getStatut());
        intent.putExtra("dateIntervention", s.getDateIntervention());
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

        List<CSang> liste = getDataForListView();

        public List<CSang> getDataForListView() {
            List<CSang> listSang = new ArrayList<CSang>();

            CSang s1 = new CSang ("000001", "15634", "30.10.2015", "30.11.2015", "Sion", "AB -", "en stock", null);
            CSang s2 = new CSang ("000002", "16894", "30.10.2015", "30.11.2015", "Sierre", "A -", "endommagé", null);
            CSang s3 = new CSang ("000003", "68431", "02.11.2015", "02.12.2015", "Sion", "O +", "en stock", null);
            CSang s4 = new CSang ("000004", "95168", "02.11.2015", "02.12.2015", "Sion", "B +", "utilisé", "15.11.2015");
            CSang s5 = new CSang ("000006", "56118", "05.11.2015", "05.12.2015", "Sion", "A +", "endommagé", null);
            CSang s7 = new CSang ("000007", "64584", "05.11.2015", "05.12.2015", "Sion", "A +", "en stock", null);
            CSang s8 = new CSang ("000008", "56184", "05.11.2015", "05.12.2015", "Sion", "A +", "en stock", null);
            CSang s9 = new CSang ("000009", "98415", "05.11.2015", "05.12.2015", "Sion", "AB +", "utilisé", null);
            CSang s10 = new CSang ("000010", "16415", "06.11.2015", "06.12.2015", "Sierre", "B +", "utilisé", null);
            CSang s11 = new CSang ("000011", "16418", "06.11.2015", "06.12.2015", "Sierre", "O -", "en stock", null);
            CSang s12 = new CSang ("000012", "65712", "06.11.2015", "06.12.2015", "Sion", "A +", "en stock", null);
            CSang s13 = new CSang ("000013", "54286", "06.11.2015", "06.12.2015", "Sion", "A +", "transfert", null);
            CSang s14 = new CSang ("000014", "55965", "09.11.2015", "09.12.2015", "Sierre", "AB +", "en stock", null);
            CSang s15 = new CSang ("000015", "98268", "09.11.2015", "09.12.2015", "Sion", "O +", "utilisé", null);
            CSang s16 = new CSang ("000016", "51353", "09.11.2015", "09.12.2015", "Sion", "A +", "en stock", null);
            CSang s17 = new CSang ("000017", "78875", "10.11.2015", "10.12.2015", "Sierre", "B +", "en stock", null);
            CSang s18 = new CSang ("000018", "51325", "10.11.2015", "10.12.2015", "Sion", "A -", "endommagé", null);
            CSang s19 = new CSang ("000019", "13596", "10.11.2015", "10.12.2015", "Sion", "A +", "en stock", "25.11.2015");

            listSang.add(s1);
            listSang.add(s2);
            listSang.add(s3);
            listSang.add(s4);
            listSang.add(s5);
            listSang.add(s7);
            listSang.add(s8);
            listSang.add(s9);
            listSang.add(s10);
            listSang.add(s11);
            listSang.add(s12);
            listSang.add(s13);
            listSang.add(s14);
            listSang.add(s15);
            listSang.add(s16);
            listSang.add(s17);
            listSang.add(s18);
            listSang.add(s19);

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
            peremption.setText(s.getPeremption());

           if(s.getGroupe().equals("A +"))
            groupe.setImageDrawable(getResources().getDrawable(R.drawable.a_plus));
            if(s.getGroupe().equals("A -"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.a_moins));
            if(s.getGroupe().equals("B +"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.b_plus));
            if(s.getGroupe().equals("B -"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.b_moins));
            if(s.getGroupe().equals("AB +"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.ab_plus));
            if(s.getGroupe().equals("AB -"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.ab_moins));
            if(s.getGroupe().equals("O +"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.o_plus));
            if(s.getGroupe().equals("O -"))
                groupe.setImageDrawable(getResources().getDrawable(R.drawable.o_moins));



            return convertView;
        }

        public CSang getSang(int position)
        {
            return liste.get(position);
        }
    }
}
