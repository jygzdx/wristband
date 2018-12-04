package com.slogan.wristband.wristband.widght.chart;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.slogan.wristband.wristband.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sll on 2018/11/28.
 */
public class MyLineChartHeartRate extends LineChart {

    private String lineColor = "#FFFFFF";

    /**
     * 数据1的完整数据
     */
    ArrayList<Entry> e0 = new ArrayList<>();
    /**
     * 数据1的前半段
     */
    ArrayList<Entry> e1 = new ArrayList<>();
    /**
     * 数据1的后半段卷
     */
    ArrayList<Entry> e2 = new ArrayList<>();

    /**
     * 背景数据，撑开布局
     */
    ArrayList<Entry> ebg = new ArrayList<>();


    private OnSelectListenner selectListenner;


    public MyLineChartHeartRate(Context context) {
        super(context);
        initView(context);
    }

    public MyLineChartHeartRate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyLineChartHeartRate(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        getDescription().setEnabled(false);
        setDrawGridBackground(false);
        setScaleEnabled(false);
        setHighlightPerTapEnabled(true);
        setPinchZoom(false);
        setDoubleTapToZoomEnabled(false);
        setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e.getY() == 0) {
                    return;
                }
                LineData lineData = getLineData();

                ILineDataSet d1 = lineData.getDataSetByIndex(0);
                ILineDataSet d2 = lineData.getDataSetByIndex(1);

                int index = findE0Index(e);
//                int index = (int)e.getX();

                e1.clear();
                e2.clear();
                if (index == 0) {
                    e1.add(e0.get(0));
                    e2.addAll(e0);
                }
                if (index == e0.size() - 1) {
                    e1.addAll(e0);
                    e2.add(e0.get(e0.size() - 1));
                }
                if (index > 0 && index < e0.size() - 1) {
                    e1.addAll(e0.subList(0, index + 1));
                    e2.addAll(e0.subList(index, e0.size()));
                }
                if (selectListenner != null) {
                    selectListenner.onSelected(e.getX(), e.getY());
                }

                refreshView();

            }

            @Override
            public void onNothingSelected() {
                clear();
                e1.clear();
                e2.clear();
                e1.addAll(e0);
                e2.add(e0.get(e0.size() - 1));
                refreshView();
            }
        });

        setMarker(new MMarkerView(context, R.layout.marker));

        setAxis();

    }

    private void setAxis() {
        XAxis xAxis = getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(8f);
        xAxis.setLabelCount(12, true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int a = (int) value;
                String v = "";
                switch (a) {
                    case 0:
                        v = "2点";
                        break;
                    case 1:
                        v = "4点";
                        break;
                    case 2:
                        v = "6点";
                        break;
                    case 3:
                        v = "8点";
                        break;
                    case 4:
                        v = "10点";
                        break;
                    case 5:
                        v = "12点";
                        break;
                    case 6:
                        v = "14点";
                        break;
                    case 7:
                        v = "16点";
                        break;
                    case 8:
                        v = "18点";
                        break;
                    case 9:
                        v = "20点";
                        break;
                    case 10:
                        v = "22点";
                        break;
                    case 11:
                        v = "24点";
                        break;

                }
                return v;
            }
        });

        YAxis leftAxis = getAxisLeft();
//        leftAxis.setLabelCount(5, true);
        leftAxis.setAxisMinimum(50f);
        leftAxis.setAxisMaximum(200f);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setEnabled(false);

        YAxis rightAxis = getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setEnabled(false);

//        图例
        Legend l = getLegend();
        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);


//        Description description = new Description();
//        description.setText("时间");
//        lChart.setDescription(description);
    }

    public void setChartData(List<String> data) {
        clear();
        e0.clear();

        for (int i = 0; i < data.size(); i++) {
            e0.add(new Entry(i, Float.parseFloat(data.get(i))));
        }

        e1.clear();
        e1.addAll(e0);
        LineDataSet d1 = new LineDataSet(e1, " ");
        d1 = initLineDataSet(d1);


        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        setBG(sets);
        sets.add(d1);
//        sets.add(d2);


        LineData cd = new LineData(sets);
        setData(cd);
        invalidate();
    }

    private void refreshView() {
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        setBG(sets);
        if (e1.size() > 0) {
            LineDataSet d1 = new LineDataSet(e1, " ");
            d1 = initLineDataSet(d1);
            sets.add(d1);
        }

        if (e2.size() > 0) {
            LineDataSet d2 = new LineDataSet(e2, " ");
            d2 = initLineDataSet(d2);
            d2.enableDashedLine(7f, 5f, 0);
//            d2.setFillDrawable(getResources().getDrawable(R.drawable.bg_blue));
//            d2.setDrawFilled(true);
            d2.setFillFormatter(new DefaultFillFormatter());
            sets.add(d2);
        }

        LineData cd = new LineData(sets);
        setData(cd);
        invalidate();
    }

    private void setBG(ArrayList<ILineDataSet> sets) {
        ebg.clear();
        ebg.add(new Entry(0, 0));
        ebg.add(new Entry(1, 0));
        ebg.add(new Entry(2, 0));
        ebg.add(new Entry(3, 0));
        ebg.add(new Entry(4, 0));
        ebg.add(new Entry(5, 0));
        ebg.add(new Entry(6, 0));
        ebg.add(new Entry(7, 0));
        ebg.add(new Entry(8, 0));
        ebg.add(new Entry(9, 0));
        ebg.add(new Entry(10, 0));
        ebg.add(new Entry(11, 0));

        LineDataSet dbg = new LineDataSet(ebg, " ");
        dbg = initLineDataSet(dbg);
        dbg.setColor(Color.parseColor("#00000000"));
        dbg.setFillFormatter(new DefaultFillFormatter());

        sets.add(dbg);
    }

    private int findE0Index(Entry entry) {
        for (int i = 0; i < e0.size(); i++) {
            Entry e = e0.get(i);
            if (e.getX() == entry.getX()) {
                return i;
            }
        }
        return 0;
    }

    private LineDataSet initLineDataSet(LineDataSet d) {
        d.setLineWidth(2f);
        d.setDrawCircleHole(false);
        d.setDrawCircles(false);
        d.setColor(Color.parseColor(lineColor));
        d.setDrawValues(false);
//        d.setDrawHorizontalHighlightIndicator(false);
        d.setDrawHighlightIndicators(false);
        d.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        d.setHighLightColor(Color.WHITE);
        d.setHighlightLineWidth(1f);
        return d;
    }

    public void setSelectListenner(OnSelectListenner selectListenner) {
        this.selectListenner = selectListenner;
    }

    public interface OnSelectListenner {
        void onSelected(float x, float y);
    }
}
