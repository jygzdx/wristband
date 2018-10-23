package com.slogan.wristband.wristband.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * 针对应用的配置信息存储,使用时注意在主线程中调用 <功能详细描述>
 * 
 * @author bin
 * @version [版本号, 2016年2月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AppConfigSharedPreferences implements UserAppConfig
{
    
    private static String name = "_app";
    
    public static boolean getAppInfoBoolean(Context context, String key, boolean value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getBoolean(key, value);
    }
    
    public static void setAppInfoBoolean(Context context, String key, boolean value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    
    public static void setAppInfoLong(Context context, String key, long value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static void setAppInfoFloat(Context context, String key, float value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    public static float getAppInfoFloat(Context context, String key, float value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getFloat(key, value);
    }
    public static long getAppInfoLong(Context context, String key, long value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getLong(key, value);
    }
    
    public static String getAppInfoString(Context context, String key, String value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getString(key, value);
    }
    
    public static int getAppInfoInt(Context context, String key, int value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getInt(key, value);
    }
    
    public static void setAppInfoString(Context context, String key, String value)
    {
        
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        
        editor.putString(key, value);
        
        editor.commit();
    }
    
    public static void setAppInfoInt(Context context, String key, int value)
    {
        
        SharedPreferences app_info = context.getSharedPreferences("_app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        
        editor.putInt(key, value);
        
        editor.commit();
    }

}
