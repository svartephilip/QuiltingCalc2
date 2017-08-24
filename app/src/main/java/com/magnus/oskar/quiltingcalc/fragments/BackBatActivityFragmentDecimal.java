package com.magnus.oskar.quiltingcalc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.magnus.oskar.quiltingcalc.BackBatDataPasser;
import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;

/**
 * Created by Magnus on 21.08.2017.
 */

public class BackBatActivityFragmentDecimal extends Fragment{

    BackBatDataPasser passData;
    EditText data;

    public BackBatActivityFragmentDecimal() {
        // Empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decimal, container, false);

        data = (EditText) view.findViewById(R.id.editText_decimal);
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view1, boolean var) {
                // Need to differentiate between metric and imperial
                Conversion[] val = new Conversion[1];
                // currently cm
                Conversion conversion = new Conversion();
                conversion.setCm(Double.parseDouble(data.toString()));
                val[0] = conversion;
                passData.measurementPassing(val);
            }

        });

        return view;
    }
}
