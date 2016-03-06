package com.brammulder.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.acitivity_main_menu);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Launches Settings or Leaderboard class depending on which button clicked
     * @param v
     */
    public void onButtonClickedOther(View v){
        Intent i;
        if(v.getId() == R.id.settings) {
            i = new Intent(getApplicationContext(),
                    Settings.class);
                    startActivity(i);
        }else if(v.getId() == R.id.leaderboards){
            i = new Intent(getApplicationContext(),
                    Leaderboard.class);
                    startActivity(i);
        }
    }

    /**
     * Launches the Questions class and passes a category value along
     * @param v
     */
    public void onButtonClickedCategory(View v){
        Intent i;
        i = new Intent(getApplicationContext(),
                Questions.class);
        if(v.getId() == R.id.food) {
            i.putExtra("category", "food");
        }else if(v.getId() == R.id.history) {
            i.putExtra("category", "history");
        }else if(v.getId() == R.id.science) {
            i.putExtra("category", "science");
        }else if(v.getId() == R.id.games) {
            i.putExtra("category", "games");
        }
        startActivity(i);
    }

    /**
     *Returns to Android Menu
     */
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}

