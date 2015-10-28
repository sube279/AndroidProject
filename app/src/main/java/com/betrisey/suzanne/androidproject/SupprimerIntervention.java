package com.betrisey.suzanne.androidproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SupprimerIntervention extends AppCompatActivity {

    InterventionAdapter liste;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_intervention);

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

        }


    public void buttonDelete(View view) {
        Intent intent = new Intent(this, Intervention.class);
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
                LayoutInflater inflater = (LayoutInflater) SupprimerIntervention.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.liste_intervention_supp, parent,false);
            }

            TextView date = (TextView)convertView.findViewById(R.id.textViewDate);
            TextView description = (TextView)convertView.findViewById(R.id.textViewDescription);
            CheckBox selected = (CheckBox) convertView.findViewById(R.id.CheckBoxSelected);


            CIntervention i = liste.get(position);

            date.setText(i.getDate());
            description.setText(i.getDescription());
            if(i.isSelected()== true) {
                selected.setChecked(true);
            }

            selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        counter++;
                    }
                    else{
                        counter--;
                    }
                    if(counter>1){
                        TextView tv = (TextView)findViewById(R.id.textView_Selection);
                        tv.setText(String.valueOf(counter)+" sélectionnés");
                    }
                    else{
                        TextView tv = (TextView)findViewById(R.id.textView_Selection);
                        tv.setText(String.valueOf(counter)+" sélectionné");
                    }
                }
            });



            return convertView;
        }


    }
}
