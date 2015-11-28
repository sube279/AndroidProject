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
import db.object.CSang;

/**
 * Created by Suzanne on 25.11.2015.
 */
public class SangDataSource {

    private SQLiteDatabase db;
    private Context context;

    public SangDataSource(Context context){
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new pochette
     */
    public long createSang(CSang sang) throws ParseException {
        long id;
        ContentValues values = new ContentValues();
        values.put(DonDeSangContract.SangEntry.KEY_ID_DONNEUR, sang.getDonneur());
        values.put(DonDeSangContract.SangEntry.KEY_DATE_DON, changeIntoString(sang.getDateDon()));
        values.put(DonDeSangContract.SangEntry.KEY_DATE_PEREMPTION, changeIntoString(sang.getPeremption()));
        values.put(DonDeSangContract.SangEntry.KEY_REGION, sang.getRegion());
        values.put(DonDeSangContract.SangEntry.KEY_GROUPE, sang.getGroupe());
        values.put(DonDeSangContract.SangEntry.KEY_STATUT, sang.getStatut());
        values.put(DonDeSangContract.SangEntry.KEY_ID_INTERVENTION, sang.getIntervention());

        id = this.db.insert(DonDeSangContract.SangEntry.TABLE_SANG, null, values);

        return id;
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
