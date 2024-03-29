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

import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {
    private static final String TAG = Quiz.class.getSimpleName();

//    1. declaration
    TextView tvQuestion;
    RadioGroup rgOptions;
    Button bNext;
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

//        2. recup des ids
        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        bNext = findViewById(R.id.bNext);

        // Randomizing the order of questions
        questions = new QuizLoader().loadQuestions(this);
        Collections.shuffle(questions);

        int currQuestionIndex = getIntent().getIntExtra("qst_num", 0);
        Question question = questions.get(currQuestionIndex);

        tvQuestion.setText(question.questionText);
        for (int i = 0; i < 4; i++) {
            RadioButton radioButton = (RadioButton) rgOptions.getChildAt(i);
            radioButton.setText(question.options.get(i));
        }

//        3. associer des listener
        bNext.setOnClickListener(v -> {
            if (rgOptions.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), R.string.no_answer_given, Toast.LENGTH_SHORT).show();
                return;
            }

            int choice = rgOptions.indexOfChild(findViewById(rgOptions.getCheckedRadioButtonId()));
            int score = getIntent().getIntExtra("score", 0);
            if (choice == question.correctAnswerIndex) {
                score++;
                Log.d(TAG, "Correct answer.\t\tScore: " + score);
            } else {
                Log.d(TAG, "Incorrect answer.\tScore: " + score);
            }

            int maxQuestions = getResources().getInteger(R.integer.max_questions);
            int nextQuestionNumber = getIntent().getIntExtra("qst_num", 0) + 1;
//            Log.d(TAG, "Next question number: " + nextQuestionNumber);

            if (nextQuestionNumber == maxQuestions) {
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