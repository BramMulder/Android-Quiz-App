package com.brammulder.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

import java.util.ArrayList;


public class Leaderboard extends Activity {

    public DBHelper dbHelper;
    private LoginScreen loginScreen;
    private ArrayList scores;
    ListAdapter listAdapter;

    /**
     * Leaderboard ListView which lists the users using the custom adapter
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_leaderboard);

        dbHelper = new DBHelper(this);
        loginScreen = new LoginScreen();

        //Load scores into Arraylist
        scores = dbHelper.databaseToScorelist();

        listAdapter = new CustomListAdapter(this, dbHelper.databaseToArraylist());
        ListView leaderboard_list = (ListView) findViewById(R.id.leaderboard_list);
        leaderboard_list.setAdapter(listAdapter);

        leaderboard_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int Position, long id) {
                        String valueSelected = String.valueOf(scores.get(Position));
                        String popup = String.valueOf(valueSelected);
                        Toast.makeText(Leaderboard.this, popup, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),
                MainMenu.class);
        startActivity(i);
    }
}

