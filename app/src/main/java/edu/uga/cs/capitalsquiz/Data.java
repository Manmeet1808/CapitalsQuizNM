package edu.uga.cs.capitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with DBHelper and meant to associate with quiz table
 */
public class Data {

    public static final String DEBUG_TAG = "QuizQuestions";

    // this is a reference to our database; it is used later to run SQL commands
    public Context myContext;
    private SQLiteDatabase db;
    private SQLiteOpenHelper CapitalsquizDbHelper;

    //defines the columns used in the table to store the information about each state
    private static final String[] allQuestionColumns = {
            CapitalQuizDBHelper.CAPITALS_COLUMN_ID,
            CapitalQuizDBHelper.CAPITALS_COLUMN_STATE,
            CapitalQuizDBHelper.CAPITALS_COLUMN_CAPITAL,
            CapitalQuizDBHelper.CAPITALS_COLUMN_CITY1,
            CapitalQuizDBHelper.CAPITALS_COLUMN_CITY2
    };
    //defines the columns used in the table to store the quizzes
    private static final String[] allQuizColumns = {
            CapitalQuizDBHelper.QUIZZES_COLUMN_ID,
            CapitalQuizDBHelper.QUIZZES_COLUMN_SCORE,
            CapitalQuizDBHelper.QUIZZES_COLUMN_DATE
    };

    public Data ( Context context ) {
        this.CapitalsquizDbHelper = CapitalQuizDBHelper.getInstance( context );
        this.myContext = context;
    }

    // Open the database
    public void open() {
        db = CapitalsquizDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "QuizQuestions: db open" );
    }

    // Close the database
    public void close() {
        if( CapitalsquizDbHelper != null ) {
            CapitalsquizDbHelper.close();
            Log.d(DEBUG_TAG, "QuizQuestions: db closed");
        }
    }

    //retrieve all the Quiz Questions and return them as a list
    public List<Questions> retrieveAllQuizQuestions() {
        ArrayList<Questions> questions = new ArrayList<>();
        Cursor cursor = null;
        Questions question;

        try {
            cursor = db.query( CapitalQuizDBHelper.TABLE_CITIES, allQuestionColumns,
                    null, null, null, null, null );

            if( cursor.getCount() > 1 ) {
                while( cursor.moveToNext() ) {
                    long id = cursor.getColumnIndex( CapitalQuizDBHelper.CAPITALS_COLUMN_ID );
                   String state = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_STATE ) );
                    String capital = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_CAPITAL ) );
                    String city1 = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY1 ) );
                    String city2 = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY2 ) );

                    question = new Questions(state, capital, city1, city2);
                    question.setId( id );
                    questions.add( question );
                    Log.d( DEBUG_TAG, "Retrieved Question: " + question );
                }
            }
            Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved questions
        return questions;
    }

    //retrieve all the Quizzes and return them as a list
    public List<QuizVariables> retrieveAllQuizzes() {
        ArrayList<QuizVariables> quizzes = new ArrayList<>();
        Cursor cursor = null;
        QuizVariables quiz;

        try {
            cursor = db.query( CapitalQuizDBHelper.TABLE_QUIZZES, allQuizColumns,
                    null, null, null, null, null );

            if( cursor.getCount() > 1 ) {
                while( cursor.moveToNext() ) {
                    long id = cursor.getLong( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.QUIZZES_COLUMN_ID ) );
                    String date = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.QUIZZES_COLUMN_DATE ) );
                    int score = Integer.parseInt(cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.QUIZZES_COLUMN_SCORE ) ));

                    quiz = new QuizVariables(score,  date);
                    quiz.setId( id );
                    quizzes.add( quiz );
                    Log.d( DEBUG_TAG, "Retrieved Quiz: " + quiz );
                }
            }
            Log.d( DEBUG_TAG, "Number of Quizzes from DB: " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved quizzes
        return quizzes;
    }

    // Store a new quiz in the database.
    public QuizVariables storeQuiz( QuizVariables quiz ) {

        ContentValues values = new ContentValues();
        values.put( CapitalQuizDBHelper.QUIZZES_COLUMN_SCORE, quiz.getScore());
        values.put( CapitalQuizDBHelper.QUIZZES_COLUMN_DATE, quiz.getDate());

        // insert a new quiz into the database
        long id = db.insert( CapitalQuizDBHelper.TABLE_QUIZZES, null, values );

        // store the id (the primary key) in the quiz instance
        quiz.setId( id );

        return quiz;
    }

    //stores a new quiz question
    public Questions storeQuizQuestion( Questions question ) {

        ContentValues values = new ContentValues();
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_STATE, question.getState());
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CAPITAL, question.getCapital() );
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY1, question.getCity1() );
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY2, question.getCity2() );

        long id = db.insert( CapitalQuizDBHelper.TABLE_CITIES, null, values );

        question.setId( id );

        return question;
    }



}
