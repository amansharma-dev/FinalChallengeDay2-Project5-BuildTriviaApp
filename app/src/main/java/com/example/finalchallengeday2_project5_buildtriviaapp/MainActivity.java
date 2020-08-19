package com.example.finalchallengeday2_project5_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.finalchallengeday2_project5_buildtriviaapp.data.QuestionBank;
import com.example.finalchallengeday2_project5_buildtriviaapp.data.QuestionListAsyncResponse;
import com.example.finalchallengeday2_project5_buildtriviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionList = new QuestionBank().getQuestions(new QuestionListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d(TAG, "processFinished: "+questionArrayList);
            }
        });
    }
}