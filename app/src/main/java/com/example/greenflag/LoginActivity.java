package com.example.greenflag;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button btnNext;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordMatch;
    Boolean email_success = false;
    Boolean create_success = false;
    Boolean match_success = false;

    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Pattern email_pattern_match = Pattern.compile(EMAIL_PATTERN);

    String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,})";
    Pattern password_pattern_match = Pattern.compile(PASSWORD_PATTERN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        // Link variables to elements in layout
        btnNext = findViewById(R.id.btn_next);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_create_password);
        etPasswordMatch = findViewById(R.id.et_match_password);

        // Create OnClick listener to launch account info activity
        btnNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (match_success && create_success && email_success) {
                            Intent newActivityIntent =
                                    new Intent(LoginActivity.this, AccountInfoActivity.class);
                            newActivityIntent.putExtra("PASSWORD", etPassword.getText().toString());
                            newActivityIntent.putExtra("USERNAME", etEmail.getText().toString());
                            startActivityForResult(newActivityIntent, 2348);
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Check fields for validation", Toast.LENGTH_LONG).show();
                    }
                }
        );

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) {
                    etEmail.setBackground(getDrawable(R.drawable.edittext_bg_red));
                    email_success = false;
                }
                else {
                    Matcher email_matcher = email_pattern_match.matcher(s);
                    if (email_matcher.matches()) {
                        etEmail.setBackground(getDrawable(R.drawable.edittext_bg_green));
                        email_success = true;
                    }
                    else {
                        etEmail.setBackground(getDrawable(R.drawable.edittext_bg_red));
                        email_success = false;
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) {
                    etPassword.setBackground(getDrawable(R.drawable.edittext_bg_red));
                    create_success = false;
                }
                else {
                    Matcher password_matcher = password_pattern_match.matcher(s);
                    if (password_matcher.matches()) {
                        etPassword.setBackground(getDrawable(R.drawable.edittext_bg_green));
                        create_success = true;
                    }
                    else {
                        etPassword.setBackground(getDrawable(R.drawable.edittext_bg_red));
                        create_success = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPasswordMatch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etPasswordMatch.getText().toString() == null
                || etPasswordMatch.getText().toString().length() == 0) {
                    etPasswordMatch.setBackground(getDrawable(R.drawable.edittext_bg_red));
                    match_success = false;
                }
                else {
                    if (etPasswordMatch.getText().toString().equals(etPassword.getText().toString())) {
                        etPasswordMatch.setBackground(getDrawable(R.drawable.edittext_bg_green));
                        match_success = true;
                    }
                    else {
                        etPasswordMatch.setBackground(getDrawable(R.drawable.edittext_bg_red));
                        match_success = false;
                    }
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2348 && resultCode == RESULT_OK)
            finish();
    }
}
