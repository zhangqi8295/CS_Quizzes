package com.bnr.qizhang.cs_quizzes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bnr.qizhang.cs_quizzes.model.Question;
import com.bnr.qizhang.cs_quizzes.model.QuestionBank;

import java.util.List;
import java.util.UUID;

public class CheatActivity extends AppCompatActivity {
    private static final String TAG = CheatActivity.class.getSimpleName();
    private static final String EXTRA_QUESTION_ID = "com.bnr.qizhang.cs_quizzes_extra_question_id";
    private static final String EXTRA_CHEATER_ID = "com.bnr.qizhang.cs_quizzes_extra_is_cheater";

    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        final UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_QUESTION_ID);
        mShowAnswerButton = findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toastId = (getAnswer(uuid)) ? R.string.true_button : R.string.false_button;
                Toast.makeText(CheatActivity.this, toastId, Toast.LENGTH_SHORT).show();

                sendResult();
            }
        });

    }

    public void sendResult() {
        Intent data = new Intent();
        data.putExtra(EXTRA_CHEATER_ID, true);
        setResult(RESULT_OK, data);
    }

    public static boolean isCheater(Intent data) {
        return data.getBooleanExtra(EXTRA_CHEATER_ID, false);
    }

    public static Intent newIntent(Context packageContext, UUID uuid) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, uuid);
        return intent;
    }

    private boolean getAnswer(UUID uuid) {
        QuestionBank bank = QuestionBank.get();
        List<Question> list = bank.getQuestions();

        for (Question question : list) {
            if (question.getId().equals(uuid)) {
                return question.isTrue();
            }
        }

        return false;
    }
}
