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
    public void queryData() {
        SQLiteDatabase readable = this.getReadableDatabase();

        String[] columns = {AccountsTable.ACCOUNTS_NAME,
                AccountsTable.ACCOUNTS_COUNTRY,
                AccountsTable.ACCOUNTS_GENDER,
                AccountsTable.ACCOUNTS_BIRTHDATE};

        String selection = " WHERE ";

        Cursor cursor = readable.query(
                AccountsTable.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        final List<AccountsPojo> accounts = new ArrayList<>();
        Log.d(TAG, "queryData: Starting Cursor");

        while (cursor.moveToNext()) {
            String name =
                    cursor.getString(
                            cursor.getColumnIndex(AccountsTable.ACCOUNTS_NAME)
                    );
            String country =
                    cursor.getString(
                            cursor.getColumnIndex(AccountsTable.ACCOUNTS_COUNTRY)
                    );
            String gender =
                    cursor.getString(
                            cursor.getColumnIndex(AccountsTable.ACCOUNTS_GENDER)
                    );
            String birthdate =
                    cursor.getString(
                            cursor.getColumnIndex(AccountsTable.ACCOUNTS_BIRTHDATE)
                    );
            accounts.add(new AccountsPojo(name, country, gender, birthdate));

            Log.d(TAG, "queryData: Reading entry " + name);
        }
        Log.d(TAG, "queryData: Cursor loop finished");
        AccountsAdapter adapter = new AccountsAdapter();
        adapter.setDataSet(accounts);
    }
}
