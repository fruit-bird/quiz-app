package com.example.quizapp_hraiche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private static final String TAG = Register.class.getSimpleName();

    EditText etUsername, etEmail, etPassword, etConfirmPassword;
    Button bRegister, bLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        bRegister = findViewById(R.id.bRegister);
        bLogin = findViewById(R.id.bLogin);

        mAuth = FirebaseAuth.getInstance();

        bRegister.setOnClickListener(v -> {
            if (etUsername.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etConfirmPassword.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            signUp(email, password);
        });

        bLogin.setOnClickListener(v -> startActivity(new Intent(this, Login.class)));
    }

    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signUpWithEmail:success");
                        Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Quiz.class));
                    } else {
                        Log.d(TAG, "signUpWithEmail:failure");
                        Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}