package com.magnus.oskar.quiltingcalc.activities;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;
import com.magnus.oskar.quiltingcalc.calculations.Fractions;
import com.magnus.oskar.quiltingcalc.fragments.ConverterActivityDecimalFragment;
import com.magnus.oskar.quiltingcalc.fragments.ConverterActivityRationalFragment;

import java.text.DecimalFormat;

public class ConverterActivity extends AppCompatActivity implements ConverterActivityDecimalFragment.DecimalDataListener {
    private EditText field;
    private Button btCalc;
    private Spinner dropMenu;
    private TextView txtConverted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        //finding the elements
        btCalc = (Button) findViewById(R.id.calculate);
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

    public void btClicked() {
        String s = "";

        String txtField = field.getText().toString();
        double toConvert = Double.parseDouble(txtField);

        Conversion con = new Conversion();

        //spinner decide what to do based on a string
        switch(dropMenu.getSelectedItem().toString()) {
            case "cm":
                con.setCm(toConvert);
                s = con.toString();
                break;
            case "m":
                con.setMeters(toConvert);
                s =  con.toString();
                break;
            case "inch":
                con.setInch(toConvert);
                s =  con.toString();
                break;
            case "feet":
                con.setFeet(toConvert);
                s =  con.toString();
                break;
            case "yard":
                con.setYard(toConvert);
                s =  con.toString();
                break;
            default:
                break;
        }
        txtConverted.setText(s);
    }

    //get called by fragment
    @Override
    public void decimalData(EditText data) {
        String s = "";

        String txtField = data.getText().toString();
        double toConvert = Double.parseDouble(txtField);

        Conversion con = new Conversion();

        //spinner decide what to do based on a string
        switch(dropMenu.getSelectedItem().toString()) {
            case "cm":
                con.setCm(toConvert);
                s = con.toString();
                break;
            case "m":
                con.setMeters(toConvert);
                s =  con.toString();
                break;
            case "inch":
                con.setInch(toConvert);
                s =  con.toString();
                break;
            case "feet":
                con.setFeet(toConvert);
                s =  con.toString();
                break;
            case "yard":
                con.setYard(toConvert);
                s =  con.toString();
                break;
            default:
                break;
        }
        txtConverted.setText(s);
    }
}