package com.example.greenflag;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Period;

public class AccountInfoActivity extends AppCompatActivity {

    Spinner spnCountry;
    Button btnSave;
    Button btnChange;
    Button btnCalendar;
    ImageView ivPhoto;
    EditText etAge;
    LocalDate today = LocalDate.now();
    int mYear = today.getYear();
    int mMonth = today.getMonthValue();
    int mDay = today.getDayOfMonth();
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Green Flag");
        setContentView(R.layout.activity_account_info);

        Intent intent = getIntent();

        spnCountry = findViewById(R.id.spn_country);

        ArrayAdapter<CharSequence> spnAdapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_dropdown_item);

        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnCountry.setAdapter(spnAdapter);

        btnSave = findViewById(R.id.btn_save);
        btnChange = findViewById(R.id.btn_change_photo);
        btnCalendar = findViewById(R.id.btn_date_picker);
        ivPhoto = findViewById(R.id.iv_photo);
        etAge = findViewById(R.id.et_age);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_import_password);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivPhoto.getImageAlpha() == 255)
                    ivPhoto.setImageAlpha(256 / 2);
                else
                    ivPhoto.setImageAlpha(255);
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpBirthdate = new DatePickerDialog(AccountInfoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                LocalDate birthday = LocalDate.of(year, month + 1, dayOfMonth);
                                Period periodAge = Period.between(birthday, today);
                                String ageString = String.valueOf(periodAge.getYears());
                                etAge.setText(ageString);
                            }
                        }, mYear, mMonth, mDay);
                dpBirthdate.show();
            }
        });

        etUsername.setText(intent.getStringExtra("USERNAME"));
        etPassword.setText(intent.getStringExtra("PASSWORD"));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountInfoActivity.this, "Data saved!", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
