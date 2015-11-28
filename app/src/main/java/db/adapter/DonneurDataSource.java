package db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import db.DonDeSangContract;
import db.SQLiteHelper;
import db.object.CDonneur;

import db.DonDeSangContract.DonneurEntry;

/**
 * Created by Suzanne on 25.11.2015.
 */
public class DonneurDataSource {

    private SQLiteDatabase db;
    private Context context;

    public DonneurDataSource(Context context){
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new donneur
     */
    public long createDonneur(CDonneur donneur) throws ParseException {
        long id;
        ContentValues values = new ContentValues();
        values.put(DonDeSangContract.DonneurEntry.KEY_NOM, donneur.getNom());
        values.put(DonDeSangContract.DonneurEntry.KEY_PRENOM, donneur.getPrenom());
        values.put(DonDeSangContract.DonneurEntry.KEY_GENRE, donneur.getSexe());
        values.put(DonDeSangContract.DonneurEntry.KEY_NAISSANCE, changeIntoString(donneur.getNaissance()));
        values.put(DonDeSangContract.DonneurEntry.KEY_RUE, donneur.getAdresse());
        values.put(DonDeSangContract.DonneurEntry.KEY_NPA, donneur.getNPA());
        values.put(DonDeSangContract.DonneurEntry.KEY_LIEU, donneur.getLieu());
        values.put(DonDeSangContract.DonneurEntry.KEY_REGION, donneur.getRegion());
        values.put(DonDeSangContract.DonneurEntry.KEY_TELEPHONE, donneur.getTelephone());
        values.put(DonDeSangContract.DonneurEntry.KEY_GROUPE, donneur.getGroupe());
        values.put(DonDeSangContract.DonneurEntry.KEY_DON, donneur.getDonsPossibles());
        values.put(DonDeSangContract.DonneurEntry.KEY_DISPONIBILITE, changeIntoString(donneur.getDisponibilite()));

        id = this.db.insert(DonDeSangContract.DonneurEntry.TABLE_DONNEUR, null, values);

        return id;
    }

    /**
     * Get all Interventions
     */
    public List<CDonneur> getAllDonneur() throws ParseException {
        List<CDonneur> donneurs = new ArrayList<CDonneur>();
        String sql = "SELECT * FROM " + DonDeSangContract.DonneurEntry.TABLE_DONNEUR + " ORDER BY " + DonDeSangContract.DonneurEntry.KEY_NOM + " ;";

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                CDonneur d = new CDonneur();
                d.setId(cursor.getInt(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_ID)));
                d.setNom(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_NOM))));
                d.setPrenom(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_PRENOM))));
                d.setSexe(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_GENRE))));
                d.setNaissance(changeIntoDate(cursor.getString(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_NAISSANCE))));
                d.setAdresse(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_RUE))));
                d.setNPA(cursor.getInt(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_NPA)));
                d.setLieu(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_LIEU))));
                d.setRegion(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_REGION))));
                d.setTelephone(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_TELEPHONE))));
                d.setGroupe(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_GROUPE))));
                d.setDonsPossibles(cursor.getInt(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_DON)));
                d.setDisponibilite(changeIntoDate(cursor.getString(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_DISPONIBILITE))));

                donneurs.add(d);
            } while(cursor.moveToNext());
        }

        return donneurs;
    }

    /**
     * Find one Donneur by Id
     */
    public CDonneur getDonneurById(int id) throws ParseException {
        String sql = "SELECT * FROM " + DonneurEntry.TABLE_DONNEUR +
                " WHERE " + DonneurEntry.KEY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        CDonneur d = new CDonneur();
        d.setId(cursor.getInt(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_ID)));
        d.setNom(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_NOM))));
        d.setPrenom(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_PRENOM))));
        d.setSexe(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_GENRE))));
        d.setNaissance(changeIntoDate(cursor.getString(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_NAISSANCE))));
        d.setAdresse(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_RUE))));
        d.setNPA(cursor.getInt(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_NPA)));
        d.setLieu(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_LIEU))));
        d.setRegion(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_REGION))));
        d.setTelephone(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_TELEPHONE))));
        d.setGroupe(cursor.getString(cursor.getColumnIndex((DonDeSangContract.DonneurEntry.KEY_GROUPE))));
        d.setDonsPossibles(cursor.getInt(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_DON)));
        d.setDisponibilite(changeIntoDate(cursor.getString(cursor.getColumnIndex(DonDeSangContract.DonneurEntry.KEY_DISPONIBILITE))));

        return d;
    }

    public Date changeIntoDate(String s) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.FRENCH);
        Date date = format.parse(s);
        return date;
    }

    public String changeIntoString(Date d) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.FRENCH);
        String str = format.format(d);
        return str;
    }
}