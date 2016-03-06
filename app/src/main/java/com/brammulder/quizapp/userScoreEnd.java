package com.brammulder.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class userScoreEnd extends Activity {

    private int userScore = 0;
    private DBHelper dbHelper;
    private LoginScreen loginScreen;
    private int highestScore;

    /**
     * Displays user user's score
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Get user's score via intent
        Intent i = getIntent();
        userScore = i.getIntExtra("userscore_total", 9000);
        TextView displayUserScore = (TextView) findViewById(R.id.userScoreText);
        displayUserScore.setText("Score: " + userScore);

        dbHelper = new DBHelper(this);
        loginScreen = new LoginScreen();

        new backgroundOperation().execute("");
    }

    private class backgroundOperation extends AsyncTask<String, Void, String> {
        /**
         * Gets user's highest score and add new one if the newest one is higher than the one in the Database
         * @param params    No real function
         * @return          Gives back executed to confirm execution
         */
        @Override
        protected String doInBackground(String... params) {
        //Check for high-score
        highestScore = dbHelper.getHighestScore(loginScreen.getUserName());

        if(highestScore < userScore) {
            //Add score to database if higher than last
            dbHelper.addUserScore(loginScreen.getUserName(), userScore);
        }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView displayUserHighscore = (TextView) findViewById(R.id.userScoreHighest);
            displayUserHighscore.setText("High Score: " + highestScore);

            if(highestScore == 0){
                displayUserHighscore = (TextView) findViewById(R.id.userScoreHighest);
                displayUserHighscore.setText("High Score: " + userScore);
            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    /**
     * Returns to main menu instead of going back the the questions
     */
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),
                MainMenu.class);
        startActivity(i);
    }

    /**
     * opens Setting / Main Menu / Leaderboard depending on button pressed
     * @param V
     */
    public void openSettings(View V){
        Intent i = new Intent(getApplicationContext(),
                Settings.class);
        startActivity(i);
    };
    public void openMainMenu(View V){
        Intent i = new Intent(getApplicationContext(),
                MainMenu.class);
        startActivity(i);
    };
    public void openLeaderboard(View V){
        Intent i = new Intent(getApplicationContext(),
                Leaderboard.class);
        startActivity(i);
    };

}
