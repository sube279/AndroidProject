package db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import db.SQLiteHelper;
import db.DonDeSangContract.InterventionEntry;
import db.object.CIntervention;

/**
 * Created by Suzanne on 07.11.2015.
 */
public class InterventionDataSource {

    private SQLiteDatabase db;
    private Context context;

    public InterventionDataSource(Context context){
        SQLiteHelper sqliteHelper = SQLiteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new intervention
     */
    public long createIntervention(CIntervention intervention){
        long id;
        ContentValues values = new ContentValues();
        values.put(InterventionEntry.KEY_QUANTITE, intervention.getQuantite());
        values.put(InterventionEntry.KEY_DATE, intervention.getDate());
        values.put(InterventionEntry.KEY_DESCRIPTION, intervention.getDescription());
        values.put(InterventionEntry.KEY_GROUPE, intervention.getGroupe());

        id = this.db.insert(InterventionEntry.TABLE_INTERVENTION, null, values);

        return id;
    }


    /**
     * Get all Interventions
     */
    public List<CIntervention> getAllInterventions(){
        List<CIntervention> interventions = new ArrayList<CIntervention>();
        String sql = "SELECT * FROM " + InterventionEntry.TABLE_INTERVENTION + " ORDER BY " + InterventionEntry.KEY_DATE + " ;";

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                CIntervention i = new CIntervention();
                i.setId(cursor.getInt(cursor.getColumnIndex(InterventionEntry.KEY_ID)));
                i.setDate(cursor.getString(cursor.getColumnIndex(InterventionEntry.KEY_DATE)));
                i.setDescription(cursor.getString(cursor.getColumnIndex(InterventionEntry.KEY_DESCRIPTION)));
                i.setGroupe(cursor.getString(cursor.getColumnIndex(InterventionEntry.KEY_GROUPE)));
                i.setQuantite(cursor.getInt(cursor.getColumnIndex(InterventionEntry.KEY_QUANTITE)));

                interventions.add(i);
            } while(cursor.moveToNext());
        }

        return interventions;
    }

    /**
     * Find one Intervention by Id
     */
    public CIntervention getInterventiononById(int id){
        String sql = "SELECT * FROM " + InterventionEntry.TABLE_INTERVENTION +
                " WHERE " + InterventionEntry.KEY_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        CIntervention i = new CIntervention();
        i.setId(cursor.getInt(cursor.getColumnIndex(InterventionEntry.KEY_ID)));
        i.setDate(cursor.getString(cursor.getColumnIndex(InterventionEntry.KEY_DATE)));
        i.setDescription(cursor.getString(cursor.getColumnIndex(InterventionEntry.KEY_DESCRIPTION)));
        i.setQuantite(cursor.getInt(cursor.getColumnIndex(InterventionEntry.KEY_QUANTITE)));
        i.setGroupe(cursor.getString(cursor.getColumnIndex(InterventionEntry.KEY_GROUPE)));

        return i;
    }

    /**
     * Delete a Intervention - this will also delete all records
     * for the intervention
     */
    public void deleteIntervention(long id){

        //delete the intervention
        this.db.delete(InterventionEntry.TABLE_INTERVENTION, InterventionEntry.KEY_ID + " = ?",
                new String[]{String.valueOf(id)});

    }


}

