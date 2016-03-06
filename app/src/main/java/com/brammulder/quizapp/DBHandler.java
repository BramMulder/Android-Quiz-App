package com.brammulder.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Bram on 31-5-2015.
 */
public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 21;
    private static final String DATABASE_NAME = "quiz.db";
    //Tables
    public static final String TABLE_QUESTIONS_FOOD = "questions_food";
    public static final String TABLE_QUESTIONS_HISTORY = "questions_history";
    public static final String TABLE_QUESTIONS_SCIENCE = "questions_science";
    public static final String TABLE_QUESTIONS_GAMES = "questions_games";
    public static final String TABLE_LEADERBOARD = "leaderboard";
    //Columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION_FOOD = "question_food";
    public static final String COLUMN_QUESTION_HISTORY = "question_history";
    public static final String COLUMN_QUESTION_SCIENCE = "question_science";
    public static final String COLUMN_QUESTION_GAMES = "question_games";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_USERNAME = "username";

    /**
     * DatabaseHandler constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Create SQL queries and execute
     * @param db    The Database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create_questions_food = "CREATE TABLE " + TABLE_QUESTIONS_FOOD + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION_FOOD + " TEXT, " +
                COLUMN_ANSWER + " TEXT" +
                ");";
        String query_create_questions_history = "CREATE TABLE " + TABLE_QUESTIONS_HISTORY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION_HISTORY + " TEXT, " +
                COLUMN_ANSWER + " TEXT" +
                ");";
        String query_create_questions_science_ = "CREATE TABLE " + TABLE_QUESTIONS_SCIENCE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION_SCIENCE + " TEXT, " +
                COLUMN_ANSWER + " TEXT" +
                ");";
        String query_create_questions_games = "CREATE TABLE " + TABLE_QUESTIONS_GAMES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION_GAMES + " TEXT, " +
                COLUMN_ANSWER + " TEXT" +
                ");";
        String query_create_leaderboard = "CREATE TABLE " + TABLE_LEADERBOARD + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SCORE + " INTEGER, " +
                COLUMN_USERNAME + " TEXT UNIQUE" +
                ");";
        //Execute SQL queries to create tables
        db.execSQL(query_create_questions_food);
        db.execSQL(query_create_questions_history);
        db.execSQL(query_create_questions_science_);
        db.execSQL(query_create_questions_games);
        db.execSQL(query_create_leaderboard);
    }

    /**
     * Execute SQL query when version changes
     * @param db            The Database
     * @param oldVersion    Old version of the DB
     * @param newVersion    New version of the DB
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_QUESTIONS_FOOD + "' ;");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_QUESTIONS_HISTORY + "' ;");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_QUESTIONS_SCIENCE + "' ;");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_QUESTIONS_GAMES + "' ;");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_LEADERBOARD + "' ;");

        onCreate(db);
        //Insert Food-Questions Into the Database
        populateDB_questions_food(db);
        //Insert History-Questions Into the Database
        populateDB_questions_history(db);
        //Insert Science-Questions Into the Database
        populateDB_questions_science(db);
        //Insert Games-Questions Into the Database
        populateDB_questions_games(db);
    }

    /**
     * Query to create populate the table questions_food with 10 questions
     * @param db        The Database
     */
    public void populateDB_questions_food(SQLiteDatabase db){
        //Insert Food-Questions Into the Database
        String query_questions_food=
        "INSERT INTO '" + TABLE_QUESTIONS_FOOD + "'" +
        "VALUES " + "(1, 'American Cheese is made from eggs.' , 'false'),"                  +
                    "(2, 'Whole wheat bread has more fiber than white bread.', 'true'),"    +
                    "(3, 'Sweet potatoes are an excellent source of iron.' , 'false'),"     +
                    "(4, 'Popcorn is a whole grain.' , 'true'),"                            +
                    "(5, 'A shrimp is a kind of fish.' , 'false'),"                         +
                    "(6, 'Corn on the cob is a good source of vitamin C.' , 'false'),"      +
                    "(7, 'Decaf coffee has caffeine in it.' , 'true'),"                     +
                    "(8, 'Bacteria multiply most rapidly at room temperature.' , 'true'),"  +
                    "(9, 'White chocolate is chocolate.' , 'false'),"                       +
                    "(10, 'Brown eggs are better for you than white eggs.' , 'false');";
        db.execSQL(query_questions_food);
    }

    /**
     * Query to create populate the table questions_history with 10 questions
     * @param db        The Database
     */
    public void populateDB_questions_history(SQLiteDatabase db){
        //Insert History-Questions Into the Database
        String query_questions_history=
        "INSERT INTO '" + TABLE_QUESTIONS_HISTORY + "'" +
        "VALUES " + "(1, 'The word [dinosaur] is derived from the Greek for [terrible lizard].' , 'true')," +
                    "(2, 'Big Ben is the clock in the tower of the Houses of Parliament' , 'false'),"        +
                    "(3, 'In the Netherlands, all governments since 1945 have been coalitions.' , 'true')," +
                    "(4, 'In Greek mythology, a Hydra had nine heads.' , 'true'),"                          +
                    "(5, 'The name of the worlds first nuclear-powered submarine is Nautilus.' , 'true')," +
                    "(6, 'The wild turkey was almost the United States National Bird.' , 'true'),"         +
                    "(7, 'ALl land mammals can jump.' , 'false'),"                                          +
                    "(8, 'The first plane was built by the Wright brothers.' , 'true'),"                    +
                    "(9, 'The Blues Brothers made mainly Jazz Music' , 'false'),"                           +
                    "(10, 'The Atomic Bomb dropped on Nagasaki during WW2 was called [Little Boy]' , 'false');";
        db.execSQL(query_questions_history);
    }

    /**
     * Query to create populate the table questions_science with 10 questions
     * @param db        The Database
     */
    public void populateDB_questions_science(SQLiteDatabase db){
        //Insert Science-Questions Into the Database
        String query_questions_science=
                "INSERT INTO '" + TABLE_QUESTIONS_SCIENCE + "'" +
        "VALUES " + "(1, 'A sneeze can travel at over 100 kilometers per hour.' , 'true'),"  +
                    "(2, 'Light travels at 300.000 meters per second', 'true'),"             +
                    "(3, 'Red blood cells carry oxygen around your body.' , 'true'),"        +
                    "(4, 'The earth is more than 5 billion years old.' , 'false'),"          +
                    "(5, 'The Earth is bigger than Jupiter.' , 'false'),"                    +
                    "(6, 'The Blue Whale is as loud as a Jumbo Jet.' , 'true'),"             +
                    "(7, 'Antibiotics kill viruses as well as bacteria.' , 'false'),"        +
                    "(8, 'Lasers work by focusing sound waves.' , 'false'),"                 +
                    "(9, 'All radioactivity is man-made.' , 'false'),"                       +
                    "(10, 'There is Helium inside our Sun' , 'true');";
        db.execSQL(query_questions_science);
    }

    /**
     * Query to create populate the table questions_games with 10 questions
     * @param db        The Database
     */
    public void populateDB_questions_games(SQLiteDatabase db){
        //Insert Game-Questions Into the Database
        String query_questions_games=
                "INSERT INTO '" + TABLE_QUESTIONS_GAMES + "'" +
        "VALUES " + "(1, 'E.T. on the Atari 2600 sold 1.5 million times.' , 'true'),"                                          +
                    "(2, 'The bushes in Super Mario Bros are the same sprite as the clouds', 'true'),"                         +
                    "(3, 'The 1-UP Mushroom in Mario was intended to be a drug reference .' , 'false'),"                       +
                    "(4, 'Goat Simulator has a [Dont try this on you farm] warning.' , 'true'),"                              +
                    "(5, '[Words with Friends] is not a rip-off of Scrabble.' , 'false'),"                                     +
                    "(6, 'The Xbox Kinect Sensor can detect other gaming consoles in the room and disable them' , 'false'),"   +
                    "(7, 'Half Life 3 will never be released.' , 'true'),"                                                     +
                    "(8, 'The Pokemon Game Designer who created [Dunsparce] was fired immediately for doing so.' , 'true'),"   +
                    "(9, 'The first ever Animal Crossing tournament was held in 2004 and is still going.' , 'false'),"         +
                    "(10, 'The developers of Dead Space were inspired to create the creatures by studying images of women without make-up ' , 'true');";
        db.execSQL(query_questions_games);
    }

}
