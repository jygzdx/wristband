package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterSuccessActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.ll_title_parent)
    LinearLayout llTitleParent;
    @BindView(R.id.tv_go_write_user_info)
    TextView tvGoWriteUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        ButterKnife.bind(this);
        initHandler();
        initWidget();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ivLeft.setVisibility(View.GONE);
        ivRight.setVisibility(View.GONE);
    }

    @Override
    public void handleMessageInfo(Message msg) {
        super.handleMessageInfo(msg);
    }

    @OnClick({R.id.tv_right_text, R.id.tv_go_write_user_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_text:
                break;
            case R.id.tv_go_write_user_info:
                Intent intent = new Intent(this,AddUserInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
