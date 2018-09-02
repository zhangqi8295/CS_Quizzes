package com.bnr.qizhang.cs_quizzes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bnr.qizhang.cs_quizzes.model.Question;
import com.bnr.qizhang.cs_quizzes.model.QuestionBank;

import java.util.Objects;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = QuizActivity.class.getSimpleName();
    private static final int REQUEST_CHEAT_ID = 1;

    private TextView mQuestionText;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mNextButton;
    private Button mPrevButton;

    QuestionBank questionBank = QuestionBank.get();

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

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CheatActivity.newIntent(QuizActivity.this, questionBank.getQuestions().get(mCurrentIndex).getId());
               // startActivity(intent);
                startActivityForResult(intent, REQUEST_CHEAT_ID);
            }
        });
    }

    private void updateQuestion() {
        int textId = questionBank.getQuestions().get(mCurrentIndex).getQusestionText();
        mQuestionText.setText(textId);
    }

    private void checkAnswer(boolean userPressed) {
        int toastId = (userPressed == questionBank.getQuestions().get(mCurrentIndex).isTrue()) ? R.string.correct_toast : R.string.incorrect_toast;

        if (questionBank.getQuestions().get(mCurrentIndex).isCheater()) {
            toastId = R.string.judgement_text;
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult get called");
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Log.d(TAG, "is data null? " + (data == null));
        switch (requestCode) {
            case REQUEST_CHEAT_ID: {
                if (data == null) {
                    return;
                }

                if (CheatActivity.isCheater(data)) {
                    questionBank.getQuestions().get(mCurrentIndex).setCheater(true);
                }

            } break;

            default: break;
        }
    }
}
