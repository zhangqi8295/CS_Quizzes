package com.bnr.qizhang.cs_quizzes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bnr.qizhang.cs_quizzes.model.Question;
import com.bnr.qizhang.cs_quizzes.model.QuestionBank;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = QuizActivity.class.getSimpleName();

    private TextView mQuestionText;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mNextButton;
    private Button mPrevButton;

    QuestionBank questionBank = new QuestionBank();

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();
        initQuestions();

        updateQuestion();

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + questionBank.getQuestions().size() - 1) % questionBank.getQuestions().size();
                updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1 ) % questionBank.getQuestions().size();
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        int textId = questionBank.getQuestions().get(mCurrentIndex).getQusestionText();
        mQuestionText.setText(textId);
    }

    private void checkAnswer(boolean userPressed) {
        int toastId = (userPressed == questionBank.getQuestions().get(mCurrentIndex).isTrue()) ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this, toastId, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mQuestionText = findViewById(R.id.question_text);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mCheatButton = findViewById(R.id.cheat_button);
        mPrevButton = findViewById(R.id.prev_button);
        mNextButton = findViewById(R.id.next_button);
    }

    private void initQuestions() {
        addQuestion(R.string.question_one_true, true);
        addQuestion(R.string.question_two_true, true);
        addQuestion(R.string.question_three_false, false);
        addQuestion(R.string.question_four_false, false);
        addQuestion(R.string.question_five_true, true);
        addQuestion(R.string.question_six_true, true);
    }

    private void addQuestion(int quesitonText, boolean isTrue) {
        Question question = new Question(quesitonText, isTrue);
        questionBank.addQuesiton(question);
    }

}
