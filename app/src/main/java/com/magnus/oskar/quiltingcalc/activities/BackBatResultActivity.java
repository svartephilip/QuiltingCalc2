package com.magnus.oskar.quiltingcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.magnus.oskar.quiltingcalc.R;

import java.util.zip.Inflater;

public class BackBatResultActivity extends AppCompatActivity {

    Button btRecalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Have to change back to activity
        setContentView(R.layout.content_back_bat_result);

        Intent intent = getIntent();
        String string = intent.getStringExtra(BackBatMainActivity.dunno);
        TextView hei = (TextView) findViewById(R.id.width_textView);
        hei.setText(string);

        btRecalc = (Button) findViewById(R.id.recalculate_bt);

        // Switch activity
        btRecalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BackBatResultActivity.this, BackBatMainActivity.class);
                startActivity(i);
            }
        });

        // Get data from another activity
    }

}
