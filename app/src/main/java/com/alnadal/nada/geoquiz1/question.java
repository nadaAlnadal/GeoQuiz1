package com.alnadal.nada.geoquiz1;


public class question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public question(int textResId,boolean answerTrue){
        mTextResId=textResId;
        mAnswerTrue=answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
