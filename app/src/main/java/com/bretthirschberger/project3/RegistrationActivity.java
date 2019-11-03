package com.bretthirschberger.project3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mDOBField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;

    private TextView mNameError;
    private TextView mPasswordError;
    private TextView mEmailError;
    private TextView mDOBError;

    private Button mRegisterButton;

    private RadioGroup mUserTypeSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        mFirstNameField = findViewById(R.id.first_name_field);
        mLastNameField = findViewById(R.id.last_name_field);
        mDOBField = findViewById(R.id.dob_field);
        mEmailField = findViewById(R.id.email_field_2);
        mPasswordField = findViewById(R.id.password_field_2);
        mConfirmPasswordField = findViewById(R.id.confirm_password_field);
        mRegisterButton = findViewById(R.id.register_button);
        mNameError = findViewById(R.id.name_err);
        mPasswordError = findViewById(R.id.password_err);
        mEmailError = findViewById(R.id.email_err);
        mDOBError = findViewById(R.id.dob_err);
        mUserTypeSelect = findViewById(R.id.type_select);

        //fills in edit text fields on rotation
        if (savedInstanceState != null) {
            mFirstNameField.setText(savedInstanceState.getString("first name", "ERROR"));
            mLastNameField.setText(savedInstanceState.getString("last name", "ERROR"));
            mDOBField.setText(savedInstanceState.getString("dob", "ERROR"));
            mEmailField.setText(savedInstanceState.getString("email", "ERROR"));
            mPasswordField.setText(savedInstanceState.getString("password", "ERROR"));
            mConfirmPasswordField.setText(savedInstanceState.getString("confirm password", "ERROR"));
        }



        mFirstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkName();
                checkValidEntry();
            }
        });
        mLastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkValidEntry();
            }
        });
        mDOBField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkDate();
                checkValidEntry();
            }
        });
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEmail();
                checkValidEntry();
            }
        });
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPassword();
                checkValidEntry();
            }
        });
        mConfirmPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPassword();
                checkValidEntry();
            }
        });

        mUserTypeSelect.setOnCheckedChangeListener((group,checkedId) -> checkValidEntry());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("first name", mFirstNameField.getText().toString());
        outState.putString("last name", mLastNameField.getText().toString());
        outState.putString("dob", mDOBField.getText().toString());
        outState.putString("email", mEmailField.getText().toString());
        outState.putString("password", mPasswordField.getText().toString());
        outState.putString("confirm password", mConfirmPasswordField.getText().toString());
    }

    public void checkName() {
        if (mFirstNameField.getText().toString().trim().length() < 3) {
            mNameError.setText(R.string.name_short);
        } else if (mFirstNameField.getText().toString().trim().length() > 30) {
            mNameError.setText(R.string.name_long);
        } else {
            mNameError.setText("");
        }
    }

    public void checkDate() {
        if (!isValidDate()) {
            mDOBError.setText(R.string.invalid_date);
        } else {
            mDOBError.setText("");
        }
    }

    public void checkPassword() {
        if (!mPasswordField.getText().toString().equals(mConfirmPasswordField.getText().toString())) {
            mPasswordError.setText(R.string.password_match);
        } else {
            mPasswordError.setText("");
        }
    }

    public void checkEmail() {
        if (!isValidEmail()) {
            mEmailError.setText(R.string.invalid_email);
        } else {
            mEmailError.setText("");
        }
    }

    public void checkValidEntry() {

        if (mFirstNameField.getText().toString().trim().length() >= 3 &&
                mFirstNameField.getText().toString().trim().length() < 30 &&
                !mLastNameField.getText().toString().trim().equals("") &&
                !mPasswordField.getText().toString().equals("") &&
                mPasswordField.getText().toString().equals(mConfirmPasswordField.getText().toString()) &&
                isValidDate() &&
                isValidEmail()&&
                !(mUserTypeSelect.getCheckedRadioButtonId()==-1)) {
            mRegisterButton.setEnabled(true);
        } else {
            mRegisterButton.setEnabled(false);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public boolean isValidDate() {

        try {
            new SimpleDateFormat("MM/dd/yyyy").parse(mDOBField.getText().toString().trim());
            return true;
        } catch (ParseException e) {

        }
        try {
            new SimpleDateFormat("MM-dd-yyyy").parse(mDOBField.getText().toString().trim());
            return true;
        } catch (ParseException e) {

        }
        try {
            new SimpleDateFormat("MM/dd/yy").parse(mDOBField.getText().toString().trim());
            return true;
        } catch (ParseException e) {

        }
        try {
            new SimpleDateFormat("MM-dd-yy").parse(mDOBField.getText().toString().trim());
            return true;
        } catch (ParseException e) {

        }
        return false;
    }

    public boolean isValidEmail() {
        //Standard regex for emails
        String rfc5322 = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\" +
                ".[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\" +
                "x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\" +
                "x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\" +
                ".)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\" +
                "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\" +
                ".){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\" +
                "x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        return Pattern.compile(rfc5322, Pattern.CASE_INSENSITIVE).matcher(mEmailField.getText().toString().trim()).matches();
    }

    public void register(View view) {
        String email = mEmailField.getText().toString().trim();
        String name = mFirstNameField.getText().toString().trim() + " " + mLastNameField.getText().toString().trim();
        String dob = mDOBField.getText().toString().trim();
        String password = mPasswordField.getText().toString();
        boolean isParent=true;

        switch (mUserTypeSelect.getCheckedRadioButtonId()){
            case R.id.parent_option:
                isParent=true;
                break;
            case R.id.student_option:
                isParent=false;
                break;
        }

        UserDatabaseHandler handler = new UserDatabaseHandler(this,null);
        handler.addUser(new User(email,name,password,dob,isParent));

        Toast.makeText(getApplicationContext(), getString(R.string.account_created), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));

    }
}