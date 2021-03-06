package com.choosemuse.example.libmuse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static com.choosemuse.example.libmuse.MainActivity.eegBuffer;

public class EegActivity extends Activity {

    private LayoutLineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeg);

        Button startButton = (Button) findViewById(R.id.start_button);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.lineChartLayout);

        lineChart = new LayoutLineChart(this);
        lineChart.initLineChart(mainLayout, 650, 1000f);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineChart.graphOutLineChart(1000, 100, eegBuffer);
            }
        });

    }
}
