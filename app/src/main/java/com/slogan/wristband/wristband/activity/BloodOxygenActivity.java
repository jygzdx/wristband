package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.SPUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BloodOxygenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_oxygen);
        ButterKnife.bind(this);
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
