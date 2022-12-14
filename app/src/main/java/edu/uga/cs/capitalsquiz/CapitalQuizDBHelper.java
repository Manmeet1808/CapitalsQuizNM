package edu.uga.cs.capitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.os.AsyncTask;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class creates the necessary tables in the database for the quiz.
 * It also populates the CSV file with each state, their capital city, and two other
 * popular cities within that state.
 */

public class CapitalQuizDBHelper extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "CapitalQuizDBHelper";

    private static final String DB_NAME = "stateInfo.db";
    private static final int DB_VERSION = 1;

    //Table for capitals
    public static final String TABLE_CITIES = "cities";
    public static final String CAPITALS_COLUMN_ID = "_id";
    public static final String CAPITALS_COLUMN_STATE = "state";
    public static final String CAPITALS_COLUMN_CAPITAL = "capital";
    public static final String CAPITALS_COLUMN_CITY1 = "cityOne";
    public static final String CAPITALS_COLUMN_CITY2 = "cityTwo";

    //Table for quizzes separate from the table for questions (organizational)
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZZES_COLUMN_ID = "_id";
    public static final String QUIZZES_COLUMN_SCORE = "score";
    public static final String QUIZZES_COLUMN_DATE = "date";

    private static CapitalQuizDBHelper helperInstance;
    private static Context myContext;

    //Creating cities table using sql
    private static final String CREATE_CITIES =
            "create table " + TABLE_CITIES + " ("
                    + CAPITALS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CAPITALS_COLUMN_STATE + " TEXT, "
                    + CAPITALS_COLUMN_CAPITAL + " TEXT, "
                    + CAPITALS_COLUMN_CITY1 + " TEXT, "
                    + CAPITALS_COLUMN_CITY2 + " TEXT"
                    + ")";

    // Creating quizzes table using sql
    private static final String CREATE_QUIZZES =
            "create table " + TABLE_QUIZZES + " ("
                    + QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZZES_COLUMN_SCORE + " INTEGER, "
                    + QUIZZES_COLUMN_DATE + " TEXT "
                    + ")";

    //Access method to the single instance of this class
    private CapitalQuizDBHelper(Context context) {
        super( context, DB_NAME, null, DB_VERSION );
        this.myContext = context;
    }

    public static synchronized CapitalQuizDBHelper getInstance( Context context ) {
        if( helperInstance == null ) {
            helperInstance = new CapitalQuizDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    // We must override onCreate method, which will be used to create the database if
    // it does not exist yet.
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_CITIES);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_QUIZZES);
        db.execSQL(CREATE_CITIES );
        db.execSQL(CREATE_QUIZZES);
        Log.d( DEBUG_TAG, "Table " + TABLE_CITIES + " created" );

        new addData().execute( db );
    }

    /**
     * addData reads from the CSV file in the assets folder in order to populate one of
     * the SQLite databases with the necessary information for the quiz
     */
    private class addData extends Async<SQLiteDatabase, SQLiteDatabase> {

        @Override
        protected SQLiteDatabase doInBackground(SQLiteDatabase... sqLiteDatabases) {
            //Log.d(DEBUG_TAG, "Line: " );
            try {
                int val = 0;
                AssetManager am = myContext.getAssets();
                InputStream in_s = am.open("quizData.csv");
                Log.d(DEBUG_TAG, "InputStream was opened " );
                CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );
               String [] nextLine;
               while( ( nextLine = reader.readNext() ) != null ) {
                   ContentValues values = new ContentValues();
                    values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_STATE, nextLine[0]);
                    values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CAPITAL, nextLine[1] );
                    values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY1, nextLine[2] );
                    values.put( CapitalQuizDBHelper.CAPITALS_COLUMN_CITY2, nextLine[3] );

                    long id = sqLiteDatabases[0].insert(CapitalQuizDBHelper.TABLE_CITIES, null, values );
                    Log.d(DEBUG_TAG, "Id Inserted: " + id);

                    Log.d( DEBUG_TAG, "Line: " + nextLine );
                    val++;

               }
                Log.d(DEBUG_TAG, "How many states: " + val );
                am.close();
                //InputStream in_s = am.open("quizData.csv");

            }  catch (Exception e) {
                Log.e( DEBUG_TAG, e.toString() );
            }

            return null;
        }
    }

    //This  method will override the onUpgrade method in order to upgrade the database if the version
    //has changed.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_CITIES);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_QUIZZES);
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_CITIES + " upgraded" );
    }

}
