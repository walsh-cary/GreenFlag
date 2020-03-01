package com.example.greenflag;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExistingAccountActivity extends AppCompatActivity {
    private static final String TAG = "ExistingAccountActivity";
    RecyclerView recyclerView;
    AccountsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.accounts_recyclerview);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        queryData();
    }
    public void queryData() {
        AccountsDatabase database = new AccountsDatabase(this);
        SQLiteDatabase readable = database.getReadableDatabase();

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

        if(cursor.moveToNext()) {
            cursor.moveToFirst();
            do {
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
            } while (cursor.moveToNext());
        }
        Log.d(TAG, "queryData: Cursor loop finished");
        AccountsAdapter adapter = new AccountsAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setDataSet(accounts);
    }
}
