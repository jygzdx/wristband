package com.slogan.wristband.wristband.requestengine.factory;

/**
 * 通过回调接口
 * 
 * @author  bin
 * @version  [版本号, 2017年2月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface HttpMsg
{
    void handleResult(Object result1, Object result2, Object result3, Object result4, int type, int page, int count,
                      int arg1);
    
    void handleErrorInfo(String error_msg, Object arg1, int error_code, int type, int arg2, int arg3);
}
