package com.edasi.kerli.androidkalkulaatorloogikastatistika;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kerli on 13.05.2016.
 */
public class UOW {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public OperationTypeRepo operationTypeRepo;
    public OperationRepo operationRepo;
    public DailyStatsRepo dailyStatsRepo;

    public UOW(Context context) {
       dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
    open();
    }

    public String[] GetOperationTypes(){
        List<OperationType> operationTypes =  this.operationTypeRepo.All();
        String[] value = new String[operationTypes.size() + 1];

        value[0] = "[Operation Type] [Counter]";
        int i = 1;
        for (OperationType op : operationTypes) {
            String val = Integer.toString(i) + ") " + op.getOperation() + " " + Long.toString(op.getLifeTimeCounter());
            value[i] = val;
            i++;
        }

        return value;
    }

    public String[] GetOperations(){
        List<Operation> operations = this.operationRepo.All();
        String[] value = new String[operations.size() + 1];
        value[0] = "[Full Operation] [Result]";
        int i = 1;
        for (Operation op : operations) {
            String val = Integer.toString(i) + ") " + op.getFullOperation() + " " + Float.toString(op.getResult()) ;
            value[i] = val;
            i++;
        }
        return value;
    }
    public String[] GetDailyStats(){
        List<DailyStats> dailyStatses = this.dailyStatsRepo.All();
        String[] value = new String[dailyStatses.size() + 1];
        value[0] = "[Day] [Operation] [Counter]";
        int i = 1;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        for (DailyStats ds : dailyStatses) {
            Date date = new Date(ds.getDayStamp());
            String currentDate = formatter.format(date);

            String val = Integer.toString(i) + ") " + currentDate + " " +
                    operationTypeRepo.getById(ds.getOperationTypeId()).getOperation() + " " + Integer.toString(ds.getDayCounter());
            value[i] = val;
            i++;
        }
        return value;
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATIONS, dbHelper.OPERATIONS_ALLCOLUMNS);
        operationTypeRepo = new OperationTypeRepo(database, dbHelper.TABLE_OPERATIONTYPES, dbHelper.OPERATIONTYPES_ALLCOLUMNS);
        dailyStatsRepo = new DailyStatsRepo(database, dbHelper.TABLE_DAILYSTATS, dbHelper.DAILYSTATS_ALLCOLUMNS);
    }
    public void close() {
        dbHelper.close();
    }

    public void DropCreateDatabase(){
        dbHelper.dropCreateDatabase(database);
    }


}
