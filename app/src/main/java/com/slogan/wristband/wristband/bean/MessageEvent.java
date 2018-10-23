package com.slogan.wristband.wristband.bean;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class MessageEvent {
    private int messageEventCode;
    private Object messageEventObject;
    private Object messageEventData;
    private int index;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getMessageEventCode() {
        return messageEventCode;
    }

    public void setMessageEventCode(int messageEventCode) {
        this.messageEventCode = messageEventCode;
    }

    public Object getMessageEventData() {
        return messageEventData;
    }

    public void setMessageEventData(Object messageEventData) {
        this.messageEventData = messageEventData;
    }

    public Object getMessageEventObject() {
        return messageEventObject;
    }

    public void setMessageEventObject(Object messageEventObject) {
        this.messageEventObject = messageEventObject;
    }
}
