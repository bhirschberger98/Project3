package com.bretthirschberger.project3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class UserDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD_HASH = "password";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DOB = "dob";
    //    private static final String COLUMN_HIGH_SCORE = "high_score";
    private static final String COLUMN_IS_PARENT = "is_parent";

    public UserDatabaseHandler(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PASSWORD_HASH + " TEXT, " +
                COLUMN_DOB + " TEXT, " +
                COLUMN_IS_PARENT + " INTEGER " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public User getUser(String email, String password) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = '" + email + "' AND "
                + COLUMN_PASSWORD_HASH + " = '" + Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString() + "';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String dob = cursor.getString(cursor.getColumnIndex(COLUMN_DOB));
            boolean isParent = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_PARENT)) == 1;
            return new User(email, name, password, dob, isParent);
        }
        return null;
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_PASSWORD_HASH, user.getPasswordHash());
        values.put(COLUMN_DOB, user.getDOB());
        values.put(COLUMN_IS_PARENT, user.isParent() ? 1 : 0);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
}
