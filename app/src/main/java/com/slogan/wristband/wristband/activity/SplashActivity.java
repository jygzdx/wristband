package com.slogan.wristband.wristband.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.DisplayUtils;
import com.slogan.wristband.wristband.utils.StatusBarCompat;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarCompat.compat(this);
        initHandler();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) btn_count_down.getLayoutParams();
//            layoutParams.topMargin = DisplayUtils.getStatusBarHeight() + DisplayUtils.dip2px(this, 24);
//        }
    }
}
