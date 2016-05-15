package com.edasi.kerli.androidkalkulaatorloogikastatistika;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Kerli on 13.05.2016.
 */
public class DailyStatsRepo extends Repo<DailyStats> {
    public DailyStatsRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }



    public void addOrUpdate(String dateStr, int operationTypeId) {
        long result = exists(dateStr, operationTypeId);

        System.out.println("exist check result "+result);
        if (result != 0){
            //exists
            DailyStats operationStatistics = getById(result);
            operationStatistics.setDayCounter(operationStatistics.getDayCounter() + 1);

            update(operationStatistics);

        } else {
            //did not exist previously
            DailyStats operationStatistics = new DailyStats();

            operationStatistics.setOperationTypeId(operationTypeId);
            operationStatistics.setDayCounter(1);
            operationStatistics.setDayStamp(dateStr);

            add(operationStatistics);
        }

    }

    public long exists(String dateStr, long operatorId){

        System.out.println("Exist check in dailystats repo");

        List<DailyStats> operationStatisticsList = All();

        for(DailyStats opstat : operationStatisticsList) {

            if (opstat.getDayStamp().equals(dateStr) && opstat.getOperationTypeId() == operatorId) {
                return opstat.getId();
            }
        }

        return 0;
    }



    @Override
    public ContentValues entityToContentValues(DailyStats entity) {
        /*
        ContentValues values = new ContentValues();
        values.put(getAllColumns()[1], entity.getOperation());
        values.put(getAllColumns()[2], entity.getLifeTimeCounter());
        return values;
        public static final String[] DAILYSTATS_ALLCOLUMNS =
         {DAILYSTATS_COLUMN_ID, DAILYSTATS_COLUMN_DAYCOUNTER, DAILYSTATS_COLUMN_OPERATIONTYPEID}
         */
        ContentValues values = new ContentValues();
        values.put(getAllColumns()[1], entity.getDayCounter());
        values.put(getAllColumns()[2], entity.getDayStamp());
        values.put(getAllColumns()[3], entity.getOperationTypeId());
         return values;
    }

    @Override
    public DailyStats cursorToEntity(Cursor cursor) {
/*
 OperationType operationType = new OperationType();
        operationType.setId(cursor.getInt(0));
        operationType.setOperation(cursor.getString(1));
        operationType.setLifeTimeCounter(cursor.getInt(2));
        return operationType;
 */
        DailyStats dailyStats = new DailyStats();
        dailyStats.setId(cursor.getInt(0));
        dailyStats.setDayCounter(cursor.getInt(1));
        dailyStats.setDayStamp(cursor.getString(2));
        dailyStats.setOperationTypeId(cursor.getInt(3));
        return dailyStats;
    }
}
