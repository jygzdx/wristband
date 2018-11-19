package com.slogan.wristband.wristband.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.adapter.BleDeviceListAdapter;
import com.slogan.wristband.wristband.app.WristbandApplication;
import com.slogan.wristband.wristband.utils.LogDataUtils;
import com.slogan.wristband.wristband.utils.ToastUtils;
import com.veclink.bracelet.bean.BleUserInfoBean;
import com.veclink.bracelet.bean.BluetoothDeviceBean;
import com.veclink.bracelet.bletask.BleAppConfirmBindSuccess;
import com.veclink.bracelet.bletask.BleCallBack;
import com.veclink.bracelet.bletask.BleRequestBindDevice;
import com.veclink.bracelet.bletask.BleScanDeviceTask;
import com.veclink.bracelet.bletask.BleSyncParamsTask;
import com.veclink.hw.bleservice.VLBleService;
import com.veclink.hw.bleservice.VLBleServiceManager;
import com.veclink.hw.bleservice.profile.BraceletGattAttributes;
import com.veclink.hw.bleservice.util.Keeper;
import com.veclink.hw.devicetype.DeviceProductFactory;
import com.veclink.hw.devicetype.pojo.BaseDeviceProduct;
import com.veclink.sdk.BindDeviceListener;
import com.veclink.sdk.ScanDeviceListener;
import com.veclink.sdk.UnBindDeviceListener;
import com.veclink.sdk.VeclinkSDK;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindDeviceActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.listView)
    ListView listView;
    private BleDeviceListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_device);
        ButterKnife.bind(this);
//        initReciver();

//        VeclinkSDK.getInstance().unBindDevice(new UnBindDeviceListener() {
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void onFail(String s) {
//
//            }
//        });
        VeclinkSDK.getInstance().startScanDevice(scanDeviceListener);
        tvTitle.setText("绑定设备");
        ivRight.setVisibility(View.GONE);
        adapter = new BleDeviceListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        adapter.clearAllDevieceItem();
    }

    @OnClick(R.id.iv_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private ScanDeviceListener scanDeviceListener = new ScanDeviceListener() {
        @Override
        public void scanFinish() {
            Logger.d("scanFinish");
        }

        @Override
        public void startScan() {
            Logger.d("startScan");
        }

        @Override
        public void scanFindOneDevice(BluetoothDeviceBean bandDeviceBean) {
            adapter.addDeviceItem(bandDeviceBean);
        }
    };

    private BindDeviceListener bindDeviceListener = new BindDeviceListener() {
        @Override
        public void onClickToBind() {
            ToastUtils.showToast("请敲击手环");
            Logger.d("onClickToBind");
        }

        @Override
        public void onComplete() {
            Logger.d("onComplete");
           initDevice();
        }

        @Override
        public void onFail(String s) {
            Logger.d("onFail"+s);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        BluetoothDeviceBean device = (BluetoothDeviceBean) adapter.listItems
                .get(position);
        String addr = device.getAddress();
        String name = device.getName();
        VeclinkSDK.getInstance().bindDevice(name,addr,bindDeviceListener);
    }


    //    private final int DEVICE_CONNECTED = 0x21;
//    private final int DEVICE_DISCONNECTED = 0x22;
//    private final int DEVICE_SYNCPARAMSDONE = 0x23;
//    private final int USER_HAS_CLICK_DEVICE = 0x25;//用户已经敲击设备，设备已回应
//    private BaseDeviceProduct deviceProduct;
//    @SuppressLint("HandlerLeak")
//    Handler syncParamHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case BleCallBack.TASK_START:
//
//                    break;
//                case BleCallBack.TASK_PROGRESS:
//                    break;
//                case BleCallBack.TASK_FINISH:
//                    connectHandler.sendEmptyMessage(DEVICE_SYNCPARAMSDONE);
//                    break;
//                case BleCallBack.TASK_FAILED:
//                    BleSyncParamsTask task = getBleSyncParamsTask();
//                    task.work();
//                    break;
//            }
//        }
//
//    };
//    @SuppressLint("HandlerLeak")
//    Handler requestBindDeviceHandler = new Handler(){
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case BleCallBack.TASK_START:
//
//                    break;
//
//                case BleCallBack.TASK_PROGRESS:
//
//                    break;
//                case BleCallBack.TASK_FINISH:
//                    //这里提示用户敲击手环进行绑定
//                    ToastUtils.showToast("当手环上的屏亮起,按击按键");
//                    break;
//                case BleCallBack.TASK_FAILED:
//
//                    break;
//            }
//        }
//
//    };
//@SuppressLint("HandlerLeak")
//    Handler connectHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case DEVICE_CONNECTED:
//                    LogDataUtils.logData(TAG,"DEVICE_CONNECTED");
//                    break;
//
//                case DEVICE_DISCONNECTED:
//                    LogDataUtils.logData(TAG,"DEVICE_DISCONNECTED");
//                    VLBleServiceManager.getInstance().unBindService(WristbandApplication.getInstance());
//                    break;
//                case DEVICE_SYNCPARAMSDONE:
//                    LogDataUtils.logData(TAG,"DEVICE_SYNCPARAMSDONE");
//
//                    deviceProduct = DeviceProductFactory.createDeviceProduct(Keeper.getDeviceType(getApplicationContext()));
//                    if(deviceProduct.bindDeviceWay== BaseDeviceProduct.CLICK_BIND_DEVICE_WAY){
//                        BleRequestBindDevice bleRequestBindDevice =
//                                new BleRequestBindDevice(getApplicationContext(), requestBindDeviceCallBack);
//                        bleRequestBindDevice.work();
//                    }else{
//                        Keeper.setUserHasBindBand(getApplicationContext(), true);
//                        finish();
//                    }
//                    break;
//
//                case USER_HAS_CLICK_DEVICE:
//                    LogDataUtils.logData(TAG,"USER_HAS_CLICK_DEVICE");
//                    BleAppConfirmBindSuccess appConfirmBindSuccess = new BleAppConfirmBindSuccess(getApplicationContext(), new BleCallBack(new Handler()));
//                    appConfirmBindSuccess.work();
//                    Keeper.setUserHasBindBand(WristbandApplication.getInstance(), true);
//                    finish();
//                    break;
//
//            }
//        }
//    };
//
    private static final String TAG = "BindDeviceActivity";
//
//    BleCallBack requestBindDeviceCallBack = new BleCallBack(requestBindDeviceHandler);
//
//    BleCallBack syncParmasCallBack = new BleCallBack(syncParamHandler);
//
//    IntentFilter intentFilter = new IntentFilter();
//
//    BroadcastReceiver connectDeviceInfoReceiver = new BroadcastReceiver(){
//
//        @Override
//        public void onReceive(Context arg0, Intent intent) {
//            String action = intent.getAction();
//            if(action.equals(VLBleService.ACTION_GATT_SERVICES_DISCOVERED)){
//                BleSyncParamsTask task = getBleSyncParamsTask();
//                task.work();
//            }else if(action.equals(VLBleService.ACTION_USER_HAD_CLICK_DEVICE)){
//                connectHandler.sendEmptyMessage(USER_HAS_CLICK_DEVICE);
//            }else if(action.equals(VLBleService.ACTION_GATT_DISCONNECTED)){
//                connectHandler.sendEmptyMessage(DEVICE_DISCONNECTED);
//            }else if(action.equals(VLBleService.ACTION_GATT_CONNECTED)){
//                connectHandler.sendEmptyMessage(DEVICE_CONNECTED);
//            }
//
//        }
//
//    };

//     ACTION_GATT_CONNECTED //收到这个aciton的广播说明设备已连接
//     ACTION_GATT_DISCONNECTED //收到这个aciton的广播说明设备已断开
//
//     ACTION_GATT_SERVICES_DISCOVERED //收到这个aciton的广播说明设备已获取到通讯服务可以执行同步数据的操作。具体操作请参考demo
//
//     ACTION_USER_HAD_CLICK_DEVICE  //收到此广播说明在绑定过程中用户敲击了设备
//
//
//     ACTION_POWER_CHANGE_DATA //收到此广播说明设备电量发生变化
//
//     ACTION_SHORT_SPORT_DATA  //收到此广播说明设备步数发生变化

    public void initDevice(){
        int  targetStep  =  100;
        int  wearLocation  =  0;
        int  sport_mode  =  1;
        int  sex  =  0;
        int  year=  1990;
        int  nowYear  =  Calendar.getInstance().get(Calendar.YEAR);
        int  age  =  nowYear-year;
        float  height  =  169;
        float  weight  =  58;
        int  distanceUnit  =  0;
        boolean  keptOnOffblean  =  false;
        int  keptOnOff  =  keptOnOffblean==true?1:0;
        int deviceLanguage = 0;
        int callRemind = 0;
        int messageRemind = 0;
        int contactRemind = 0;
        BleUserInfoBean  bean  =  new  BleUserInfoBean(targetStep,  wearLocation, sport_mode,  sex,  age,  weight,  height,  distanceUnit,
                keptOnOff,deviceLanguage,callRemind,messageRemind,contactRemind);
        VeclinkSDK.getInstance().syncParams(bean,  new  BleCallBack()  {
            @Override
            public  void  onStart(Object  startObject)  {
                Logger.d("initDevice--onStart");
            }
            @Override
            public  void  onFailed(Object  error)  {
                Logger.d("initDevice--onFailed");
            }

            @Override
            public void onFinish(Object o) {
//                Gson  gson  =  new  Gson();
//                DeviceInfo  deviceInfo  =  (DeviceInfo)  result;
                ToastUtils.showToast("同步参数成功");
                finish();
            }
        });
    }
//    public BleSyncParamsTask getBleSyncParamsTask() {
//        int targetStep = 100;
//        int wearLocation = 0;
//        int sport_mode = 1;
//        int sex = 0;
//        int year = 1990;
//        int nowYear = Calendar.getInstance().get(Calendar.YEAR);
//        int age = nowYear - year;
//        float height = 169;
//        float weight = 58;
//        int distanceUnit = 0;
//        boolean keptOnOffblean = false;
//        int keptOnOff = keptOnOffblean == true ? 1 : 0;
//        BleUserInfoBean bean = new BleUserInfoBean(targetStep, wearLocation, sport_mode, sex, age, weight, height, distanceUnit, keptOnOff);
//        BleSyncParamsTask bleSyncParamsTask = new BleSyncParamsTask(this, syncParmasCallBack, bean);
//        return bleSyncParamsTask;
//    }
}
