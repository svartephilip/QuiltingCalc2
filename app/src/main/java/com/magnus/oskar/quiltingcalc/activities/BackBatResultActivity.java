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
    TextView widthMidIn, lengthMidIn, yardageMid, lengthMidCm, widthMidCm, metersMid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Have to change back to activity
        setContentView(R.layout.content_back_bat_result);

        // Get the string that came from BackBatMainActivity
        Intent intent = getIntent();
        String string = intent.getStringExtra(BackBatMainActivity.dunno);
        lengthIn = (TextView) findViewById(R.id.width_textView);
        lengthIn.setText(string);

        btRecalc = (Button) findViewById(R.id.recalculate_bt);

        /*lengthIn = (TextView) findViewById(R.id.length_right);
        lengthCm = (TextView) findViewById(R.id.length_cm_right);
        widthIn = (TextView) findViewById(R.id.width_right);
        widthCm = (TextView) findViewById(R.id.width_cm_right);
        yardage = (TextView) findViewById(R.id.yard_right);
        meters = (TextView) findViewById(R.id.m_right);
        widthMidIn = (TextView) findViewById(R.id.width_middle);
        widthMidCm = (TextView) findViewById(R.id.width_cm_middle);
        lengthMidIn = (TextView) findViewById(R.id.length_middle);
        lengthMidCm = (TextView) findViewById(R.id.length_cm_middle);
        yardageMid = (TextView) findViewById(R.id.yard_middle);
        metersMid = (TextView) findViewById(R.id.m_middle);

        String[] separateString = string.split(System.getProperty("line.separator"));
        for( int i = 0; i<separateString.length; i++) {
            separateString[i].replaceAll("[a-zA-Z:]","");
        }
        // Get the values from the string
        widthIn.setText(separateString[5]);
        widthCm.setText(separateString[0]);
        lengthIn.setText(separateString[13]);
        lengthCm.setText(separateString[8]);
        yardage.setText(separateString[39]);
        meters.setText(separateString[33]);

        widthMidCm.setText(separateString[8]);
        widthMidIn.setText(separateString[13]);
        lengthMidCm.setText(separateString[0]);
        lengthMidIn.setText(separateString[5]);
        yardageMid.setText(separateString[47]);
        metersMid.setText(separateString[41]);*/

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
