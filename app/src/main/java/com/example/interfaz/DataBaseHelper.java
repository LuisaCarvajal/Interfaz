package com.example.interfaz;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CouchAnt.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "usuarios";


    private static final String COL_ID = "id";
    private static final String COL_NOMBRES = "nombre";
    private static final String COL_APELLIDOS = "apellido";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_CREATED_AT = "created_at";
    private static final String COL_UPDATED_AT = "updated_at";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // SQL to create the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOMBRES + " TEXT NOT NULL UNIQUE, " +
                    COL_APELLIDOS + " TEXT NOT NULL UNIQUE, " +
                    COL_EMAIL + " TEXT NOT NULL UNIQUE, " +
                    COL_PASSWORD + " TEXT NOT NULL, " +
                    COL_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    COL_UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    // Trigger to update 'updated_at'
    private static final String TRIGGER_UPDATE_TIMESTAMP =
            "CREATE TRIGGER update_timestamp " +
                    "AFTER UPDATE ON " + TABLE_NAME + " " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "UPDATE " + TABLE_NAME + " SET " + COL_UPDATED_AT + " = CURRENT_TIMESTAMP WHERE " + COL_ID + " = OLD." + COL_ID + "; " +
                    "END;";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TRIGGER_UPDATE_TIMESTAMP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert a user
    public boolean insertUser(String nombres, String apellidos, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOMBRES, nombres);
        contentValues.put(COL_APELLIDOS, apellidos);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // returns true if insert is successful
    }

    public boolean login (String contrasena, String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { "*" };
        String[] selectionArgs = { email, contrasena };
        Cursor cursor = db.query("usuarios", columns, "email =? and password =?", selectionArgs, null, null,null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

}
