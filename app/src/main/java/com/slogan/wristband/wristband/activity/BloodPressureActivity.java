package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.SPUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BloodPressureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_left, R.id.ll_hand_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                onBackPressed();
                break;
            case R.id.ll_hand_test:
                boolean aBoolean = SPUtils.getInstance().getBoolean(SPUtils.SP_OPEN_BLOOD_PRESSURE_FIRST, true);
                if (aBoolean) {
                    startActivity(new Intent(this, BloodPressureFirstTestActivity.class));
                } else {

                    startActivity(new Intent(this, BloodPressureTestActivity.class));
                }
                break;
        }
    }
}
