package com.alnadal.nada.geoquiz1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuiz1 extends AppCompatActivity {
    private static final String TAG="GeoQuiz1";
    private static final String KYE_INDEX="index";


    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;
    private static final int REQUUEST_CODE_CHEAT=0;
    private boolean mIsCheater;

    private  question[] mQuestionBank =new question[]{
            new question(R.string.question_1,false),
            new question(R.string.question_22,true),
            new question(R.string.question_3,true),
            new question(R.string.question_4,true),
            new question(R.string.question_5,true),
    };

    private int mCurrentIndex=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_geo_quiz1);


        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KYE_INDEX,0);

        }

        mQuestionTextView=(TextView) findViewById(R.id.textView);
        mNextButton=(Button)findViewById(R.id.next_button1);
        mTrueButton =(Button) findViewById(R.id.true_button);
        mFalseButton =(Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast toast =Toast.makeText(GeoQuiz1.this,"correct_toast",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();*/
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*  Toast toast =Toast.makeText(GeoQuiz1.this,"Incorrect_toast",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();*/

                checkAnswer(false);

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)% mQuestionBank.length;
                updateQuestion();
            }
        });

        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent=new Intent(GeoQuiz1.this,CheatActivity.class);
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent=CheatActivity.newIntent(GeoQuiz1.this,answerIsTrue);
               // startActivity(intent);
                startActivityForResult(intent,REQUUEST_CODE_CHEAT);
            }
        });
        mIsCheater=false;
        updateQuestion();
    }
    protected void  updateQuestion(){
        int question =mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUUEST_CODE_CHEAT){
            if(data==null){
                return;
            }
            mIsCheater=CheatActivity.wasAnswerShown(data);
        }
    }

    private void checkAnswer(boolean userPressedTrue){

        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;

        if(mIsCheater){

            messageResId=R.string.judgment_toast;

        }else{
            if(userPressedTrue==answerIsTrue){
                messageResId=R.string.corect_toast;
            }else{
                messageResId=R.string.Incorect_toast;
            }
        }
       Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }




    @Override
    public  void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    public void onResume(){
        super.onResume();
        Log.d(TAG ,"onResum() called");
    }
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    public void onSaveInstanceState(Bundle SaveInstanceState){
        super.onSaveInstanceState(SaveInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        SaveInstanceState.putInt(KYE_INDEX,mCurrentIndex);
    }

    }

