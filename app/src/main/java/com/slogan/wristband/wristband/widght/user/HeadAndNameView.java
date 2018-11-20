package com.slogan.wristband.wristband.widght.user;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.AddUserInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeadAndNameView extends LinearLayout {
    private final View view;
    @BindView(R.id.iv_add_pic)
    ImageView ivAddPic;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_show_password)
    ImageView tvShowPassword;
    private AddUserInfoActivity activity;

    public HeadAndNameView(AddUserInfoActivity activity) {
        super(activity.mContext);
        view = LayoutInflater.from(activity.mContext).inflate(R.layout.layout_head_and_name, this);
        ButterKnife.bind(view);
        this.activity = activity;
    }

    @OnClick({R.id.iv_add_pic, R.id.tv_show_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_pic:
                break;
            case R.id.tv_show_password:
                break;
        }
    }
}
