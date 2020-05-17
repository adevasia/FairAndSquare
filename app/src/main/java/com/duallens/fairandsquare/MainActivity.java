package com.duallens.fairandsquare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Views the home screen of the app
public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //"Single Mode" goes to Pick Method activity
        button = (Button) findViewById(R.id.modebtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(MainActivity.this,
                        pickMethod.class);
                startActivity(myIntent);
            }
        });
    }
}
