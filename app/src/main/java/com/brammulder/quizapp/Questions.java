package com.brammulder.quizapp;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Questions extends Activity {

    private String question_group = "Error";
    private int user_score = 0;
    public int global_counter = 0;

    private String[] questions = new String[10];
    private String[] answers_database = new String[10];
    private String[] answers_database_shuffled = new String[10];
    private String[] answers_user = new String[10];
    private List<Integer> indexArray = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * Gets category value given by intent
     * Request the questions linked to the category from the Database
     * Displays the questions in the text-field
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_quiz_questions);

        //Get category value
        Intent i = getIntent();
        question_group = i.getStringExtra("category");

        //Fill Arrays with questions and answers
        DBHelper dbHelper = new DBHelper(this);
        questions =  dbHelper.getQuestions(question_group);
        answers_database = dbHelper.getAnswers(question_group);

        //Shuffle Question Pick-Order
        Collections.shuffle(indexArray);

        //Fill Array with answers from Database
        for(int x = 0; x < 10; x++){
            answers_database_shuffled[indexArray.get(x)] = answers_database[x];
        }

        //Set Question in text display
        TextView displayQuestion = (TextView) findViewById(R.id.textView_question);
        displayQuestion.setText(questions[indexArray.get(0)]);
    }

    /**
     * When the true button is clicked, it adds the true value to the user answer list
     * Loads the new question
     * @param v
     */
    public void onButtonClickedTrue(View v){
        try {
            answers_user[global_counter] = "true";
            global_counter++;
            if (global_counter == 10) {
                countScore();
            } else {
                TextView displayQuestion = (TextView) findViewById(R.id.textView_question);
                displayQuestion.setText(questions[indexArray.get(global_counter)]);
            }
        }catch(Exception e){
            String popup = "Please Return to Main Menu";
            Toast.makeText(Questions.this, popup, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * When the false button is clicked, it adds the true value to the user answer list
     * Loads the new question
     * @param v
     */
    public void onButtonClickedFalse(View v){
        try {
            answers_user[global_counter] = "false";
            global_counter++;
            if(global_counter == 10){
                countScore();
            }else {
                TextView displayQuestion = (TextView) findViewById(R.id.textView_question);
                displayQuestion.setText(questions[indexArray.get(global_counter)]);
            }
        }catch(Exception e){
            String popup = "Please Return to Main Menu";
            Toast.makeText(Questions.this, popup, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Counts the score when 10 questions have been answered
     * Compares the actual answers with the user's answers and calculates score
     */
    public void countScore(){
        for(int i = 0 ; i < 10; i++) {
            if (answers_database_shuffled[i].equals(answers_user[i])) {
                user_score++;
            }
        }
        Intent intent;
        intent = new Intent(getApplicationContext(),
                userScoreEnd.class);
                intent.putExtra("userscore_total", user_score);
        startActivity(intent);


        if(answers_database_shuffled.length != answers_user.length){
            Toast toast = Toast.makeText(getApplicationContext(), "Error on Length", Toast.LENGTH_LONG);
            toast.show();
            }
        }

}
