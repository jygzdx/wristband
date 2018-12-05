package com.slogan.wristband.wristband.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息
 */
public class FrontUserInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 真实名称
     */
    private String reakName;

    private String saleName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 性别(0未知1男2女)
     */
    private Integer gender;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 邀请人编号
     */
    private String inviteUserNo;

    /**
     * 我的邀请码
     */
    private String inviteCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态(1正常2未激活3禁止4冻结)
     */
    private Integer userStatus;

    private String openid;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getInviteUserNo() {
        return inviteUserNo;
    }

    public void setInviteUserNo(String inviteUserNo) {
        this.inviteUserNo = inviteUserNo;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getReakName() {
        return reakName;
    }

    public void setReakName(String reakName) {
        this.reakName = reakName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
