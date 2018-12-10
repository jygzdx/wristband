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
import com.slogan.wristband.wristband.activity.MainActivity;
import com.slogan.wristband.wristband.activity.RegisterActivity;
import com.slogan.wristband.wristband.activity.VerifyCodeActivity;
import com.slogan.wristband.wristband.api.ApiFactory;
import com.slogan.wristband.wristband.api.model.base.BaseResp;
import com.slogan.wristband.wristband.api.model.base.TokenResp;
import com.slogan.wristband.wristband.app.WristbandApplication;
import com.slogan.wristband.wristband.db.UserInfoConfig;
import com.slogan.wristband.wristband.db.UserInfoSharedPreference;
import com.slogan.wristband.wristband.utils.CommTool;
import com.slogan.wristband.wristband.utils.ResponseUtils;
import com.slogan.wristband.wristband.utils.StringUtils;
import com.slogan.wristband.wristband.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void onDestroy(){
        handler.removeCallbacksAndMessages(null);
    }

    private void initWidget() {
        tvLogin.setEnabled(false);
        tvVerifyTime.setEnabled(true);
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
                        if(mCodeSecond>0){
                            mCodeSecond --;
                            handler.sendEmptyMessageDelayed(1,1000);
                            tvVerifyTime.setText("重新发送("+mCodeSecond+")");
                            tvVerifyTime.setEnabled(false);
                        }else if(mCodeSecond<= 0){
                            mCodeSecond = 60;
                            handler.removeMessages(1);
                            tvVerifyTime.setText("获取验证码");
                            tvVerifyTime.setEnabled(true);
                        }
                        break;
                }
            }
        };
    }

    int mCodeSecond = 60;
    @OnClick({R.id.tv_verify_time, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verify_time:
                String mobile = etPhoneNumber.getText().toString().trim();
                if(StringUtils.isBlank(mobile)){
                    WristbandApplication.getInstance().showToast("手机号码不能为空");
                    return;
                }
                handler.sendEmptyMessage(1);
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
            case R.id.tv_login:
                Call<TokenResp> codeCall = ApiFactory.provideAccountUserService().loginByCode(phone,code);
                codeCall.enqueue(new Callback<TokenResp>() {
                    @Override
                    public void onResponse(Call<TokenResp> call, Response<TokenResp> response) {
                        TokenResp tokenResp = response.body();
                        if(ResponseUtils.isSuccess(tokenResp)){
                            UserInfoSharedPreference.saveUserInfoString(mContext,UserInfoConfig.TOKEN,tokenResp.getToken());
                            gotoMainActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResp> call, Throwable t) {

                    }
                });
                break;
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(mContext,MainActivity.class);
        mContext.startActivity(intent);
    }

    public void closeKeybroad() {
        CommTool.closeKeyBord((Activity) mContext,etPhoneNumber);
        CommTool.closeKeyBord((Activity) mContext,etVerifyCode);
    }
}
