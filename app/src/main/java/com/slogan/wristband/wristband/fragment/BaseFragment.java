package com.slogan.wristband.wristband.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.slogan.wristband.wristband.bean.MessageEvent;
import com.slogan.wristband.wristband.db.UserInfoConfig;
import com.slogan.wristband.wristband.db.UserInfoSharedPreference;
import com.slogan.wristband.wristband.requestengine.factory.HttpMsg;
import com.slogan.wristband.wristband.requestengine.factory.RequestTypeCode;
import com.slogan.wristband.wristband.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.lang.ref.WeakReference;

public abstract class BaseFragment extends Fragment implements HttpMsg,RequestTypeCode {
    protected View mRootView;

    protected Activity mContext;

    public int screenWidth;

    public int screenHeight;

    public MyHandler handler;

    public boolean loading = false;

    public String CALLBACK_DATA1 = "call_back_data1";

    public String CALLBACK_DATA2 = "call_back_data2";

    public String CALLBACK_DATA3 = "call_back_data3";

    public String CALLBACK_DATA_INT = "call_back_data_int";

    private long myUid;

    private String loginKey;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        screenHeight=dpMetrics.heightPixels;

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResId(), container, false);
        initView();
        return mRootView;
    }
    public static class MyHandler extends Handler {
        private WeakReference<BaseFragment> baseFragmentWeakReference;

        public MyHandler(BaseFragment baseFragment) {
            baseFragmentWeakReference = new WeakReference<BaseFragment>(baseFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseFragment baseFragment = baseFragmentWeakReference.get();
            super.handleMessage(msg);
            if (baseFragment == null || baseFragment.mContext==null||baseFragment.mContext.isFinishing()) {
                return;
            }
            baseFragment.handleMessageInfo(msg);
        }
    }
    public void handleMessageInfo(Message message) {

    }

    protected void initHandler(){
        handler=new MyHandler(this);
    }

    protected abstract int getLayoutResId();

    protected abstract void initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void handleResult(Object result1, Object result2, Object result3, Object result4, int type, int page, int count, int arg1) {
        Message message=new Message();
        message.what=type;
        message.obj=result1;
        message.arg1=page;
        message.arg2=count;
        message.getData().putInt(CALLBACK_DATA_INT,arg1);
        if(result2!=null){
            message.getData().putSerializable(CALLBACK_DATA1, (Serializable) result2);
        }
        if(result3!=null){
            message.getData().putSerializable(CALLBACK_DATA2, (Serializable) result3);
        }
        if(result4!=null){
            message.getData().putSerializable(CALLBACK_DATA3, (Serializable) result4);
        }
        handler.sendMessage(message);
    }

    @Override
    public void handleErrorInfo(String error_msg, Object arg1, int error_code, int type, int arg2, int arg3) {
        Message message=new Message();
        message.what=ERROR;
        message.obj=error_msg;
        message.arg1=type;
        message.arg2=error_code;
        message.getData().putInt(CALLBACK_DATA_INT,arg3);
        if(arg1!=null){
            message.getData().putSerializable(CALLBACK_DATA1, (Serializable) arg1);
        }
        handler.sendMessage(message);
    }

    /**
     *
     * @return 当前操作用户ID
     */
    public long getMyUid() {
        if (myUid == 0) {
            myUid = UserInfoSharedPreference.getUserInfoLong(mContext, UserInfoConfig.USER_ID, 0);
        }
        return myUid;
    }
    /**
     *
     * @return 当前操作用户LOGIN_KEY
     * */
    public String getLoginKey() {
        if (StringUtils.isBlank(loginKey)) {
            loginKey = UserInfoSharedPreference.getUserInfoString(mContext, UserInfoConfig.LOGIN_KEY, "");
        }
        return loginKey;
    }

    @Subscribe
    public void onEvent(MessageEvent event) {

    }
}
