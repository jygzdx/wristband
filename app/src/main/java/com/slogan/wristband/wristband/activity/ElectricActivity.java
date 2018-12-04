package com.slogan.wristband.wristband.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ElectricActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.ll_title_parent)
    LinearLayout llTitleParent;
    @BindView(R.id.tv_electric_per)
    TextView tvElectricPer;
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.tv_clock)
    TextView tvClock;
    @BindView(R.id.ll_alarm_clock)
    LinearLayout llAlarmClock;
    @BindView(R.id.tv_old_seat)
    TextView tvOldSeat;
    @BindView(R.id.ll_old_seat)
    LinearLayout llOldSeat;
    @BindView(R.id.iv_remind)
    ImageView ivRemind;
    @BindView(R.id.ll_remind)
    LinearLayout llRemind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        ButterKnife.bind(this);
        initHandler();
        initWidget();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tvTitle.setText(R.string.title_electric);
        tvTitle.setTextColor(getResources().getColor(R.color.white));
        ivLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_back_white));
        llTitleParent.setBackgroundColor(getResources().getColor(R.color.electric_top_color));
    }

    @OnClick({R.id.iv_left, R.id.ll_alarm_clock, R.id.ll_old_seat, R.id.iv_remind, R.id.ll_remind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_alarm_clock:
                break;
            case R.id.ll_old_seat:
                break;
            case R.id.iv_remind:
                break;
            case R.id.ll_remind:
                break;
        }
    }
}
