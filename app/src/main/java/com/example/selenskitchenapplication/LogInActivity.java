package com.example.selenskitchenapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void register(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
