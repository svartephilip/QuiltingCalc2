package com.magnus.oskar.quiltingcalc.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.activities.PieceCountActivity;
import com.magnus.oskar.quiltingcalc.activities.StartActivity;

import static com.magnus.oskar.quiltingcalc.R.array.units;

/**
 * A placeholder fragment containing a simple view.
 */
public class BackBatActivityFragmentMain extends Fragment {

    Spinner dropMenu;
    PassData passData;
    Button btCalc;

    public BackBatActivityFragmentMain() {
        //empty
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        passData = (PassData) a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back_bat_main, container, false);

        dropMenu = (Spinner) view.findViewById(R.id.back_bat_spinner);
        btCalc = (Button) view.findViewById(R.id.calculate_bt);

        ArrayAdapter<CharSequence> units = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.units,
                android.R.layout.simple_spinner_item);
        units.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropMenu.setAdapter(units);

        dropMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                String selected = parent.getItemAtPosition(position).toString();

                if(selected.contains("fraction")) {

                    BackBatActivityFragmentRational rationalFragment = new BackBatActivityFragmentRational();

                    fragmentTransaction.replace(R.id.fragmentContainer_measurement, rationalFragment);
                }
                else {

                    BackBatActivityFragmentDecimal decimalFragment = new BackBatActivityFragmentDecimal();

                    fragmentTransaction.replace(R.id.fragmentContainer_measurement, decimalFragment);
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        btCalc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Call interface method
                passData.dataPlaceholder("1");
            }
        });

        return view;
    }
}
