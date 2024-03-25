package com.example.quizapp_hraiche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp_hraiche.questions.Question;
import com.example.quizapp_hraiche.questions.QuizLoader;

import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {
    private static final String TAG = Quiz.class.getSimpleName();
    private static final int MAX_QUESTIONS = 5;

    TextView tvQuestion;
    RadioGroup rgOptions;
    Button bNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        bNext = findViewById(R.id.bNext);

        // Setting the question and options
        List<Question> questions = new QuizLoader().loadQuestions(this);

        int index = new Random().nextInt(questions.size());
        Question question = questions.get(index);
        questions.remove(index);
        tvQuestion.setText(question.questionText);
        for (int i = 0; i < 4; i++) {
            RadioButton radioButton = (RadioButton) rgOptions.getChildAt(i);
            radioButton.setText(question.options.get(i));
        }

        bNext.setOnClickListener(v -> {
            if (rgOptions.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), R.string.no_answer_given, Toast.LENGTH_SHORT).show();
                return;
            }

            int choice = rgOptions.indexOfChild(findViewById(rgOptions.getCheckedRadioButtonId()));
            int score = getIntent().getIntExtra("score", 0);
            if (choice == question.correctAnswerIndex) {
                score++;
                Log.i(TAG, "Correct answer. Score: " + score);
            } else {
                Log.i(TAG, "Incorrect answer. Score: " + score);
            }

            int nextQuestionNumber = getIntent().getIntExtra("qst_num", 0) + 1;
//            Log.i("Quiz1", "Next question number: " + nextQuestionNumber);
            if (nextQuestionNumber == MAX_QUESTIONS) {
                Intent resultsIntent = new Intent(this, Results.class);
                resultsIntent.putExtra("score", score);
                startActivity(resultsIntent);
                finish();
            } else {
                Intent nextQuestionIntent = new Intent(this, Quiz.class);
                nextQuestionIntent.putExtra("score", score);
                nextQuestionIntent.putExtra("qst_num", nextQuestionNumber);
                startActivity(nextQuestionIntent);
                finish();
            }
        });
    }
}