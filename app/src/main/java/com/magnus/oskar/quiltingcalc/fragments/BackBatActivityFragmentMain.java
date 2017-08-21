package com.magnus.oskar.quiltingcalc.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.magnus.oskar.quiltingcalc.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class BackBatActivityFragmentMain extends Fragment {

    Spinner dropMenu;

    public BackBatActivityFragmentMain() {
        //empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back_bat_main, container, false);

        dropMenu = (Spinner) view.findViewById(R.id.back_bat_spinner);

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

        return view;
    }
}
