package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseManager {
    private Databasehelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new Databasehelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertTask(ModelToDo modelToDo) {
        ContentValues values = new ContentValues();
        values.put(Databasehelper.COLUMN_TASK, modelToDo.getTask());
        values.put(Databasehelper.COLUMN_DATE, modelToDo.getDate());
        database.insert(Databasehelper.TABLE_NAME, null, values);
    }

    public void deleteTask(int id) {
        database.delete(Databasehelper.TABLE_NAME, Databasehelper.COLUMN_ID + " = " + id, null);
    }

    public ArrayList<ModelToDo> getAllTasks() {
        ArrayList<ModelToDo> tasks = new ArrayList<>();
        Cursor cursor = database.query(Databasehelper.TABLE_NAME,
                null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Databasehelper.COLUMN_ID));
                String task = cursor.getString(cursor.getColumnIndexOrThrow(Databasehelper.COLUMN_TASK));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(Databasehelper.COLUMN_DATE));
                tasks.add(new ModelToDo(id, task, date));
            }
            cursor.close();
        }
        return tasks;
    }
}

