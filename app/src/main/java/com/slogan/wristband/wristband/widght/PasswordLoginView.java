package com.slogan.wristband.wristband.widght;

import android.accounts.Account;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.MainActivity;
import com.slogan.wristband.wristband.activity.RegisterActivity;
import com.slogan.wristband.wristband.activity.RegisterSuccessActivity;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.CommTool;
import com.slogan.wristband.wristband.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by free_boy on 2018/10/24.
 */

public class PasswordLoginView extends LinearLayout {
    private final Context mContext;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_show_password)
    ImageView tvShowPassword;
    @BindView(R.id.tv_to_register)
    TextView tvToRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private View view;
    private Handler handler;

    public PasswordLoginView(Context context) {
        super(context);
        this.mContext = context;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_password_view, this);
        ButterKnife.bind(view);
        initHandler();
        initWidget();
    }

    private void initWidget() {
        tvLogin.setEnabled(false);
        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                account = etUser.getText().toString().trim();
                if(StringUtils.isBlank(account)||StringUtils.isBlank(password)){
                    tvLogin.setEnabled(false);
                }else{
                    tvLogin.setEnabled(true);
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password = etPassword.getText().toString().trim();
                if(StringUtils.isBlank(account)||StringUtils.isBlank(password)){
                    tvLogin.setEnabled(false);
                }else{
                    tvLogin.setEnabled(true);
                }
            }
        });
    }

    private String account;
    private String password;

    @SuppressLint("HandlerLeak")
    public void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        break;
                }
            }
        };
    }

    @OnClick({R.id.tv_show_password, R.id.tv_to_register, R.id.tv_forget_password, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_show_password:
                break;
            case R.id.tv_to_register:
                Intent register = new Intent(mContext, RegisterActivity.class);
                mContext.startActivity(register);
                break;
            case R.id.tv_forget_password:
                Intent loginSuccess = new Intent(mContext,RegisterSuccessActivity.class);
                mContext.startActivity(loginSuccess);
                break;
            case R.id.tv_login:
                gotoMainActivity();
                break;
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(mContext,MainActivity.class);
        mContext.startActivity(intent);
    }

    public void closeKeybroad() {
        CommTool.closeKeyBord((Activity) mContext,etUser);
        CommTool.closeKeyBord((Activity) mContext,etPassword);
    }
}
