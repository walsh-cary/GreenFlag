package com.example.greenflag;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        AccountsDatabase db = new AccountsDatabase(this);

        adapter = new AccountsAdapter();

        recyclerView.setAdapter(adapter);

        db.queryData();
    }
}
