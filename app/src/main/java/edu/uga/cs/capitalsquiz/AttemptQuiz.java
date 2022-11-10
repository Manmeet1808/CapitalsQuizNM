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

//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            int pos;
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                pos=position;
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if(state == 1) {
//                    Log.d(DEBUG_TAG, "pos: " + pos);
//                    gradeQuestion(pos);
//                }
//            }
//
//            public void gradeQuestion(int position) {
//                if(position != 5) {
//                    //Sydney!!!
//                    correctAnswer = (String) rbSelected.getText();
//                    correctAnswer = correctAnswer.substring(3);
//                    Log.d(DEBUG_TAG, "RBSelected: " + correctAnswer);
//                    Log.d(DEBUG_TAG, "Correct Answer: " + quizList.get(position).getCapital());
//                    correct = quizList.get(position).gradeQuestion(String.valueOf(correctAnswer));
//                    Log.d(DEBUG_TAG, "Correct?: " + correct);
//                    if(correct) {
//                        currentQuiz.incrementScore();
//                    }
//                    Log.d(DEBUG_TAG, "Score: " + currentQuiz.getScore());
//                }
//                else if(position == 5) {
//                    Log.d(DEBUG_TAG, "Store Quiz");
//                    //Molly!!!
//                    //show submit button : button onclick stores final q answer, quiz results, launches results screen
//                    //this makes the button visible on the last page!
//                    submitButton.setVisibility(View.VISIBLE);
//                    submitButton.setOnClickListener(new AttemptQuiz.ButtonClickListener());
//                }
//            }
//
//        });
//
//    } //End of onViewCreated
//
//    /**
//     * Listens for the button to be clicked and calls onClick when button is clicked
//     */
//    private class ButtonClickListener implements View.OnClickListener
//    {
//        @Override
//        public void onClick(View v)
//        {
//            if(v == submitButton) {
//                correctAnswer = (String) rbSelected.getText();
//                correctAnswer = correctAnswer.substring(3);
//                correct = quizList.get(5).gradeQuestion(correctAnswer);
//                Log.d(DEBUG_TAG, "Correct?: " + correct);
//                if(correct) {
//                    currentQuiz.incrementScore();
//                }
//
//                Fragment fragment = null;
//
////                setContentView(R.layout.results_page);
////                newQuiz = (Button) findViewById(R.id.button);
////                newQuiz.setOnClickListener(new Quiz.ButtonClickListener());
////                viewPastResults.setOnClickListener(new Quiz.ButtonClickListener());
////
////                TextView resultText = (TextView) findViewById(R.id.textView3);
////                TextView dateText = (TextView) findViewById(R.id.textView4);
//
//                //need to get the result from the database and then set this text box to the appropriate value
////                resultText.setText(currentQuiz.getScore() + " out of 6");
////                //should store this in the database or replace this with getting this from the database
////                Date date = new Date();
////                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
////                String strDate = formatter.format(date);
////                currentQuiz.setDate(strDate);
////
////                new QuizDBWriterTask().execute( currentQuiz );
////                dateText.setText(strDate);
//            }
//            //if go Home button is pushed on the results page
//            else if(v == newQuiz) {
//                Intent intent = new Intent(v.getContext(), MainActivity.class);
//                startActivity(intent);
//            }
//
//        }
//    } // End of buttonClick Listener
//
//    private class QuizDBWriterTask extends AsyncTask<QuizVariables, QuizVariables> {
//
//        @Override
//        protected QuizVariables doInBackground( QuizVariables... quiz ) {
//            quizQuestionsData.storeQuiz( quiz[0] );
//            return quiz[0];
//        }
//    }
//
//    private QuizVariables createQuiz(ArrayList<Questions> quizList) {
//        currentQuiz.setQ1(quizList.get(0));
//        currentQuiz.setQ2(quizList.get(0));
//        currentQuiz.setQ3(quizList.get(0));
//        currentQuiz.setQ4(quizList.get(0));
//        currentQuiz.setQ5(quizList.get(0));
//        currentQuiz.setQ6(quizList.get(0));
//
//        return currentQuiz;
//    }
//
//    public void loadView(TextView question, String quest, RadioButton option1, String opt1, RadioButton option2,
//                         String opt2, RadioButton option3, String opt3) {
//        question.setText(quest);
//        option1.setText("A) " + opt1);
//        option2.setText("B) " + opt2);
//        option3.setText("C) " + opt3);
//    }
//
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//        private final int mSize;
//
//        public SectionsPagerAdapter(FragmentManager fm, int size) {
//            super(fm);
//            this.mSize = size;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return Part2.newInstance(position + 1);
//        }
//
//        @Override
//        public int getCount() {
//            return mSize;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            int imageNum = position + 1;
//            currentQuiz.setNumberAnswered(position);
//            Log.d(DEBUG_TAG, "Number Answered: " + currentQuiz.getNumberAnswered());
//            return String.valueOf("Question " + imageNum);
//        }
//    } //End of SectionsPagerAdapter
//

    }
}




