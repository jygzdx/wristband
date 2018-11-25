package com.slogan.wristband.wristband.activity;

import android.os.Bundle;
import android.view.View;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeartRateTipActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_tip);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                onBackPressed();
                break;
        }
    }
}
