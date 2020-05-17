package com.duallens.fairandsquare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class RollDice extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorMan;
    private Sensor accelerometer;

    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    private TextView finalresult;
    private TextView type;

    Integer rollNum = 0;

    TextView tx;
    //Number of options
    String[] s = { "2", "3","4","5","6"};
    ArrayList<Editable> options =  new ArrayList<>();
    ImageView dice;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    Random r;
    int roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(RollDice.this,
                android.R.layout.simple_spinner_item, s);

        final Spinner sp = new Spinner(RollDice.this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);
        AlertDialog.Builder builder = new AlertDialog.Builder(RollDice.this);
        builder.setTitle("How many options?");
        builder.setView(sp);


        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {


        @Override
        public void onClick (DialogInterface dialogInterface,int i){
            rollNum = sp.getSelectedItemPosition();

            //If the user selects two options present the user with 2 option input boxes
            if(sp.getSelectedItemPosition() == 0){
                LayoutInflater layoutInflaterT = LayoutInflater.from(RollDice.this);
                View promptViewT = layoutInflaterT.inflate(R.layout.dice_list, null);
                AlertDialog.Builder alertDialogBuilderT = new AlertDialog.Builder(RollDice.this);
                alertDialogBuilderT.setView(promptViewT);
                alertDialogBuilderT.setTitle("Option 1");
                edit1 = (EditText) promptViewT.findViewById(R.id.userInput);

                alertDialogBuilderT.setCancelable(false)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LayoutInflater layoutInflater = LayoutInflater.from(RollDice.this);
                                View promptView = layoutInflater.inflate(R.layout.dice_list, null);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RollDice.this);
                                alertDialogBuilder.setView(promptView);
                                alertDialogBuilder.setTitle("Option 2");
                                edit2 = (EditText) promptView.findViewById(R.id.userInput);

                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Roll", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Display HEADS option to screen
                                            }
                                        });

                                // create an alert dialog
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            }
                        });

                // create an alert dialog
                AlertDialog alertT = alertDialogBuilderT.create();
                alertT.show();
            }
            //If the user selects three options present the user with 3 option input boxes
            if(sp.getSelectedItemPosition() == 1){
                LayoutInflater layoutInflaterT = LayoutInflater.from(RollDice.this);
                View promptViewT = layoutInflaterT.inflate(R.layout.dice_list, null);
                AlertDialog.Builder alertDialogBuilderT = new AlertDialog.Builder(RollDice.this);
                alertDialogBuilderT.setView(promptViewT);
                alertDialogBuilderT.setTitle("Option 1");
                edit1 = (EditText) promptViewT.findViewById(R.id.userInput);

                alertDialogBuilderT.setCancelable(false)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LayoutInflater layoutInflater = LayoutInflater.from(RollDice.this);
                                View promptView = layoutInflater.inflate(R.layout.dice_list, null);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RollDice.this);
                                alertDialogBuilder.setView(promptView);
                                alertDialogBuilder.setTitle("Option 2");
                                edit2 = (EditText) promptView.findViewById(R.id.userInput);

                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                alertDialogBuilderH.setView(promptViewH);
                                                alertDialogBuilderH.setTitle("Option 3");
                                                edit3 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                alertDialogBuilderH.setCancelable(false)
                                                        .setPositiveButton("Roll", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                //Display HEADS option to screen
                                                            }
                                                        });

                                                // create an alert dialog
                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                alertH.show();
                                            }
                                        });

                                // create an alert dialog
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            }
                        });

                // create an alert dialog
                AlertDialog alertT = alertDialogBuilderT.create();
                alertT.show();
            }
            //If the user selects four options present the user with 4 option input boxes
            if(sp.getSelectedItemPosition() == 2){
                LayoutInflater layoutInflaterT = LayoutInflater.from(RollDice.this);
                View promptViewT = layoutInflaterT.inflate(R.layout.dice_list, null);
                AlertDialog.Builder alertDialogBuilderT = new AlertDialog.Builder(RollDice.this);
                alertDialogBuilderT.setView(promptViewT);
                alertDialogBuilderT.setTitle("Option 1");
                edit1 = (EditText) promptViewT.findViewById(R.id.userInput);

                alertDialogBuilderT.setCancelable(false)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LayoutInflater layoutInflater = LayoutInflater.from(RollDice.this);
                                View promptView = layoutInflater.inflate(R.layout.dice_list, null);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RollDice.this);
                                alertDialogBuilder.setView(promptView);
                                alertDialogBuilder.setTitle("Option 2");
                                edit2 = (EditText) promptView.findViewById(R.id.userInput);

                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                alertDialogBuilderH.setView(promptViewH);
                                                alertDialogBuilderH.setTitle("Option 3");
                                                edit3 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                alertDialogBuilderH.setCancelable(false)
                                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                                alertDialogBuilderH.setView(promptViewH);
                                                                alertDialogBuilderH.setTitle("Option 4");
                                                                edit4 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                                alertDialogBuilderH.setCancelable(false)
                                                                        .setPositiveButton("Roll", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                //Display HEADS option to screen
                                                                            }
                                                                        });

                                                                // create an alert dialog
                                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                                alertH.show();
                                                            }
                                                        });

                                                // create an alert dialog
                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                alertH.show();
                                            }
                                        });

                                // create an alert dialog
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            }
                        });

                // create an alert dialog
                AlertDialog alertT = alertDialogBuilderT.create();
                alertT.show();
            }
            //If the user selects five options present the user with 5 option input boxes
            if(sp.getSelectedItemPosition() == 3){
                LayoutInflater layoutInflaterT = LayoutInflater.from(RollDice.this);
                View promptViewT = layoutInflaterT.inflate(R.layout.dice_list, null);
                AlertDialog.Builder alertDialogBuilderT = new AlertDialog.Builder(RollDice.this);
                alertDialogBuilderT.setView(promptViewT);
                alertDialogBuilderT.setTitle("Option 1");
                edit1 = (EditText) promptViewT.findViewById(R.id.userInput);

                alertDialogBuilderT.setCancelable(false)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LayoutInflater layoutInflater = LayoutInflater.from(RollDice.this);
                                View promptView = layoutInflater.inflate(R.layout.dice_list, null);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RollDice.this);
                                alertDialogBuilder.setView(promptView);
                                alertDialogBuilder.setTitle("Option 2");
                                edit2 = (EditText) promptView.findViewById(R.id.userInput);

                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                alertDialogBuilderH.setView(promptViewH);
                                                alertDialogBuilderH.setTitle("Option 3");
                                                edit3 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                alertDialogBuilderH.setCancelable(false)
                                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                                alertDialogBuilderH.setView(promptViewH);
                                                                alertDialogBuilderH.setTitle("Option 4");
                                                                edit4 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                                alertDialogBuilderH.setCancelable(false)
                                                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                                                alertDialogBuilderH.setView(promptViewH);
                                                                                alertDialogBuilderH.setTitle("Option 5");

                                                                                edit5 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                                                alertDialogBuilderH.setCancelable(false)
                                                                                        .setPositiveButton("Roll", new DialogInterface.OnClickListener() {
                                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                                //Display HEADS option to screen
                                                                                            }
                                                                                        });

                                                                                // create an alert dialog
                                                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                                                alertH.show();
                                                                            }
                                                                        });

                                                                // create an alert dialog
                                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                                alertH.show();
                                                            }
                                                        });

                                                // create an alert dialog
                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                alertH.show();
                                            }
                                        });

                                // create an alert dialog
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            }
                        });

                // create an alert dialog
                AlertDialog alertT = alertDialogBuilderT.create();
                alertT.show();
            }
            //If the user selects six options present the user with 6 option input boxes
            if(sp.getSelectedItemPosition() == 4){
                LayoutInflater layoutInflaterT = LayoutInflater.from(RollDice.this);
                View promptViewT = layoutInflaterT.inflate(R.layout.dice_list, null);
                AlertDialog.Builder alertDialogBuilderT = new AlertDialog.Builder(RollDice.this);
                alertDialogBuilderT.setView(promptViewT);
                alertDialogBuilderT.setTitle("Option 1");
                edit1 = (EditText) promptViewT.findViewById(R.id.userInput);

                alertDialogBuilderT.setCancelable(false)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LayoutInflater layoutInflater = LayoutInflater.from(RollDice.this);
                                View promptView = layoutInflater.inflate(R.layout.dice_list, null);
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RollDice.this);
                                alertDialogBuilder.setView(promptView);
                                alertDialogBuilder.setTitle("Option 2");
                                edit2 = (EditText) promptView.findViewById(R.id.userInput);

                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                alertDialogBuilderH.setView(promptViewH);
                                                alertDialogBuilderH.setTitle("Option 3");
                                                edit3 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                alertDialogBuilderH.setCancelable(false)
                                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                                alertDialogBuilderH.setView(promptViewH);
                                                                alertDialogBuilderH.setTitle("Option 4");
                                                                edit4 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                                alertDialogBuilderH.setCancelable(false)
                                                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                                                alertDialogBuilderH.setView(promptViewH);
                                                                                alertDialogBuilderH.setTitle("Option 5");

                                                                                edit5 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                                                alertDialogBuilderH.setCancelable(false)
                                                                                        .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                                //Display HEADS option to screen
                                                                                                LayoutInflater layoutInflaterH = LayoutInflater.from(RollDice.this);
                                                                                                View promptViewH = layoutInflaterH.inflate(R.layout.dice_list, null);
                                                                                                AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(RollDice.this);
                                                                                                alertDialogBuilderH.setView(promptViewH);
                                                                                                alertDialogBuilderH.setTitle("Option 6");

                                                                                                edit6 = (EditText) promptViewH.findViewById(R.id.userInput);

                                                                                                alertDialogBuilderH.setCancelable(false)
                                                                                                        .setPositiveButton("Roll", new DialogInterface.OnClickListener() {
                                                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                                                //Display HEADS option to screen
                                                                                                            }
                                                                                                        });

                                                                                                // create an alert dialog
                                                                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                                                                alertH.show();
                                                                                            }
                                                                                        });

                                                                                // create an alert dialog
                                                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                                                alertH.show();
                                                                            }
                                                                        });

                                                                // create an alert dialog
                                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                                alertH.show();
                                                            }
                                                        });

                                                // create an alert dialog
                                                AlertDialog alertH = alertDialogBuilderH.create();
                                                alertH.show();
                                            }
                                        });

                                // create an alert dialog
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            }
                        });

                // create an alert dialog
                AlertDialog alertT = alertDialogBuilderT.create();
                alertT.show();
            }

        }

        });
        builder.create().show();





        sensorMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }


    //Detects use of accelerometer(shake)
    @Override
    public void onResume() {
        super.onResume();
        sensorMan.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }

    //Detects the stopping of the shake
    @Override
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(this);
    }

    //Detects if there is a shake given an event
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();
            // Shake detection
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt(x*x + y*y + z*z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            // Make this higher or lower according to how much
            // motion you want to detect
            if(mAccel > 5.9) {
                dice = (ImageView) findViewById(R.id.dice);

                r = new Random();

                //randomly selects dice value
                roll = r.nextInt(6) + 1;
                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                dice.startAnimation(rotate);
                setDiceImage(roll);


                //if the dice value has an option associated with it
                if (roll <= rollNum+2) {
                    LayoutInflater layoutInflaterFinal = LayoutInflater.from(RollDice.this);
                    View promptViewFinal = layoutInflaterFinal.inflate(R.layout.final_result, null);
                    AlertDialog.Builder alertDialogBuilderFinal = new AlertDialog.Builder(RollDice.this);
                    finalresult = (TextView) promptViewFinal.findViewById(R.id.chosen);
                    type = (TextView) promptViewFinal.findViewById(R.id.type);
                    if (roll == 1) {
                        type.setText("You rolled a One");
                        finalresult.setText(edit1.getText());
                    } else if (roll == 2 ) {
                        type.setText("You rolled a Two");
                        finalresult.setText(edit2.getText());
                    } else if (roll == 3 ) {
                        type.setText("You rolled a Three");
                        finalresult.setText(edit3.getText());
                    } else if (roll == 4 ) {
                        type.setText("You rolled a Four");
                        finalresult.setText(edit4.getText());
                    } else if (roll == 5 ) {
                        type.setText("You rolled a Five");
                        finalresult.setText(edit5.getText());
                    } else if (roll == 6 ) {
                        type.setText("You rolled a Six");
                        finalresult.setText(edit6.getText());
                    }
                    alertDialogBuilderFinal.setView(promptViewFinal);

                    alertDialogBuilderFinal.setCancelable(false)
                            .setPositiveButton("Roll Again", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent myIntent = new Intent(RollDice.this,
                                            RollDice.class);
                                    startActivity(myIntent);
                                }
                            })
                            .setNegativeButton("Different Method",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent myIntent = new Intent(RollDice.this,
                                                    pickMethod.class);
                                            startActivity(myIntent);
                                        }
                                    });

                    // create an alert dialog
                    AlertDialog alertFinal = alertDialogBuilderFinal.create();
                    alertFinal.show();

                }
            }
        }
    }

    //Helper function for onSensorChanged
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method
    }


    //Sets the dice image based on the randomized value
    public void setDiceImage(int value){
        switch(value){
            case 1:
                dice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                dice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                dice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                dice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                dice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                dice.setImageResource(R.drawable.dice6);
                break;
        }
    }
}
