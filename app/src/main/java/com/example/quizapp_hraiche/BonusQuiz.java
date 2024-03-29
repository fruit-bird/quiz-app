package com.example.quizapp_hraiche;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BonusQuiz extends AppCompatActivity {
    private static final String TAG = BonusQuiz.class.getSimpleName();
    TextView tvQuestion;
    RadioGroup rgOptions;
    RadioButton rbYes, rbNo;
    Button bNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        rbYes = findViewById(R.id.rbYes);
        rbNo = findViewById(R.id.rbNo);
        bNext = findViewById(R.id.bNext);

        bNext.setOnClickListener(v -> {
            if (rgOptions.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), R.string.no_answer_given, Toast.LENGTH_SHORT).show();
                return;
            }

            int score = getIntent().getIntExtra("score", 0);

            if (rbYes.isChecked()) {
                Intent alternateIntent = new Intent(this, AlternateEnding.class);
                startActivity(alternateIntent);
                finish();
            } else {
                Intent resultsIntent = new Intent(this, Results.class);
                resultsIntent.putExtra("score", score);
                startActivity(resultsIntent);
                finish();
            }
        });
    }
}