package com.slogan.wristband.wristband.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.utils.LogDataUtils;
import com.slogan.wristband.wristband.widght.XiaoMiStep;
import com.slogan.wristband.wristband.widght.YueDuPaiMing;
import com.veclink.bracelet.bean.DeviceSleepData;
import com.veclink.bracelet.bean.DeviceSportAndSleepData;
import com.veclink.bracelet.bean.DeviceSportData;
import com.veclink.bracelet.bletask.BleCallBack;
import com.veclink.bracelet.bletask.BleSyncNewDeviceDataTask;
import com.veclink.hw.bledevice.BraceletNewDevice;

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
    private BleSyncNewDeviceDataTask sportTask;
    private Unbinder unbind;

    private BleSyncNewDeviceDataTask sleepTask;

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

    }

    @Override
    public void handleMessageInfo(Message message) {
        super.handleMessageInfo(message);
        switch (message.what){
            case 1:
                break;
        }
    }

    private static final String TAG = "HomeFragment";
    @SuppressLint("HandlerLeak")
    Handler syncStepDataHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BleCallBack.TASK_START:
                    LogDataUtils.logData(TAG, "TASK_START");
                    break;
                case BleCallBack.TASK_PROGRESS:
                    LogDataUtils.logData(TAG, "TASK_PROGRESS");
                    break;

                case BleCallBack.TASK_FINISH:
                    LogDataUtils.logData(TAG, "TASK_FINISH");
                    if (msg.obj != null) {
                        DeviceSportAndSleepData deviceSportAndSleepData = (DeviceSportAndSleepData) msg.obj;
                        List<DeviceSportData> sportDataList = deviceSportAndSleepData.syncSportDataResult;
                        int totalStep = 0;
                        for (int i = 0; i < sportDataList.size(); i++) {
                            int step = sportDataList.get(i).sportStep;
                            totalStep = totalStep + step;
                        }
                        LogDataUtils.logData(TAG, totalStep + "");
                        xiaoMiStep.setMyFootNum(totalStep);
                    }

                    break;

                case BleCallBack.TASK_FAILED:
                    LogDataUtils.logData(TAG, "TASK_FAILED");
                    break;
            }
        }

    };

    @SuppressLint("HandlerLeak")
    Handler sleepHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BleCallBack.TASK_START:
                    LogDataUtils.logData(TAG, "TASK_START");
                    break;
                case BleCallBack.TASK_PROGRESS:
                    LogDataUtils.logData(TAG, "TASK_PROGRESS");
                    break;

                case BleCallBack.TASK_FINISH:
                    LogDataUtils.logData(TAG, "TASK_FINISH");
                    if (msg.obj != null) {
//                        DeviceSportAndSleepData deviceSportAndSleepData = (DeviceSportAndSleepData) msg.obj;
//                        List<DeviceSleepData> sleepDataList = deviceSportAndSleepData.syncSleepDataResult;
//                        int totalStep = 0;
//                        for (int i = 0; i < sleepDataList.size(); i++) {
//                            int step = sportDataList.get(i).sportStep;
//                            totalStep = totalStep + step;
//                        }
//                        LogDataUtils.logData(TAG, totalStep + "");
//                        xiaoMiStep.setMyFootNum(totalStep);
                    }

                    break;

                case BleCallBack.TASK_FAILED:
                    LogDataUtils.logData(TAG, "TASK_FAILED");
                    break;
            }
        }

    };

    BleCallBack syncStepDataCallBack = new BleCallBack(syncStepDataHandler);
    private BleCallBack syncSleepCallBack = new BleCallBack(sleepHandler);

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }

    public void connectDeviceSuccess() {
        Date startDate = new Date();
        Date endDate = new Date();
        if (sportTask == null) {
            sportTask = new BleSyncNewDeviceDataTask(mContext, syncStepDataCallBack,
                    BraceletNewDevice.SPORT_DATA, startDate, endDate);
        }
        sportTask.work();
        if(sleepTask == null){
            sleepTask = new BleSyncNewDeviceDataTask(mContext,
                    syncSleepCallBack,BraceletNewDevice.SLEEP_DATA,startDate,endDate);
        }
        sleepTask.work();
    }
}
