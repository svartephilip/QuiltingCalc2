package com.magnus.oskar.quiltingcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.calculations.BackBat;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;
import com.magnus.oskar.quiltingcalc.fragments.ConverterActivityDecimalFragment;
import com.magnus.oskar.quiltingcalc.fragments.ConverterActivityRationalFragment;

public class BackBatMainActivity extends AppCompatActivity implements PassData {

    public final static String dunno = "What";

    private Conversion fabric;
    private Spinner dropMenu;
    private Button btCalc;
    private ToggleButton toggle;
    private EditText editWidth, editLength, editOverage;

    private BackBat backBat;

    private String[] units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_bat);

        dropMenu = (Spinner) findViewById(R.id.menu);
        btCalc = (Button) findViewById(R.id.calculate);
        editWidth = (EditText) findViewById(R.id.editText_width);
        editLength = (EditText) findViewById(R.id.editText_length);
        editOverage = (EditText) findViewById(R.id.editText_overage);
        toggle = (ToggleButton) findViewById(R.id.toggle_bt);

        units = getResources().getStringArray(R.array.units);

        // Set an adapter to the spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, units) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v;
                // Change what is shown dependent on toggle
                if(toggle.isChecked()) {
                    if (position < 2 || position > 3) {
                        TextView tv = new TextView(getContext());
                        tv.setHeight(0);
                        tv.setVisibility(View.GONE);
                        v = tv;
                    } else {
                        v = super.getDropDownView(position, null, parent);
                    }
                } else {
                    if (position > 1) {
                        TextView tv = new TextView(getContext());
                        tv.setHeight(0);
                        tv.setVisibility(View.GONE);
                        v = tv;
                    } else {
                        v = super.getDropDownView(position, null, parent);
                    }
                }
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropMenu.setAdapter(spinnerAdapter);

        // Set up fragments dependent on dropMenu
        dropMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Fragment controller
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                String selected = parent.getItemAtPosition(position).toString();
                // Action depends on spinner
                if(selected.contains("fraction")) {

                    ConverterActivityRationalFragment rationalFragment = new ConverterActivityRationalFragment();

                    fragmentTransaction.replace(R.id.fragmentContainer_measurement, rationalFragment);
                }
                else {

                    ConverterActivityDecimalFragment decimalFragment = new ConverterActivityDecimalFragment();

                    fragmentTransaction.replace(R.id.fragmentContainer_measurement, decimalFragment);
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Empty
            }
        });

        // Make dropMenu default selection depend on toggle
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    dropMenu.setSelection(2);
                else
                    dropMenu.setSelection(0);
            }
        });

    }
    // Get called when button clicked, and when fabric changed
    @Override
    public void dataPlaceholder(Conversion[] data) {
        // Perform Calculations
        fabric = data[0];

        String[] string = {editWidth.getText().toString(), editLength.getText().toString(), editOverage.getText().toString()};

        if(TextUtils.isEmpty(string[0]) && TextUtils.isEmpty(string[1]) && TextUtils.isEmpty(string[2])) {
            backBat = new BackBat();
            Intent i = new Intent(BackBatMainActivity.this, BackBatResultActivity.class);
            startActivity(i);
            return;
        }

        double valWidth = Double.parseDouble(editWidth.getText().toString());
        double valLength = Double.parseDouble(editLength.getText().toString());
        double valOverage = Double.parseDouble(editOverage.getText().toString());

        // Have to depend on toggle
        Conversion width = new Conversion();
        Conversion length = new Conversion();
        Conversion overage = new Conversion();

        backBat = new BackBat(width, length, fabric, overage);

        if(!toggle.isChecked()) {
            width.setCm(valWidth);
            length.setCm(valLength);
            overage.setCm(valOverage);

            backBat.findLengthCm();
        } else {
            width.setInch(valWidth);
            length.setInch(valLength);
            overage.setInch(valOverage);

            backBat.findLengthInch();
        }
        Intent i = new Intent(BackBatMainActivity.this, BackBatResultActivity.class);
        i.putExtra(dunno, backBat.toString());
        startActivity(i);
    }
}
