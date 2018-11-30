package com.slogan.wristband.wristband.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.bean.ExSleepEntity;
import com.slogan.wristband.wristband.utils.MPChartUtils;
import com.slogan.wristband.wristband.widght.SleepQualityView;
import com.slogan.wristband.wristband.widght.TimeView;
import com.veclink.bracelet.bean.BleDeviceData;
import com.veclink.bracelet.bean.DeviceSleepData;
import com.veclink.bracelet.bletask.BleProgressCallback;
import com.veclink.sdk.VeclinkSDK;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 睡眠质量
 */
public class SleepQualityActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_title_parent)
    LinearLayout llTitleParent;
    @BindView(R.id.tv_compare_text)
    TextView tvCompareText;
    @BindView(R.id.tv_sleep_time)
    TextView tvSleepTime;
    @BindView(R.id.time_sleep)
    TimeView timeSleep;
    @BindView(R.id.tv_deep_sleep)
    TextView tvDeepSleep;
    @BindView(R.id.time_deep_sleep)
    TimeView timeDeepSleep;
    @BindView(R.id.tv_light_sleep)
    TextView tvLightSleep;
    @BindView(R.id.time_light_sleep)
    TimeView timeLightSleep;
    @BindView(R.id.tv_yes_sleep)
    TextView tvYesSleep;
    @BindView(R.id.time_yes_sleep)
    TimeView timeYesSleep;
    @BindView(R.id.tv_day_clear_sleep)
    TextView tvDayClearSleep;
    @BindView(R.id.time_day_clear_sleep)
    TimeView timeDayClearSleep;
    @BindView(R.id.tv_clear_sleep)
    TextView tvClearSleep;
    @BindView(R.id.time_clear_sleep)
    TimeView timeClearSleep;
    @BindView(R.id.tv_detailed_info)
    TextView tvDetailedInfo;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_sleep_time_top)
    TextView tvSleepTimeTop;
    @BindView(R.id.tv_wake_time_top)
    TextView tvWakeTimeTop;
    @BindView(R.id.sqv_sleep)
    SleepQualityView sqvSleep;
    @BindView(R.id.rv_detailed_time)
    RecyclerView rvDetailedTime;
    @BindView(R.id.ll_detailed)
    LinearLayout llDetailed;
    @BindView(R.id.sleep_quality_barchart)
    BarChart sleepQualityBarchart;
    @BindView(R.id.rl_undetailed)
    RelativeLayout rlUndetailed;
    /**
     * 0->详细信息,1->日视图,2->周视图,3->月视图
     */
    private int state;
    private List<DeviceSleepData> sleeps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_quality);
        ButterKnife.bind(this);
        initWidget();
        VeclinkSDK.getInstance().syncAllSleepData(new BleProgressCallback() {
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
//                BleDeviceData sleepData = (BleDeviceData) result;
//
//                sleeps = sleepData.syncSleepDataResult;


            }
        });

        //假数据
        int random = (int) (Math.random()*30);
        int random1 = (int) (Math.random()*10);
        for (int i = 0; i < random; i++) {
            DeviceSleepData deviceSleepData = new DeviceSleepData();
            deviceSleepData.sleepState = random%5;
            deviceSleepData.sleepDuration = random*2;
            deviceSleepData.startTime = random*10;
            deviceSleepData.deviceId = random+"";
            deviceSleepData.dateTime = 20181101+i+"";
            sleeps.add(deviceSleepData);
        }
        refreshSleepUi(sleeps);

    }

    private List<ExSleepEntity> exSleepList = new ArrayList<>();

    private void refreshSleepUi(List<DeviceSleepData> sleeps) {
        for (int i = 0; i < sleeps.size(); i++) {
            DeviceSleepData sleep = sleeps.get(i);
            if (isAdded(sleep)) {
                int position = getAddedPosition(sleep);
                if (position == -1) {
                    continue;
                }
                exSleepList.get(position).addEntity(sleep);
            } else {
                ExSleepEntity entity = new ExSleepEntity();
                entity.setTime(sleep.dateTime);
                entity.addEntity(sleep);
                exSleepList.add(entity);
            }
        }
        Logger.d("refreshSleepUi = " + new Gson().toJson(exSleepList));
        sqvSleep.refreshView(sleeps);
        refreshChartData();
    }

    private int getAddedPosition(DeviceSleepData sleep) {
        for (int i = 0; i < exSleepList.size(); i++) {
            ExSleepEntity entity = exSleepList.get(i);
            if (entity.getTime().equals(sleep)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isAdded(DeviceSleepData sleep) {
        if (sleep == null) {
            return false;
        }
        for (int i = 0; i < exSleepList.size(); i++) {
            ExSleepEntity entity = exSleepList.get(i);
            if (entity.getTime().equals(sleep)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        llDetailed.setVisibility(View.GONE);
        rlUndetailed.setVisibility(View.VISIBLE);

    }

    private void refreshChartData(){
        // 1.配置基础图表配置
//        List<String> xLabels = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            xLabels.add("l"+i);
//        }
        MPChartUtils.configBarChart(sleepQualityBarchart);
// 2,获取数据Data，这里有2条曲线
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < exSleepList.size(); i++) {
            ExSleepEntity exSleepEntity = exSleepList.get(i);
            List<DeviceSleepData> sleepData = exSleepEntity.getSleepDataList();
            int size = sleepData.size();
            int deepDuration = 0;
            int lightDuration = 0;
            int clearDuration = 0;
            for (int j = 0; j < size; j++) {
                DeviceSleepData data = sleepData.get(j);
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
            entries.add(new BarEntry(
                    i,
                    new float[]{deepDuration,lightDuration,clearDuration},
                    exSleepList.get(i)
                    ));
        }
        MPChartUtils.getBarDataSet(entries,"label",Color.WHITE,Color.GREEN);
        //  3,初始化数据并绘制
        MPChartUtils.initBarChart(sleepQualityBarchart,entries,"title", new int[]{rgb("#f5bc47"), rgb("#5C51BD")});
    }

    public static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.rgb(r, g, b);
    }

    @OnClick({R.id.tv_detailed_info, R.id.tv_day, R.id.tv_week, R.id.tv_month})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_detailed_info:
                state = 0;
                initUI();
                tvDetailedInfo.setSelected(true);
                tvDay.setSelected(false);
                tvWeek.setSelected(false);
                tvMonth.setSelected(false);
                break;
            case R.id.tv_day:
                state = 1;
                initUI();
                tvDetailedInfo.setSelected(false);
                tvDay.setSelected(true);
                tvWeek.setSelected(false);
                tvMonth.setSelected(false);
                break;
            case R.id.tv_week:
                state = 2;
                initUI();
                tvDetailedInfo.setSelected(false);
                tvDay.setSelected(false);
                tvWeek.setSelected(true);
                tvMonth.setSelected(false);
                break;
            case R.id.tv_month:
                state = 3;
                initUI();
                tvDetailedInfo.setSelected(false);
                tvDay.setSelected(false);
                tvWeek.setSelected(false);
                tvMonth.setSelected(true);
                break;
        }
    }

    private void initUI() {
        if (state <= 1) {
            tvSleepTime.setText(R.string.sleep_time);
            tvDeepSleep.setText(R.string.deep_sleep);
            tvLightSleep.setText(R.string.light_sleep);
            tvYesSleep.setText(R.string.yes_sleep);
            tvDayClearSleep.setText(R.string.day_clear_sleep);
            tvClearSleep.setText(R.string.clear_sleep);
        } else {
            tvSleepTime.setText(R.string.average_sleep_time);
            tvDeepSleep.setText(R.string.average_deep_sleep);
            tvLightSleep.setText(R.string.average_light_sleep);
            tvYesSleep.setText(R.string.average_yes_sleep);
            tvDayClearSleep.setText(R.string.average_day_clear_sleep);
            tvClearSleep.setText(R.string.average_clear_sleep);
        }

    }
}
