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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Intervention extends AppCompatActivity {

    InterventionAdapter liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervention);

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
        liste = new InterventionAdapter();

        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(liste);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CIntervention i = liste.getIntervention(position);
                sendIntervention(i);
            }
        });


    }

    public void sendIntervention(CIntervention i){
        Intent intent = new Intent(this, AfficherIntervention.class);
        intent.putExtra("date", i.getDate());
        intent.putExtra("quantite", i.getQuantite());
        intent.putExtra("groupe", i.getGroupe());
        intent.putExtra("description", i.getDescription());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intervention, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_ajouter_intervention:
                Intent intent = new Intent(this, AjouterIntervention.class);
                startActivity(intent);
                return true;
            case R.id.action_supprimer_intervention:
                Intent intent3 = new Intent(this, SupprimerIntervention.class);
                startActivity(intent3);
                return true;
            case R.id.action_parametre:
                Intent intent2 = new Intent(this, Parametre.class);
                startActivity(intent2);
        }
        return (super.onOptionsItemSelected(item));
    }


        public void buttonAfficher(View view) {
        Intent intent = new Intent(this, AfficherIntervention.class);
        startActivity(intent);
    }

    public class InterventionAdapter extends BaseAdapter {

        List<CIntervention> liste = getDataForListView();

        public List<CIntervention> getDataForListView() {
            List<CIntervention> listIntervention = new ArrayList<CIntervention>();

            CIntervention i1 = new CIntervention("02.10.2015", "Chimiothérapie", "1", "A +", false);
            CIntervention i2 = new CIntervention("12.10.2015", "Opération cardiaque", "2", "A +", false);
            CIntervention i3 = new CIntervention("12.10.2015", "Anémie", "2", "0-", false);
            CIntervention i4 = new CIntervention("16.10.2015", "Chimiothérapie", "1", "0 -", false);
            CIntervention i5 = new CIntervention("16.10.2015", "Chimiothérapie", "1", "B -", false);
            CIntervention i6 = new CIntervention("19.10.2015", "Prothèse de hanche", "3", "B +", false);
            CIntervention i7 = new CIntervention("19.10.2015", "Chimiothérapie", "1", "A +", false);
            CIntervention i8 = new CIntervention("20.10.2015", "Accouchement", "2", "AB +", false);
            CIntervention i9 = new CIntervention("20.10.2015", "Anémie", "1", "0 -", false);

            listIntervention.add(i1);
            listIntervention.add(i2);
            listIntervention.add(i3);
            listIntervention.add(i4);
            listIntervention.add(i5);
            listIntervention.add(i6);
            listIntervention.add(i7);
            listIntervention.add(i8);
            listIntervention.add(i9);

            return listIntervention;

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
                LayoutInflater inflater = (LayoutInflater) Intervention.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.liste_intervention, parent,false);
            }

            TextView date = (TextView)convertView.findViewById(R.id.textViewDate);
            TextView description = (TextView)convertView.findViewById(R.id.textViewDescription);

            CIntervention i = liste.get(position);

            date.setText(i.getDate());
            description.setText(i.getDescription());

            return convertView;
        }

        public CIntervention getIntervention(int position)
        {
            return liste.get(position);
        }
    }
}
