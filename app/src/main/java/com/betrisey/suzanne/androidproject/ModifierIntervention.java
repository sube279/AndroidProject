package com.betrisey.suzanne.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import db.adapter.InterventionDataSource;
import db.object.CIntervention;

public class ModifierIntervention extends AppCompatActivity {
    private int id;
    public CIntervention i;
    public InterventionDataSource ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_intervention);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        // Get the message from the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        //spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinnerGroupe);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.groupe_sanguin, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        ia = new InterventionDataSource(getApplicationContext());
        i = ia.getInterventiononById(id);

        // Create the text view
        EditText tw = (EditText) findViewById(R.id.textViewDate);
        tw.setText(i.getDate());
        tw = (EditText) findViewById(R.id.textViewQuantite);
        tw.setText(String.valueOf(i.getQuantite()));
        final Spinner spin = (Spinner) findViewById (R.id.spinnerGroupe);
        String[] groupe = getResources().getStringArray(R.array.groupe_sanguin);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(ModifierIntervention.this, android.R.layout.simple_spinner_dropdown_item,groupe);
        spin.setAdapter(ad);
        spin.setSelection(ad.getPosition(i.getGroupe()));

        tw = (EditText) findViewById(R.id.textViewDescription);
        tw.setText(i.getDescription());



    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, AfficherIntervention.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void buttonOk(View view) {

        EditText et = (EditText) findViewById (R.id.textViewDate);
        i.setDate(et.getText().toString());
        et = (EditText) findViewById (R.id.textViewDescription);
        i.setDescription(et.getText().toString());
        et = (EditText) findViewById (R.id.textViewQuantite);
        i.setQuantite(Integer.parseInt(et.getText().toString()));
        Spinner spin = (Spinner) findViewById (R.id.spinnerGroupe);
        i.setGroupe((String) spin.getSelectedItem().toString());

        ia.updateIntervention(i);

        Intent intent = new Intent(this, AfficherIntervention.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
