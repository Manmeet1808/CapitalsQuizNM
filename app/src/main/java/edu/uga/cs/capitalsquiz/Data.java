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

    //references to the database
    public Context myContext;
    private SQLiteDatabase db;
    private SQLiteOpenHelper CapitalsquizDbHelper;

    //declares the various columns in the table holding the information used to create each quiz
    private static final String[] allQuestionColumns = {
            CapitalQuizDBHelper.CAPITALS_COLUMN_ID,
            CapitalQuizDBHelper.CAPITALS_COLUMN_STATE,
            CapitalQuizDBHelper.CAPITALS_COLUMN_CAPITAL,
            CapitalQuizDBHelper.CAPITALS_COLUMN_CITY1,
            CapitalQuizDBHelper.CAPITALS_COLUMN_CITY2
    };
    //declares the various columns in the table storing past quiz information
    private static final String[] allQuizColumns = {
            CapitalQuizDBHelper.QUIZZES_COLUMN_ID,
            CapitalQuizDBHelper.QUIZZES_COLUMN_SCORE,
            CapitalQuizDBHelper.QUIZZES_COLUMN_DATE
    };

    public Data ( Context context ) {
        this.CapitalsquizDbHelper = CapitalQuizDBHelper.getInstance( context );
        this.myContext = context;
    }

    //opens up the database
    public void open() {
        db = CapitalsquizDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "QuizQuestions: database is open" );
    }

    //closes the database
    public void close() {
        if( CapitalsquizDbHelper != null ) {
            CapitalsquizDbHelper.close();
            Log.d(DEBUG_TAG, "QuizQuestions: database is closed");
        }
    }

    /**
     * This method goes through and fetches all the quiz questions needed to display in the past history
     * page that shows your previous quizzes. It returns the questions as a list.
     */
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
                    Log.d( DEBUG_TAG, "Past Quiz Question - " + question );
                }
            }
            Log.d( DEBUG_TAG, "Record Count - " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception - " + e );
        }
        finally{
            if (cursor != null) {
                cursor.close();
            }
        }
        return questions;
    }

    /**
     * This method goes through and fetches all the information needed to display in the past history
     * page that shows your previous quizzes. It returns the quizzes and associated information in the
     * form of a list.
     */
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

                    quiz = new QuizVariables(score, date);
                    quiz.setId( id );
                    quizzes.add( quiz );
                    Log.d( DEBUG_TAG, "Past Quiz - " + quiz );
                }
            }
            Log.d( DEBUG_TAG, "Quiz Count - " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception - " + e );
        }
        finally{
            if (cursor != null) {
                cursor.close();
            }
        }
        return quizzes;
    }

    /**
     * This method stores and inserts a new quiz into the table that holds the past quizzes information.
     * The quiz score and the date taken is stored in this method.
     */
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

    /**
     * This method stores and inserts a new quiz into the table that holds the past quizzes information.
     * The states and cities the user is quizzed on are stored in this method.
     */
    public Questions storeQuizQuestion( Questions question ) {

        ContentValues values = new ContentValues();
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_STATE, question.getState());
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CAPITAL, question.getCapital() );
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY1, question.getCity1() );
        values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY2, question.getCity2() );
        long id = db.insert( CapitalQuizDBHelper.TABLE_CITIES, null, values );
        question.setId(id);
        return question;
    }



}
