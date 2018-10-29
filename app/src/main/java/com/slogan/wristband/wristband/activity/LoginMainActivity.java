package com.slogan.wristband.wristband.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.adapter.CommonPagerAdapter;
import com.slogan.wristband.wristband.widght.PasswordLoginView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginMainActivity extends BaseActivity {


    @BindView(R.id.tv_password_login)
    TextView tvPasswordLogin;
    @BindView(R.id.tv_line1)
    TextView tvLine1;
    @BindView(R.id.ll_password)
    LinearLayout llPassword;
    @BindView(R.id.tv_verify_login)
    TextView tvVerifyLogin;
    @BindView(R.id.tv_line2)
    TextView tvLine2;
    @BindView(R.id.ll_verify)
    LinearLayout llVerify;
    @BindView(R.id.vp_login)
    ViewPager vpLogin;
    private List<View> views = new ArrayList<>();
    private CommonPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        ButterKnife.bind(this);
        initHandler();
        initWidget();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        PasswordLoginView passwordLoginView = new PasswordLoginView(mContext);
        views.add(passwordLoginView);
        PasswordLoginView passwordLoginView1 = new PasswordLoginView(mContext);
        views.add(passwordLoginView1);
        adapter = new CommonPagerAdapter(views);
        vpLogin.setAdapter(adapter);
        vpLogin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.ll_password, R.id.ll_verify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_password:
                vpLogin.setCurrentItem(0);
                break;
            case R.id.ll_verify:
                vpLogin.setCurrentItem(1);
                break;
        }
    }
}

