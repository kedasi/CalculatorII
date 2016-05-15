package com.edasi.kerli.androidkalkulaatorloogikastatistika;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kerli on 13.05.2016.
 */
public class OperationRepo extends Repo<Operation> {
    public OperationRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }



    @Override
    public ContentValues entityToContentValues(Operation entity) {
       /*
       ContentValues values = new ContentValues();
        values.put(getAllColumns()[1], entity.getOperation());
        values.put(getAllColumns()[2], entity.getLifeTimeCounter());
        return values;
         public static final String[] OPERATIONS_ALLCOLUMNS = {OPERATIONS_COLUMN_ID, OPERATIONS_COLUMN_FULLOPERATION,
            OPERATIONS_COLUMN_RESULT, OPERATIONS_COLUMN_TIMESTAMP, OPERATIONS_COLUMN_OPERATIONTYPEID};
        */
        ContentValues values = new ContentValues();
        values.put(getAllColumns()[1],entity.getFullOperation());
        values.put(getAllColumns()[2],entity.getResult());
        values.put(getAllColumns()[3], entity.getTimeStamp());
        values.put(getAllColumns()[4], entity.getOperationTypeId());
        return values;
    }

    @Override
    public Operation cursorToEntity(Cursor cursor) {
       /*
        OperationType operationType = new OperationType();
        operationType.setId(cursor.getInt(0));
        operationType.setOperation(cursor.getString(1));
        operationType.setLifeTimeCounter(cursor.getInt(2));
        return operationType;
        */
        Operation operation = new Operation();
        operation.setId(cursor.getInt(0));
        operation.setFullOperation(cursor.getString(1));
        operation.setResult(cursor.getInt(2));
        operation.setTimeStamp(cursor.getInt(3));
        operation.setOperationTypeId(cursor.getInt(4));
        return operation;
    }
}
