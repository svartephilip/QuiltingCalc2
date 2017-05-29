package com.magnus.oskar.quiltingcalc;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;

public class ConverterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText field;
    private Button btCalc;
    private Spinner dropMenu;
    private TextView txtConverted;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        //finding the elements
        field = (EditText) findViewById(R.id.field);
        btCalc = (Button) findViewById(R.id.calculate);
        dropMenu = (Spinner) findViewById(R.id.menu);
        txtConverted = (TextView) findViewById(R.id.view1);

        //set an adapter to the spinner
        ArrayAdapter<CharSequence> units = ArrayAdapter.createFromResource(this, R.array.units,
                        android.R.layout.simple_spinner_item);
        units.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropMenu.setAdapter(units);

        //buttonListener
        btCalc.setOnClickListener(this);

        //show the add in the app
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {

        double m = 0;
        double cm = 0;
        double yard = 0;
        double inch = 0;
        double feet = 0;
        Fractions inchfr = new Fractions();
        Fractions feetfr = new Fractions();
        Fractions yardft = new Fractions();

        String s = "";

        DecimalFormat f = new DecimalFormat("#0.##");

        if(TextUtils.isEmpty(field.getText().toString())) {
            txtConverted.setText(getString(R.string.textView_string));
            return;
        }

        String txtField = field.getText().toString();
        double toConvert = Double.parseDouble(txtField);

        Conversion con = new Conversion();

        //spinner decide what to do based on a string
        switch(dropMenu.getSelectedItem().toString()) {
            case "cm":
                con.setCm(toConvert);

                s = f.format(con.getMeters()) + " m\n";
                s +=  f.format(con.getInch()) + " inches\n";
                s +=  f.format(con.getFeet()) + " feet\n";
                s +=  f.format(con.getYard()) + " yard\n";
                break;
            case "m":
                con.setMeters(toConvert);

                s =  f.format(con.getCm()) + " cm\n";
                s +=  f.format(con.getInch()) + " inches\n";
                s +=  f.format(con.getFeet()) + " feet\n";
                s +=  f.format(con.getYard()) + " yard";
                break;
            case "inch":
                con.setInch(toConvert);

                s =  f.format(con.getCm()) + " cm\n";
                s +=  f.format(con.getMeters()) + " m\n";
                s +=  f.format(con.getFeet()) + " feet\n";
                s +=  f.format(con.getYard()) + " yard";
                break;
            case "feet":
                con.setFeet(toConvert);

                s =  f.format(con.getCm()) + " cm\n";
                s +=  f.format(con.getMeters()) + " m\n";
                s +=  f.format(con.getInch()) + " inch\n";
                s +=  f.format(con.getYard()) + " yard";
                break;
            case "yard":
                con.setYard(toConvert);

                s =  f.format(con.getCm()) + " cm\n";
                s +=  f.format(con.getMeters()) + " m\n";
                s +=  f.format(con.getInch()) + " inches\n";
                s +=  f.format(con.getFeet()) + " feet";
                break;
            default:
                break;
        }
        txtConverted.setText(s);
    }
}

