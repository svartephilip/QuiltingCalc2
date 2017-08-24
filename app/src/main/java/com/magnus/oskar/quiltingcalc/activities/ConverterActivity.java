package com.magnus.oskar.quiltingcalc.activities;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.fragments.ConverterActivityDecimalFragment;
import com.magnus.oskar.quiltingcalc.fragments.ConverterActivityRationalFragment;

public class ConverterActivity extends AppCompatActivity implements PassData {

    private Spinner dropMenu;
    private TextView txtConverted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        //finding the elements
        dropMenu = (Spinner) findViewById(R.id.menu);
        txtConverted = (TextView) findViewById(R.id.view1);

        //set an adapter to the spinner
        ArrayAdapter<CharSequence> units = ArrayAdapter.createFromResource(this, R.array.units,
                        android.R.layout.simple_spinner_item);
        units.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropMenu.setAdapter(units);

        //set up fragments
        dropMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.contains("fraction")) {

                    ConverterActivityRationalFragment rationalFragment = new ConverterActivityRationalFragment();

                    fragmentTransaction.replace(R.id.contentFragment, rationalFragment);
                }
                else {

                    ConverterActivityDecimalFragment decimalFragment = new ConverterActivityDecimalFragment();

                    fragmentTransaction.replace(R.id.contentFragment, decimalFragment);
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }

    //get called by fragment
    @Override
    public void dataPlaceholder(String[] data) {
        txtConverted.setText(data[0]);
    }
}