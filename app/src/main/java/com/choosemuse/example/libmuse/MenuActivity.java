package com.choosemuse.example.libmuse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initUI();
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch(view.getId()) {

            case R.id.connect_button:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.eeg_btn:
                intent = new Intent(this, EegActivity.class);
                startActivity(intent);
                break;

            case R.id.alpha_btn:
                intent = new Intent(this, AlphaActivity.class);
                startActivity(intent);
                break;

            case R.id.beta_btn:
                intent = new Intent(this, BetaActivity.class);
                startActivity(intent);
                break;

            case R.id.gamma_btn:
                intent = new Intent(this, GammaActivity.class);
                startActivity(intent);
                break;

            case R.id.theta_btn:
                intent = new Intent(this, ThetaActivity.class);
                startActivity(intent);
                break;

            case R.id.delta_btn:
                intent = new Intent(this, DeltaActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void initUI() {
        Button eegButton = (Button) findViewById(R.id.eeg_btn);
        eegButton.setOnClickListener(this);
        Button connectButton = (Button) findViewById(R.id.connect_button);
        connectButton.setOnClickListener(this);
        Button alphaButton = (Button) findViewById(R.id.alpha_btn);
        alphaButton.setOnClickListener(this);
        Button betaButton = (Button) findViewById(R.id.beta_btn);
        betaButton.setOnClickListener(this);
        Button gammaButton = (Button) findViewById(R.id.gamma_btn);
        gammaButton.setOnClickListener(this);
        Button thetaButton = (Button) findViewById(R.id.theta_btn);
        thetaButton.setOnClickListener(this);
        Button deltaButton = (Button) findViewById(R.id.delta_btn);
        deltaButton.setOnClickListener(this);
    }
}
