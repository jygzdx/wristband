package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        StatusBarCompat.compat(this);
        initHandler();
        handler.sendEmptyMessageDelayed(1,1000);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) btn_count_down.getLayoutParams();
//            layoutParams.topMargin = DisplayUtils.getStatusBarHeight() + DisplayUtils.dip2px(this, 24);
//        }
    }

    @Override
    public void handleMessageInfo(Message message) {
        super.handleMessageInfo(message);
switch (message.what){
    case 1:
        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        break;
}
    }

    @OnClick(R.id.iv_logo)
    public void onViewClicked() {

    }
}
