package com.example.finalchallengeday2_project5_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalchallengeday2_project5_buildtriviaapp.data.QuestionBank;
import com.example.finalchallengeday2_project5_buildtriviaapp.data.QuestionListAsyncResponse;
import com.example.finalchallengeday2_project5_buildtriviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    private List<Question> questionList;

    private CardView cardView;
    private TextView question_tv;
    private Button false_btn;
    private Button true_btn;
    private ImageButton previous_imageBtn;
    private ImageButton next_imageBtn;
    private int questionCounter = 0;
    private TextView calculateQuestionNumber_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.cardView);
        question_tv = findViewById(R.id.question_textView);
        false_btn = findViewById(R.id.false_button);
        true_btn = findViewById(R.id.true_button);
        previous_imageBtn = findViewById(R.id.previous_ImageButton);
        next_imageBtn = findViewById(R.id.next_ImageButton);
        calculateQuestionNumber_tv = findViewById(R.id.calculateQuestionNumbers_textView);


        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorCard));

        false_btn.setOnClickListener(this);
        true_btn.setOnClickListener(this);
        previous_imageBtn.setOnClickListener(this);
        next_imageBtn.setOnClickListener(this);

        questionList = new QuestionBank().getQuestions(new QuestionListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d(TAG, "processFinished: "+questionArrayList);
                calculateQuestionNumber();
                updateQuestion();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.false_button:
                checkIfAnswer(false);
                break;
            case R.id.true_button:
                checkIfAnswer(true);
                break;
            case R.id.previous_ImageButton:
                if (questionCounter>0) {
                    questionCounter = (questionCounter - 1) % questionList.size();
                    updateQuestion();
                } else Toast.makeText(getApplicationContext(), R.string.cant_go_back, Toast.LENGTH_SHORT).show();
                break;

            case R.id.next_ImageButton:
                questionCounter = (questionCounter + 1) % questionList.size();
                updateQuestion();
                break;
        }
    }

    private void updateQuestion(){
        String question = questionList.get(questionCounter).getQuestion();
        question_tv.setText(question);
        calculateQuestionNumber();
    }

    private void checkIfAnswer(boolean ifAnswerTrue){
        boolean answer = questionList.get(questionCounter).isAnswerTrue();
        if(ifAnswerTrue == answer){
            Toast.makeText(getApplicationContext(), R.string.correct_answer,Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getApplicationContext(), R.string.wrong_answer,Toast.LENGTH_SHORT).show();
    }

    private void calculateQuestionNumber(){

        calculateQuestionNumber_tv.setText(questionCounter+"/"+questionList.size());
    }
}