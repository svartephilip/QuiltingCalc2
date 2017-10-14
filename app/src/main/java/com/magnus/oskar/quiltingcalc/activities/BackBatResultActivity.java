package com.magnus.oskar.quiltingcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.magnus.oskar.quiltingcalc.R;

import java.util.zip.Inflater;

public class BackBatResultActivity extends AppCompatActivity {

    Button btRecalc;
    TextView lengthIn, widthIn, yardage, lengthCm, widthCm, meters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Have to change back to activity
        setContentView(R.layout.activity_back_bat_result);

        // Get the string that came from BackBatMainActivity
        Intent intent = getIntent();
        String string = intent.getStringExtra(BackBatMainActivity.dunno);

        btRecalc = (Button) findViewById(R.id.recalculate_bt);

        lengthIn = (TextView) findViewById(R.id.length_numbers);
        lengthCm = (TextView) findViewById(R.id.length_cm_numbers);
        widthIn = (TextView) findViewById(R.id.width_numbers);
        widthCm = (TextView) findViewById(R.id.width_cm_numbers);
        yardage = (TextView) findViewById(R.id.yard_numbers);
        meters = (TextView) findViewById(R.id.m_numbers);

        String[] separateString = string.split(System.getProperty("line.separator"));
        // Get the values from the string
        widthIn.setText(separateString[5].replaceAll("[a-zA-Z:]",""));
        widthCm.setText(separateString[0].replaceAll("[a-zA-Z:]",""));
        lengthIn.setText(separateString[13].replaceAll("[a-zA-Z:]",""));
        lengthCm.setText(separateString[8].replaceAll("[a-zA-Z:]",""));
        yardage.setText(separateString[38].replaceAll("[a-zA-Z:]",""));
        meters.setText(separateString[32].replaceAll("[a-zA-Z:]",""));

        // Switch activity
        btRecalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get data from another activity
    }

}
