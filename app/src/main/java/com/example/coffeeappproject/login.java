package com.example.coffeeappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    TextView signupRedirectText;

    EditText loginEmail, loginPassword;
    Button loginButton2;
    FirebaseDatabase database;
    DatabaseReference reference;

    public class LoginActivity extends AppCompatActivity {
        EditText loginEmail, loginPassword;
        Button loginButton2;
        TextView signupRedirectText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            loginButton2 = findViewById(R.id.loginButton2);

            loginButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(login.this, Selections.class);
                    startActivity(intent);
                }
            });




            loginEmail = findViewById(R.id.editTextEmailAddress3);
            loginPassword = findViewById(R.id.editTextPassword);
            signupRedirectText = findViewById(R.id.signupRedirectText);
            loginButton2 = findViewById(R.id.loginButton2);

            loginButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!validateEmail() | !validatePassword()) {

                    } else {
                        checkUser();
                    }
                }
            });
            signupRedirectText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(login.this, SignUpActivity.class);
                    startActivity(intent);
                }
            });

        }

        public boolean validateEmail() {
            String val = loginEmail.getText().toString();
            if (val.isEmpty()) {
                loginEmail.setError("Email cannot be empty");
                return false;
            } else {
                loginEmail.setError(null);
                return true;
            }
        }

        public boolean validatePassword() {
            String val = loginPassword.getText().toString();
            if (val.isEmpty()) {
                loginPassword.setError("Password cannot be empty");
                return false;
            } else {
                loginPassword.setError(null);
                return true;
            }
        }

        public void checkUser() {
            String userEmail = loginEmail.getText().toString().trim();
            String userPassword = loginPassword.getText().toString().trim();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
            Query checkUserDatabase = reference.orderByChild("Email").equalTo(userEmail);
            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        loginEmail.setError(null);
                        String passwordFromDB = snapshot.child(userEmail).child("password").getValue(String.class);

                        if (!Objects.equals(passwordFromDB, userPassword)) {
                            loginEmail.setError(null);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            loginPassword.setError(" Invalid Password");
                            loginPassword.requestFocus();
                        }
                    } else {
                        loginEmail.setError("Email does not exist");
                        loginEmail.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}