package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.SPUtils;
import com.slogan.wristband.wristband.widght.DateSlider.SliderContainer;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeartRate2Activity extends BaseActivity {

    @BindView(R.id.dateSliderContainer)
    SliderContainer mContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate2);
        ButterKnife.bind(this);
        initContainer();
    }

    private void initContainer() {
        mContainer.setOnTimeChangeListener(new SliderContainer.OnTimeChangeListener() {
            @Override
            public void onTimeChange(Calendar time) {
                Calendar selectedDate = mContainer.getTime();
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
