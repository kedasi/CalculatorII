package com.edasi.kerli.androidkalkulaatorloogikastatistika;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Kerli on 13.05.2016.
 */
public class OperationTypeRepo extends Repo<OperationType> {
    public OperationTypeRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }


    public OperationType GetOperationTypeByStringOperator(String opType) {
        List<OperationType> opTypesList = All();

        for(OperationType operationType : opTypesList) {
            if (operationType.getOperation().equals(opType)){
                return operationType;
            }
        }
        return null;
    }

    @Override
    public ContentValues entityToContentValues(OperationType entity) {
        ContentValues values = new ContentValues();
        values.put(getAllColumns()[1], entity.getOperation());
        values.put(getAllColumns()[2], entity.getLifeTimeCounter());
        return values;
    }

    @Override
    public OperationType cursorToEntity(Cursor cursor) {
        OperationType operationType = new OperationType();
        operationType.setId(cursor.getInt(0));
        operationType.setOperation(cursor.getString(1));
        operationType.setLifeTimeCounter(cursor.getInt(2));
        return operationType;

    }
}
