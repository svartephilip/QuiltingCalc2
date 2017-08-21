package com.magnus.oskar.quiltingcalc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magnus.oskar.quiltingcalc.R;

/**
 * Created by Magnus on 21.08.2017.
 */

public class BackBatActivityFragmentResult extends Fragment{

    public BackBatActivityFragmentResult() {
        // Empty
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back_bat_result, container, false);

        return view;
    }
}
