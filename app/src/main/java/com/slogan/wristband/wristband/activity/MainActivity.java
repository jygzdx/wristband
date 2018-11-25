package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseFragmentActivity;
import com.slogan.wristband.wristband.fragment.FindFragment;
import com.slogan.wristband.wristband.fragment.HomeFragment;
import com.slogan.wristband.wristband.fragment.MeFragment;
import com.slogan.wristband.wristband.utils.CommTool;
import com.slogan.wristband.wristband.utils.ToastUtils;
import com.veclink.bracelet.bean.DeviceInfo;
import com.veclink.bracelet.bletask.BleCallBack;
import com.veclink.sdk.DeviceStateObserver;
import com.veclink.sdk.RssiListener;
import com.veclink.sdk.SittingRemindObserver;
import com.veclink.sdk.VeclinkSDK;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity implements RssiListener {

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
        VeclinkSDK.getInstance().registerDeviceStateObserver(deviceStateObserver);
        VeclinkSDK.getInstance().registerSittingRemindObserver(sittingRemindObserver);
        VeclinkSDK.getInstance().getRssi(this);
        initHandler();
        initWidget();

        VeclinkSDK.getInstance().queryFirmwareVersion(new BleCallBack() {
            @Override
            public void onStart(Object o) {
                Logger.d("queryFirmwareVersion->onStart" + o.toString());
            }

            @Override
            public void onFailed(Object o) {
                Logger.d("queryFirmwareVersion->onFailed" + o.toString());
            }

            @Override
            public void onFinish(Object o) {
                Logger.d("queryFirmwareVersion->onFinish" + new Gson().toJson((DeviceInfo) o));
            }
        });


        if (!VeclinkSDK.getInstance().isHasBindDevice()) {
            Intent intent = new Intent(MainActivity.this, BindDeviceActivity.class);
            startActivity(intent);
        } else {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VeclinkSDK.getInstance().unregisterDeviceStateObserver(deviceStateObserver);
        VeclinkSDK.getInstance().unregisterSittingRemindObserver(sittingRemindObserver);
    }

    private SittingRemindObserver sittingRemindObserver = new SittingRemindObserver() {
        @Override
        public void onReceiveSittingRemind() {
            Logger.d("onReceiveSittingRemind");
        }
    };

    private DeviceStateObserver deviceStateObserver = new DeviceStateObserver() {
        @Override
        public void connecting() {
            Logger.d("connecting");
        }

        @Override
        public void connected() {
            Logger.d("connected");
            if (!VeclinkSDK.getInstance().isHasBindDevice()) {
                Intent intent = new Intent(MainActivity.this, BindDeviceActivity.class);
                startActivity(intent);
            }
        }

        @Override
        public void disConnected() {
            Logger.d("disConnected");
            ToastUtils.showToast("蓝牙断开连接，请检查手机蓝牙是否打开");
            VeclinkSDK.getInstance().reConnectDevice();
        }

        @Override
        public void blueToothClose() {
            Logger.d("blueToothClose");
            ToastUtils.showToast("请打开蓝牙");
        }
    };


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

    private static final String TAG = "MainActivity";

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

    @Override
    public void rssiChange(int i) {
        Logger.d("rssiChange" + i);
    }
}
