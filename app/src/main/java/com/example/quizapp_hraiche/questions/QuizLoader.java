package com.example.quizapp_hraiche.questions;

import android.content.Context;
import android.util.Log;

import com.example.quizapp_hraiche.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuizLoader {
    private static final String TAG = QuizLoader.class.getSimpleName();

    public List<Question> loadQuestions(Context context) {
        List<Question> questions = new ArrayList<>();
        String json = loadJsonFromRaw(context);
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("questions");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject questionObject = jsonArray.getJSONObject(i);
                    String questionText = questionObject.getString("question");
                    JSONArray optionsArray = questionObject.getJSONArray("options");
                    List<String> options = new ArrayList<>();
                    for (int j = 0; j < optionsArray.length(); j++) {
                        options.add(optionsArray.getString(j));
                    }
                    int correctAnswerIndex = questionObject.getInt("correct_answer_index");

                    Question question = new Question(questionText, options, correctAnswerIndex);
                    questions.add(question);
                }
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON", e);
            }
        }
        return questions;
    }

    private String loadJsonFromRaw(Context context) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.questions);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON from raw resource", e);
        }
        return json;
    }
}
