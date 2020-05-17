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
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

//Activity that handles all necessary requirements needed to flip a coin virtually
public class CoinFlip extends AppCompatActivity implements Animation.AnimationListener, SensorEventListener {

    ImageView imgCoin;
    Button btnFlip;
    Animation fromMiddle,toMiddle;

    boolean isStop=true;
    boolean isBackShow=true;
    private TextView resultTextH;
    private TextView resultTextT;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView finalresult;
    private TextView type;
    private EditText editTextT;
    private EditText editTextH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);

        //Variables needed to initialized once the activity started
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        //Variables needed to flip the coin using Animation
        imgCoin=(ImageView)findViewById(R.id.imgCoin);
        //btnFlip=(Button)findViewById(R.id.btnFlip);

        toMiddle= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.to_middle);
        fromMiddle= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.from_middle);

        toMiddle.setRepeatCount(1);
        fromMiddle.setRepeatCount(1);

        toMiddle.setAnimationListener(this);
        fromMiddle.setAnimationListener(this);

        //To start the coin flip animation
        imgCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStop){
                    imgCoin.clearAnimation();
                    imgCoin.setAnimation(toMiddle);
                    imgCoin.startAnimation((toMiddle));
                }
                else{
                    int number=new Random().nextInt(100)+1;
                    if(number % 2 ==0)
                        imgCoin.setImageResource(R.drawable.coin_tails);
                    else
                        imgCoin.setImageResource(R.drawable.coin_picture);
                    imgCoin.clearAnimation();
                }

                isStop=!isStop;


            }
        });

        //Setup the dialog box to ask the user what heads represent
        resultTextH = (TextView) findViewById(R.id.resultHeads);
        LayoutInflater layoutInflaterH = LayoutInflater.from(CoinFlip.this);
        View promptViewH = layoutInflaterH.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilderH = new AlertDialog.Builder(CoinFlip.this);
        alertDialogBuilderH.setView(promptViewH);

        editTextH = (EditText) promptViewH.findViewById(R.id.editHeads);

        alertDialogBuilderH.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Display HEADS option to screen
                        resultTextH.setText("Heads: " + editTextH.getText());
                    }
                })
                .setNegativeButton("Skip",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alertH = alertDialogBuilderH.create();
        alertH.show();


        resultTextT = (TextView) findViewById(R.id.resultTails);
        LayoutInflater layoutInflaterT = LayoutInflater.from(CoinFlip.this);
        View promptViewT = layoutInflaterT.inflate(R.layout.input_tails, null);
        AlertDialog.Builder alertDialogBuilderT = new AlertDialog.Builder(CoinFlip.this);
        alertDialogBuilderT.setView(promptViewT);

        editTextT = (EditText) promptViewT.findViewById(R.id.editTails);
        // setup a dialog window


        alertDialogBuilderT.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Display TAILS option to screen
                        resultTextT.setText("Tails: " + editTextT.getText());
                    }
                })
                .setNegativeButton("Skip",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });



        // create an alert dialog
        AlertDialog alertT = alertDialogBuilderT.create();
        alertT.show();

    }

    //Detects start of animation
    @Override
    public void onAnimationStart(Animation animation) {

    }

    //Detects end of animation
    @Override
    public void onAnimationEnd(Animation animation) {

    }

    //Continues the animation
    @Override
    public void onAnimationRepeat(Animation animation) {
        if(animation == toMiddle){
            imgCoin.clearAnimation();
            imgCoin.setAnimation(fromMiddle);
            imgCoin.startAnimation(fromMiddle);

            if(isBackShow)
                imgCoin.setImageResource(R.drawable.coin_picture);
            else
                imgCoin.setImageResource(R.drawable.coin_tails);

            isBackShow = !isBackShow;
        }
        else
        imgCoin.clearAnimation();
        imgCoin.setAnimation(toMiddle);
        imgCoin.startAnimation(toMiddle);

        if(isBackShow)
            imgCoin.setImageResource(R.drawable.coin_tails);
        else
            imgCoin.setImageResource(R.drawable.coin_picture);

    }

    //Resumes animation
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    //Pauses animation
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    //Helper function for onSensorChanged
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    //Detects if the proximity is 0 to stop animation
    public void onSensorChanged(SensorEvent event) {


        if (event.values[0] == 0) {
            imgCoin.clearAnimation();

            LayoutInflater layoutInflaterFinal = LayoutInflater.from(CoinFlip.this);
            View promptViewFinal = layoutInflaterFinal.inflate(R.layout.final_result, null);
            AlertDialog.Builder alertDialogBuilderFinal = new AlertDialog.Builder(CoinFlip.this);
            finalresult = (TextView) promptViewFinal.findViewById(R.id.chosen);
            type = (TextView) promptViewFinal.findViewById(R.id.type);
            if (imgCoin.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.coin_picture).getConstantState()) {
                type.setText("Heads");
                finalresult.setText(editTextH.getText());
            }else {
                type.setText("Tails");
                finalresult.setText(editTextT.getText());
            }
            alertDialogBuilderFinal.setView(promptViewFinal);

            alertDialogBuilderFinal.setCancelable(false)
                    .setPositiveButton("Flip Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(CoinFlip.this,
                                    CoinFlip.class);
                            startActivity(myIntent);
                        }
                    })
                    .setNegativeButton("Different Method",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent myIntent = new Intent(CoinFlip.this,
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

