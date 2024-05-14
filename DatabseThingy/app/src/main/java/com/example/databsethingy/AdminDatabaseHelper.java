package com.example.databsethingy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "admin.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ADMIN = "admin";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public AdminDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_ADMIN + "(" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableQuery);

        // Insert the single admin user
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, "san");
        values.put(COLUMN_PASSWORD, "nCj6B58]'Q.@eJz$M*(~+,");
        db.insert(TABLE_ADMIN, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        onCreate(db);
    }

    public boolean isValidAdmin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COLUMN_USERNAME};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_ADMIN, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}