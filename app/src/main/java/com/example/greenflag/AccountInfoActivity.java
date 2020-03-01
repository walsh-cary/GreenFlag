package com.example.greenflag;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.invoke.MethodType;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AccountInfoActivity extends AppCompatActivity {
    private static final String TAG = "AccountInfoActivity";

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
    String accountBirthdate;
    RadioGroup genderSelect;
    String selected;

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
                                accountBirthdate = birthday.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
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
                saveData(
                        etUsername.getText().toString(),
                        spnCountry.getSelectedItem().toString(),
                        selected,
                        accountBirthdate
                );
                setResult(RESULT_OK);
                finish();
            }
        });
        genderSelect = findViewById(R.id.rg_gender);
        genderSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_male:
                        selected = "Male";
                        break;
                    case R.id.rb_female:
                        selected = "Female";
                        break;
                    case R.id.rb_neither:
                        selected = "Prefer not to answer";
                        break;
                }
            }
        });

    }

    public void saveData(String name, String country, String gender, String age) {
        Log.d(TAG, "saveData: Executed");
        AccountsDatabase database = new AccountsDatabase(this);
        SQLiteDatabase writable = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountsTable.ACCOUNTS_NAME, name);
        contentValues.put(AccountsTable.ACCOUNTS_COUNTRY, country);
        contentValues.put(AccountsTable.ACCOUNTS_GENDER, gender);
        contentValues.put(AccountsTable.ACCOUNTS_BIRTHDATE, age);

        long row = writable.insert(
                AccountsTable.TABLE_NAME,
                null,
                contentValues
        );

        if (row < 0) {
            Toast.makeText(
                    AccountInfoActivity.this,
                    "Failure",
                    Toast.LENGTH_SHORT
            ).show();
        }
        else {
            Toast.makeText(
                    AccountInfoActivity.this,
                    "Data saved!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
