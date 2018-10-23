package com.slogan.wristband.wristband.requestengine.factory;

public interface ErrCode extends ErrInfo
{
    public int CODE_ACCOUNT_FORBID=120;

    public int CODE_ACCOUNT_LOGOUT=728;

    public int CODE_ACCOUNT_CHECK_FAILED=1;
    public static int CODE_ACCOUNT_ERROR_CHECK=716;
    public static int CODE_PHONE_HAS_REGISTER=77;
    public static int CODE_PHONE_UNREGISTER=79;

    public static int CODE_ACCOUNT_NO_EXIST=108;

    public int CODE_RECHARGE_TIP=600;

    public int CODE_RECHARGE_TIP_FIRST=601;
}
