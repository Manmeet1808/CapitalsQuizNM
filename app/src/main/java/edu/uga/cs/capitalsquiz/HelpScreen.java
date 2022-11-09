package edu.uga.cs.capitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

/**
 * Code is from Dr. Kochut's sample app, JobsTrackerSQLite.
 */

public class HelpScreen extends Fragment {

    //required constructor
    public HelpScreen() {
    }

    public static HelpScreen newInstance() {
        HelpScreen fragment = new HelpScreen();
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflates the layout for the fragment
        return inflater.inflate( R.layout.help_screen, container, false );
    }

    @Override
    public void onViewCreated( @NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view,savedInstanceState);

        TextView textView = getView().findViewById( R.id.textView3 );
        String text = getResources().getString( R.string.help_text);
        textView.setText( HtmlCompat.fromHtml( text, HtmlCompat.FROM_HTML_MODE_LEGACY ) );
    }
}
