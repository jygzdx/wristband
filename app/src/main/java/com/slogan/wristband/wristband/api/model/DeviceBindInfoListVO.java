package com.slogan.wristband.wristband.api.model;

/**
 * 用户绑定设备信息
 */
public class DeviceBindInfoListVO extends DeviceBindInfo{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 会员用户名称
     */
    private String reakName;

    /**
     * 会员用户手机号
     */
    private String mobile;

    public String getReakName() {
        return reakName;
    }


    public void setReakName(String reakName) {
        this.reakName = reakName;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}