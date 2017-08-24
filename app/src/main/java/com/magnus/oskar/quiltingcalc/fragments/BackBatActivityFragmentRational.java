package com.magnus.oskar.quiltingcalc.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.magnus.oskar.quiltingcalc.BackBatDataPasser;
import com.magnus.oskar.quiltingcalc.R;

/**
 * Created by Magnus on 21.08.2017.
 */

public class BackBatActivityFragmentRational extends Fragment {

    BackBatDataPasser passData;
    EditText whole, denominator, numerator;

    public BackBatActivityFragmentRational() {
        // Empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rational, container, false);

        whole = (EditText) view.findViewById(R.id.editText_whole);
        denominator = (EditText) view.findViewById(R.id.editText_denominator);
        numerator = (EditText) view.findViewById(R.id.editText_numerator);

        // one listener for all?

        return view;
    }
}
