package com.example.quizapp_hraiche;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Results extends AppCompatActivity {
    TextView tvScore;
    Button bTryAgain, bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvScore = findViewById(R.id.tvScore);
        bTryAgain = findViewById(R.id.bTryAgain);
        bLogout = findViewById(R.id.bLogout);

        int maxQuestions = getResources().getInteger(R.integer.max_questions);
        int correctAnswers = getIntent().getIntExtra("score", -1);
        if (correctAnswers == -1) {
            Toast.makeText(this, "Sorry, something went wrong.", Toast.LENGTH_SHORT).show();
            finish();
        }

        String scoreText = correctAnswers + " / " + maxQuestions;
        tvScore.setText(scoreText);

        bTryAgain.setOnClickListener(v -> finish());
        bLogout.setOnClickListener(v -> {
            finish();
            finishAffinity();
        });
    }
}