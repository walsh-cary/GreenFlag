package com.example.greenflag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AccountsDatabase
        extends SQLiteOpenHelper {
    private static final String TAG = "AccountsDatabase";

    public AccountsDatabase(@Nullable Context context) {
        super(context, "accounts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " +
                        AccountsTable.TABLE_NAME +
                        " (" +
                        AccountsTable._ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        AccountsTable.ACCOUNTS_NAME +
                        " VARCHAR(255)," +
                        AccountsTable.ACCOUNTS_COUNTRY +
                        " VARCHAR(255)," +
                        AccountsTable.ACCOUNTS_GENDER +
                        " VARCHAR(255)," +
                        AccountsTable.ACCOUNTS_BIRTHDATE +
                        " VARCHAR(255))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
