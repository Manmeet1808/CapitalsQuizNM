package edu.uga.cs.capitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewHistory2 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    private Data quizData = null;
    private List<QuizVariables> quizList;

    public ReviewHistory2() {
        // Required empty public constructor
    }

    public static ReviewHistory2 newInstance() {
        ReviewHistory2 fragment = new ReviewHistory2();
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.review_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        // use a linear layout manager for the recycler view
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( layoutManager );


        quizData = new Data( getContext() );


        new QuizzesDBReaderTask().execute();

        return view;
    }

    //This is an AsyncTask class (it extends AsyncTask) to read quizzes
    private class QuizzesDBReaderTask extends AsyncTask<Void, List<QuizVariables>> {

        @Override
        protected List<QuizVariables> doInBackground( Void... params ) {
            quizData.open();
            quizList = quizData.retrieveAllQuizzes();

            return quizList;
        }

        @Override
        protected void onPostExecute( List<QuizVariables> quizObjectList ) {
//            super.onPostExecute(quizList);
            recyclerAdapter = new Recycler( quizObjectList );

            recyclerView.setAdapter( recyclerAdapter );
            recyclerAdapter.notifyDataSetChanged();


            super.onPostExecute(quizObjectList);

        }


    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view,savedInstanceState);
    }


}

