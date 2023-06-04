package com.example.selenskitchenapplication;

import android.widget.EditText;

public class ReadWriteUserDetails {
    public EditText emailEditText;
    public EditText passwordEditText;

    public ReadWriteUserDetails(EditText passwordEditText) {
        this.passwordEditText = passwordEditText;
    }
}
