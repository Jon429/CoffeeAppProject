package com.example.coffeeappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword2;
    TextView loginRedirectText;
    Button createButton;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createButton = findViewById(R.id.createButton);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              database = FirebaseDatabase.getInstance();
              reference = database.getReference("user");

              String email = editTextEmail.getText().toString();
              String password = editTextPassword2.getText().toString();

              HelperClass helperClass = new HelperClass(email,password);
              reference.child(email).setValue(helperClass);

                Toast.makeText(SignUpActivity.this, "You have successfully signed up! ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignUpActivity.this, login.class);
                startActivity(intent);
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, login.class);
            }

        });

    }
}