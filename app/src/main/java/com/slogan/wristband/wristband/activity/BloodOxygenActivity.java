package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.SPUtils;
import com.slogan.wristband.wristband.widght.DateSlider.SliderContainer;
import com.slogan.wristband.wristband.widght.chart.MyLineChartBloodOxygen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BloodOxygenActivity extends BaseActivity {

    @BindView(R.id.dateSliderContainer)
    SliderContainer mContainer;

    @BindView(R.id.chart)
    MyLineChartBloodOxygen chart;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_value)
    TextView tvValue;

    /**
     * 模拟数据 满一天的
     */
    private List<String> data24 = new ArrayList<>();
    /**
     * 模拟数据  不满一天的；
     */
    private List<String> data12 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_oxygen);
        ButterKnife.bind(this);
        initChartDate();
        initChart();
        initContainer();
    }

    private void initChartDate() {
        data24.clear();
        data12.clear();

        data12.add("23");
        data12.add("44");
        data12.add("68");
        data12.add("75");
        data12.add("36");
        data12.add("68");
        data12.add("99");


        data24.add("33");
        data24.add("80");
        data24.add("45");
        data24.add("55");
        data24.add("77");
        data24.add("12");
        data24.add("90");
        data24.add("88");
        data24.add("97");
        data24.add("44");
        data24.add("22");
        data24.add("88");

    }

    private void initChart() {
        chart.setSelectListener(new MyLineChartBloodOxygen.OnSelectListener() {
            @Override
            public void onSelected(float x, float y) {
                tvValue.setText("血氧 "+(int)y+"%");
            }
        });
    }

    private void initContainer() {
        mContainer.setOnTimeChangeListener(new SliderContainer.OnTimeChangeListener() {
            @Override
            public void onTimeChange(Calendar time) {
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
        if (maxTime!=null) {
            mContainer.setMaxTime(maxTime);
        }
    }

    @OnClick({R.id.iv_left, R.id.ll_hand_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                onBackPressed();
                break;
            case R.id.ll_hand_test:
                boolean aBoolean = SPUtils.getInstance().getBoolean(SPUtils.SP_OPEN_BLOOD_OXYGEN_FIRST, true);
                if (aBoolean) {
                    startActivity(new Intent(this, BloodOxygenFirstTestActivity.class));
                } else {

                    startActivity(new Intent(this, BloodOxygenTestActivity.class));
                }
                break;
        }
    }
}
