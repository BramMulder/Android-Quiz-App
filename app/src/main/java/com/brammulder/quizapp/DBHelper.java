package com.brammulder.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Bram on 21-6-2015.
 * Handles SQL queries on database
 */
public class DBHelper extends SQLiteOpenHelper {

    DBHandler dbHandler;

    public DBHelper(Context context) {
        super(context, "quiz.db", null, 21);
        dbHandler = new DBHandler(context, null, null, 1);
    }

    /**
     * Requests all the Usernames from the database and puts then into an Arraylist using a cursor
     * @return  Arraylist with Usernames
     */
    public ArrayList databaseToArraylist(){
        ArrayList<String> dbArraylist = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT " + "username" +
                " FROM " + "leaderboard" ;

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your result
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("username"))!= null){
                dbArraylist.add(c.getString(c.getColumnIndex("username")));
            }
            c.moveToNext();
        }

        db.close();
        return dbArraylist;
    }

    /**
     * Requests all the Scores from the database and puts then into an Arraylist using a cursor
     * @return  Arraylist with Scores
     */
    public ArrayList databaseToScorelist(){
        ArrayList<String> dbArraylist = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT " + "score" +
                " FROM " + "leaderboard" ;
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your result
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("score"))!= null){
                dbArraylist.add(c.getString(c.getColumnIndex("score")));
            }
            c.moveToNext();
        }

        db.close();
        return dbArraylist;
    }

    /**
     * Adds the name the user has enterd at the login screen into the Database
     * @param user  instantiation of the User class
     */
    public void addName(Users user) {
        ContentValues values = new ContentValues();
        values.put("username", user.get_name());
        values.put("score", 0);
        SQLiteDatabase db = getWritableDatabase();
        db.insertOrThrow("leaderboard", null, values);
        db.close();
    }

    /**
     * Get the questions from the database for Questions.class
     * @param question_group    String which defines which category questions is required (given via the intent extra)
     * @return String[]         The questions in a String Array
     */
    public String[] getQuestions(String question_group) {

        String[] questions = new String[10];

        String database_location = "questions_" + question_group;
        SQLiteDatabase db = getWritableDatabase();

        //Request Questions and put in String Array
        String query_questions = " SELECT " + "question_" + question_group  +
                " FROM " + database_location + ";";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query_questions, null);
        //Move to the first row in your result
        c.moveToFirst();

        int counter = 0;
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("question_"+question_group)) != null) {
                questions[counter] = c.getString(c.getColumnIndex("question_"+ question_group));
                counter++;
            }
            c.moveToNext();
        }

        db.close();
        return questions;
    }

    /**
     * Get the answers from the database for Questions.class
     * @param question_group    String which defines which category questions is required (given via the intent extra)
     * @return String[]         The answers in a String Array
     */
    public String[] getAnswers(String question_group){

        String[] answers = new String[10];

        String database_location = "questions_" + question_group;
        SQLiteDatabase db = getWritableDatabase();

        //Request answers and put in String Array
        String query_answers = " SELECT " + "answer" +
                " FROM " + database_location + ";";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query_answers, null);
        //Move to the first row in your result
        c.moveToFirst();

        int counter = 0;
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("answer"))!= null){
                answers[counter] = c.getString(c.getColumnIndex("answer"));
                counter++;
            }
            c.moveToNext();
        }

        db.close();
        return answers;
    }

    /**
     * Adds the user's score into the Database
     * @param userName      The user's name which is entered at LoginScreen
     * @param score         The user's score
     */
    //Adds the user's score to the database
    public void addUserScore(String userName, int score){
        SQLiteDatabase db = getWritableDatabase();
        try {
            String addScore = "UPDATE " + "leaderboard" +
                    " SET " + "score" + " = " + score +
                    " WHERE " + "username" + " = " + "'" + userName + "'" + ";";
            db.execSQL(addScore);
        }catch(Exception e){
            e.printStackTrace();
            Log.d(DBHandler.class.getSimpleName(), "Name not in database");
        }
        db.close();
    }

    /**
     * Get the highest score the user has achieved so far
     * @param userName   The user's name which is entered at LoginScreen
     * @return           The user's highest score
     */
    public int getHighestScore (String userName){
        SQLiteDatabase db = getWritableDatabase();
        int highestScore = 0;
        try{
            String checkScore = "SELECT " + "score" +
                    " FROM " + "leaderboard" +
                    " WHERE " + "username" + " LIKE " + "'%" + userName + "%'" + ";";
            //Cursor point to a location in your results
            Cursor c = db.rawQuery(checkScore, null);
            //Move to the first row in your result
            c.moveToFirst();
            //Put highest value in highestScore
            highestScore = c.getInt(c.getColumnIndex("score"));

        }catch(Exception e){
            e.printStackTrace();
            Log.d(DBHandler.class.getSimpleName(), "No Score");
        }

        db.close();
        return highestScore;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
