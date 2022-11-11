package edu.uga.cs.capitalsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.util.List;

/**
 * The ReviewQuizzesActivity class uses the QuizRecyclerAdapter to show the previous
 * quizzes stored in the app
 */
public class ReviewHistory2 extends AppCompatActivity {

    public static final String DEBUG_TAG = "ReviewHistory2";

    private RecyclerView.LayoutManager layManager;
    private RecyclerView rView;
    private RecyclerView.Adapter rAdapter;

    private Data qData = null;
    private List<QuizVariables> quizList;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        Log.d( DEBUG_TAG, "ReviewQuizActivity.onCreate()" );

        super.onCreate( savedInstanceState );
        setContentView( R.layout.review_history );
        rView = (RecyclerView) findViewById( R.id.recyclerView );
        layManager = new LinearLayoutManager(this );
        rView.setLayoutManager(layManager);
        qData = new Data( this );
        new QuizzesDBReaderTask().execute();

    }

    // Opens the database and retrieves the quizzes
    private class QuizzesDBReaderTask extends Async<Void, List<QuizVariables>> {

        @Override
        protected List<QuizVariables> doInBackground( Void... params ) {
            QuizVariables q;
            qData.open();
            quizList = qData.retrieveAllQuizzes();
            Log.d(DEBUG_TAG, "Size of Quiz: " + quizList.size());
            return quizList;
        }

        @Override
        protected void onPostExecute( List<QuizVariables> quizObjectList ) {
            super.onPostExecute(quizObjectList);
            rAdapter = new Recycler( quizObjectList );
            //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rView.setAdapter(rAdapter);

        }
    }

    @Override
    protected void onResume() {
        if( qData != null )
            qData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // if the app is paused then closes the database
        if( qData != null )
            qData.close();
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    protected void onRestart() {
        super.onRestart();
    }
}