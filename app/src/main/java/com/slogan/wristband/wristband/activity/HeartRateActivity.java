package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.SPUtils;
import com.slogan.wristband.wristband.widght.DateSlider.SliderContainer;
import com.slogan.wristband.wristband.widght.chart.MyLineChartHeartRate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeartRateActivity extends BaseActivity {

    @BindView(R.id.dateSliderContainer)
    SliderContainer mContainer;
    @BindView(R.id.chart)
    MyLineChartHeartRate chart;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_value)
    TextView tvValue;

    /**
     * 模拟数据 满一天的
     */
    private List<String> data24 = new ArrayList<>();
    private List<String> data24L = new ArrayList<>();
    /**
     * 模拟数据  不满一天的；
     */
    private List<String> data12 = new ArrayList<>();
    private List<String> data12L = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        ButterKnife.bind(this);

        initChartDate();
        initChart();
        initContainer();
    }

    private void initChartDate() {
        data24.clear();
        data12.clear();

        data12.add("123");
        data12.add("189");
        data12.add("111");
        data12.add("80");
        data12.add("130");
        data12.add("80");
        data12.add("111");

        data12L.add("113");
        data12L.add("179");
        data12L.add("101");
        data12L.add("70");
        data12L.add("110");
        data12L.add("60");
        data12L.add("101");

        data24.add("111");
        data24.add("80");
        data24.add("111");
        data24.add("123");
        data24.add("159");
        data24.add("130");
        data24.add("90");
        data24.add("123");
        data24.add("111");
        data24.add("130");
        data24.add("80");
        data24.add("130");

        data24L.add("101");
        data24L.add("70");
        data24L.add("101");
        data24L.add("103");
        data24L.add("139");
        data24L.add("110");
        data24L.add("80");
        data24L.add("113");
        data24L.add("101");
        data24L.add("120");
        data24L.add("70");
        data24L.add("120");
    }

    private void initChart() {
        chart.setSelectListenner(new MyLineChartHeartRate.OnSelectListenner() {
            @Override
            public void onSelected(float x, float y) {
                tvValue.setText("心率 "+(int)y+"次/分");
            }
        });
    }

    private void initContainer() {
        mContainer.setOnTimeChangeListener(new SliderContainer.OnTimeChangeListener() {
            @Override
            public void onTimeChange(Calendar time) {
//                Calendar selectedDate = mContainer.getTime();
                int selectDay = time.get(Calendar.DAY_OF_YEAR);
                int now = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
                if (now == selectDay) {
                    chart.setChartData(data12);
                } else {
                    chart.setChartData(data24);
                }

                tvTime.setText(String.format("%d年%d月%d日",time.get(Calendar.YEAR),time.get(Calendar.MONTH),time.get(Calendar.DAY_OF_MONTH)));
            }
        });
        mContainer.setMinuteInterval(1);
        mContainer.setTime(Calendar.getInstance());
        Calendar maxTime = Calendar.getInstance();
        maxTime.add(Calendar.HOUR, 1);
        Calendar minTime = Calendar.getInstance();
        if (maxTime != null) {
            mContainer.setMaxTime(maxTime);
        }
    }

    @OnClick({R.id.iv_left, R.id.ll_heartrate_tips, R.id.ll_hand_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                onBackPressed();
                break;
            case R.id.ll_heartrate_tips:
                startActivity(new Intent(this, HeartRateTipActivity.class));
                break;
            case R.id.ll_hand_test:
                boolean aBoolean = SPUtils.getInstance().getBoolean(SPUtils.SP_OPEN_HEART_RATE_FIRST, true);
                if (aBoolean) {
                    startActivity(new Intent(this, HeartRateFirstTestActivity.class));
                } else {

                    startActivity(new Intent(this, HeartRateTestActivity.class));
                }
                break;
        }
    }
}
