package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.api.ApiFactory;
import com.slogan.wristband.wristband.api.model.base.BaseResp;
import com.slogan.wristband.wristband.app.ActivityResultCode;
import com.slogan.wristband.wristband.app.WristbandApplication;
import com.slogan.wristband.wristband.utils.ResponseUtils;
import com.slogan.wristband.wristband.utils.StringUtils;
import com.slogan.wristband.wristband.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_title_parent)
    LinearLayout llTitleParent;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.iv_delete_phone)
    ImageView ivDeletePhone;
    @BindView(R.id.tv_gain_sms)
    TextView tvGainSms;
    @BindView(R.id.iv_choose_agreement)
    ImageView ivChooseAgreement;
    @BindView(R.id.tv_area_select)
    TextView tvAreaSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initHandler();
        initWidget();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ActivityResultCode.ACTIVITY_MOBILE_AREA&&resultCode == ActivityResultCode.ACTIVITY_MOBILE_AREA){
            String code = data.getStringExtra("area_code");
            tvAreaSelect.setText("+"+code);
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(StringUtils.isBlank(s.toString())){
                    tvGainSms.setEnabled(false);
                    ivDeletePhone.setVisibility(View.GONE);
                }else{
                    tvGainSms.setEnabled(true);
                    ivDeletePhone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
private int isAgree = 1;
    @OnClick({R.id.iv_left, R.id.iv_delete_phone, R.id.tv_gain_sms, R.id.iv_choose_agreement
    ,R.id.tv_area_select
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_area_select:
                gotoMobileAreaActivity();
                break;
            case R.id.iv_delete_phone:
                etPhoneNumber.setText("");
                break;
            case R.id.tv_gain_sms:
                sendGainSmsRequest();
                break;
            case R.id.iv_choose_agreement:
                isAgree = isAgree == 0?1:0;
                if(isAgree == 1){
                    ivChooseAgreement.setImageDrawable(getResources().getDrawable(R.drawable.icon_sex_selected));
                }else{
                    ivChooseAgreement.setImageDrawable(getResources().getDrawable(R.drawable.icon_sex_unselected));
                }
                break;
        }
    }

    private void gotoMobileAreaActivity() {
        Intent intent = new Intent(this,MobileAreaActivity.class);
        startActivityForResult(intent,ActivityResultCode.ACTIVITY_MOBILE_AREA);
    }

    private void sendGainSmsRequest(){
        if(isAgree != 1){
            WristbandApplication.getInstance().showToast("需同意用户协议及隐私政策才能注册");
            return;
        }
        final String mobile = etPhoneNumber.getText().toString().trim();
        Call<BaseResp> call = ApiFactory.provideAccountUserService().sendMsg(mobile);
        call.enqueue(new Callback<BaseResp>() {
            @Override
            public void onResponse(Call<BaseResp> call, Response<BaseResp> response) {
                BaseResp baseResp = response.body();
                if(ResponseUtils.isSuccess(baseResp)){
                    WristbandApplication.getInstance().showToast("获取验证码成功");
                    gotoVerifyActivity(mobile);
                }
            }

            @Override
            public void onFailure(Call<BaseResp> call, Throwable t) {

            }
        });
    }

    @Override
    public void handleMessageInfo(Message msg) {
        super.handleMessageInfo(msg);
        switch (msg.what){
            case 1:
                break;
        }
    }

    private void gotoVerifyActivity(String mobile) {
        Intent intent = new Intent(this,VerifyCodeActivity.class);
        intent.putExtra("mobile",mobile);
        startActivity(intent);
    }
}
