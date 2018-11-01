package com.slogan.wristband.wristband.widght;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.RegisterActivity;
import com.slogan.wristband.wristband.utils.CommTool;
import com.slogan.wristband.wristband.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by free_boy on 2018/10/24.
 */

public class CodeLoginView extends LinearLayout {
    private final Context mContext;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.tv_verify_time)
    TextView tvVerifyTime;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private View view;
    private Handler handler;

    public CodeLoginView(Context context) {
        super(context);
        this.mContext = context;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_code_login_view, this);
        ButterKnife.bind(view);
        initHandler();
        initWidget();
    }

    private void initWidget() {
        tvLogin.setEnabled(false);
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phone = etPhoneNumber.getText().toString().trim();
                if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
                    tvLogin.setEnabled(false);
                } else {
                    tvLogin.setEnabled(true);
                }
            }
        });
        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                code = etVerifyCode.getText().toString().trim();
                if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
                    tvLogin.setEnabled(false);
                } else {
                    tvLogin.setEnabled(true);
                }
            }
        });
    }

    private String phone;
    private String code;

    @SuppressLint("HandlerLeak")
    public void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        break;
                }
            }
        };
    }


    @OnClick({R.id.tv_verify_time, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verify_time:
                break;
            case R.id.tv_login:
                break;
        }
    }

    public void closeKeybroad() {
        CommTool.closeKeyBord((Activity) mContext,etPhoneNumber);
        CommTool.closeKeyBord((Activity) mContext,etVerifyCode);
    }
}
