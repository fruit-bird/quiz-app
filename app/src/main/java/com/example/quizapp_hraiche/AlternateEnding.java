package com.example.quizapp_hraiche;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlternateEnding extends AppCompatActivity {

    Button bEnd1, bEnd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate_ending);

        bEnd1 = findViewById(R.id.bEnd1);
        bEnd2 = findViewById(R.id.bEnd2);

        bEnd1.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "-100 Social Credits", Toast.LENGTH_SHORT).show());
        bEnd2.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "-1,000 Social Credits", Toast.LENGTH_SHORT).show());
    }
}