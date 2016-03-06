package com.brammulder.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class Settings extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_settings);
    }

    /**
     * Returns to main menu when the back-button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),
                MainMenu.class);
        startActivity(i);
    }
}
