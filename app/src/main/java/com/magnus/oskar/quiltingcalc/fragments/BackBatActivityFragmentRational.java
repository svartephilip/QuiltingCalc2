package com.magnus.oskar.quiltingcalc.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;

import java.util.zip.Inflater;

/**
 * Created by Magnus on 21.08.2017.
 */

public class BackBatActivityFragmentRational extends Fragment {

    public BackBatActivityFragmentRational() {
        // Empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rational, container, false);

        return view;
    }
}
