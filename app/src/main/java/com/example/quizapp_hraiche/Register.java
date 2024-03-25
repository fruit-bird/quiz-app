package com.example.quizapp_hraiche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    EditText etUsername, etEmail, etPassword, etConfirmPassword;
    Button bRegister;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        bRegister = findViewById(R.id.bRegister);
        tvRegister = findViewById(R.id.tvRegister);

        bRegister.setOnClickListener(v -> {
            if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                startActivity(new Intent(Register.this, Quiz1.class));
            }
        });

        tvRegister.setOnClickListener(v -> startActivity(new Intent(this, Login.class)));
    }
}