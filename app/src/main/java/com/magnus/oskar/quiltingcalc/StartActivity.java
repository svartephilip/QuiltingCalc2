package com.magnus.oskar.quiltingcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class StartActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        //set up the button
        Button btFab = (Button)findViewById(R.id.fabric);
        btFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(StartActivity.this, ConverterActivity.class);
                startActivity(i);
            }
        });
        Button btBatting = (Button)findViewById(R.id.backing);
        btBatting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //need the activity
                Intent i = new Intent(StartActivity.this, ConverterActivity.class);
                startActivity(i);
            }
        });
        Button btPiece = (Button)findViewById(R.id.piece);
        btPiece.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //need the activity
                Intent i = new Intent(StartActivity.this, ConverterActivity.class);
                startActivity(i);
            }
        });

        //show the add in the app
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
