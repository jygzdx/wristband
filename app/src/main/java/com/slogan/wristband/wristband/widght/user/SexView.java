package com.slogan.wristband.wristband.widght.user;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.AddUserInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SexView extends LinearLayout {
    private final View view;
    @BindView(R.id.tv_man)
    TextView tvMan;
    @BindView(R.id.tv_women)
    TextView tvWomen;
    private AddUserInfoActivity activity;
    /**
     * 0 man  1 women
     */
    private int selectSex = 0;

    public SexView(AddUserInfoActivity activity) {
        super(activity.mContext);
        view = LayoutInflater.from(activity.mContext).inflate(R.layout.layout_sex, this);
        ButterKnife.bind(view);
        this.activity = activity;
        tvMan.setSelected(true);
    }

    @OnClick({R.id.tv_man, R.id.tv_women})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_man:
                selectSex = 0;
                tvMan.setSelected(true);
                tvWomen.setSelected(false);
                break;
            case R.id.tv_women:
                selectSex = 1;
                tvMan.setSelected(false);
                tvWomen.setSelected(true);
                break;
        }
    }
}
