package com.slogan.wristband.wristband.bean;

import com.veclink.bracelet.bean.DeviceSleepData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExSleepEntity implements Serializable {
    private String time;
    private List<DeviceSleepData> sleepDataList = new ArrayList<>();

    public void addEntity(DeviceSleepData data){
        sleepDataList.add(data);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DeviceSleepData> getSleepDataList() {
        return sleepDataList;
    }

    public void setSleepDataList(List<DeviceSleepData> sleepDataList) {
        this.sleepDataList = sleepDataList;
    }
}
