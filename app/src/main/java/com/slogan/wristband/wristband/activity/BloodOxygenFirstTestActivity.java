package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BloodOxygenFirstTestActivity extends AppCompatActivity {

    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_know)
    TextView tvKnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_first_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_close, R.id.tv_know})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                SPUtils.getInstance().put(SPUtils.SP_OPEN_BLOOD_OXYGEN_FIRST,false);
                startActivity(new Intent(this,BloodOxygenTestActivity.class));
                finish();
                break;
            case R.id.tv_know:
                SPUtils.getInstance().put(SPUtils.SP_OPEN_BLOOD_OXYGEN_FIRST,true);
                startActivity(new Intent(this,BloodOxygenTestActivity.class));
                finish();
                break;
        }
    }
}
