package com.magnus.oskar.quiltingcalc.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.magnus.oskar.quiltingcalc.BackBatDataPasser;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.calculations.BackBat;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;

/**
 * Created by Magnus on 21.08.2017.
 */
public class BackBatActivityFragmentMain extends Fragment {

    private Spinner dropMenu;
    private BackBatDataPasser passData;
    private Button btCalc;
    private EditText editWidth, editLength, editOverage;

    public BackBatActivityFragmentMain() {
        //empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back_bat_main, container, false);

        dropMenu = (Spinner) view.findViewById(R.id.back_bat_spinner);
        btCalc = (Button) view.findViewById(R.id.calculate_bt);
        editWidth = (EditText) view.findViewById(R.id.editText_width);
        editLength = (EditText) view.findViewById(R.id.editText_length);
        editOverage = (EditText) view.findViewById(R.id.editText_overage);

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
                // Empty
            }
        });

        btCalc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(editWidth.getText().toString()) && TextUtils.isEmpty(editLength.getText().toString()) && TextUtils.isEmpty(editOverage.getText().toString())) {
                    return;
                }

                BackBat data = new BackBat();

                //also here the values need to be differentiated
                String txtField = editWidth.getText().toString();
                Conversion conWidth = new Conversion();
                conWidth.setCm(Double.parseDouble(txtField));

                txtField = editLength.getText().toString();
                Conversion conLength = new Conversion();
                conLength.setCm(Double.parseDouble(txtField));

                txtField = editOverage.getText().toString();
                Conversion conOverage = new Conversion();
                conOverage.setCm(Double.parseDouble(txtField));

                String[] s = {"1"};

                passData.calculationPassing(data);
                passData.dataPlaceholder(s);
            }
        });

        return view;
    }

}
