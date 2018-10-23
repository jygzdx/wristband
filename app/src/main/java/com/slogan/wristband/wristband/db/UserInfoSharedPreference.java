package com.slogan.wristband.wristband.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 存储登录用户信息 使用时注意在主线程中调用<功能详细描述>
 *
 * @author {user}
 * @version [版本号, 2016年2月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserInfoSharedPreference implements UserInfoConfig {
    private static final String SHARED_PREFERENCE_NAME = "user";

    public static String getUserInfoString(Context context, String key, String defaultValue) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        String value = user.getString(key, defaultValue);

        return value;
    }

    public static int getUserInfoInt(Context context, String key, int defaultValue) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        int value = user.getInt(key, defaultValue);

        return value;
    }

    public static long getUserInfoLong(Context context, String key, long defaultValue) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        long value = user.getLong(key, defaultValue);

        return value;
    }


    public static boolean getUserInfoBool(Context context, String key, boolean defaultValue) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        boolean value = user.getBoolean(key, defaultValue);

        return value;
    }


    public static void saveUserInfoString(Context context, String key, String value) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        // 让setting处于编辑状态
        SharedPreferences.Editor editor = user.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static void saveUserInfoBoolean(Context context, String key, boolean value) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        // 让setting处于编辑状态
        SharedPreferences.Editor editor = user.edit();

        editor.putBoolean(key, value);

        editor.commit();
    }


    public static void saveUserInfoInt(Context context, String key, int value) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        // 让setting处于编辑状态
        SharedPreferences.Editor editor = user.edit();

        editor.putInt(key, value);

        editor.commit();
    }

    public static void saveUserInfoLong(Context context, String key, long value) {

        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        // 让setting处于编辑状态
        SharedPreferences.Editor editor = user.edit();

        editor.putLong(key, value);

        editor.commit();
    }

    public static void clearUserInfo(Context context) {
        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = user.edit();
        editor.clear();
        editor.commit();
    }

    public static void clearChildUserInfo(Context context, String key) {
        // 打开Preferences，名称为user，如果存在则打开它，否则创建新的Preferences
        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = user.edit();
        editor.remove(key);
        editor.apply();
    }


//    public static void saveUserInfoLoginRegister(Context context, UserInfo userInfo) {
//        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        // 让setting处于编辑状态
//        SharedPreferences.Editor editor = user.edit();
//
//        editor.putLong(USER_ID, userInfo.getUid());
//        if (!StringUtils.isBlank(userInfo.getLoginKey())) {
//            editor.putString(LOGIN_KEY, userInfo.getLoginKey());
//        }
//
//
//        editor.putString(NAME, userInfo.getName());
//
//        editor.putString(HEAD, userInfo.getHead());
//
//        editor.putString(HEAD_HD, userInfo.getHeadHd());
//
//        editor.putString(BIRTHDAY, userInfo.getBirthday());
//
//        editor.putInt(AGE, userInfo.getAge());
//
//        editor.putInt(STAR_SIGN, userInfo.getStarSign());
//
//        editor.putInt(GENDER, userInfo.getGender());
//
//        editor.putString(ABOUT, userInfo.getAbout());
//
//        editor.putString(PHONE, userInfo.getPhone());
//
//        editor.putLong(SHORT_ID,userInfo.getShortId());
//        if (userInfo.getRoomId() != 0) {
//            editor.putLong(ROOM_ID, userInfo.getRoomId());
//        }
//        editor.commit();
//        saveImConnectInfo(context,userInfo.getImConnectInfo());
//    }
//
//    public static void saveUserInfoMe(Context context, UserInfo userInfo) {
//        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        // 让setting处于编辑状态
//        SharedPreferences.Editor editor = user.edit();
//
//        editor.putLong(USER_ID, userInfo.getUid());
//
//        editor.putLong(SHORT_ID, userInfo.getShortId());
//
//        editor.putString(NAME, userInfo.getName());
//
//        editor.putString(HEAD, userInfo.getHead());
//
//        editor.putString(HEAD_HD, userInfo.getHeadHd());
//
//        editor.putInt(GENDER, userInfo.getGender());
//
//        editor.putString(ABOUT, userInfo.getAbout());
//
//        editor.putString(GIFT_TOP, userInfo.getContribute());
//
//        editor.putInt(FANS_NUM, userInfo.getFansNum());
//
//        editor.putInt(FOLLOW_NUM, userInfo.getFollowNum());
//
//        editor.putInt(LEVEL, userInfo.getLevel());
//
//        editor.putInt(FOLLOW_ROOM_NUM, userInfo.getFollowRoomNum());
//
//        editor.putInt(WITHDRAW_COUNT, userInfo.getIntegral());
//
//        editor.putInt(ACCOUNT, userInfo.getAccount());
//
//        editor.putInt(AGE, userInfo.getAge());
//
//        editor.apply();
//    }
//
//    public static void saveUserInfoCheck(Context context, UserInfo userInfo) {
//        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        // 让setting处于编辑状态
//        SharedPreferences.Editor editor = user.edit();
//
//
//        editor.putString(NAME, userInfo.getName());
//
//        editor.putString(HEAD, userInfo.getHead());
//
//        editor.putString(HEAD_HD, userInfo.getHeadHd());
//
//        editor.putInt(GENDER, userInfo.getGender());
//
//        editor.putString(ABOUT, userInfo.getAbout());
//
//        editor.commit();
//    }
//
//
//    public static UserInfo getUserInfo(Context context) {
//        UserInfo userInfo = new UserInfo();
//        SharedPreferences user = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        userInfo.setUid(user.getLong(USER_ID, 0));
//        userInfo.setHeadHd(user.getString(HEAD_HD, ""));
//        userInfo.setHead(user.getString(HEAD, ""));
//        userInfo.setName(user.getString(NAME, ""));
//        userInfo.setLoginKey(user.getString(LOGIN_KEY, ""));
//        userInfo.setGender(user.getInt(GENDER, 0));
//        userInfo.setAbout(user.getString(ABOUT, ""));
//        userInfo.setBirthday(user.getString(BIRTHDAY, ""));
//        userInfo.setFollowNum(user.getInt(FOLLOW_NUM, 0));
//        userInfo.setFansNum(user.getInt(FANS_NUM, 0));
//        userInfo.setLevel(user.getInt(LEVEL, 1));
//        userInfo.setFollowRoomNum(user.getInt(FOLLOW_ROOM_NUM, 0));
//        userInfo.setIntegral(user.getInt(WITHDRAW_COUNT, 0));
//        userInfo.setAccount(user.getInt(ACCOUNT, 0));
//        userInfo.setStarSign(user.getInt(STAR_SIGN, 0));
//        userInfo.setContribute(user.getString(GIFT_TOP, ""));
//        userInfo.setAge(user.getInt(AGE, 1));
//        userInfo.setShortId(user.getLong(SHORT_ID,0));
//        return userInfo;
//    }

}
