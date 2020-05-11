package com.duallens.fairandsquare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//Displays the Fragment
public class DetailsFragment extends Fragment {

    private Methods mMethod;
    TextView nameTextView;

    public static DetailsFragment newInstance(int methodId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("methodId", methodId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the method ID from the intent that started DetailsActivity
        int methodId = 1;
        if (getArguments() != null) {
            methodId = getArguments().getInt("methodId");
        }

        mMethod = MethodsData.get(getContext()).getMethod(methodId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        //Displays type of choosing method
        nameTextView = (TextView) view.findViewById(R.id.methodName);
        nameTextView.setText(mMethod.getName());

        //Displays the description of the chosen method
        TextView descriptionTextView = (TextView) view.findViewById(R.id.methodDescription);
        descriptionTextView.setText(mMethod.getDescription());

        //Display additional information about the chosen method
        TextView GameTextView = (TextView) view.findViewById(R.id.Description);
        GameTextView.setText(mMethod.getGame());

        //Redirects the Play button to the request choosing method
        Button button = (Button) view.findViewById(R.id.play_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(mMethod.getName().equals("ROCK PAPER SCISSORS")){
                     intent = new Intent(getActivity(), Rock.class);

                }else if(mMethod.getName().equals("COIN FLIP")){
                     intent = new Intent(getActivity(), CoinFlip.class);
                }else{
                     intent = new Intent(getActivity(), RollDice.class);
                }
                startActivity(intent);
            }
        });

        return view;
    }
}

