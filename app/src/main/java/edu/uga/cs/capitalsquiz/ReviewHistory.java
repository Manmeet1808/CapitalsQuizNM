package edu.uga.cs.capitalsquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

//import android.os.AsyncTask;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

//import java.util.List;

public class ReviewHistory extends Fragment {

    public static final String DEBUG_TAG = "ReviewQuizzesActivity";
//
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private RecyclerView.Adapter recyclerAdapter;
//
//    private Data quizData = null;
//    private List<QuizVariables> quizList;

//    @Override
//    protected void onCreate( Bundle savedInstanceState ) {
//
//        Log.d( DEBUG_TAG, "ReviewQuizActivity.onCreate()" );
//
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.review_history );

//        recyclerView = (RecyclerView) findViewById( R.id.recyclerView );
//
//        // use a linear layout manager for the recycler view
//        layoutManager = new LinearLayoutManager(this );
//        recyclerView.setLayoutManager( layoutManager );
//
//        quizData = new Data( this );
//
//        new QuizzesDBReaderTask().execute();

    }

//    // This is an AsyncTask class (it extends AsyncTask) to read quizzes
//    private class QuizzesDBReaderTask extends AsyncTask<Void, Void, List<QuizVariables>> {
//
//        @Override
//        protected List<QuizVariables> doInBackground( Void... params ) {
//            quizData.open();
//            quizList = quizData.retrieveAllQuizzes();
//
//            return quizList;
//        }
//
//        @Override
//        protected void onPostExecute( List<QuizVariables> quizObjectList ) {
//            super.onPostExecute(quizList);
//            recyclerAdapter = new Recycler( quizList );
//            recyclerView.setAdapter( recyclerAdapter );
//        }
//
//
//    }
//
//    @Override
//    protected void onResume() {
//        // open the database in onResume
//        if( quizData != null )
//            quizData.open();
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        // close the database in onPause
//        if( quizData != null )
//            quizData.close();
//        super.onPause();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//    }
//}