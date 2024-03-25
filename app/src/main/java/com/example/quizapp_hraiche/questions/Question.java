package com.example.quizapp_hraiche.questions;

import java.util.List;

public class Question {
    public String questionText;
    public List<String> options;
    public int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

