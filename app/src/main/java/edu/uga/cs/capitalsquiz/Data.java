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
        //Questions question;

       // long id = cursor.getColumnIndex( CapitalQuizDBHelper.CAPITALS_COLUMN_ID );

        Questions question1 = new Questions("Alabama", "Montgomery", "Huntsville", "Birmingham");
        questions.add(question1);
        Questions question2 = new Questions("Alaska", "Juneau", "Anchorage", "Fairbanks");
        questions.add(question2);
        Questions question3 = new Questions("Arizona", "Phoenix", "Tuscon", "Mesa");
        questions.add(question3);
        Questions question4 = new Questions("Arkansas", "Little Rock", "Fayetteville", "Fort Smith");
        questions.add(question4);
        Questions question5 = new Questions("California", "Sacramento", "Los Angeles", "San Francisco");
        questions.add(question5);
        Questions question6 = new Questions("Colorado", "Denver", "Colorado Springs", "Aurora");
        questions.add(question6);
        Questions question7 = new Questions("Connecticut", "Hartford", "Bridgeport", "New Haven");
        questions.add(question7);
        Questions question8 = new Questions("Delaware", "Dover", "Wilmington", "Newark");
        questions.add(question8);
        Questions question9 = new Questions("Florida", "Tallahassee", "Miami", "Tampa");
        questions.add(question9);
        Questions question10 = new Questions("Georgia", "Atlanta", "Savannah", "Augusta");
        questions.add(question10);
        Questions question11 = new Questions("Hawaii", "Honolulu", "Wailea", "Kailua-Kona");
        questions.add(question11);
        Questions question12 = new Questions("Idaho", "Boise", "Meridian", "Nampa");
        questions.add(question12);
        Questions question13 = new Questions("Illinois", "Springfield", "Chicago", "Aurora");
        questions.add(question13);
        Questions question14 = new Questions("Indiana", "Indianapolis", "Fort Wayne", "Evansville");
        questions.add(question14);
        Questions question15 = new Questions("Iowa", "Des Moines", "Cedar Rapids", "Davenport");
        questions.add(question15);
        Questions question16 = new Questions("Kansas", "Topeka", "Wichita", "Kansas City");
        questions.add(question16);
        Questions question17 = new Questions("Kentucky", "Frankfort", "Louisville", "Lexington");
        questions.add(question17);
        Questions question18 = new Questions("Louisiana", "Baton Rouge", "New Orleans", "Lafayette");
        questions.add(question18);
        Questions question19 = new Questions("Maine", "Augusta", "Portland", "Lubec");
        questions.add(question19);
        Questions question20 = new Questions("Maryland", "Annapolis", "Baltimore", "Columbia");
        questions.add(question20);
        Questions question21 = new Questions("Massachusetts", "Boston", "Cambridge", "Worcester");
        questions.add(question21);
        Questions question22 = new Questions("Michigan", "Lansing", "Detroit", "Grand Rapids");
        questions.add(question22);
        Questions question23 = new Questions("Minnesota", "Saint Paul", "Minneapolis", "Rochester");
        questions.add(question23);
        Questions question24 = new Questions("Mississippi", "Jackson", "Gulfport", "Southaven");
        questions.add(question24);
        Questions question25 = new Questions("Missouri", "Jefferson City", "St. Louis", "Kansas City");
        questions.add(question25);
        Questions question26 = new Questions("Montana", "Helena", "Billings", "Missoula");
        questions.add(question26);
        Questions question27 = new Questions("Nebraska", "Lincoln", "Omaha", "Bellevue");
        questions.add(question27);
        Questions question28 = new Questions("Nevada", "Carson City", "las Vegas", "Henderson");
        questions.add(question28);
        Questions question29 = new Questions("New Hampshire", "Concord", "Manchester", "Nashua");
        questions.add(question29);
        Questions question30 = new Questions("New Jersey", "Trenton", "Newark", "Jersey City");
        questions.add(question30);
        Questions question31 = new Questions("New Mexico", "Santa Fe", "Albuquerque", "Roswell");
        questions.add(question31);
        Questions question32 = new Questions("New York", "Albany", "New York City", "Buffalo");
        questions.add(question32);
        Questions question33 = new Questions("North Carolina", "Raleigh", "Charlotte", "Greensboro");
        questions.add(question33);
        Questions question34 = new Questions("North Dakota", "Bismarck", "Fargo", "Grand Forks");
        questions.add(question34);
        Questions question35 = new Questions("Ohio", "Columbus", "Cleveland", "Cincinnati");
        questions.add(question35);
        Questions question36 = new Questions("Oklahoma", "Oklahoma City", "Tulsa", "Norman");
        questions.add(question36);
        Questions question37 = new Questions("Oregon", "Salem", "Portland", "Eugene");
        questions.add(question37);
        Questions question38 = new Questions("Pennsylvania", "Harrisburg", "Philadelphia", "Pittsburg");
        questions.add(question38);
        Questions question39 = new Questions("Rhode Island", "Providence", "Charlestown", "Warwick");
        questions.add(question39);
        Questions question40 = new Questions("South Carolina", "Columbia", "Charleston", "Hilton Head");
        questions.add(question40);
        Questions question41 = new Questions("South Dakota", "Pierre", "Sioux Falls", "Rapid City");
        questions.add(question41);
        Questions question42 = new Questions("Tennessee", "Nashville", "Memphis", "Knoxville");
        questions.add(question42);
        Questions question43 = new Questions("Texas", "Austin", "Houston", "Dallas");
        questions.add(question43);
        Questions question44 = new Questions("Utah", "Salt Lake City", "Cedar City", "Provo");
        questions.add(question44);
        Questions question45 = new Questions("Vermont", "Montpelier", "Burlington", "Essex");
        questions.add(question45);
        Questions question46 = new Questions("Virginia", "Richmond", "Chesapeake", "Arlington");
        questions.add(question46);
        Questions question47 = new Questions("Washington", "Olympia", "Seattle", "Spokane");
        questions.add(question47);
        Questions question48 = new Questions("West Virginia", "Charleston", "Huntington", "Morgantown");
        questions.add(question48);
        Questions question49 = new Questions("Wisconsin", "Madison", "Milwaukee", "Green Bay");
        questions.add(question49);
        Questions question50 = new Questions("Wyoming", "Cheyenne", "Casper", "Gillette");
        questions.add(question50);


//        try {
//            cursor = db.query( CapitalQuizDBHelper.TABLE_CITIES, allQuestionColumns,
//                    null, null, null, null, null );
//            Log.d( DEBUG_TAG, "HELLLOOO ");
//
//           // if( cursor.getCount() > 1 ) {
//                while( cursor.moveToNext() ) {
//                    long id = cursor.getColumnIndex( CapitalQuizDBHelper.CAPITALS_COLUMN_ID );
//                    String state = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_STATE ) );
//                    String capital = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_CAPITAL ) );
//                    String city1 = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY1 ) );
//                    String city2 = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY2 ) );
//
//                    question = new Questions(state, capital, city1, city2);
//                    question.setId( id );
//                    questions.add( question );
//                    Log.d( DEBUG_TAG, "Retrieved Question: " + question );
//                }
//           // }
//            Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
//        }
//        catch( Exception e ){
//            Log.d( DEBUG_TAG, "Exception caught: " + e );
//        }
//        finally{
//            // close the cursor
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
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

            long id = cursor.getLong( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.QUIZZES_COLUMN_ID ) );
            if( cursor.getCount() > 1 ) {
                while( cursor.moveToNext() ) {

                    String date = cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.QUIZZES_COLUMN_DATE ) );
                    int score = Integer.parseInt(cursor.getString( cursor.getColumnIndexOrThrow( CapitalQuizDBHelper.QUIZZES_COLUMN_SCORE ) ));

                    quiz = new QuizVariables(score,  date);
                    quiz.setId( id );
                    quizzes.add( quiz );
                    Log.d( DEBUG_TAG, "Retrieved Quiz: " + quiz );
                    id++;
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
