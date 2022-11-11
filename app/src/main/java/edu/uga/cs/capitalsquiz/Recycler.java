package edu.uga.cs.capitalsquiz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This class is the adapter used to display the previous quizzes the user had taken.
 */

public class Recycler extends RecyclerView.Adapter<Recycler.QuizHolder> {

    public static final String DEBUG_TAG = "Recycler";

    private List<QuizVariables> quizList;

    public Recycler( List<QuizVariables> quizList ) {
        this.quizList = quizList;
    }

    class QuizHolder extends RecyclerView.ViewHolder {

        TextView d; TextView s;

        public QuizHolder(View itemView ) {
            super(itemView);
            d = (TextView) itemView.findViewById( R.id.Textdate);
            s = (TextView) itemView.findViewById( R.id.Textscore);
        }
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.results_page, parent, false );
        return new QuizHolder( view );
    }

    //fills in the values of a holder to show the score and date of the quiz taken
    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {
        QuizVariables quiz = quizList.get( position );
        Log.d( DEBUG_TAG, "onBindViewHolder: " + quiz );
        Log.d(DEBUG_TAG, "" + quiz.getScore());
        holder.s.setText( "" + quiz.getScore() );
        holder.d.setText( quiz.getDate() );
    }

    @Override
    public int getItemCount() {
        if (quizList != null)
            return quizList.size();
        else
            return 0;
    }

}
