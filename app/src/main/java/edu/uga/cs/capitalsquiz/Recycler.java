package edu.uga.cs.capitalsquiz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This class is the adapter used to display the previous quizzes.
 */

public class Recycler extends RecyclerView.Adapter<Recycler.QuizHolder> {

    public static final String DEBUG_TAG = "Recycler";

    private List<QuizVariables> quizList;

    public Recycler( List<QuizVariables> quizList ) {
        this.quizList = quizList;
    }

    class QuizHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView score;

        public QuizHolder(View itemView ) {
            super(itemView);
            date = (TextView) itemView.findViewById( R.id.date );
            score = (TextView) itemView.findViewById( R.id.score );
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

        holder.score.setText( "" + quiz.getScore() );
        holder.date.setText( quiz.getDate() );
    }

    @Override
    public int getItemCount() {
        if (quizList != null)
            return quizList.size();
        else
            return 0;
    }

}
