package com.magnus.oskar.quiltingcalc.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.magnus.oskar.quiltingcalc.BackBatDataPasser;
import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.calculations.BackBat;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;
import com.magnus.oskar.quiltingcalc.fragments.BackBatActivityFragmentMain;
import com.magnus.oskar.quiltingcalc.fragments.BackBatActivityFragmentResult;

public class BackBatActivity extends AppCompatActivity implements BackBatDataPasser {

    private Conversion fabric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_bat);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        BackBatActivityFragmentMain mainFragment = new BackBatActivityFragmentMain();

        fragmentTransaction.replace(R.id.fragmentContainer_main, mainFragment).commit();

    }
    // Get called when button clicked, and when fabric changed
    @Override
    public void dataPlaceholder(String[] data) {
        // switch fragment

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        BackBatActivityFragmentResult resultFragment = new BackBatActivityFragmentResult();
        BackBatActivityFragmentMain mainFragment = new BackBatActivityFragmentMain();

        if(data[0].equals("1"))
            fragmentTransaction.replace(R.id.fragmentContainer_main, resultFragment).commit();
        else if(data[0].equals("0"))
            fragmentTransaction.replace(R.id.fragmentContainer_main, mainFragment).commit();
        // See what data to transform

    }
    public void measurementPassing(Conversion[] data) {
        // Connect the fragments
        if(data.length < 3) {
            fabric = data[0];
        }
    }
    // Get called from fragment
    public void calculationPassing(BackBat data) {
        data.setFWidth(fabric);
        data.findLengthCm();

        // Generate a string...

        // Send string to result

    }
}
