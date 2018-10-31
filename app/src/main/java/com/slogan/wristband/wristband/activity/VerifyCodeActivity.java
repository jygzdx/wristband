package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyCodeActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.tv_verify_time)
    TextView tvVerifyTime;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_show_password)
    ImageView tvShowPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        ButterKnife.bind(this);
        initHandler();
        initWidget();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_verify_time, R.id.tv_show_password, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verify_time:
                break;
            case R.id.tv_show_password:
                break;
            case R.id.tv_register:
                gotoMainActivity();
                break;
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(VerifyCodeActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
