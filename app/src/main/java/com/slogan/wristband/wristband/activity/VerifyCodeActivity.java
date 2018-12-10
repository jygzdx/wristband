package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.api.ApiFactory;
import com.slogan.wristband.wristband.api.model.base.BaseResp;
import com.slogan.wristband.wristband.app.WristbandApplication;
import com.slogan.wristband.wristband.utils.ResponseUtils;
import com.slogan.wristband.wristband.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String mobile;
    private int mShowPassword;
    private String code;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        ButterKnife.bind(this);
        mobile = getIntent().getStringExtra("mobile");
        initHandler();
        initWidget();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        handler.sendEmptyMessage(1);
        tvPhone.setText(mobile);
        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                code  = etVerifyCode.getText().toString().trim();
                if (StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
                    tvRegister.setEnabled(false);
                } else {
                    tvRegister.setEnabled(true);
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
                password  = etPassword.getText().toString().trim();
                if (StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
                    tvRegister.setEnabled(false);
                } else {
                    tvRegister.setEnabled(true);
                }
            }
        });
    }
private int mCodeSecond = 60;
    @Override
    public void handleMessageInfo(Message msg) {
        super.handleMessageInfo(msg);
        switch (msg.what){
            case 1:
                if(mCodeSecond>0){
                    mCodeSecond --;
                    handler.sendEmptyMessageDelayed(1,1000);
                    tvVerifyTime.setText("重新发送("+mCodeSecond+")");
                    tvVerifyTime.setEnabled(false);
                }else if(mCodeSecond<= 0){
                    mCodeSecond = 60;
                    handler.removeMessages(1);
                    tvVerifyTime.setText("重新获取验证码");
                    tvVerifyTime.setEnabled(true);
                }
                break;
        }
    }

    @OnClick({R.id.tv_verify_time, R.id.tv_show_password, R.id.tv_register,R.id.iv_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
finish();
                break;
            case R.id.tv_verify_time:
                handler.sendEmptyMessage(1);
                if(StringUtils.isBlank(mobile)){
                    WristbandApplication.getInstance().showToast("手机号码不能为空");
                    return;
                }
                Call<BaseResp> call = ApiFactory.provideAccountUserService().sendMsg(mobile);
                call.enqueue(new Callback<BaseResp>() {
                    @Override
                    public void onResponse(Call<BaseResp> call, Response<BaseResp> response) {
                        BaseResp baseResp = response.body();
                        if(ResponseUtils.isSuccess(baseResp)){
                            WristbandApplication.getInstance().showToast("获取验证码成功");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResp> call, Throwable t) {

                    }
                });
                break;
            case R.id.tv_show_password:
                mShowPassword = mShowPassword == 0?1:0;
                if(mShowPassword == 1){
                    tvShowPassword.setImageDrawable(mContext.getResources().getDrawable(R.drawable.open_eye));
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    tvShowPassword.setImageDrawable(mContext.getResources().getDrawable(R.drawable.close_eye));
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.tv_register:
                Call<BaseResp> respCall = ApiFactory.provideAccountUserService().register(mobile,password,code);
                respCall.enqueue(new Callback<BaseResp>() {
                    @Override
                    public void onResponse(Call<BaseResp> call, Response<BaseResp> response) {
                        BaseResp baseResp = response.body();
                        if(ResponseUtils.isSuccess(baseResp)){
                            WristbandApplication.getInstance().showToast("注册成功");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResp> call, Throwable t) {

                    }
                });
                break;
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(VerifyCodeActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
