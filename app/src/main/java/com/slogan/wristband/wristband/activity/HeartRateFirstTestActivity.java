package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeartRateFirstTestActivity extends BaseActivity {

    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_know)
    TextView tvKnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_first_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_close, R.id.tv_know})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                SPUtils.getInstance().put(SPUtils.SP_OPEN_HEART_RATE_FIRST,false);
                startActivity(new Intent(this,HeartRateTestActivity.class));
                finish();
                break;
            case R.id.tv_know:
                SPUtils.getInstance().put(SPUtils.SP_OPEN_HEART_RATE_FIRST,true);
                startActivity(new Intent(this,HeartRateTestActivity.class));
                finish();
                break;
        }
    }
}
