package com.bnr.qizhang.cs_quizzes.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private static List<Question> mQuestions;

    public QuestionBank() {
        mQuestions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void addQuesiton(Question question) {
        mQuestions.add(question);
    }
}
