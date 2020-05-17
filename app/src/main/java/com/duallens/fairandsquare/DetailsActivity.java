package com.duallens.fairandsquare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
//Uses fragments to display the "How To" screen
public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_METHOD_ID = "methodId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        //Connects the Fragment to the activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.details_fragment_container);

        if (fragment == null) {
            // Use method ID from ListFragment to instantiate DetailsFragment
            int methodId = getIntent().getIntExtra(EXTRA_METHOD_ID, 1);
            fragment = DetailsFragment.newInstance(methodId);
            fragmentManager.beginTransaction()
                    .add(R.id.details_fragment_container, fragment)
                    .commit();
        }
    }
}