package com.duallens.fairandsquare;
/*
Silas Adams
C10417262
silasa@g.clemson.edu

Alicia Devasia
C18551184
amdevas@g.clemson.edu

CPSC 4150: Fair and Square
December 2, 2019
*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

//Connects with Pick Method Fragments that displays the Choosing Methods page
public class pickMethod extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_method);

        //Connects the Fragment to the activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.pickMethod_fragment);

        if (fragment == null) {
            fragment = new pickMethod_Fragment();
            fragmentManager.beginTransaction()
                    .add(R.id.pickMethod_fragment, fragment)
                    .commit();
        }


    }
}
