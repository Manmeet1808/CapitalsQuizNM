package edu.uga.cs.capitalsquiz;

import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The main class for running the quiz activity
 */
public class ActualClass extends AppCompatActivity {

    public static final String NUMBER_ANSWERED = "num answered";
    public static final String SCORE = "score";
    public static final String QUIZ_LIST = "quiz question list";

    private int increment = 0;
    public ArrayList<Integer> scores = new ArrayList<Integer>();
    public ArrayList<String> dates = new ArrayList<String>();


    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ActionBar mActionBar;

    static Button submitButton;
    private Button goHome;
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

    /**
     * onSaveInstanceState is used to save the variables when the app is stopped abruptly
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //Need to save num questions answered, score, and quizlist
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBER_ANSWERED, currentQuiz.getNumberAnswered());
        outState.putInt(SCORE, currentQuiz.getScore());
        outState.putParcelableArrayList(QUIZ_LIST, (ArrayList<? extends Parcelable>) quizList);

    }


    /**
     * Creates the basic information for the quiz activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_page);

        CapitalQuizDBHelper helper = CapitalQuizDBHelper.getInstance(this);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), 7);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        if (ActualClass.class.isInstance(this)) {

            if(savedInstanceState == null) {

                quizQuestionsData = new Data(this);
                quizQuestionsData.open();
                fullQuestionsList = quizQuestionsData.retrieveAllQuizQuestions();

                quizList = new ArrayList<>();
                int size = 49;
                ArrayList<Integer> list = new ArrayList<>(size);
                for (int i = 0; i <= size; i++) {
                    list.add(i);
                }

                //randomly selects six states to use for the quiz questions
                Random rand = new Random();
                for (int i = 0; i < 7; i++) {
                    int index = rand.nextInt(list.size());
                    if (index != 0) {
                        quizList.add(fullQuestionsList.get(list.get(index)));
                    } else
                        i--;
                    Log.d(DEBUG_TAG, "Random number selected: " + list.get(index));
                    list.remove(index);
                }
                createQuiz(quizList);
            }
            else {
                //saves the current state in case the app is paused
                quizQuestionsData.open();
                quizList = savedInstanceState.<Questions>getParcelableArrayList(QUIZ_LIST);
                currentQuiz.setScore(savedInstanceState.getInt(SCORE));
                currentQuiz.setNumberAnswered(savedInstanceState.getInt(NUMBER_ANSWERED));
                numAnswered = savedInstanceState.getInt(NUMBER_ANSWERED);
                mActionBar.setTitle(mSectionsPagerAdapter.getPageTitle(numAnswered));
            }

        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int pos;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pos=position;
            }

            @Override
            public void onPageSelected(int position) {
                gradeQuestion(pos);
                if(position == mViewPager.getAdapter().getCount() - 1) {

                    setContentView(R.layout.results_page);

                    TextView resultText = (TextView) findViewById(R.id.textView3);
                    TextView dateText = (TextView) findViewById(R.id.textView4);
                    resultText.setText(currentQuiz.getScore() + " out of 6");

                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                    String strDate = formatter.format(date);
                    currentQuiz.setDate(strDate);

                    scores.add(currentQuiz.getScore());
                    dates.add(strDate);

                    new QuizDBWriterTask().execute( currentQuiz );
                    dateText.setText(strDate);

                    //sends the user back to the home page when the button is clicked
                    goHome = (Button) findViewById(R.id.button);
                    goHome.setOnClickListener( new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == 1) {
                    Log.d(DEBUG_TAG, "pos: " + pos);

                }
            }

            //checks if the user's answers are correct
            public void gradeQuestion(int position) {
                if(position != 6) {

                    correctAnswer = (String) rbSelected.getText();
                    correctAnswer = correctAnswer.substring(3);
                    Log.d(DEBUG_TAG, "RBSelected: " + correctAnswer);
                    Log.d(DEBUG_TAG, "Correct Answer: " + quizList.get(position).getCapital());
                    correct = quizList.get(position).gradeQuestion(String.valueOf(correctAnswer));
                    Log.d(DEBUG_TAG, "Correct?: " + correct);
                    if(correct) {
                        currentQuiz.incrementScore();
                    }
                    Log.d(DEBUG_TAG, "Score: " + currentQuiz.getScore());
                }
                else if(position == 5) {
                    Log.d(DEBUG_TAG, "Store Quiz");

                    //makes the submit button visible on the last page
                    submitButton.setVisibility(View.VISIBLE);
                    submitButton.setOnClickListener(new ActualClass.ButtonClickListener() {

                    });
                }
            }
        });

    }

    /**
     * Listens for the button to be clicked and calls onClick when button is clicked
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ReviewHistory2.class);
            startActivity(intent);
        }
    }

    public ArrayList<Integer> getResult() {
        return scores;
    }

    public ArrayList<String> getTime() {
        return dates;
    }

    /**
     * An AsyncTask to store new quizzes when the submit button is clicked
     */
    private class QuizDBWriterTask extends Async<QuizVariables, QuizVariables> {

        @Override
        protected QuizVariables doInBackground( QuizVariables... quiz ) {
            quizQuestionsData.storeQuiz( quiz[0] );
            Log.d(DEBUG_TAG, String.valueOf(quiz[0]));
            return quiz[0];
        }
    }

    private QuizVariables createQuiz(ArrayList<Questions> quizList) {
        currentQuiz.setQ1(quizList.get(0));
        currentQuiz.setQ2(quizList.get(0));
        currentQuiz.setQ3(quizList.get(0));
        currentQuiz.setQ4(quizList.get(0));
        currentQuiz.setQ5(quizList.get(0));
        currentQuiz.setQ6(quizList.get(0));

        return currentQuiz;
    }

    //loads the question answers for the quiz question being displayed
    public void loadView(TextView question, String quest, RadioButton option1, String opt1, RadioButton option2,
                         String opt2, RadioButton option3, String opt3) {
        question.setText(quest);
        option1.setText("A) " + opt1);
        option2.setText("B) " + opt2);
        option3.setText("C) " + opt3);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final int mSize;

        public SectionsPagerAdapter(FragmentManager fm, int size) {
            super(fm);
            this.mSize = size;
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int imageNum = position + 1;
            currentQuiz.setNumberAnswered(position);
            Log.d(DEBUG_TAG, "Number Answered: " + currentQuiz.getNumberAnswered());
            return String.valueOf("Question " + imageNum);
        }
    }

    /**
     * The Fragment class that is laid over the ViewPager while the quiz is running
     */
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private String quest;
        private String opt1;
        private String opt2;
        private String opt3;
        private int questionNum;
        private TextView question;
        private RadioButton option1;
        private RadioButton option2;
        private RadioButton option3;
        View rootView;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (getArguments() != null) {
                questionNum = getArguments().getInt(ARG_SECTION_NUMBER);
            } else {
                questionNum = -1;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.quiz_display, container, false);
            initialize();
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            initialize();
        }

        //initializes the variables for the UI controls on the quiz
        public void initialize() {
            submitButton = (Button) rootView.findViewById(R.id.button3);
            question = (TextView) rootView.findViewById(R.id.textView1);
            option1 = (RadioButton) rootView.findViewById(R.id.radioButton);
            option2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
            option3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
            radioGroup = rootView.findViewById(R.id.radioGroup);

            Log.d(DEBUG_TAG, "onCreateView called");
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    rbSelected = rootView.findViewById(checkedId);
                }
            });
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            Log.d( DEBUG_TAG, "onBindViewHolder: " + quizList );

            if(savedInstanceState == null) {
                ArrayList<Integer> intList = new ArrayList<>(3);
                for (int i = 0; i <= 2; i++) {
                    intList.add(i);
                }

                options = new ArrayList<String>();

                options.add(quizList.get(questionNum - 1).getCapital());
                options.add(quizList.get(questionNum - 1).getCity1());
                options.add(quizList.get(questionNum - 1).getCity2());

                Collections.shuffle(options);
            }
            else {
                options = new ArrayList<String>();

                options.add(quizList.get(questionNum - 1).getCapital());
                options.add(quizList.get(questionNum - 1).getCity1());
                options.add(quizList.get(questionNum - 1).getCity2());

                Collections.shuffle(options);

                Log.d(DEBUG_TAG, "Options: " + options);
            }

            //displayes the question to be asked on each page
            quest = "What is the capital of \n" + quizList.get(questionNum - 1).getState() + "?";

            opt1 = options.get(0);
            opt2 = options.get(1);
            opt3 = options.get(2);

            ((ActualClass) getActivity()).loadView(question, quest, option1, opt1, option2, opt2, option3, opt3);
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        @Override
        public void onPause() {
            super.onPause();
            currentOptions = options;
        }

        @Override
        public void onDestroy()
        {
            super.onDestroy();
        }
    }
}

