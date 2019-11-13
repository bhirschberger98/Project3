package com.bretthirschberger.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private EditText mEmailField;
    private EditText mPasswordField;

    private UserDatabaseHandler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new UserDatabaseHandler(this, null);

        mEmailField = findViewById(R.id.email_field_1);
        mPasswordField = findViewById(R.id.password_field_1);

    }

    public void login(View view) {
        User user = mHandler.getUser(mEmailField.getText().toString(), mPasswordField.getText().toString());
        if (true) {
//            Log.i("user", user.toString());
            startActivity(new Intent(getApplicationContext(),GameActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegister(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}