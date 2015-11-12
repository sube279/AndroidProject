package db;

import android.provider.BaseColumns;




/**
 * Created by Suzanne on 06.11.2015.
 */
public final class DonDeSangContract {

    public DonDeSangContract(){
        //empty constructor
        //should never be instantiated
    }

    /*//Represents the rows of a table
    public static abstract class DonneurEntry implements BaseColumns {
        //Table name
        public static final String TABLE_DONNEUR = "donneur";

        //Person Column names
        public static final String KEY_ID = "id";
        public static final String KEY_NOM = "nom";
        public static final String KEY_PRENOM = "prenom";
        public static final String KEY_GENRE = "genre";
        public static final String KEY_NAISSANCE = "naissance";
        public static final String KEY_TELEPHONE = "telephone";
        public static final String KEY_RUE = "rue";
        public static final String KEY_ID_LIEU = "id_lieu";
        public static final String KEY_GROUPE = "groupe";
        public static final String KEY_DON = "don";
        public static final String KEY_DISPONIBILITE = "disponibilite";

        //Table person create statement
        public static final String CREATE_TABLE_PERSONNE = "CREATE TABLE "
                + TABLE_DONNEUR + "("
                + DonneurEntry.KEY_ID + " INTEGER PRIMARY KEY,"
                + DonneurEntry.KEY_NOM + " TEXT, "
                + DonneurEntry.KEY_PRENOM + " TEXT, "
                + DonneurEntry.KEY_GENRE + " TEXT "
                + DonneurEntry.KEY_NAISSANCE + " DATE "
                + DonneurEntry.KEY_TELEPHONE + " TEXT "
                + DonneurEntry.KEY_RUE + " TEXT "
                + "FOREIGN KEY (" + KEY_ID_LIEU + ") REFERENCES " + LieuEntry.TABLE_LIEU + " (" + KEY_ID + "), "
                + DonneurEntry.KEY_GROUPE + " TEXT "
                + DonneurEntry.KEY_DON + " INTEGER "
                + DonneurEntry.KEY_DISPONIBILITE + " TEXT "
                + ");";
    }*/

        //Represents the rows of a table
        public static abstract class InterventionEntry implements BaseColumns {
            //Table name
            public static final String TABLE_INTERVENTION = "intervention";

            //Person Column names
            public static final String KEY_ID = "id";
            public static final String KEY_QUANTITE = "quantite";
            public static final String KEY_DATE = "date";
            public static final String KEY_DESCRIPTION = "description";
            public static final String KEY_GROUPE = "groupe";

            //Table person create statement
            public static final String CREATE_TABLE_INTERVENTION = "CREATE TABLE "
                    + TABLE_INTERVENTION + "("
                    + InterventionEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + InterventionEntry.KEY_QUANTITE + " INTEGER, "
                    + InterventionEntry.KEY_DATE + " TEXT, "
                    + InterventionEntry.KEY_DESCRIPTION + " TEXT, "
                    + InterventionEntry.KEY_GROUPE + " TEXT "
                    + ");";
        }
/*
    //Represents the rows of a table
    public static abstract class LieuEntry implements BaseColumns {
        //Table name
        public static final String TABLE_LIEU = "lieu";

        //Person Column names
        public static final String KEY_ID = "id";
        public static final String KEY_NPA = "npa";
        public static final String KEY_LOCALITE = "localite";
        public static final String KEY_ID_REGION = "region";

        //Table person create statement
        public static final String CREATE_TABLE_LIEU = "CREATE TABLE "
                + TABLE_LIEU + "("
                + LieuEntry.KEY_ID + " INTEGER PRIMARY KEY,"
                + LieuEntry.KEY_NPA + " TEXT, "
                + LieuEntry.KEY_LOCALITE + " TEXT, "
                + "FOREIGN KEY (" + KEY_ID_REGION + ") REFERENCES " + RegionEntry.TABLE_REGION + " (" + KEY_ID + "), "
                + ");";
    }

    //Represents the rows of a table
    public static abstract class SangEntry implements BaseColumns {
        //Table name
        public static final String TABLE_SANG = "sang";

        //Person Column names
        public static final String KEY_ID = "id";
        public static final String KEY_ID_DONNEUR = "id_donateur";
        public static final String KEY_ID_INTERVENTION = "id_intervention";
        public static final String KEY_GROUPE = "groupe";
        public static final String KEY_DATE_DON = "date_don";
        public static final String KEY_DATE_PEREMPTION = "date_peremption";
        public static final String KEY_ID_REGION = "id_region";
        public static final String KEY_STATUT = "statut";


        //Table person create statement
        public static final String CREATE_TABLE_SANG = "CREATE TABLE "
                + TABLE_SANG + "("
                + SangEntry.KEY_ID + " INTEGER PRIMARY KEY,"
                + "FOREIGN KEY (" + KEY_ID_DONNEUR + ") REFERENCES " + DonneurEntry.TABLE_DONNEUR + " (" + KEY_ID + "), "
                + "FOREIGN KEY (" + KEY_ID_INTERVENTION + ") REFERENCES " + InterventionEntry.TABLE_INTERVENTION + " (" + KEY_ID + "), "
                + SangEntry.KEY_GROUPE + " TEXT "
                + SangEntry.KEY_DATE_DON + " DATE "
                + SangEntry.KEY_DATE_PEREMPTION + " DATE "
                + "FOREIGN KEY (" + KEY_ID_REGION + ") REFERENCES " + RegionEntry.TABLE_REGION + " (" + KEY_ID + "), "
                + SangEntry.KEY_STATUT + " TEXT "
                + ");";
    }

    //Represents the rows of a table
    public static abstract class RegionEntry implements BaseColumns {
        //Table name
        public static final String TABLE_REGION = "region";

        //Person Column names
        public static final String KEY_ID = "id";
        public static final String KEY_NOM = "nom";


        //Table person create statement
        public static final String CREATE_TABLE_REGION = "CREATE TABLE "
                + TABLE_REGION + "("
                + RegionEntry.KEY_ID + " INTEGER PRIMARY KEY,"
                + RegionEntry.KEY_NOM + " TEXT, "
                + ");";
    }*/
}
