package com.slogan.wristband.wristband.utils;

/**
 * Created by czb on 2018/11/1.
 */
//
//import android.app.Application;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Build.VERSION;
//import android.os.Handler;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.veclink.bracelet.bean.BleAlarmRemindParam;
//import com.veclink.bracelet.bean.BleDeviceData;
//import com.veclink.bracelet.bean.BleEventRemindParam;
//import com.veclink.bracelet.bean.BleLongSittingRemindParam;
//import com.veclink.bracelet.bean.BleUserInfoBean;
//import com.veclink.bracelet.bletask.BleCallBack;
//import com.veclink.bracelet.bletask.BleProgressCallback;
//import com.veclink.bracelet.bletask.BleSettingRemindParamsTask;
//import com.veclink.hw.bleservice.VLBleServiceManager;
//import com.veclink.hw.bleservice.util.Debug;
//import com.veclink.hw.bleservice.util.Keeper;
//import com.veclink.hw.devicetype.DeviceProductFactory;
//import com.veclink.hw.devicetype.pojo.BaseDeviceProduct;
//import com.veclink.microcomm.healthy.bean.DeviceBean;
//import com.veclink.microcomm.healthy.bean.User;
//import com.veclink.sdk.DeviceStateObserver;
//import com.veclink.sdk.HeartRateObserver;
//import com.veclink.sdk.PowerObserver;
//import com.veclink.sdk.SittingRemindObserver;
//import com.veclink.sdk.StepObserver;
//import com.veclink.sdk.UnBindDeviceListener;
//import com.veclink.sdk.VeclinkSDK;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Iterator;
//import java.util.List;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class BlueToothUtil
//        implements DbUtil.Callback, DeviceStateObserver, StepObserver, HeartRateObserver, PowerObserver, SittingRemindObserver {
//    public static final int CONNECT = 1;
//    public static final int DISCONNECT = 0;
//    public static final int SERVICE_DISCOVERED = 2;
//    private static final String TAG = "BlueToothUtil";
//    private static volatile BlueToothUtil mInstance;
//    BleCallBack changeLightCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onChangeColor();
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleCallBack clearCacheCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onClearCacheFail();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onClearCacheSuccess();
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onClearCacheStart();
//            }
//        }
//    };
//    private Handler connectHandler = new Handler();
//    private DbUtil dbUtil;
//    private List<DeviceBean> devices;
//    BroadcastReceiver getdeviceMessageReceiver = new BroadcastReceiver() {
//        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {
//            if (paramAnonymousIntent.getAction().equals("action_get_goal")) {
//                try {
//                    paramAnonymousContext = new JSONObject(paramAnonymousIntent.getStringExtra("broadcast_extra_data"));
//                    int j = paramAnonymousContext.getInt("goalStep");
//                    paramAnonymousContext.getInt("goalHot");
//                    int i = j;
//                    if (j <= 0) {
//                        i = 7000;
//                    }
//                    if (Keeper.getUserHasBindBand(BlueToothUtil.this.mApplication) == true) {
//                        BlueToothUtil.this.syncParms(i);
//                    }
//                    BlueToothUtil.this.mApplication.unregisterReceiver(BlueToothUtil.this.getdeviceMessageReceiver);
//                    return;
//                } catch (JSONException paramAnonymousContext) {
//                    paramAnonymousContext.printStackTrace();
//                }
//            }
//        }
//    };
//    private Application mApplication;
//    private ConnectListener mListenner;
//    private int mStatus = 0;
//    BleCallBack queryPowerCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onQueryPower(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleCallBack queryVersionCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleCallBack readSerialNumberCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//            Lug.e("zheng", "��������������");
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onReadSerialNumberFail();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            Object localObject;
//            if (paramAnonymousObject == null) {
//                localObject = null;
//            } else {
//                localObject = paramAnonymousObject.toString();
//            }
//            Lug.e("zheng", (String) localObject);
//            if (paramAnonymousObject != null) {
//                paramAnonymousObject = (String) paramAnonymousObject;
//                localObject = BlueToothUtil.this.getDefaultDevice();
//                if (localObject != null) {
//                    ((DeviceBean) localObject).setSerialNumber((String) paramAnonymousObject);
//                    BlueToothUtil.this.dbUtil.asyncUpdate((DeviceBean) localObject);
//                }
//                if (BlueToothUtil.this.mListenner != null) {
//                    BlueToothUtil.this.mListenner.onReadSerialNumberFinish((String) paramAnonymousObject);
//                }
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleCallBack resetDeviceCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleProgressCallback sendMsgCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleCallBack settingRemindCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleCallBack switchHeartRateCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            Lug.e("zheng", "TASK_FINISH ");
//            if (paramAnonymousObject != null) {
//                Lug.e("zheng", paramAnonymousObject.toString());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleProgressCallback syncBloodOxygenDataCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodOxygenFailed();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (paramAnonymousObject != null) {
//                paramAnonymousObject = (BleDeviceData) paramAnonymousObject;
//                if (BlueToothUtil.this.mListenner != null) {
//                    BlueToothUtil.this.mListenner.onSyncBloodOxygenFinish((BleDeviceData) paramAnonymousObject);
//                }
//            } else if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodOxygenFailed();
//            }
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodOxygenProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodOxygenStart();
//            }
//        }
//    };
//    BleProgressCallback syncBloodPressDataCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodPressFailed();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (paramAnonymousObject != null) {
//                paramAnonymousObject = (BleDeviceData) paramAnonymousObject;
//                if (BlueToothUtil.this.mListenner != null) {
//                    BlueToothUtil.this.mListenner.onSyncBloodPressFinish((BleDeviceData) paramAnonymousObject);
//                }
//            } else if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodPressFailed();
//            }
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodPressProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncBloodPressStart();
//            }
//        }
//    };
//    BleProgressCallback syncHearateDataCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncHeartRateFailed();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (paramAnonymousObject != null) {
//                paramAnonymousObject = (BleDeviceData) paramAnonymousObject;
//                if (BlueToothUtil.this.mListenner != null) {
//                    BlueToothUtil.this.mListenner.onSyncHeartRateFinish((BleDeviceData) paramAnonymousObject);
//                }
//            } else if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncHeartRateFailed();
//            }
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncHeartRateProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncHeartRateStart();
//            }
//        }
//    };
//    BleCallBack syncParmasCallBack = new BleCallBack() {
//        public void onFailed(Object paramAnonymousObject) {
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//        }
//    };
//    BleProgressCallback syncSleepCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSleepFailed();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            paramAnonymousObject = (BleDeviceData) paramAnonymousObject;
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSleepFinish((BleDeviceData) paramAnonymousObject);
//            }
//            Debug.logBleByTag("sync result is ", new Gson().toJson(paramAnonymousObject));
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSleepProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSleepStart();
//            }
//        }
//    };
//    BleProgressCallback syncSportModeDataCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportModeFailed();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (paramAnonymousObject != null) {
//                paramAnonymousObject = (BleDeviceData) paramAnonymousObject;
//                if (BlueToothUtil.this.mListenner != null) {
//                    BlueToothUtil.this.mListenner.onSyncSportModeFinish((BleDeviceData) paramAnonymousObject);
//                }
//            } else if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportModeFailed();
//            }
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportModeProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportModeStart();
//            }
//        }
//    };
//    BleProgressCallback syncStepDataCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportFailed();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            paramAnonymousObject = (BleDeviceData) paramAnonymousObject;
//            String str = new Gson().toJson(paramAnonymousObject);
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportFinish((BleDeviceData) paramAnonymousObject);
//            }
//            Debug.logBleByTag("sync result is ", str);
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncSportStart();
//            }
//        }
//    };
//    BleProgressCallback syncThermometerDataCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncThermometerFailed();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (paramAnonymousObject != null) {
//                paramAnonymousObject = (BleDeviceData) paramAnonymousObject;
//                if (BlueToothUtil.this.mListenner != null) {
//                    BlueToothUtil.this.mListenner.onSyncThermometerFinish((BleDeviceData) paramAnonymousObject);
//                }
//            } else if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncThermometerFailed();
//            }
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncThermometerProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onSyncThermometerStart();
//            }
//        }
//    };
//    BleProgressCallback updateCallBack = new BleProgressCallback() {
//        public void onFailed(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onUpdateFirmwareFail();
//            }
//        }
//
//        public void onFinish(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onUpdateFirmwareFinish();
//            }
//        }
//
//        public void onProgress(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onUpdateFirmwareProgress(((Integer) paramAnonymousObject).intValue());
//            }
//        }
//
//        public void onStart(Object paramAnonymousObject) {
//            if (BlueToothUtil.this.mListenner != null) {
//                BlueToothUtil.this.mListenner.onUpdateFirmwareStart();
//            }
//        }
//    };
//    private VeclinkSDK veclinkSDK;
//
//    private BlueToothUtil(Application paramApplication) {
//        this.mApplication = paramApplication;
//        initReciver();
//        this.dbUtil = DbUtil.getInstance(this.mApplication);
//        this.dbUtil.addCallback(this);
//        Keeper.setUserHasBindBand(this.mApplication, true);
//        this.veclinkSDK = VeclinkSDK.getInstance();
//        setObserver();
//    }
//
//    public static BlueToothUtil getInstance(Application paramApplication) {
//        if (mInstance == null) {
//            try {
//                if (mInstance == null) {
//                    mInstance = new BlueToothUtil(paramApplication);
//                }
//            } finally {
//            }
//        }
//        return mInstance;
//    }
//
//    private void initReciver() {
//        IntentFilter localIntentFilter = new IntentFilter();
//        localIntentFilter.addAction("action_get_goal");
//        this.mApplication.registerReceiver(this.getdeviceMessageReceiver, localIntentFilter);
//    }
//
//    private void setObserver() {
//        this.veclinkSDK.registerDeviceStateObserver(this);
//        this.veclinkSDK.registerStepObserver(this);
//        this.veclinkSDK.registerHeartRateObserver(this);
//        this.veclinkSDK.registerPowerObserver(this);
//        this.veclinkSDK.registerSittingRemindObserver(this);
//    }
//
//    public void addDevice(DeviceBean paramDeviceBean) {
//        if (this.devices == null) {
//            this.devices = new ArrayList();
//        }
//        if (containDevice(paramDeviceBean)) {
//            return;
//        }
//        this.devices.add(0, paramDeviceBean);
//        this.dbUtil.addDevice(paramDeviceBean);
//    }
//
//    public void addDevices(DeviceBean paramDeviceBean) {
//        if (this.devices == null) {
//            this.devices = new ArrayList();
//        }
//        if (containDevice(paramDeviceBean.getAddress(), paramDeviceBean.getUid())) {
//            return;
//        }
//        Lug.i("bluetoothUtil", "��������");
//        this.devices.add(0, paramDeviceBean);
//        this.dbUtil.addDevice(paramDeviceBean);
//    }
//
//    public void blueToothClose() {
//        if (this.mListenner != null) {
//            this.mListenner.blueToothClose();
//        }
//    }
//
//    public void cancelAlarm(String paramString) {
//        for (; ; ) {
//            try {
//                i = DateTimeUtil.convertStrTimeToHour(paramString);
//            } catch (Exception paramString) {
//                int i;
//                int j;
//                continue;
//            }
//            try {
//                j = DateTimeUtil.convertStrTimeToMinute(paramString);
//            } catch (Exception paramString) {
//            }
//        }
//        i = 0;
//        j = 0;
//        paramString = new BleAlarmRemindParam(i, j, new int[]{0, 0, 0, 0, 0, 0, 0});
//        paramString.openflag = 0;
//        this.veclinkSDK.setAlarmRemind(this.settingRemindCallBack, paramString);
//    }
//
//    public void cancelEventAlarm(int paramInt1, int paramInt2, String paramString) {
//        if (Keeper.getDeviceType(this.mApplication).equals("W027G")) {
//            Calendar localCalendar = Calendar.getInstance();
//            paramString = new BleEventRemindParam(0, localCalendar.get(2) + 1, localCalendar.get(5), paramInt1, paramInt2, 1, paramString.getBytes());
//            new BleSettingRemindParamsTask(this.mApplication, this.settingRemindCallBack, paramString).work();
//            return;
//        }
//        paramString = new BleAlarmRemindParam(paramInt1, paramInt2, new int[]{0, 0, 0, 0, 0, 0, 0});
//        paramString.openflag = 0;
//        new BleSettingRemindParamsTask(this.mApplication, this.settingRemindCallBack, paramString).work();
//    }
//
//    public void cancelLastAlarm() {
//        try {
//            i = getDefaultDevice().getAlarmTime();
//            j = i / 60;
//            i %= 60;
//        } catch (Exception localException) {
//            int i;
//            int j;
//            BleAlarmRemindParam localBleAlarmRemindParam;
//            for (; ; ) {
//            }
//        }
//        j = 0;
//        i = 0;
//        localBleAlarmRemindParam = new BleAlarmRemindParam(j, i, new int[]{0, 0, 0, 0, 0, 0, 0});
//        localBleAlarmRemindParam.openflag = 0;
//        this.veclinkSDK.setAlarmRemind(this.settingRemindCallBack, localBleAlarmRemindParam);
//    }
//
//    public void changeLightColor(int paramInt) {
//        this.veclinkSDK.changeLightColor(this.changeLightCallBack, paramInt);
//    }
//
//    public void clearCache() {
//        this.veclinkSDK.clearPreUserData(this.clearCacheCallBack);
//    }
//
//    public BlueToothUtil connect(DeviceBean paramDeviceBean) {
//        if (!this.veclinkSDK.isBlueToothOpen()) {
//            return this;
//        }
//        if (paramDeviceBean.getType() != 1) {
//            return this;
//        }
//        if (!Keeper.getBindDeviceMacAddress(this.mApplication).equals(paramDeviceBean.getAddress())) {
//            DeviceUtil.saveDeviceToSp(this.mApplication, paramDeviceBean);
//        }
//        this.veclinkSDK.disConnectDevice();
//        this.veclinkSDK.connectOtherOneDevice(paramDeviceBean.getAddress(), paramDeviceBean.getName());
//        return this;
//    }
//
//    public void connectDefault() {
//        if ((this.devices != null) && (this.devices.size() > 0)) {
//            connect((DeviceBean) this.devices.get(0));
//        }
//    }
//
//    public void connected() {
//        Lug.e("zheng", "connected");
//        this.mStatus = 1;
//        DeviceBean localDeviceBean = getDefaultDevice();
//        if (localDeviceBean != null) {
//            localDeviceBean.setLastTime(System.currentTimeMillis());
//            this.dbUtil.asyncUpdate(localDeviceBean);
//        }
//        if (this.mListenner != null) {
//            this.mListenner.onConnected();
//        }
//        this.mStatus = 2;
//        this.connectHandler.postDelayed(new Runnable() {
//            public void run() {
//            }
//        }, 2000L);
//        if (this.mListenner != null) {
//            this.mListenner.onServiceDiscoverd();
//        }
//    }
//
//    public void connecting() {
//        Lug.e("zheng", "connecting");
//        if (this.mListenner != null) {
//            this.mListenner.connecting();
//        }
//    }
//
//    public boolean containDevice(DeviceBean paramDeviceBean) {
//        return containDevice(paramDeviceBean.getAddress());
//    }
//
//    public boolean containDevice(String paramString) {
//        if (paramString == null) {
//            return false;
//        }
//        if (!hasDevice()) {
//            return false;
//        }
//        Iterator localIterator = this.devices.iterator();
//        while (localIterator.hasNext()) {
//            if (paramString.equals(((DeviceBean) localIterator.next()).getAddress())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean containDevice(String paramString1, String paramString2) {
//        if (paramString1 == null) {
//            return false;
//        }
//        if (!hasDevice()) {
//            return false;
//        }
//        Iterator localIterator = this.devices.iterator();
//        while (localIterator.hasNext()) {
//            DeviceBean localDeviceBean = (DeviceBean) localIterator.next();
//            if ((paramString1.equals(localDeviceBean.getAddress())) && (paramString2.equals(localDeviceBean.getUid()))) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void deleteDevice(DeviceBean paramDeviceBean) {
//        if (this.devices != null) {
//            this.devices.remove(paramDeviceBean);
//        }
//        this.dbUtil.deleteDevice(paramDeviceBean);
//    }
//
//    public BlueToothUtil disConnect() {
//        if (hasDevice()) {
//            this.veclinkSDK.disConnectDevice();
//        }
//        return this;
//    }
//
//    public void disConnected() {
//        Lug.e("zheng", "disConnected");
//        Log.v(getClass().getSimpleName(), "disconnected");
//        this.mStatus = 0;
//        if (this.mListenner != null) {
//            this.mListenner.onDisconnected();
//        }
//    }
//
//    public void endHeartRateTest(int paramInt) {
//    }
//
//    public void findDevice() {
//        this.veclinkSDK.findDevice(this.queryVersionCallBack);
//    }
//
//    public void getAllDevice() {
//        User localUser = HwApiUtil.getUser();
//        if ((localUser != null) && (localUser.getUser_id() != null)) {
//            this.dbUtil.ayncGetAllDevice(localUser.getUser_id());
//        }
//    }
//
//    public BleUserInfoBean getBleUserInfoBean(int paramInt) {
//        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
//    }
//
//    public DeviceBean getDefaultDevice() {
//        if (!hasDevice()) {
//            return null;
//        }
//        String str = Keeper.getBindDeviceMacAddress(this.mApplication);
//        if ((str != null) && (!str.isEmpty())) {
//            Iterator localIterator = this.devices.iterator();
//            while (localIterator.hasNext()) {
//                DeviceBean localDeviceBean = (DeviceBean) localIterator.next();
//                if (str.equals(localDeviceBean.getAddress())) {
//                    return localDeviceBean;
//                }
//            }
//            return (DeviceBean) this.devices.get(0);
//        }
//        return (DeviceBean) this.devices.get(0);
//    }
//
//    public List<DeviceBean> getDevices() {
//        return this.devices;
//    }
//
//    public int getStatus() {
//        return this.mStatus;
//    }
//
//    public boolean hasDevice() {
//        return (this.devices != null) && (this.devices.size() > 0);
//    }
//
//    public boolean isConnected() {
//        return this.veclinkSDK.isConnected();
//    }
//
//    public boolean isConnectedDefault() {
//        String str = VeclinkSDK.getInstance().getConnectedMac();
//        StringBuilder localStringBuilder = new StringBuilder();
//        localStringBuilder.append("connectMacAddress = ");
//        localStringBuilder.append(str);
//        Log.i("BlueToothUtil", localStringBuilder.toString());
//        if (getDefaultDevice() != null) {
//            if (str.equals(getDefaultDevice().getAddress())) {
//                return true;
//            }
//        } else {
//            Log.i("BlueToothUtil", "getDefaultDevice = NULL");
//        }
//        return false;
//    }
//
//    public boolean isConnecting() {
//        return this.veclinkSDK.isConnecting();
//    }
//
//    public void onDevicesReturn(List<DeviceBean> paramList) {
//        this.devices = paramList;
//        Log.i("BlueToothUtil", "onDevicesReturn");
//        if (this.mListenner != null) {
//            this.mListenner.onDeviceReturn(paramList);
//        }
//    }
//
//    public void onReceiveSittingRemind() {
//    }
//
//    public void powerChange(int paramInt) {
//        if (this.mListenner != null) {
//            this.mListenner.onPowerChange(paramInt);
//        }
//    }
//
//    public void queryPower() {
//        this.veclinkSDK.queryDevicePower(this.queryPowerCallBack);
//    }
//
//    public void readSerialNumber() {
//        if (Build.VERSION.SDK_INT < 18) {
//            return;
//        }
//        this.veclinkSDK.readDeviceSerialNumber(this.readSerialNumberCallBack);
//    }
//
//    public void receiveHeartRate(int paramInt) {
//    }
//
//    public void resetDevice() {
//        this.veclinkSDK.factoryReset(this.resetDeviceCallBack);
//    }
//
//    public void sendCallMsgRemind(String paramString1, String paramString2) {
//        this.veclinkSDK.sendCallMsgRemind(this.sendMsgCallBack, paramString2, paramString1);
//    }
//
//    public void sendMessage(byte paramByte, String paramString) {
//        this.veclinkSDK.sendAppNotificationRemind(this.sendMsgCallBack, paramByte, paramString);
//    }
//
//    public void sendSmsMsgRemind(String paramString1, String paramString2) {
//        this.veclinkSDK.sendSmsRemind(this.sendMsgCallBack, paramString2, paramString1);
//    }
//
//    public void setEventAlarm(int paramInt1, int paramInt2, String paramString) {
//        this.veclinkSDK.setEventRemind(this.settingRemindCallBack, paramInt1, paramInt2, paramString);
//    }
//
//    public void setListenner(ConnectListener paramConnectListener) {
//        setObserver();
//        if (this.mListenner == paramConnectListener) {
//            return;
//        }
//        this.mListenner = paramConnectListener;
//    }
//
//    public void setRemind(BleAlarmRemindParam paramBleAlarmRemindParam) {
//        if (DeviceProductFactory.createDeviceProduct(Keeper.getDeviceType(this.mApplication)).updateFirewareWay == 2) {
//            cancelLastAlarm();
//        }
//        this.veclinkSDK.setAlarmRemind(this.settingRemindCallBack, paramBleAlarmRemindParam);
//    }
//
//    public void setSedantaryAlarm(BleLongSittingRemindParam paramBleLongSittingRemindParam) {
//        this.veclinkSDK.setLongSittingRemind(this.settingRemindCallBack, paramBleLongSittingRemindParam);
//    }
//
//    public void stepCountChange(int paramInt) {
//        if (this.mListenner != null) {
//            this.mListenner.onReceivedSportStep(paramInt);
//        }
//    }
//
//    public boolean swapPosition(DeviceBean paramDeviceBean) {
//        if (!hasDevice()) {
//            return false;
//        }
//        DeviceBean localDeviceBean = (DeviceBean) this.devices.get(0);
//        int i = this.devices.indexOf(paramDeviceBean);
//        if (i == -1) {
//            return false;
//        }
//        this.devices.set(0, paramDeviceBean);
//        this.devices.set(i, localDeviceBean);
//        return true;
//    }
//
//    public void switchHeartRate(boolean paramBoolean) {
//        this.veclinkSDK.openOrCloseDateHeartRateTest(this.switchHeartRateCallBack, paramBoolean);
//    }
//
//    public void syncBloodOxygenData() {
//        this.veclinkSDK.syncBloodOxygenData(this.syncBloodOxygenDataCallBack);
//    }
//
//    public void syncBloodOxygenData(long paramLong) {
//        this.veclinkSDK.syncBloodOxygenData(paramLong, System.currentTimeMillis(), this.syncBloodOxygenDataCallBack);
//    }
//
//    public void syncBloodOxygenNewData(long paramLong) {
//        this.veclinkSDK.syncBloodOxygenNewData(paramLong, System.currentTimeMillis(), this.syncBloodOxygenDataCallBack);
//    }
//
//    public void syncHeartRateData() {
//        this.veclinkSDK.syncAllHeartRateData(this.syncHearateDataCallBack);
//    }
//
//    public void syncHeartRateData(long paramLong) {
//        this.veclinkSDK.syncHeartRateData(paramLong, System.currentTimeMillis(), this.syncHearateDataCallBack);
//    }
//
//    public void syncHeartRateNewData(long paramLong) {
//        this.veclinkSDK.syncHeartRateNewData(paramLong, System.currentTimeMillis(), this.syncHearateDataCallBack);
//    }
//
//    public void syncParms(int paramInt) {
//        Lug.e("zheng", "Bluetooth syncParms");
//        this.veclinkSDK.syncParams(getBleUserInfoBean(paramInt), this.syncParmasCallBack);
//    }
//
//    public void syncSleepData() {
//        this.veclinkSDK.syncAllSleepData(this.syncSleepCallBack);
//    }
//
//    public void syncSleepData(long paramLong) {
//        this.veclinkSDK.syncSleepData(paramLong, System.currentTimeMillis(), this.syncSleepCallBack);
//    }
//
//    public void syncSleepData(long paramLong1, long paramLong2) {
//        this.veclinkSDK.syncSleepData(paramLong1, paramLong2, this.syncSleepCallBack);
//    }
//
//    public void syncSportModeData() {
//        this.veclinkSDK.syncSportModeData(this.syncSportModeDataCallBack);
//    }
//
//    public void syncSportModeData(long paramLong) {
//        this.veclinkSDK.syncSportModeData(paramLong, System.currentTimeMillis(), this.syncSportModeDataCallBack);
//    }
//
//    public void syncStepData() {
//        this.veclinkSDK.syncAllStepData(this.syncStepDataCallBack);
//    }
//
//    public void syncStepData(long paramLong) {
//        this.veclinkSDK.syncStepData(paramLong, System.currentTimeMillis(), this.syncStepDataCallBack);
//    }
//
//    public void syncStepData(long paramLong1, long paramLong2) {
//        this.veclinkSDK.syncStepData(paramLong1, paramLong2, this.syncStepDataCallBack);
//    }
//
//    public void syncThermometerData() {
//        this.veclinkSDK.syncThermometerData(this.syncThermometerDataCallBack);
//    }
//
//    public void syncThermometerData(long paramLong, boolean paramBoolean) {
//        this.veclinkSDK.syncThermometerData(paramLong, System.currentTimeMillis(), paramBoolean, this.syncThermometerDataCallBack);
//    }
//
//    public void synccBloodPressData() {
//        this.veclinkSDK.syncBloodPressData(this.syncBloodPressDataCallBack);
//    }
//
//    public void synccBloodPressData(long paramLong) {
//        this.veclinkSDK.syncBloodPressData(paramLong, System.currentTimeMillis(), this.syncBloodPressDataCallBack);
//    }
//
//    public void synccBloodPressNewData(long paramLong) {
//        this.veclinkSDK.syncBloodPressNewData(paramLong, System.currentTimeMillis(), this.syncBloodPressDataCallBack);
//    }
//
//    public void unBindDevice() {
//        if (!hasDevice()) {
//            return;
//        }
//        Lug.e("zheng", "unBindDevice");
//        UnBindDeviceListener local1 = new UnBindDeviceListener() {
//            public void onComplete() {
//                Lug.e("zheng", "unBindDevice: onComplete");
//                DeviceBean localDeviceBean = BlueToothUtil.this.getDefaultDevice();
//                Keeper.clearBindDeviceMessage(BlueToothUtil.this.mApplication);
//                Keeper.setUserHasBindBand(BlueToothUtil.this.mApplication, true);
//                BlueToothUtil.this.disConnect();
//                BlueToothUtil.this.deleteDevice(localDeviceBean);
//                if (BlueToothUtil.this.mListenner != null) {
//                    BlueToothUtil.this.mListenner.onUnbindSuccess();
//                }
//            }
//
//            public void onFail(String paramAnonymousString) {
//                StringBuilder localStringBuilder = new StringBuilder();
//                localStringBuilder.append("unbind fail: ");
//                localStringBuilder.append(paramAnonymousString);
//                if (localStringBuilder.toString() == null) {
//                    paramAnonymousString = "null";
//                }
//                Lug.e("zheng", paramAnonymousString);
//            }
//        };
//        if (isConnected()) {
//            Log.i("BlueToothUtil", "unbind isConnected ");
//            this.veclinkSDK.unBindDevice(local1);
//            return;
//        }
//        Log.i("BlueToothUtil", "unbind isConnected false");
//        VeclinkSDK.getInstance().setBindDeviceInfo("", "");
//        VLBleServiceManager.getInstance();
//        VLBleServiceManager.setAutoReConnect(false);
//        local1.onComplete();
//    }
//
//    public void updateFirmWare(String paramString) {
//        this.veclinkSDK.otaFirmwareRom(paramString, this.updateCallBack);
//    }
//
//    public static abstract interface ConnectListener {
//        public abstract void blueToothClose();
//
//        public abstract void connecting();
//
//        public abstract void onChangeColor();
//
//        public abstract void onClearCacheFail();
//
//        public abstract void onClearCacheStart();
//
//        public abstract void onClearCacheSuccess();
//
//        public abstract void onConnected();
//
//        public abstract void onDeviceReturn(List<DeviceBean> paramList);
//
//        public abstract void onDisconnected();
//
//        public abstract void onPowerChange(int paramInt);
//
//        public abstract void onQueryPower(int paramInt);
//
//        public abstract void onReadSerialNumberFail();
//
//        public abstract void onReadSerialNumberFinish(String paramString);
//
//        public abstract void onReceivedSportStep(int paramInt);
//
//        public abstract void onServiceDiscoverd();
//
//        public abstract void onSyncBloodOxygenFailed();
//
//        public abstract void onSyncBloodOxygenFinish(BleDeviceData paramBleDeviceData);
//
//        public abstract void onSyncBloodOxygenProgress(int paramInt);
//
//        public abstract void onSyncBloodOxygenStart();
//
//        public abstract void onSyncBloodPressFailed();
//
//        public abstract void onSyncBloodPressFinish(BleDeviceData paramBleDeviceData);
//
//        public abstract void onSyncBloodPressProgress(int paramInt);
//
//        public abstract void onSyncBloodPressStart();
//
//        public abstract void onSyncHeartRateFailed();
//
//        public abstract void onSyncHeartRateFinish(BleDeviceData paramBleDeviceData);
//
//        public abstract void onSyncHeartRateProgress(int paramInt);
//
//        public abstract void onSyncHeartRateStart();
//
//        public abstract void onSyncSleepFailed();
//
//        public abstract void onSyncSleepFinish(BleDeviceData paramBleDeviceData);
//
//        public abstract void onSyncSleepProgress(int paramInt);
//
//        public abstract void onSyncSleepStart();
//
//        public abstract void onSyncSportFailed();
//
//        public abstract void onSyncSportFinish(BleDeviceData paramBleDeviceData);
//
//        public abstract void onSyncSportModeFailed();
//
//        public abstract void onSyncSportModeFinish(BleDeviceData paramBleDeviceData);
//
//        public abstract void onSyncSportModeProgress(int paramInt);
//
//        public abstract void onSyncSportModeStart();
//
//        public abstract void onSyncSportProgress(int paramInt);
//
//        public abstract void onSyncSportStart();
//
//        public abstract void onSyncThermometerFailed();
//
//        public abstract void onSyncThermometerFinish(BleDeviceData paramBleDeviceData);
//
//        public abstract void onSyncThermometerProgress(int paramInt);
//
//        public abstract void onSyncThermometerStart();
//
//        public abstract void onUnbindSuccess();
//
//        public abstract void onUpdateFirmwareFail();
//
//        public abstract void onUpdateFirmwareFinish();
//
//        public abstract void onUpdateFirmwareProgress(int paramInt);
//
//        public abstract void onUpdateFirmwareStart();
//    }
//}
