package com.magnus.oskar.quiltingcalc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by ohauk on 5/29/2017.
 */

public class ConverterActivityDecimalFragment extends Fragment {

    private static EditText decimal;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decimal, container, false);


        return view;
    }
}
