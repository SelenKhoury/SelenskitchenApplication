package com.example.selenskitchenapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText;
    private Button registerButton;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Retrieve previously registered user data from SharedPreferences
            String registeredEmail = sharedPreferences.getString("email", "");
            String registeredPassword = sharedPreferences.getString("password", "");

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

           /* if (!email.equalsIgnoreCase(registeredEmail) || !password.equals(registeredPassword)) {
                Toast.makeText(this, "Email or password does not match the registered user", Toast.LENGTH_SHORT).show();
                return;
            }
*/
            // Save user registration data using SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

            // Redirect to the login activity
            startActivity(new Intent(this, LogInActivity.class));
            finish(); // Finish the current activity to prevent navigating back to it
        }

        public void login(View view) {
                startActivity(new Intent(this, LogInActivity.class));
        }
}
