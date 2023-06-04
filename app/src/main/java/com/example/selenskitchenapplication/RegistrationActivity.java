package com.example.selenskitchenapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private ProgressBar progressBar;

    private SharedPreferences sharedPreferences;

    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseApp.initializeApp(this);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        progressBar = findViewById(R.id.ProgressBar); // Replace R.id.progressBar with the actual ID of your ProgressBar in the XML layout

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        sharedPreferences = getSharedPreferences("Userinfo", 0);
    }

    private void register() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
            nameEditText.setError("Name is required");
            nameEditText.requestFocus();
            return;
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            emailEditText.setError("Email Address is required");
            emailEditText.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_LONG).show();
            emailEditText.setError("Valid email Address is required");
            emailEditText.requestFocus();
            return;
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        } else if (password.length() < 8) {
            Toast.makeText(this, "Password should be at least 8 characters", Toast.LENGTH_LONG).show();
            passwordEditText.setError("Password is too weak");
            passwordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        // Create user Profile
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            // Update Display Name of User
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            firebaseUser.updateProfile(profileChangeRequest);
                            // Enter User data into the Firebase Realtime Database
                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered User");
                            referenceProfile.child(firebaseUser.getUid()).setValue(firebaseUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // send Verification Email
                                        firebaseUser.sendEmailVerification();
                                        Toast.makeText(RegistrationActivity.this, "User registered successfully. Please verify your email", Toast.LENGTH_LONG).show();
                                        // open User profile after successful registration
                                        Intent intent = new Intent(RegistrationActivity.this, LogInActivity.class);
                                        // to prevent user from returning back to Register Activity on pressing back button after registration
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish(); //to close Register Activity
                                    } else {
                                        Toast.makeText(RegistrationActivity.this, "User registration failed. Please try again", Toast.LENGTH_LONG).show();
                                    }
                                    // Hide ProgressBar whether User creation is successful or failed
                                    progressBar.setVisibility(View.GONE);
                                }
                            });

                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                passwordEditText.setError("Your password is too weak. Please use a mix of alphabets, numbers, and special characters");
                                passwordEditText.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                passwordEditText.setError("Your password is invalid or already in use. Please re-enter.");
                                passwordEditText.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                passwordEditText.setError("A user is already registered with this password. Please use another password");
                                passwordEditText.requestFocus();
                            } catch (Exception e) {
                                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            // Hide ProgressBar whether User creation is successful or failed
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public void login(View view) {
        startActivity(new Intent(this, LogInActivity.class));
    }
}
