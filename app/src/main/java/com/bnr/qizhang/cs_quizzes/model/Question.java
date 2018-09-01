package com.bnr.qizhang.cs_quizzes.model;

import java.util.UUID;

public class Question {
    private UUID mId;
    private int mQusestionText = 0;
    private boolean mIsTrue;
    private boolean mIsCheater;

    public Question() {
        mId = UUID.randomUUID();
    }

    public Question(int qusestionText, boolean isTrue) {
        mId = UUID.randomUUID();
        mQusestionText = qusestionText;
        mIsTrue = isTrue;
    }

    public UUID getId() {
        return mId;
    }

    public int getQusestionText() {
        return mQusestionText;
    }

    public void setQusestionText(int qusestionText) {
        mQusestionText = qusestionText;
    }

    public boolean isTrue() {
        return mIsTrue;
    }

    public void setTrue(boolean aTrue) {
        mIsTrue = aTrue;
    }

    public boolean isCheater() {
        return mIsCheater;
    }

    public void setCheater(boolean cheater) {
        mIsCheater = cheater;
    }
}
