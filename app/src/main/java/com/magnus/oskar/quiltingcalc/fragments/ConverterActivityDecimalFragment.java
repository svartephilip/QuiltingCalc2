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

import com.magnus.oskar.quiltingcalc.R;

/**
 * Created by ohauk on 5/29/2017.
 */

public class ConverterActivityDecimalFragment extends Fragment {

    private static EditText decimalInput;
    private Button calculate;

    public ConverterActivityDecimalFragment() {
        //supposed to be empty
    }

    public interface DecimalDataListener {
        public void decimalData(EditText data);
    }

    DecimalDataListener passData;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        passData = (DecimalDataListener) a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decimal, container, false);

        calculate = (Button) getActivity().findViewById(R.id.calculate);

        decimalInput = (EditText) view.findViewById(R.id.editText_decimal);

        calculate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data(v);
            }
        });

        return view;
    }

    public void data(View v) {
        if(TextUtils.isEmpty(decimalInput.getText().toString())) {
            return;
        }
        passData.decimalData(decimalInput);
    }
}
