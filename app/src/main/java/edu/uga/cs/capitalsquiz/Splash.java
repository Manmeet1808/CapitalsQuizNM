package edu.uga.cs.capitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Code taken from Dr. Kochut's sample app, JobsTrackerSQLite
 */

public class Splash extends Fragment {

    //class constructor
    public Splash() {
    }

    public static Splash newInstance() {
        Splash fragment = new Splash();
        return fragment;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        //Inflate the layout for this fragment
        return inflater.inflate( R.layout.main_screen_frag, container, false );
    }

    @Override
    public void onViewCreated( @NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( getResources().getString( R.string.app_name ) );
    }
}
