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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//This activity handles all require actions to perform Rock,Paper,and scissors
public class Rock extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    ArrayList<String> arlist = new ArrayList<String>();

    ImageView input, output;
    int[] images = new int[]{
            R.mipmap.cpu_rock,
            R.mipmap.cpu_paper,
            R.mipmap.cpu_scis
    };
    int userinput = 0;
    int cpuWins =0;
    int userWins=0;
    private EditText winInput;
    private EditText loseInput;
    private TextView finalresult;
    private TextView type;
    private TextView cpuScore;
    private TextView userScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock);

        //Setup the dialog box to ask the user what the Preferred option should represent
        LayoutInflater layoutInflaterW = LayoutInflater.from(Rock.this);
        View promptViewW = layoutInflaterW.inflate(R.layout.win_rock, null);
        AlertDialog.Builder alertDialogBuilderW = new AlertDialog.Builder(Rock.this);
        alertDialogBuilderW.setView(promptViewW);
        alertDialogBuilderW.setTitle("Preferred Option:");
        winInput = (EditText) promptViewW.findViewById(R.id.wins);

        alertDialogBuilderW.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //Setup the dialog box to ask the user what the Alternative option should represent
                        LayoutInflater layoutInflaterL = LayoutInflater.from(Rock.this);
                        View promptViewL = layoutInflaterL.inflate(R.layout.win_rock, null);
                        AlertDialog.Builder alertDialogBuilderL = new AlertDialog.Builder(Rock.this);
                        alertDialogBuilderL.setView(promptViewL);
                        alertDialogBuilderL.setTitle("Alternative Option:");
                        loseInput = (EditText) promptViewL.findViewById(R.id.wins);
                        // setup a dialog window


                        alertDialogBuilderL.setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });



                        // create an alert dialog
                        AlertDialog alertL = alertDialogBuilderL.create();
                        alertL.show();
                    }
                });

        // create an alert dialog
        AlertDialog alertW = alertDialogBuilderW.create();
        alertW.show();



        //Image that will contain the visual representation of the user's move
        input = (ImageView) findViewById(R.id.iv_input);
        //Image that will contain the visual representation of the CPU's move
        output = (ImageView) findViewById(R.id.iv_output);
        //Current score for CPU is displayed here
        cpuScore= (TextView)findViewById(R.id.cpuScore);
        //Current score for user is displayed here
        userScore= (TextView)findViewById(R.id.userScore);

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
    }

    //Listens for user's touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    //Detects finger on the screen
    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDown: " + event.toString());
        return true;
    }

    //Detects the user swiping/flinging finger on screen
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        userinput = 3;
        input.setBackgroundResource(R.mipmap.cpu_scis);
        setOutput();

        return true;
    }

    //Detects the user holding their finger on the screen
    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
        userinput = 2;
        input.setBackgroundResource(R.mipmap.cpu_paper);
        setOutput();

    }

    //Detects the sliding of the user's finger. Used by onFling.
    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
        return true;
    }

    //
    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    //Detects the users finger lifted from the screen
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    //Detects the users user's double tap
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        userinput = 1;
        input.setBackgroundResource(R.mipmap.cpu_rock);
        setOutput();

        return true;
    }

    //Registers the user's double tap on the screen
    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    //Confirms the users single tap on the screen
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }



        //Set the output of the CPU's move and display to the screen
        private void setOutput() {
            //The CPU's move is randomized
            int imageId = (int) (Math.random() * images.length);
            output.setBackgroundResource(images[imageId]);
            //Determine the result of the round based on the CPU's move
            checkresult(imageId);
        }

        //Determine the winner of the round given the input the CPU's move
        private void checkresult(int imageId) {
            if (userinput == 1 && imageId == 0) {     //User choose Rock,Computer choose Rock
                showresult(2);
            } else if (userinput == 1 && imageId == 1) { //User choose Rock,Computer choose Paper
                showresult(0);
            } else if (userinput == 1 && imageId == 2) { //User choose Rock,Computer choose Scissors
                showresult(1);
            } else if (userinput == 2 && imageId == 0) { //User choose Paper,Computer choose Rock
                showresult(1);
            } else if (userinput == 2 && imageId == 1) { //User choose Paper,Computer choose Paper
                showresult(2);
            } else if (userinput == 2 && imageId == 2) { //User choose Paper,Computer choose Scissors
                showresult(0);
            } else if (userinput == 3 && imageId == 0) {//User choose Scissors,Computer choose Rock
                showresult(0);
            } else if (userinput == 3 && imageId == 1) { //User choose Scissors,Computer choose Paper
                showresult(1);
            } else if (userinput == 3 && imageId == 2) { //User choose Scissors,Computer choose Scissors
                showresult(2);
            }

            //When the CPU has one two rounds, display to the screen that Option 2 has been chosen
            if(cpuWins == 2){
                LayoutInflater layoutInflaterFinal = LayoutInflater.from(Rock.this);
                View promptViewFinal = layoutInflaterFinal.inflate(R.layout.final_result, null);
                AlertDialog.Builder alertDialogBuilderFinal = new AlertDialog.Builder(Rock.this);
                finalresult = (TextView) promptViewFinal.findViewById(R.id.chosen);
                finalresult.setText(loseInput.getText());
                type = (TextView) promptViewFinal.findViewById(R.id.type);
                type.setText("You lost!");
                alertDialogBuilderFinal.setView(promptViewFinal);

                alertDialogBuilderFinal.setCancelable(false)
                        .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent myIntent = new Intent(Rock.this,
                                        Rock.class);
                                startActivity(myIntent);
                            }
                        })
                        .setNegativeButton("Different Method",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent myIntent = new Intent(Rock.this,
                                                pickMethod.class);
                                        startActivity(myIntent);
                                    }
                                });

                // create an alert dialog
                AlertDialog alertFinal = alertDialogBuilderFinal.create();
                alertFinal.show();

            }
            //When the user has one two rounds, display to the screen that Option 1 has been chosen
            if(userWins == 2){
                LayoutInflater layoutInflaterFinal = LayoutInflater.from(Rock.this);
                View promptViewFinal = layoutInflaterFinal.inflate(R.layout.final_result, null);
                AlertDialog.Builder alertDialogBuilderFinal = new AlertDialog.Builder(Rock.this);
                finalresult = (TextView) promptViewFinal.findViewById(R.id.chosen);
                finalresult.setText(winInput.getText());
                type = (TextView) promptViewFinal.findViewById(R.id.type);
                type.setText("You Won!");
                alertDialogBuilderFinal.setView(promptViewFinal);

                alertDialogBuilderFinal.setCancelable(false)
                        .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent myIntent = new Intent(Rock.this,
                                        Rock.class);
                                startActivity(myIntent);
                            }
                        })
                        .setNegativeButton("Different Method",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent myIntent = new Intent(Rock.this,
                                                pickMethod.class);
                                        startActivity(myIntent);
                                    }
                                });

                // create an alert dialog
                AlertDialog alertFinal = alertDialogBuilderFinal.create();
                alertFinal.show();

            }
        }

        //Show the result of each round given the result of each round
        private void showresult(int result) {
            //If user loses...
            if (result == 0) {
                Toast.makeText(getApplicationContext(), "Oh! You Lost :(", Toast.LENGTH_SHORT).show();
                cpuWins++;
                if(cpuWins==1){
                    cpuScore.setText("1");
                }else {
                    cpuScore.setText("2");
                }
            }
            //If user wins..
            else if (result == 1){
                Toast.makeText(getApplicationContext(), "You Won! Yeah! :)", Toast.LENGTH_SHORT).show();
                userWins++;
                if(userWins==1){
                    userScore.setText("1");
                }else {
                    userScore.setText("2");
                }

            }
            //If CPU and user tie for the round...
            else {
                Toast.makeText(getApplicationContext(), "OOPS! It's a Tie! :P", Toast.LENGTH_SHORT).show();
            }

        }

    }

