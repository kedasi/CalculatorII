package com.edasi.kerli.androidkalkulaatorloogikastatistika;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kerli on 13.05.2016.
 */
public abstract class Repo<T extends IEntity>  {
    private SQLiteDatabase database;
    private String tablename;
    private String[] allColumns;

    public Repo(SQLiteDatabase database, String tablename, String[] allColumns) {
        this.database = database;
        this.tablename = tablename;
        this.allColumns = allColumns;
    }
    //crud - create, update, delete
    public String[] getAllColumns() {
        return allColumns;
    }
    public void setAllColumns(String[] allColumns) {
        this.allColumns = allColumns;
    }

    public List<T> All(){

        ArrayList<T> list = new ArrayList<T>();
        Cursor cursor = database.query(tablename,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            T entity = cursorToEntity(cursor);
            list.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return list;
    }

    public T add(T entity) {

        ContentValues values = entityToContentValues(entity);
        long insertId = database.insert(tablename, null, values);
        return getById(insertId);

    }

    public T getById(long id) {
        Cursor cursor = database.query(tablename, allColumns, allColumns[0] + " = " + id, null,
                null, null, null);
        cursor.moveToFirst();
        T entity = cursorToEntity(cursor);
        cursor.close();
        return entity;
    }

    public void update(T entity) {
        ContentValues values = entityToContentValues(entity);
        database.update(tablename, values, allColumns[0] + "=" + entity.getId(), null);
    }

    public void delete(T entity) {
        long id = entity.getId();
        System.out.println("Entity deleted from table "+tablename+" with id: " + id);
        database.delete(tablename, allColumns[0] + " = " + id, null);
    }

    public void delete(long id) {
        System.out.println("Entity deleted from table "+tablename+" with id: " + id);
        database.delete(tablename, allColumns[0] + " = " + id, null);
    }
    public void deleteAll(){
        database.delete(tablename, null , null);
    }
    public abstract ContentValues entityToContentValues(T entity);
    public abstract T cursorToEntity(Cursor cursor);
}
