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
