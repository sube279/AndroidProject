package com.betrisey.suzanne.androidproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
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

public class AjouterIntervention extends AppCompatActivity {

    InterventionDataSource ia;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_intervention);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

       ia = new InterventionDataSource(this);

       //spinner
       Spinner spinner = (Spinner) findViewById(R.id.spinnerGroupe);
// Create an ArrayAdapter using the string array and a default spinner layout
       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.groupe_sanguin, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
       spinner.setAdapter(adapter);
    }

    public void buttonAnnuler(View view) {
        Intent intent = new Intent(this, Intervention.class);
        startActivity(intent);
    }

    public void buttonOk(View view) throws ParseException {

        if(testQuantite() == true)
        {
            CIntervention i = new CIntervention();

            EditText et = (EditText) findViewById (R.id.textViewDate);
            i.setDate(changeIntoDate(et.getText().toString()));
            et = (EditText) findViewById (R.id.textViewDescription);
            i.setDescription(et.getText().toString());
            et = (EditText) findViewById (R.id.textViewQuantite);
            i.setQuantite(Integer.parseInt(et.getText().toString()));
            Spinner spin = (Spinner) findViewById (R.id.spinnerGroupe);
            i.setGroupe((String) spin.getSelectedItem().toString());

            ia.createIntervention(i);

            Intent intent = new Intent(this, Intervention.class);
            startActivity(intent);
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(AjouterIntervention.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("La quantité de pochette doit être un nombre.");

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }



    }

    public Date changeIntoDate(String s) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.FRENCH);
        Date date = format.parse(s);
        return date;
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
}
