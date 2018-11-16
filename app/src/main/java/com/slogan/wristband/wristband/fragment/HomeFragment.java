package com.slogan.wristband.wristband.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.HeartRateActivity;
import com.slogan.wristband.wristband.utils.CommTool;
import com.slogan.wristband.wristband.widght.TimeView;
import com.slogan.wristband.wristband.widght.XiaoMiStep;
import com.veclink.bracelet.bean.BleDeviceData;
import com.veclink.bracelet.bean.BleLongSittingRemindParam;
import com.veclink.bracelet.bean.BloodOxygenBean;
import com.veclink.bracelet.bean.BloodPressBean;
import com.veclink.bracelet.bean.DeviceSleepData;
import com.veclink.bracelet.bean.DeviceSportData;
import com.veclink.bracelet.bean.HeartRateBean;
import com.veclink.bracelet.bletask.BleCallBack;
import com.veclink.bracelet.bletask.BleProgressCallback;
import com.veclink.sdk.VeclinkSDK;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by czb on 2018/10/31.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.xiao_mi_step)
    XiaoMiStep xiaoMiStep;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_total_sleep_time)
    TimeView tvTotalSleepTime;
    @BindView(R.id.time_deep_sleep)
    TimeView timeDeepSleep;
    @BindView(R.id.time_light_sleep)
    TimeView timeLightSleep;
    @BindView(R.id.time_clear_sleep)
    TimeView timeClearSleep;
    @BindView(R.id.tv_heart_rate_time)
    TextView tvHeartRateTime;
    @BindView(R.id.heart_rate_num)
    TimeView heartRateNum;
    @BindView(R.id.tv_blood_pressure_time)
    TextView tvBloodPressureTime;
    @BindView(R.id.tv_blood_pressure_num)
    TextView tvBloodPressureNum;
    @BindView(R.id.tv_blood_oxygen_time)
    TextView tvBloodOxygenTime;
    @BindView(R.id.blood_oxygen_num)
    TimeView bloodOxygenNum;
    @BindView(R.id.tv_most_arrive_count)
    TextView tvMostArriveCount;
    @BindView(R.id.arrive_count_day)
    TimeView arriveCountDay;
    Unbinder unbinder;
    @BindView(R.id.ll_heart_rate)
    LinearLayout llHeartRate;
    @BindView(R.id.ll_blood_pressure)
    LinearLayout llBloodPressure;
    @BindView(R.id.ll_blood_oxygen)
    LinearLayout llBloodOxygen;
    @BindView(R.id.ll_most_arrive)
    LinearLayout llMostArrive;
    private Unbinder unbind;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        unbind = ButterKnife.bind(this, mRootView);
        initHandler();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        refreshSport();
        refreshSleep();
        refreshHeartRate();
        refreshBloodPressure();
        refreshBloodOxygen();
        setLongSeatRemind();
    }

    private void setLongSeatRemind() {
        BleLongSittingRemindParam bleLongSittingRemindParam = new BleLongSittingRemindParam(0, 10, 9, 20, 22, 10, BleLongSittingRemindParam.OPEN_REMIND);
        // bleLongSittingRemindParam.openflag = BleLongSittingRemindParam.OPEN_REMIND;
        // 设置为 OPEN_RMIND 表示打开久坐提醒
        // bleLongSittingRemindParam.openflag = BleLongSittingRemindParam.CLOSE_REMIND;
        // 设置为CLOSE_REMIND表示关闭久坐提醒
        VeclinkSDK.getInstance().setLongSittingRemind(new BleCallBack() {
            @Override
            public void onStart(Object o) {
                Logger.d("setLongSeatRemind--onStart" + o.toString());
            }

            @Override
            public void onFailed(Object o) {
                Logger.d("setLongSeatRemind--onFailed" + o.toString());
            }

            @Override
            public void onFinish(Object o) {
                Logger.d("setLongSeatRemind--onFinish" + o.toString());
            }
        }, bleLongSittingRemindParam);

    }

    /**
     * 血氧
     */
    private void refreshBloodOxygen() {
        long startTimeInmills = Calendar.getInstance().getTimeInMillis();
        long endTimeInMills = Calendar.getInstance().getTimeInMillis();
        VeclinkSDK.getInstance().syncBloodOxygenData(new BleProgressCallback() {
            @Override
            public void onProgress(Object progress) {
                Logger.d("refreshBloodOxygen--onProgress" + progress);
            }

            @Override
            public void onStart(Object startObject) {
                Logger.d("refreshBloodOxygen--onStart");
            }

            @Override
            public void onFailed(Object error) {
                Logger.d("refreshBloodOxygen--onFailed");
//                ToastUtils.showToast("查询血氧数据出错");
            }

            @Override
            public void onFinish(Object result) {
                Logger.d("refreshBloodOxygen--onFinish" + new Gson().toJson((BleDeviceData) result));
                BleDeviceData deviceData = (BleDeviceData) result;
                List<BloodOxygenBean> bloodOxygenData = deviceData.syncBloodOxygenDataResult;
                int size = bloodOxygenData.size();
                if (size > 0) {
                    BloodOxygenBean bean = bloodOxygenData.get(0);
                    tvBloodOxygenTime.setText(CommTool.getHearRateTime(bean.getDate() + "", bean.getMinute()));
                    bloodOxygenNum.setOneCount(bean.getBloodOxygen(), "%");
                }
            }
        });
    }

    /**
     * 血压
     */
    private void refreshBloodPressure() {
        long startTimeInmills = Calendar.getInstance().getTimeInMillis();
        long endTimeInMills = Calendar.getInstance().getTimeInMillis();
        VeclinkSDK.getInstance().syncBloodPressData(new BleProgressCallback() {
            @Override
            public void onProgress(Object progress) {
                Logger.d("refreshBloodPressure--onProgress" + progress);
            }

            @Override
            public void onStart(Object startObject) {
                Logger.d("refreshBloodPressure--onStart");
            }

            @Override
            public void onFailed(Object error) {
                Logger.d("refreshBloodPressure--onFailed");
//                ToastUtils.showToast("查询血压数据出错");
            }

            @Override
            public void onFinish(Object result) {
                Logger.d("refreshBloodPressure--onFinish" + new Gson().toJson((BleDeviceData) result));
                BleDeviceData deviceData = (BleDeviceData) result;
                List<BloodPressBean> bloodPressBeans = deviceData.syncBloodPressDataResult;
                int size = bloodPressBeans.size();
                if (size > 0) {
                    BloodPressBean bean = bloodPressBeans.get(0);
                    tvBloodPressureTime.setText(CommTool.getBloodPressTime(bean.getDate() + ""));
                    tvBloodPressureNum.setText(bean.getHightpress() + "/" + bean.getLowpress());
                }
            }
        });
    }

    /**
     * 心率
     */
    private void refreshHeartRate() {
        long startTimeInmills = Calendar.getInstance().getTimeInMillis();
        long endTimeInMills = Calendar.getInstance().getTimeInMillis();
        VeclinkSDK.getInstance().syncAllHeartRateData(new BleProgressCallback() {
            @Override
            public void onProgress(Object progress) {
                Logger.d("refreshHeartRate--onProgress" + progress);
            }

            @Override
            public void onStart(Object startObject) {
                Logger.d("refreshHeartRate--onStart");
            }

            @Override
            public void onFailed(Object error) {
                Logger.d("refreshHeartRate--onFailed");
//                ToastUtils.showToast("查询心率数据出错");
            }

            @Override
            public void onFinish(Object result) {
                Logger.d("refreshHeartRate--onFinish" + new Gson().toJson((BleDeviceData) result));
                BleDeviceData deviceData = (BleDeviceData) result;
                List<HeartRateBean> heartRateBeans = deviceData.syncHeartRateDataResult;
                int size = heartRateBeans.size();
                if (size > 0) {
                    HeartRateBean bean = heartRateBeans.get(0);
                    tvHeartRateTime.setText(CommTool.getHearRateTime(bean.getDate() + "", bean.getMinute()));
                    heartRateNum.setOneCount(bean.getHeartRate(), "次/分");
                }
            }
        });
    }

    private void refreshSleep() {
        long startTimeInmills = Calendar.getInstance().getTimeInMillis();
        long endTimeInMills = Calendar.getInstance().getTimeInMillis();
        VeclinkSDK.getInstance().syncSleepData(startTimeInmills, endTimeInMills, new BleProgressCallback() {
            @Override
            public void onProgress(Object progress) {
                Logger.d("refreshSleep--onProgress" + progress);
            }

            @Override
            public void onStart(Object startObject) {
                Logger.d("refreshSleep--onStart");
            }

            @Override
            public void onFailed(Object error) {
                Logger.d("refreshSleep--onFailed");
            }

            @Override
            public void onFinish(Object result) {
                Logger.d("refreshSleep--onFinish" + new Gson().toJson((BleDeviceData) result));
                BleDeviceData deviceData = (BleDeviceData) result;
                List<DeviceSleepData> sleepData = deviceData.syncSleepDataResult;
                refreshSleepUi(sleepData);
            }
        });
    }

    private void refreshSleepUi(List<DeviceSleepData> sleepData) {
        int size = sleepData.size();
        int total = 0;
        int deepDuration = 0;
        int lightDuration = 0;
        int clearDuration = 0;
        for (int i = 0; i < size; i++) {
            DeviceSleepData data = sleepData.get(i);
            total = total + data.sleepDuration;
            int status = data.sleepState;
            int duration = data.sleepDuration;
            if (status <= 1) {
                deepDuration = deepDuration + duration;
            } else if (status > 1 && status <= 3) {
                lightDuration = lightDuration + duration;
            } else {
                clearDuration = clearDuration + duration;
            }
        }
        timeDeepSleep.setTime(deepDuration / 60, deepDuration % 60);
        timeLightSleep.setTime(lightDuration / 60, lightDuration % 60);
        timeClearSleep.setTime(clearDuration / 60, clearDuration % 60);

        tvTotalSleepTime.setTime(total / 60, total % 60);
    }

    private void refreshSport() {
        long startTimeInmills = Calendar.getInstance().getTimeInMillis();
        long endTimeInMills = Calendar.getInstance().getTimeInMillis();
        VeclinkSDK.getInstance().syncStepData(startTimeInmills, endTimeInMills, new BleProgressCallback() {
            @Override
            public void onProgress(Object progress) {
                Logger.d("refreshSport--onProgress" + progress);
            }

            @Override
            public void onStart(Object startObject) {
                Logger.d("refreshSport--onStart");
            }

            @Override
            public void onFailed(Object error) {
                Logger.d("refreshSport--onFailed");
            }

            @Override
            public void onFinish(Object result) {
                Logger.d("refreshSport--onFinish" + Thread.currentThread().getName());
                BleDeviceData deviceData = (BleDeviceData) result;
                List<DeviceSportData> sportData = deviceData.syncSportDataResult;
                int size = sportData.size();
                int total = 0;
                for (int i = 0; i < size; i++) {
                    DeviceSportData data = sportData.get(i);
                    total = total + data.sportStep;
                }
                xiaoMiStep.reSet(total);
            }
        });
    }

    @Override
    public void handleMessageInfo(Message message) {
        super.handleMessageInfo(message);
        switch (message.what) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_heart_rate, R.id.ll_blood_pressure, R.id.ll_blood_oxygen, R.id.ll_most_arrive,
            R.id.ll_sleep
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_heart_rate:
                break;
            case R.id.ll_blood_pressure:
                break;
            case R.id.ll_blood_oxygen:
                break;
            case R.id.ll_most_arrive:
                break;
            case R.id.ll_sleep:
                Intent sleepIntent = new Intent(getContext(),HeartRateActivity.class);
                startActivity(sleepIntent);
                break;
        }
    }
}
