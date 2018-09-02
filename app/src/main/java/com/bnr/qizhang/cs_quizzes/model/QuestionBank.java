package com.bnr.qizhang.cs_quizzes.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private static QuestionBank sQuestionBank;
    private static List<Question> mQuestions;

    private QuestionBank() {
        mQuestions = new ArrayList<>();
    }

    public static List<Question> getQuestions() {
        return mQuestions;
    }

    public static void addQuesiton(Question question) {
        mQuestions.add(question);
    }

    public static QuestionBank get() {
        if (sQuestionBank == null) {
            sQuestionBank = new QuestionBank();
        }

        return sQuestionBank;
    }
}
