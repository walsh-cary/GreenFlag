package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCreateAccount, btnExistingAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // Link button variable to button in layout
        btnCreateAccount = findViewById(R.id.btn_create_account);
        btnExistingAccount = findViewById(R.id.btn_existing_account);

        // Create OnClickListener to launch login screen activity
        btnCreateAccount.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent newActivityIntent =
                                new Intent(MainActivity.this,
                                        LoginActivity.class);
                        startActivity(newActivityIntent);
                    }
                }
        );

        btnExistingAccount.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent newActivityIntent =
                                new Intent(MainActivity.this,
                                        ExistingAccountActivity.class);
                        startActivity(newActivityIntent);
                    }
                }
        );
    }


}
