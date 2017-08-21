package com.magnus.oskar.quiltingcalc.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.widget.Spinner;

import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;

/**
 * Created by ohauk on 5/29/2017.
 */

public class ConverterActivityRationalFragment extends Fragment {

    private static EditText whole, numerator, denominator;
    private Button calculate;
    private Spinner dropMenu;

    // Reference to the interface
    PassData passData;

    public ConverterActivityRationalFragment() {
        // Supposed to be empty
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        passData = (PassData) a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rational, container, false);

        // Connections to datafields
        whole = (EditText) view.findViewById(R.id.editText_whole);
        numerator = (EditText) view.findViewById(R.id.editText_numerator);
        denominator = (EditText) view.findViewById(R.id.editText_denominator);

        // Get views from activity
        calculate = (Button) getActivity().findViewById(R.id.calculate);
        dropMenu = (Spinner) getActivity().findViewById(R.id.menu);

        // Listener on the button
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data(v);
            }
        });

        return view;
    }//onCreateView()

    // Used by the button
    // Converts the values in the EditTexts
    // Returns a String value
    public void data(View v) {

        String s = "";

        // Exeptionhandling
        if(TextUtils.isEmpty(denominator.getText().toString()) || TextUtils.isEmpty(numerator.getText().toString())) {
            s = getString(R.string.textView_string);
            passData.dataPlaceholder(s);
            return;
        } else if(TextUtils.isEmpty(whole.getText().toString())) {
            whole.setText("0");
        }

        // Convert EditText values to int
        int wholeInt = Integer.parseInt(whole.getText().toString());
        int numerInt = Integer.parseInt(numerator.getText().toString());
        int denomInt = Integer.parseInt(denominator.getText().toString());

        Conversion con = new Conversion();

        // Spinner decide what to do based on a string
        switch(dropMenu.getSelectedItem().toString()) {
            case "inch fraction":
                con.setRationalInch(numerInt, denomInt, wholeInt);
                s =  con.toString();
                break;
            case "feet fraction":
                con.setRationalFeet(numerInt, denomInt, wholeInt);
                s =  con.toString();
                break;
            case "yard fraction":
                con.setRationalYard(numerInt, denomInt, wholeInt);
                s =  con.toString();
                break;
            default:
                s = getString(R.string.textView_string);
                break;
        }
        passData.dataPlaceholder(s);
    }//data()
}//class
