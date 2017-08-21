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

        String s = "";

        if(TextUtils.isEmpty(decimalInput.getText().toString())) {
            s = getString(R.string.textView_string);
            passData.dataPlaceholder(s);
            return;
        }


        String txtField = decimalInput.getText().toString();
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
                s = getString(R.string.textView_string);
                break;
        }
        passData.dataPlaceholder(s);
    }
}
