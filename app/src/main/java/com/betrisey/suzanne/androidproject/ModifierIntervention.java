package com.betrisey.suzanne.androidproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        try {
            i = ia.getInterventiononById(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create the text view
        EditText tw = (EditText) findViewById(R.id.textViewDate);
        try {
            tw.setText(changeIntoString(i.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public void buttonOk(View view) throws ParseException {

        if(testQuantite() == true)
        {
        EditText et = (EditText) findViewById (R.id.textViewDate);
        i.setDate(changeIntoDate(et.getText().toString()));
        et = (EditText) findViewById (R.id.textViewDescription);
        i.setDescription(et.getText().toString());
        et = (EditText) findViewById (R.id.textViewQuantite);
        i.setQuantite(Integer.parseInt(et.getText().toString()));
        Spinner spin = (Spinner) findViewById (R.id.spinnerGroupe);
        i.setGroupe(spin.getSelectedItem().toString());

        ia.updateIntervention(i);

        Intent intent = new Intent(this, AfficherIntervention.class);
        intent.putExtra("id", id);
        startActivity(intent);
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(ModifierIntervention.this).create();
            alertDialog.setTitle(getResources().getString(R.string.alerteErreur));
            alertDialog.setMessage(getResources().getString(R.string.alerteQuPochette));

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public boolean testQuantite(){
        try {
            EditText text = (EditText) findViewById (R.id.textViewQuantite);
            Integer.parseInt(text.getText().toString());
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    public Date changeIntoDate(String s) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.FRENCH);
        Date date = format.parse(s);
        return date;
    }

    public String changeIntoString(Date d) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.FRENCH);
        String s = df.format(d);
        return s;
    }
}
