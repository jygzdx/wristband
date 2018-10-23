package com.slogan.wristband.wristband.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * 储存用户访问过的房间锁 <功能详细描述>
 * 
 * @author bin
 * @version [版本号, 2016年2月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RoomLockSharedPreferences implements UserAppConfig
{
    
    private static String name = "_room_lock";
    
    public static boolean getRoomLockBoolean(Context context, String key, boolean value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getBoolean(key, value);
    }
    
    public static void setRoomLockBoolean(Context context, String key, boolean value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    
    public static void setRoomLockLong(Context context, String key, long value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static void setRoomLockFloat(Context context, String key, float value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    public static float getRoomLockFloat(Context context, String key, float value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getFloat(key, value);
    }
    public static long getRoomLockLong(Context context, String key, long value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getLong(key, value);
    }
    
    public static String getRoomLockString(Context context, String key, String value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getString(key, value);
    }
    
    public static int getRoomLockInt(Context context, String key, int value)
    {
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return app_info.getInt(key, value);
    }
    
    public static void setRoomLockString(Context context, String key, String value)
    {
        
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        
        editor.putString(key, value);
        
        editor.commit();
    }
    
    public static void setRoomLockInt(Context context, String key, int value)
    {
        
        SharedPreferences app_info = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_info.edit();
        
        editor.putInt(key, value);
        
        editor.commit();
    }

}
