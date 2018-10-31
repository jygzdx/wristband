package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initHandler();
        initWidget();
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
                    tvGainSms.setBackgroundResource(R.drawable.shape_check_code_unselect_bg);
                    tvGainSms.setEnabled(false);
                    ivDeletePhone.setVisibility(View.GONE);
                }else{
                    tvGainSms.setBackgroundResource(R.drawable.shape_check_code_bg);
                    tvGainSms.setEnabled(true);
                    ivDeletePhone.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_left, R.id.iv_delete_phone, R.id.tv_gain_sms, R.id.iv_choose_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_delete_phone:
                etPhoneNumber.setText("");
                break;
            case R.id.tv_gain_sms:
                sendGainSmsRequest();
                break;
            case R.id.iv_choose_agreement:
                break;
        }
    }

    private void sendGainSmsRequest(){
        handler.sendEmptyMessageDelayed(1,3000);
    }

    @Override
    public void handleMessageInfo(Message msg) {
        super.handleMessageInfo(msg);
        switch (msg.what){
            case 1:
                gotoVerifyActivity();
                break;
        }
    }

    private void gotoVerifyActivity() {
        Intent intent = new Intent(this,VerifyCodeActivity.class);
        startActivity(intent);
    }
}
