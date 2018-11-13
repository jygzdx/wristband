package com.slogan.wristband.wristband.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.utils.LogDataUtils;
import com.slogan.wristband.wristband.widght.XiaoMiStep;
import com.slogan.wristband.wristband.widght.YueDuPaiMing;
import com.veclink.bracelet.bean.BleDeviceData;
import com.veclink.bracelet.bean.DeviceSleepData;
import com.veclink.bracelet.bean.DeviceSportAndSleepData;
import com.veclink.bracelet.bean.DeviceSportData;
import com.veclink.bracelet.bletask.BleCallBack;
import com.veclink.bracelet.bletask.BleProgressCallback;
import com.veclink.bracelet.bletask.BleSyncNewDeviceDataTask;
import com.veclink.hw.bledevice.BraceletNewDevice;
import com.veclink.sdk.VeclinkSDK;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by czb on 2018/10/31.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.xiao_mi_step)
    XiaoMiStep xiaoMiStep;
    private Unbinder unbind;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        unbind = ButterKnife.bind(this,mRootView);
        initHandler();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        refreshSport();
    }

    private void refreshSport() {
        long  startTimeInmills  =  Calendar.getInstance().getTimeInMillis();
        long  endTimeInMills  =  Calendar.getInstance().getTimeInMillis();
        VeclinkSDK.getInstance().syncStepData(startTimeInmills,  endTimeInMills,  new BleProgressCallback()  {
            @Override
            public  void  onProgress(Object  progress)  {
                Logger.d("refreshSport--onProgress"+progress);
            }
            @Override
            public  void  onStart(Object  startObject)  {
                Logger.d("refreshSport--onStart");
            }
            @Override
            public  void  onFailed(Object  error)  {
                Logger.d("refreshSport--onFailed");
            }
            @Override
            public  void  onFinish(Object  result)  {
                Logger.d("refreshSport--onFinish"+Thread.currentThread().getName());
                BleDeviceData deviceData  =  (BleDeviceData)  result;
                List<DeviceSportData> sportData = deviceData.syncSportDataResult;
                int size = sportData.size();
                int total = 0;
                for (int i = 0; i < size; i++) {
                    DeviceSportData data = sportData.get(i);
                    total = total+data.sportStep;
                }
                xiaoMiStep.reSet(total);
//                Message msg = new Message();
//                msg.what = 1;
//                msg.obj = total;
//                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void handleMessageInfo(Message message) {
        super.handleMessageInfo(message);
        switch (message.what){
            case 1:
                int total = (int) message.obj;
                xiaoMiStep.setMyFootNum(total);
                break;
        }
    }

    private static final String TAG = "HomeFragment";
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}
