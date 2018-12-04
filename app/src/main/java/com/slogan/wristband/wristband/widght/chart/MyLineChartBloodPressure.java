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
public class MyLineChartBloodPressure extends LineChart {
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
     * 数据2的完整数据
     */
    ArrayList<Entry> e3 = new ArrayList<>();
    /**
     * 数据2的前半段
     */
    ArrayList<Entry> e4 = new ArrayList<>();
    /**
     * 数据2的后半段
     */
    ArrayList<Entry> e5 = new ArrayList<>();


    /**
     * 背景数据，撑开布局
     */
    ArrayList<Entry> ebg = new ArrayList<>();

    private OnSelectListener selectListener;


    public MyLineChartBloodPressure(Context context) {
        super(context);
        initView(context);
    }

    public MyLineChartBloodPressure(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyLineChartBloodPressure(Context context, AttributeSet attrs, int defStyle) {
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

                int index = (int)e.getX();

                e1.clear();
                e2.clear();
                e4.clear();
                e5.clear();
                if (index == 0) {
                    e1.add(e0.get(0));
                    e2.addAll(e0);
                    e4.add(e3.get(0));
                    e5.addAll(e3);
                }
                if (index == e0.size() - 1) {
                    e1.addAll(e0);
                    e2.add(e0.get(e0.size()-1));
                }
                if (index > 0 && index < e0.size() - 1) {
                    e1.addAll(e0.subList(0, index + 1));
                    e2.addAll(e0.subList(index, e0.size()));
                }

                if (index == e3.size() - 1) {
                    e4.addAll(e3);
                    e5.add(e3.get(e3.size()-1));
                }
                if (index > 0 && index < e3.size() - 1) {
                    e4.addAll(e3.subList(0, index + 1));
                    e5.addAll(e3.subList(index, e3.size()));
                }
                if (selectListener != null) {
                    selectListener.onSelected(e0.get(index).getY(), e3.get(index).getY());
                }
                refreshView();

            }

            @Override
            public void onNothingSelected() {

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

    public void setChartData(List<String> dataH,List<String> dataL) {
        clear();
        e0.clear();
        for (int i = 0; i < dataH.size(); i++) {
            e0.add(new Entry(i, Float.parseFloat(dataH.get(i))));
        }

        e1.clear();
        e1.addAll(e0);
        LineDataSet d1 = new LineDataSet(e1, " ");
        d1.setLineWidth(2f);
        d1.setDrawCircleHole(false);
        d1.setDrawCircles(false);
        d1.setColor(Color.parseColor(lineColor));
        d1.setDrawValues(false);
        d1.setDrawHorizontalHighlightIndicator(false);
        d1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        d1.setHighLightColor(Color.WHITE);

        e3.clear();
        for (int i = 0; i < dataL.size(); i++) {
            e3.add(new Entry(i, Float.parseFloat(dataL.get(i))));
        }
        e4.clear();
        e4.addAll(e3);
        LineDataSet d2 = new LineDataSet(e4, " ");
        d2.setLineWidth(2f);
        d2.setDrawCircleHole(false);
        d2.setDrawCircles(false);
        d2.setColor(Color.parseColor(lineColor));
        d2.setDrawValues(false);
        d2.setDrawHorizontalHighlightIndicator(false);
        d2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        d2.setHighLightColor(Color.WHITE);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        setBG(sets);
        sets.add(d1);
        sets.add(d2);
        LineData cd = new LineData(sets);
        setData(cd);
        invalidate();
    }

    private void refreshView() {
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        setBG(sets);
        if (e1.size() > 0) {
            LineDataSet d1 = new LineDataSet(e1, " ");
            d1.setLineWidth(2f);
            d1.setDrawCircleHole(false);
            d1.setDrawCircles(false);
            d1.setColor(Color.parseColor(lineColor));
            d1.setDrawValues(false);
            d1.setDrawHorizontalHighlightIndicator(false);
            d1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            d1.setHighLightColor(Color.WHITE);
            sets.add(d1);
        }

        if (e2.size() > 0) {
            LineDataSet d2 = new LineDataSet(e2, " ");
            d2.setLineWidth(2f);
            d2.setDrawCircleHole(false);
            d2.setDrawValues(false);
            d2.setDrawCircles(false);
            d2.setColor(Color.parseColor(lineColor));
            d2.enableDashedLine(7f, 5f, 0);
//            d2.setFillDrawable(getResources().getDrawable(R.drawable.bg_blue));
//            d2.setDrawFilled(true);
            d2.setFillFormatter(new DefaultFillFormatter());
            d2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            d2.setDrawHorizontalHighlightIndicator(false);
            d2.setHighLightColor(Color.WHITE);


            sets.add(d2);
        }

        if (e4.size() > 0) {
            LineDataSet d4 = new LineDataSet(e4, " ");
            d4.setLineWidth(2f);
            d4.setDrawCircleHole(false);
            d4.setDrawCircles(false);
            d4.setColor(Color.parseColor(lineColor));
            d4.setDrawValues(false);
            d4.setDrawHorizontalHighlightIndicator(false);
            d4.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            d4.setHighLightColor(Color.WHITE);
            sets.add(d4);
        }

        if (e5.size() > 0) {
            LineDataSet d5 = new LineDataSet(e5, " ");
            d5.setLineWidth(2f);
            d5.setDrawCircleHole(false);
            d5.setDrawValues(false);
            d5.setDrawCircles(false);
            d5.setColor(Color.parseColor(lineColor));
            d5.enableDashedLine(7f, 5f, 0);
//           d5.setFillDrawable(getResources().getDrawable(R.drawable.bg_blue));
//           d5.setDrawFilled(true);
            d5.setFillFormatter(new DefaultFillFormatter());
            d5.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            d5.setDrawHorizontalHighlightIndicator(false);
            d5.setHighLightColor(Color.WHITE);

            sets.add(d5);
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
        dbg.setLineWidth(1f);
        dbg.setCircleRadius(3f);
        dbg.setDrawCircleHole(false);
        dbg.setDrawValues(false);
        dbg.setDrawCircles(false);
        dbg.setColor(Color.parseColor("#00000000"));
        dbg.setFillFormatter(new DefaultFillFormatter());
        dbg.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dbg.setDrawHorizontalHighlightIndicator(false);
        dbg.setHighLightColor(Color.WHITE);

        sets.add(dbg);
    }

    private int findDataIndex(Entry entry) {
        for (int i = 0; i < e0.size(); i++) {
            Entry e = e0.get(i);
            if (e.getX() == entry.getX()) {
                return i;
            }
        }
        return 0;
    }

    public void setSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public interface OnSelectListener {
        void onSelected(float high, float low);
    }
}
