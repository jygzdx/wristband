package com.slogan.wristband.wristband.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.ElectricActivity;
import com.slogan.wristband.wristband.activity.UserInfoActivity;
import com.veclink.bracelet.bletask.BleCallBack;
import com.veclink.sdk.VeclinkSDK;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by czb on 2018/10/31.
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_uid)
    TextView tvUid;
    @BindView(R.id.tv_sport_step)
    TextView tvSportStep;
    @BindView(R.id.tv_total_km)
    TextView tvTotalKm;
    @BindView(R.id.tv_arrived_day)
    TextView tvArrivedDay;
    @BindView(R.id.tv_electric)
    TextView tvElectric;
    @BindView(R.id.ll_electric)
    LinearLayout llElectric;
    @BindView(R.id.tv_sport)
    TextView tvSport;
    @BindView(R.id.ll_sport)
    LinearLayout llSport;
    @BindView(R.id.ll_report)
    LinearLayout llReport;
    @BindView(R.id.ll_question)
    LinearLayout llQuestion;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.ll_user_info)
            LinearLayout llUserInfo;
    Unbinder unbinder;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this, mRootView);
        rl
    }

    @Override
    public void onResume() {
        super.onResume();
        VeclinkSDK.getInstance().queryDevicePower(new BleCallBack() {
            @Override
            public void onStart(Object o) {

            }

            @Override
            public void onFailed(Object o) {

            }

            @Override
            public void onFinish(Object o) {
                tvElectric.setText("剩余用电量"+o.toString()+"%");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_electric, R.id.ll_sport, R.id.ll_report, R.id.ll_question, R.id.ll_setting,
            R.id.ll_user_info
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_info:
                gotoUserInfoActivity();
                break;
            case R.id.ll_electric:
                gotoElectricActivity();
                break;
            case R.id.ll_sport:
                break;
            case R.id.ll_report:
                break;
            case R.id.ll_question:
                break;
            case R.id.ll_setting:
                break;
        }
    }

    private void gotoElectricActivity() {
        Intent intent = new Intent(this.getContext(),ElectricActivity.class);
        startActivity(intent);
    }

    private void gotoUserInfoActivity() {
        Intent intent = new Intent(this.getContext(),UserInfoActivity.class);
        startActivity(intent);
    }
}
