package com.slogan.wristband.wristband.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.widght.TimeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeartRateActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_title_parent)
    LinearLayout llTitleParent;
    @BindView(R.id.tv_compare_text)
    TextView tvCompareText;
    @BindView(R.id.tv_sleep_time)
    TextView tvSleepTime;
    @BindView(R.id.time_sleep)
    TimeView timeSleep;
    @BindView(R.id.tv_deep_sleep)
    TextView tvDeepSleep;
    @BindView(R.id.time_deep_sleep)
    TimeView timeDeepSleep;
    @BindView(R.id.tv_light_sleep)
    TextView tvLightSleep;
    @BindView(R.id.time_light_sleep)
    TimeView timeLightSleep;
    @BindView(R.id.tv_yes_sleep)
    TextView tvYesSleep;
    @BindView(R.id.time_yes_sleep)
    TimeView timeYesSleep;
    @BindView(R.id.tv_day_clear_sleep)
    TextView tvDayClearSleep;
    @BindView(R.id.time_day_clear_sleep)
    TimeView timeDayClearSleep;
    @BindView(R.id.tv_clear_sleep)
    TextView tvClearSleep;
    @BindView(R.id.time_clear_sleep)
    TimeView timeClearSleep;
    @BindView(R.id.tv_detailed_info)
    TextView tvDetailedInfo;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    /**
     * 0->详细信息,1->日视图,2->周视图,3->月视图
     */
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_detailed_info, R.id.tv_day, R.id.tv_week, R.id.tv_month})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_detailed_info:
                state = 0;
                initUI();
                tvDetailedInfo.setSelected(true);
                tvDay.setSelected(false);
                tvWeek.setSelected(false);
                tvMonth.setSelected(false);
                break;
            case R.id.tv_day:
                state = 1;
                initUI();
                tvDetailedInfo.setSelected(false);
                tvDay.setSelected(true);
                tvWeek.setSelected(false);
                tvMonth.setSelected(false);
                break;
            case R.id.tv_week:
                state = 2;
                initUI();
                tvDetailedInfo.setSelected(false);
                tvDay.setSelected(false);
                tvWeek.setSelected(true);
                tvMonth.setSelected(false);
                break;
            case R.id.tv_month:
                state = 3;
                initUI();
                tvDetailedInfo.setSelected(false);
                tvDay.setSelected(false);
                tvWeek.setSelected(false);
                tvMonth.setSelected(true);
                break;
        }
    }
    private void initUI(){
        if(state<=1){
            tvSleepTime.setText(R.string.sleep_time);
            tvDeepSleep.setText(R.string.deep_sleep);
            tvLightSleep.setText(R.string.light_sleep);
            tvYesSleep.setText(R.string.yes_sleep);
            tvDayClearSleep.setText(R.string.day_clear_sleep);
            tvClearSleep.setText(R.string.clear_sleep);
        }else{
            tvSleepTime.setText(R.string.average_sleep_time);
            tvDeepSleep.setText(R.string.average_deep_sleep);
            tvLightSleep.setText(R.string.average_light_sleep);
            tvYesSleep.setText(R.string.average_yes_sleep);
            tvDayClearSleep.setText(R.string.average_day_clear_sleep);
            tvClearSleep.setText(R.string.average_clear_sleep);
        }

    }
}
