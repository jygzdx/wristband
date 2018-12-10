package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.api.ApiFactory;
import com.slogan.wristband.wristband.api.model.base.BaseResp;
import com.slogan.wristband.wristband.api.model.base.TokenResp;
import com.slogan.wristband.wristband.db.UserInfoConfig;
import com.slogan.wristband.wristband.db.UserInfoSharedPreference;
import com.slogan.wristband.wristband.utils.ResponseUtils;
import com.slogan.wristband.wristband.utils.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        StatusBarCompat.compat(this);
        initHandler();
        handler.sendEmptyMessageDelayed(1,1000);

    }

    private void checkLogin() {
        Call<BaseResp> call = ApiFactory.provideAccountUserService().validateToken(getToken());
        call.enqueue(new Callback<BaseResp>() {
            @Override
            public void onResponse(Call<BaseResp> call, Response<BaseResp> response) {
                BaseResp baseResp = response.body();
                if(baseResp.getCode() == ResponseUtils.SUCCESS_CODE){
                    refreshToken();
                }else{
                    gotoLoginActivity();
                }
            }

            @Override
            public void onFailure(Call<BaseResp> call, Throwable t) {
                gotoLoginActivity();
            }
        });
    }

    private void refreshToken() {
        Call<TokenResp> call = ApiFactory.provideAccountUserService().refreshToken(getToken());
        call.enqueue(new Callback<TokenResp>() {
            @Override
            public void onResponse(Call<TokenResp> call, Response<TokenResp> response) {
                TokenResp tokenResp = response.body();
                if(tokenResp.getCode() == ResponseUtils.SUCCESS_CODE){
                    UserInfoSharedPreference.saveUserInfoString(mContext,UserInfoConfig.TOKEN,tokenResp.getToken());
                    gotoMainActivity();
                }else{
                    gotoLoginActivity();
                }
            }

            @Override
            public void onFailure(Call<TokenResp> call, Throwable t) {
                gotoLoginActivity();
            }
        });
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoLoginActivity(){
        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleMessageInfo(Message message) {
        super.handleMessageInfo(message);
switch (message.what){
    case 1:
        checkLogin();
        break;
}
    }

    @OnClick(R.id.iv_logo)
    public void onViewClicked() {

    }
}
