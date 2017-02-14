//package com.choosemuse.example.libmuse;
//
//import android.graphics.Color;
//import android.widget.LinearLayout;
//
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.components.YAxis;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.utils.ColorTemplate;
//
///**
// * Created by Johnson Lam on 2/13/2017.
// */
//
//public class LineChartGraph {
//
//    private LinearLayout mainLayout;
//    private LineChart mChart;
//
//    public LineChartGraph() {
//
//    }
//
//    private void initLineChart() {
//        mainLayout = (LinearLayout) findViewById(R.id.lineChartLayout);
//
//        //create line chart
//        mChart = new LineChart(this);
//
//        mainLayout.addView(mChart);
//
//        mChart.setDescription("");
//        mChart.setNoDataTextDescription("No Data");
//
//        mChart.setHighlightEnabled(true);
//        mChart.setTouchEnabled(true);
//
//        mChart.setDragEnabled(true);
//        mChart.setScaleEnabled(true);
//        mChart.setDrawGridBackground(false);
//
//        mChart.setPinchZoom(true);
//        mChart.setBackgroundColor(Color.LTGRAY);
//
//        LineData data = new LineData();
//        data.setValueTextColor(Color.WHITE);
//
//        mChart.setData(data);
//
//        Legend legend1 = mChart.getLegend();
//
//        legend1.setForm(Legend.LegendForm.LINE);
//        legend1.setTextColor(Color.WHITE);
//
//        XAxis x1 = mChart.getXAxis();
//        x1.setTextColor(Color.WHITE);
//        x1.setDrawGridLines(false);
//        x1.setAvoidFirstLastClipping(true);
//
//        YAxis y1 = mChart.getAxisLeft();
//        y1.setTextColor(Color.WHITE);
//        y1.setAxisMaxValue(1500f);
//        y1.setDrawGridLines(true);
//
//        YAxis y2 = mChart.getAxisRight();
//        y2.setEnabled(false);
//    }
//
//    //Method for running, delay in milliseconds
//    private void graphOutLineChart(final int loop, final int delay) {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                for(int i=0; i<loop; i++) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            addEntry();
//                        }
//                    });
//
//                    try{
//                        Thread.sleep(delay);
//                    } catch(InterruptedException e) {
//
//                    }
//                }
//            }
//
//        }).start();
//    }
//
//    private void addEntry(double[] filteredData) {
//        LineData data = mChart.getData();
//
//        if(data != null) {
//            LineDataSet set = data.getDataSetByIndex(0);
//
//            if(set == null) {
//                set = createSet();
//                data.addDataSet(set);
//            }
//
//            data.addXValue("");
//            data.addEntry(new Entry((float) (filteredData[0], set.getEntryCount()), 0);
//
//            mChart.notifyDataSetChanged();
//
//            mChart.setVisibleXRange(6);
//
//            mChart.moveViewToX(data.getXValCount() - 7);
//
//        }
//    }
//
//    //Create set's attributes (text and color)
//    private LineDataSet createSet() {
//        LineDataSet set = new LineDataSet(null, "EEG(0)");
//        set.setDrawCubic(true);
//        set.setCubicIntensity(0.2f);
//        set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set.setColor(ColorTemplate.getHoloBlue());
//        set.setCircleColor(ColorTemplate.getHoloBlue());
//        set.setLineWidth(2f);
//        set.setCircleSize(4f);
//        set.setFillAlpha(65);
//        set.setFillColor(ColorTemplate.getHoloBlue());
//        set.setHighLightColor(Color.rgb(244, 117, 177));
//        set.setValueTextColor(Color.WHITE);
//        set.setValueTextSize(10f);
//
//        return set;
//    }
//
//}
