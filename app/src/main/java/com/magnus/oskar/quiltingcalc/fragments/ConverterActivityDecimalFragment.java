package com.magnus.oskar.quiltingcalc.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;

/**
 * Created by ohauk on 5/29/2017.
 */

public class ConverterActivityDecimalFragment extends Fragment{

    private static EditText decimalInput;
    private Button calculate;
    private Spinner dropMenu;

    public ConverterActivityDecimalFragment() {
        // Supposed to be empty
    }

    // Reference to the interface
    PassData passData;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        passData = (PassData) a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decimal, container, false);

        // get Views from activity and reference to editText
        decimalInput = (EditText) view.findViewById(R.id.editText_decimal);

        calculate = (Button) getActivity().findViewById(R.id.calculate);
        dropMenu = (Spinner) getActivity().findViewById(R.id.menu);

        // Listener on the button
        calculate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data(v);
            }
        });

        return view;
    }

    // Used by the button
    // Converts the values in the EditText
    // Returns a String value
    public void data(View v) {

        Conversion s = new Conversion();

        Conversion[] data = {s};

        if(TextUtils.isEmpty(decimalInput.getText().toString())) {
            s.setCm(0);
            passData.dataPlaceholder(data);
            return;
        }


        String txtField = decimalInput.getText().toString();
        double toConvert = Double.parseDouble(txtField);


        //spinner decide what to do based on a string
        switch(dropMenu.getSelectedItem().toString()) {
            case "cm":
                s.setCm(toConvert);
                break;
            case "m":
                s.setMeters(toConvert);
                break;
            case "inch":
                s.setInch(toConvert);
                break;
            case "feet":
                s.setFeet(toConvert);
                break;
            case "yard":
                s.setYard(toConvert);
                break;
            default:
                s.setCm(0);
                break;
        }
        passData.dataPlaceholder(data);
    }
}
