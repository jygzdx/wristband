package com.slogan.wristband.wristband.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.slogan.wristband.wristband.bean.MessageEvent;
import com.slogan.wristband.wristband.db.UserInfoConfig;
import com.slogan.wristband.wristband.db.UserInfoSharedPreference;
import com.slogan.wristband.wristband.requestengine.factory.HttpMsg;
import com.slogan.wristband.wristband.requestengine.factory.RequestTypeCode;
import com.slogan.wristband.wristband.utils.CommTool;
import com.slogan.wristband.wristband.utils.DisplayUtils;
import com.slogan.wristband.wristband.utils.StatusBarTintManager;
import com.slogan.wristband.wristband.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.lang.ref.WeakReference;

public class BaseFragmentActivity extends FragmentActivity implements HttpMsg, RequestTypeCode {
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

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.mContext = this;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }
    public void setStatusBarTint(int color, boolean dark) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        StatusBarTintManager tintManager = new StatusBarTintManager(this);
        tintManager.setTintColor(color);
        tintManager.setStatusBarTintEnabled(true);
        View root = f(android.R.id.content);
        root.setPadding(0, DisplayUtils.getStatusBarHeight(), 0, 0);
        CommTool.setStatusBarFontDark(dark, this);

    }

    public <T extends View> T f(int id) {
        return CommTool.findById(this, id);
    }

    /**
     * 查询化控件
     *
     * @see [类、类#方法、类#成员]
     */
    public void findWidget() {

    }

    /**
     * 初始化控件
     *
     * @see [类、类#方法、类#成员]
     */
    public void initWidget() {

    }

    /**
     * 初始化Handler
     *
     * @see [类、类#方法、类#成员]
     */
    public void initHandler() {
        handler=new MyHandler(this);
    }

//    public void setUnloading() {
//        if (loading) {
//            loading = false;
//        }
//        closeLoadingDialog();
//    }
//
//    public void setLoading(int textId, String text) {
//        loading = true;
//        showLoadingDialog(text, textId);
//    }
//
//    public void showLoadingDialog(String text, int textId) {
//        if (commLoadingDialog == null) {
//            commLoadingDialog = new CommLoadingDialog(this);
//        }
//        commLoadingDialog.showDialog(text, textId);
//    }
//
//    public void closeLoadingDialog() {
//        if (commLoadingDialog != null) {
//            commLoadingDialog.closeDialog();
//        }
//    }

    /**
     * @return 当前操作用户ID
     */
    public long getMyUid() {
        if (myUid == 0) {
            myUid = UserInfoSharedPreference.getUserInfoLong(BaseFragmentActivity.this, UserInfoConfig.USER_ID, 0);
        }
        return myUid;
    }

    /**
     * @return 当前操作用户LOGIN_KEY
     */
    public String getLoginKey() {
        if (StringUtils.isBlank(loginKey)) {
            loginKey = UserInfoSharedPreference.getUserInfoString(BaseFragmentActivity.this, UserInfoConfig.LOGIN_KEY, "");
        }
        return loginKey;
    }

    @Override
    public void handleResult(Object result1, Object result2, Object result3, Object result4, int type, int page, int count, int arg1) {
        Message message = new Message();
        message.what = type;
        message.obj = result1;
        message.arg1 = page;
        message.arg2 = count;
        message.getData().putInt(CALLBACK_DATA_INT, arg1);
        if (result2 != null) {
            message.getData().putSerializable(CALLBACK_DATA1, (Serializable) result2);
        }
        if (result3 != null) {
            message.getData().putSerializable(CALLBACK_DATA2, (Serializable) result3);
        }
        if (result4 != null) {
            message.getData().putSerializable(CALLBACK_DATA3, (Serializable) result4);
        }
        handler.sendMessage(message);
    }

    @Override
    public void handleErrorInfo(String error_msg, Object arg1, int error_code, int type, int arg2, int arg3) {
        Message message = new Message();
        message.what = ERROR;
        message.obj = error_msg;
        message.arg1 = type;
        message.arg2 = error_code;
        message.getData().putInt(CALLBACK_DATA_INT, arg3);
        if (arg1 != null) {
            message.getData().putSerializable(CALLBACK_DATA1, (Serializable) arg1);
        }
        handler.sendMessage(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Subscribe
    public void onEvent(MessageEvent event) {

    }

    public void handleMessageInfo(Message message) {

    }

    public static class MyHandler extends Handler {
        private WeakReference<BaseFragmentActivity> baseActivity;

        public MyHandler(BaseFragmentActivity activity) {
            baseActivity = new WeakReference<BaseFragmentActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseFragmentActivity activity = baseActivity.get();
            super.handleMessage(msg);
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.handleMessageInfo(msg);
        }
    }
}
