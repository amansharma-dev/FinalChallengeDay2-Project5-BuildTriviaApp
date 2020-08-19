package com.example.finalchallengeday2_project5_buildtriviaapp.data;

import com.example.finalchallengeday2_project5_buildtriviaapp.model.Question;

import java.util.ArrayList;

public interface QuestionListAsyncResponse {
     void processFinished(ArrayList<Question> questionArrayList);
}
