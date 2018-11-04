package com.slogan.wristband.wristband.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.activity.base.BaseFragmentActivity;
import com.slogan.wristband.wristband.app.WristbandApplication;
import com.slogan.wristband.wristband.fragment.FindFragment;
import com.slogan.wristband.wristband.fragment.HomeFragment;
import com.slogan.wristband.wristband.fragment.MeFragment;
import com.slogan.wristband.wristband.utils.CommTool;
import com.slogan.wristband.wristband.utils.LogDataUtils;
import com.veclink.bracelet.bletask.BleCallBack;
import com.veclink.bracelet.bletask.BleRequestBindDevice;
import com.veclink.bracelet.bletask.BleScanDeviceTask;
import com.veclink.bracelet.bletask.BleSyncParamsTask;
import com.veclink.hw.bleservice.VLBleService;
import com.veclink.hw.bleservice.VLBleServiceManager;
import com.veclink.hw.bleservice.profile.BraceletGattAttributes;
import com.veclink.hw.bleservice.util.Keeper;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.tab_content)
    FrameLayout tabContent;
    @BindView(R.id.home_tab_img)
    TextView homeTabImg;
    @BindView(R.id.find_tab_img)
    TextView findTabImg;
    @BindView(R.id.me_tab_img)
    TextView meTabImg;
    @BindView(R.id.bottom_rl)
    LinearLayout bottomRl;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private MeFragment meFragment;
    private List<Fragment> fragments = new ArrayList<>();
    private int tab_content;
    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(Keeper.getUserHasBindBand(WristbandApplication.getInstance())){
            VLBleServiceManager.setAutoReConnect(true);
            VLBleServiceManager.getInstance().bindService(WristbandApplication.getInstance());
        }
        initHandler();
        initWidget();
        initReciver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!Keeper.getUserHasBindBand(WristbandApplication.getInstance())){
            Intent intent = new Intent(mContext,BindDeviceActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VLBleServiceManager.getInstance().unBindService(WristbandApplication.getInstance());
    }


    @Override
    public void initWidget() {
        super.initWidget();
        homeFragment = new HomeFragment();
        findFragment = new FindFragment();
        meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(findFragment);
        fragments.add(meFragment);
        tab_content = R.id.tab_content;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(tab_content, fragments.get(0));
        ft.commit();
        homeTabImg.setSelected(true);
    }

    @Override
    public void handleMessageInfo(Message msg) {
        super.handleMessageInfo(msg);
    }

    private void initReciver(){
        intentFilter.addAction(VLBleService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(VLBleService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(VLBleService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(VLBleService.ACTION_SHORT_SPORT_DATA);
        intentFilter.addAction(VLBleService.ACTION_POWER_CHANGE_DATA);
        registerReceiver(getdeviceMessageReceiver, intentFilter);
    }
    IntentFilter intentFilter = new IntentFilter();
    private static final String TAG = "MainActivity";
    BroadcastReceiver getdeviceMessageReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context arg0, Intent intent) {
            String action = intent.getAction();
            if(action.equals(VLBleService.ACTION_GATT_SERVICES_DISCOVERED)){
//                connectHandler.sendEmptyMessage(DEVICE_SERVERDISCOVER);
                LogDataUtils.logData(TAG,"ACTION_GATT_SERVICES_DISCOVERED");
            }else if(action.equals(VLBleService.ACTION_GATT_DISCONNECTED)){
//                connectHandler.sendEmptyMessage(DEVICE_DISCONNECTED);
                LogDataUtils.logData(TAG,"ACTION_GATT_DISCONNECTED");
            }else if(action.equals(VLBleService.ACTION_GATT_CONNECTED)){
//                connectHandler.sendEmptyMessage(DEVICE_CONNECTED);
                LogDataUtils.logData(TAG,"ACTION_GATT_CONNECTED");
            }else if(action.equals(VLBleService.ACTION_SHORT_SPORT_DATA)){
//                int sportSteps = intent.getIntExtra(VLBleService.EXTRA_DATA, 0);
//                showMsgView.setText(getString(R.string.today_step,sportSteps));
                LogDataUtils.logData(TAG,"ACTION_SHORT_SPORT_DATA");
            }else if(action.equals(VLBleService.ACTION_POWER_CHANGE_DATA)){
//                int powerValue = intent.getIntExtra(VLBleService.EXTRA_DATA, 0);
//                showMsgView.setText(getString(R.string.power_value,powerValue));
                LogDataUtils.logData(TAG,"ACTION_POWER_CHANGE_DATA");
            }

        }

    };

    @OnClick({R.id.home_tab_img, R.id.find_tab_img, R.id.me_tab_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_tab_img:
                if (current != 0) {
                    homeTabImg.setSelected(true);
                    findTabImg.setSelected(false);
                    meTabImg.setSelected(false);
                    switchTab(0);

                } else {
                    // 刷新
                }
                break;
            case R.id.find_tab_img:
                if (current != 1) {
                    homeTabImg.setSelected(false);
                    findTabImg.setSelected(true);
                    meTabImg.setSelected(false);
                    switchTab(1);

                } else {
                    // 刷新
                }
                break;
            case R.id.me_tab_img:
                if (current != 2) {
                    homeTabImg.setSelected(false);
                    findTabImg.setSelected(false);
                    meTabImg.setSelected(true);
                    switchTab(2);

                } else {
                    // 刷新
                }
                break;
        }
    }

    private void switchTab(int which) {
        try {
            Fragment fragment = fragments.get(which);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragments.get(current).onPause(); // 暂停当前tab

            if (fragment.isAdded()) {
                fragment.onResume(); // 启动目标tab的onResume()
            } else {
                ft.add(tab_content, fragment);
            }
            setTopState(which);
            showTab(which); // 显示目标tab
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            System.out.println(CommTool.getErrorStack(e, -1));
        }
    }

    private void setTopState(int which) {
        //根据which更改状态栏
    }

    private void showTab(int idx) {
        try {
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                if (idx == i) {
                    ft.show(fragment);
                } else {
                    ft.hide(fragment);
                }
                ft.commit();
            }
            current = idx; // 更新目标tab为当前tab
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(CommTool.getErrorStack(e, -1));
        }
    }

}
