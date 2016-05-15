package com.edasi.kerli.androidkalkulaatorloogikastatistika;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kerli on 13.05.2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_OPERATIONTYPES = "operationtypes";
    public static final String OPERATIONTYPES_COLUMN_ID = "_id";
    public static final String OPERATIONTYPES_COLUMN_OPERATION = "operation";
    public static final String OPERATIONTYPES_COLUMN_LIFETIMECOUNTER = "lifetimecounter";
    public static final String[] OPERATIONTYPES_ALLCOLUMNS = {OPERATIONTYPES_COLUMN_ID, OPERATIONTYPES_COLUMN_OPERATION, OPERATIONTYPES_COLUMN_LIFETIMECOUNTER};

    public static final String TABLE_OPERATIONS = "operations";
    public static final String OPERATIONS_COLUMN_ID = "_id";
    public static final String OPERATIONS_COLUMN_FULLOPERATION = "fulloperation";
    public static final String OPERATIONS_COLUMN_RESULT = "result";
    public static final String OPERATIONS_COLUMN_TIMESTAMP = "timestamp";
    public static final String OPERATIONS_COLUMN_OPERATIONTYPEID = "operationTypeId";
    public static final String[] OPERATIONS_ALLCOLUMNS = {OPERATIONS_COLUMN_ID, OPERATIONS_COLUMN_FULLOPERATION,
            OPERATIONS_COLUMN_RESULT, OPERATIONS_COLUMN_TIMESTAMP, OPERATIONS_COLUMN_OPERATIONTYPEID};

    public static final String TABLE_DAILYSTATS = "dailystats";
    public static final String DAILYSTATS_COLUMN_ID = "_id";
    public static final String DAILYSTATS_COLUMN_DAYCOUNTER = "daycounter";
    public static final String DAILYSTATS_COLUMN_DAYSTAMP = "daystamp";
    public static final String DAILYSTATS_COLUMN_OPERATIONTYPEID = "operationTypeId";
    public static final String[] DAILYSTATS_ALLCOLUMNS = {DAILYSTATS_COLUMN_ID, DAILYSTATS_COLUMN_DAYCOUNTER,DAILYSTATS_COLUMN_DAYSTAMP, DAILYSTATS_COLUMN_OPERATIONTYPEID};

    private static final String DATABASE_NAME = "statsdb.db";
    private static final int DATABASE_VERSION = 1;



       private static final String DATABASE_CREATE_OPERATIONTYPES = "create table "
            + TABLE_OPERATIONTYPES + "("
            + OPERATIONTYPES_COLUMN_ID + " integer primary key autoincrement, "
            + OPERATIONTYPES_COLUMN_LIFETIMECOUNTER + " integer,"
            + OPERATIONTYPES_COLUMN_OPERATION + " text not null);";

    private static final String DATABASE_CREATE_OPERATIONS = "create table "
            + TABLE_OPERATIONS + "("
            + OPERATIONS_COLUMN_ID + " integer primary key autoincrement, "
            + OPERATIONS_COLUMN_FULLOPERATION + " text not null, "
            + OPERATIONS_COLUMN_RESULT + " float, "
            + OPERATIONS_COLUMN_TIMESTAMP + " integer, "
            + OPERATIONS_COLUMN_OPERATIONTYPEID + " integer);";

    private static final String DATABASE_CREATE_DAILYSTATS = "create table "
            + TABLE_DAILYSTATS + "("
            + DAILYSTATS_COLUMN_ID + " integer primary key autoincrement, "
            + DAILYSTATS_COLUMN_DAYCOUNTER + " integer, "
            + DAILYSTATS_COLUMN_DAYSTAMP + " text not null, "
            + DAILYSTATS_COLUMN_OPERATIONTYPEID + " integer);";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_OPERATIONTYPES);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
        db.execSQL(DATABASE_CREATE_DAILYSTATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONTYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILYSTATS);
        onCreate(db);
    }
    public void dropCreateDatabase(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONTYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILYSTATS);

        db.execSQL(DATABASE_CREATE_DAILYSTATS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
        db.execSQL(DATABASE_CREATE_OPERATIONTYPES);
    }
}
