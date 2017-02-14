package com.choosemuse.example.libmuse;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import static com.choosemuse.example.libmuse.MainActivity.eegBuffer;

public class EegActivity extends Activity {

    private LinearLayout mainLayout;
    private LineChart mChart;
    private TextView tp9, tp10, fp1, fp2;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeg);

        mainLayout = (LinearLayout) findViewById(R.id.lineChartLayout);
        Button startButton = (Button) findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphOutLineChart(1000, 200);
            }
        });

//        handler.post(tickUi);

        initLineChart();
    }

//    private final Runnable tickUi = new Runnable() {
//        @Override
//        public void run() {
//            if (MainActivity.eegStale) {
//                updateEeg();
//            }
//            handler.postDelayed(tickUi, 1000 / 60);
//        }
//    };

//    private void updateEeg() {
//        tp9 = (TextView) findViewById(R.id.eeg0);
//        tp10 = (TextView) findViewById(R.id.eeg1);
//        tp9.setText(String.format("%6.2f", eegBuffer[0]));
//    }

    private void initLineChart() {
        mainLayout = (LinearLayout) findViewById(R.id.lineChartLayout);

        //create line chart
        mChart = new LineChart(this);

        mainLayout.addView(mChart);

        mChart.setDescription("");
        mChart.setNoDataTextDescription("No Data");

        mChart.setHighlightEnabled(true);
        mChart.setTouchEnabled(true);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        mChart.setPinchZoom(true);
        mChart.setBackgroundColor(Color.LTGRAY);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        Legend legend1 = mChart.getLegend();

        legend1.setForm(Legend.LegendForm.LINE);
        legend1.setTextColor(Color.WHITE);

        XAxis x1 = mChart.getXAxis();
        x1.setTextColor(Color.WHITE);
        x1.setDrawGridLines(false);
        x1.setAvoidFirstLastClipping(true);

        YAxis y1 = mChart.getAxisLeft();
        y1.setTextColor(Color.WHITE);
        y1.setAxisMaxValue(1500f);
        y1.setDrawGridLines(true);

        YAxis y2 = mChart.getAxisRight();
        y2.setEnabled(false);
    }

    //Method for running, delay in milliseconds
    private void graphOutLineChart(final int loop, final int delay) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                for(int i=0; i<loop; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    try{
                        Thread.sleep(delay);
                    } catch(InterruptedException e) {

                    }
                }
            }

        }).start();
    }

    private void addEntry() {
        LineData data = mChart.getData();

        if(data != null) {
            LineDataSet set = data.getDataSetByIndex(0);

            if(set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            data.addXValue("");
            data.addEntry(new Entry((float) ((eegBuffer[0] + eegBuffer[1] + eegBuffer[2] + eegBuffer[3]) / 4), set.getEntryCount()), 0);

            mChart.notifyDataSetChanged();

            mChart.setVisibleXRange(6);

            mChart.moveViewToX(data.getXValCount() - 7);

        }
    }

    //Create set's attributes (text and color)
    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "EEG");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 177));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);

        return set;
    }

}
