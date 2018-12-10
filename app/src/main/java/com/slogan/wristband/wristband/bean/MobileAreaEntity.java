package com.slogan.wristband.wristband.bean;

import java.io.Serializable;

public class MobileAreaEntity implements Serializable {
    private String area;
    private String code;
    private boolean isSeleced;

    public MobileAreaEntity(String area, String code, boolean isSeleced) {
        this.area = area;
        this.code = code;
        this.isSeleced = isSeleced;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSeleced() {
        return isSeleced;
    }

    public void setSeleced(boolean seleced) {
        isSeleced = seleced;
    }
}
