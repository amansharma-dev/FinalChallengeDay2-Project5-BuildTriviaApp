package com.example.finalchallengeday2_project5_buildtriviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.finalchallengeday2_project5_buildtriviaapp.controller.ApplicationController;
import com.example.finalchallengeday2_project5_buildtriviaapp.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    public static final String TAG = "QuestionBank";
    public static final String URL = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    private ArrayList<Question> questionArrayList;

    public List<Question> getQuestions(final QuestionListAsyncResponse callback){

        questionArrayList = new ArrayList<>();

         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

 //                       Log.d(TAG, "onResponse: "+response);
                        for (int i = 0; i<response.length(); i++){
                            try {
                                Question question = new Question();
                                question.setQuestion(response.getJSONArray(i).get(0).toString());
                                question.setAnswerTrue(response.getJSONArray(i).getBoolean(1));
                                questionArrayList.add(question);
//                                Log.d(TAG, "onResponse: "+question);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        if (callback != null) {
                            callback.processFinished(questionArrayList);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
            }
        });

        ApplicationController.getInstance().addToRequestQueue(jsonArrayRequest);
        return questionArrayList;
    }
}
