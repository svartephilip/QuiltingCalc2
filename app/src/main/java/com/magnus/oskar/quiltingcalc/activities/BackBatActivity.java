package com.magnus.oskar.quiltingcalc.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.fragments.BackBatActivityFragmentMain;

public class BackBatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_bat);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        BackBatActivityFragmentMain mainFragment = new BackBatActivityFragmentMain();

        fragmentTransaction.replace(R.id.fragmentContainer_main, mainFragment).commit();

    }
}
