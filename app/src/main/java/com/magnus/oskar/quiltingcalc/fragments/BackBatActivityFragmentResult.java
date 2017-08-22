package com.magnus.oskar.quiltingcalc.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.magnus.oskar.quiltingcalc.PassData;
import com.magnus.oskar.quiltingcalc.R;

/**
 * Created by Magnus on 21.08.2017.
 */

public class BackBatActivityFragmentResult extends Fragment{

    PassData passData;
    Button btCalc;

    public BackBatActivityFragmentResult() {
        // Empty
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        passData = (PassData) a;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_back_bat_result, container, false);

        btCalc = (Button) view.findViewById(R.id.recalculate_bt);

        btCalc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Call interface method
                passData.dataPlaceholder("0");
            }
        });

        return view;
    }
}
