package com.choosemuse.example.libmuse;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


/**
 * Created by Johnson Lam on 2/14/2017.
 */

public class LayoutLineChart extends Activity {

    private LineChart mChart;

    public LayoutLineChart(Context context) {
        mChart = new LineChart(context);
    }

    public void graphOutLineChart(final int loop, final int delay, final double[] dataBuffer) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                for(int i=0; i<loop; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry(dataBuffer);
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

    public void initLineChart(LinearLayout mainLayout, float yAxisMinValue, float yAxisMaxValue) {
        mainLayout.addView(mChart);

        mChart.setDescription("");
        mChart.setNoDataTextDescription("Press Start");

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
        y1.setStartAtZero(false);
        y1.setAxisMinValue(yAxisMinValue);
        y1.setAxisMaxValue(yAxisMaxValue);
        y1.setDrawGridLines(true);

        YAxis y2 = mChart.getAxisRight();
        y2.setEnabled(false);
    }

    //Create set's attributes (text and color)
    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "");
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
        set.setValueTextSize(10);

        return set;
    }

    private void addEntry(double[] dataBuffer) {
        LineData data = mChart.getData();

        if(data != null) {
            LineDataSet set = data.getDataSetByIndex(0);

            if(set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            data.addXValue("");
            data.addEntry(new Entry((float) ((dataBuffer[0] + dataBuffer[1] + dataBuffer[2] + dataBuffer[3]) / 4), set.getEntryCount()), 0);

            mChart.notifyDataSetChanged();

            mChart.setVisibleXRange(6);

            mChart.moveViewToX(data.getXValCount() - 7);

        }
    }

}
