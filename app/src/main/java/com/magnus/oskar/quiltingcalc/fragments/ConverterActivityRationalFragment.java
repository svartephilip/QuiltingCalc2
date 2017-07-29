package com.magnus.oskar.quiltingcalc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.support.v4.app.Fragment;

import com.magnus.oskar.quiltingcalc.R;

/**
 * Created by ohauk on 5/29/2017.
 */

public class ConverterActivityRationalFragment extends Fragment{

    private static EditText whole, numerator, denominator;

    public ConverterActivityRationalFragment() {
        //supposed to be empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rational, container, false);

        whole = (EditText) view.findViewById(R.id.editText_whole);
        numerator = (EditText) view.findViewById(R.id.editText_numerator);
        denominator = (EditText) view.findViewById(R.id.editText_denominator);

        return view;
    }
}
