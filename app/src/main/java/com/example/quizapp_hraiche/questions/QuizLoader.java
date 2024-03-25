package com.example.quizapp_hraiche.questions;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizLoader {
    private static List<Question> questions;

    public static List<Question> loadQuizQuestions(Context context, String fileName) {
        if (questions == null) {
            questions = new ArrayList<>();
            try {
                // Read JSON file from assets directory
                InputStream inputStream = context.getAssets().open(fileName);
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                String json = new String(buffer, "UTF-8");

                // Parse JSON data
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String questionText = jsonObject.getString("question");
                    JSONArray optionsArray = jsonObject.getJSONArray("options");
                    List<String> options = new ArrayList<>();
                    for (int j = 0; j < optionsArray.length(); j++) {
                        options.add(optionsArray.getString(j));
                    }
                    int correctAnswerIndex = jsonObject.getInt("correct_answer_index");
                    questions.add(new Question(questionText, options, correctAnswerIndex));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }

    public static Question getRandomQuestion(Context context, String fileName) {
        List<Question> questions = loadQuizQuestions(context, fileName);
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
}

