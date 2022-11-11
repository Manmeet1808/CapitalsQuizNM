package edu.uga.cs.capitalsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AttemptQuiz extends Fragment {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.

    public static final String NUMBER_ANSWERED = "num answered";
    public static final String SCORE = "score";
    public static final String QUIZ_LIST = "quiz question list";

    static Button submitButton;
    static Button viewPastResults;
    static Button newQuiz;

    static RadioGroup radioGroup;
    static RadioButton rbSelected;

    static List<Questions> fullQuestionsList;
    static ArrayList<Questions> quizList;
    static Data quizQuestionsData;
    static String correctAnswer;
    public Boolean correct = false;
    public QuizVariables currentQuiz = new QuizVariables();
    public Integer numAnswered;
    public static final String DEBUG_TAG = "DEBUG_QuizQuestions";

    public static ArrayList<String> options;
    public static ArrayList<String> currentOptions = new ArrayList<>();

    Part1 demoCollectionAdapter;
    ViewPager2 viewPager;

   @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //Need to save num questions answered, score, and quizlist
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBER_ANSWERED, currentQuiz.getNumberAnswered());
        outState.putInt(SCORE, currentQuiz.getScore());
        outState.putParcelableArrayList(QUIZ_LIST, (ArrayList<? extends Parcelable>) quizList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quiz_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CapitalQuizDBHelper helper = CapitalQuizDBHelper.getInstance(getContext());

        demoCollectionAdapter = new Part1(this);
        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(demoCollectionAdapter);

        if (AttemptQuiz.class.isInstance(this)) {

            if (savedInstanceState == null) {


                quizQuestionsData = new Data(getContext());
                quizQuestionsData.open();
                fullQuestionsList = quizQuestionsData.retrieveAllQuizQuestions();

                quizList = new ArrayList<>();
                int size = 50;
                ArrayList<Integer> list = new ArrayList<>(size);
                for (int i = 0; i <= size; i++) {
                    list.add(i);
                }

                Random rand = new Random();
                for (int i = 0; i < 6; i++) {
                    int index = rand.nextInt(list.size());
                    if (index != 0) {
                        quizList.add(fullQuestionsList.get(list.get(index)));
                    } else
                        i--;
                    Log.d(DEBUG_TAG, "Random number selected: " + list.get(index));
                    list.remove(index);
                }
                //createQuiz(quizList);
            } else {
                quizQuestionsData.open();
                quizList = savedInstanceState.<Questions>getParcelableArrayList(QUIZ_LIST);
                currentQuiz.setScore(savedInstanceState.getInt(SCORE));
                currentQuiz.setNumberAnswered(savedInstanceState.getInt(NUMBER_ANSWERED));
                numAnswered = savedInstanceState.getInt(NUMBER_ANSWERED);
            }

        }

    }
}




