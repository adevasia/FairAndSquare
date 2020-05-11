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
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

//Display Choosing Methods page and generates the buttons
public class pickMethod_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pick_method, container, false);
        LinearLayout layout = (LinearLayout) view;

        // Create the buttons using the methods names and ids from MethodDatabase
        List<Methods> methodList = MethodsData.get(getContext()).getMethods();
        for (int i = 0; i < methodList.size(); i++) {
            Button button = new Button(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 100, 0, 100);   // 10 px
            button.setLayoutParams(layoutParams);

            // Set the text to the method's name and tag to the method ID
            Methods method = MethodsData.get(getContext()).getMethod(i+1);
            button.setText(method.getName());
            button.setTag(Integer.toString(method.getId()));

            // All the buttons have the same click listener
            button.setOnClickListener(buttonClickListener);

            // Add the child to the LinearLayout
            layout.addView(button);
        }

        return view;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Send the method ID of the clicked button to DetailsActivity
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            String methodId = (String) view.getTag();
            intent.putExtra(DetailsActivity.EXTRA_METHOD_ID, Integer.parseInt(methodId));
            startActivity(intent);
        }
    };
}

