package edu.uga.cs.capitalsquiz;

import android.os.Bundle;
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

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

// Instances of this class are fragments representing a single
// object in our collection.
public class Part2 extends Fragment {
    public static final String ARG_OBJECT = "object";
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
    static Button submitButton;
    static RadioGroup radioGroup;
    static RadioButton rbSelected;
    public static ArrayList<String> options;
    static ArrayList<Questions> quizList;
    public static ArrayList<String> currentOptions = new ArrayList<>();
    View rootView;

    public static Part2 newInstance(int sectionNumber) {
        Part2 fragment = new Part2();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Part2() {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quiz_display, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        //initialize();
    }

//    public void initialize() {
//        submitButton = (Button) rootView.findViewById(R.id.button3);
//        question = (TextView) rootView.findViewById(R.id.textView1);
//        option1 = (RadioButton) rootView.findViewById(R.id.radioButton);
//        option2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
//        option3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
//        radioGroup = rootView.findViewById(R.id.radioGroup);
//
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                rbSelected = rootView.findViewById(checkedId);
//            }
//        });
//    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();


//            if(savedInstanceState == null) {
//                ArrayList<Integer> intList = new ArrayList<>(3);
//                for (int i = 0; i <= 2; i++) {
//                    intList.add(i);
//                }
//
//                options = new ArrayList<String>();
//
//                options.add(quizList.get(questionNum - 1).getCapital());
//                options.add(quizList.get(questionNum - 1).getCity1());
//                options.add(quizList.get(questionNum - 1).getCity2());
//
//                Collections.shuffle(options);
//            }
//            else {
//                options = new ArrayList<String>();
//
//                options.add(quizList.get(questionNum - 1).getCapital());
//                options.add(quizList.get(questionNum - 1).getCity1());
//                options.add(quizList.get(questionNum - 1).getCity2());
//
//                Collections.shuffle(options);
//
//
//            }
//
//            quest = "What is the capital of " + quizList.get(questionNum - 1).getState();
//
//            opt1 = options.get(0);
//            opt2 = options.get(1);
//            opt3 = options.get(2);
//
//            //((AttemptQuiz) getActivity()).loadView(question, quest, option1, opt1, option2, opt2, option3, opt3);
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

