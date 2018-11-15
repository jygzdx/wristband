package com.slogan.wristband.wristband.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CommTool {

    /**
     * 获取颜色值，例如：#452dbc
     *
     * @param color
     */
    public static String getColor(long color) {
        int r = (int) (color >> 24 & 0xff);
        int g = (int) ((color >> 16) & 0xff);
        int b = (int) ((color >> 8) & 0xff);
//        int a = (int) ((color >> 0) & 0xff);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#");
//        stringBuffer.append(a < 10 ? "0" + Integer.toHexString(a) : Integer.toHexString(a));
        stringBuffer.append(r < 10 ? "0" + Integer.toHexString(r) : Integer.toHexString(r));
        stringBuffer.append(g < 10 ? "0" + Integer.toHexString(g) : Integer.toHexString(g));
        stringBuffer.append(b < 10 ? "0" + Integer.toHexString(b) : Integer.toHexString(b));
        String s = stringBuffer.toString();
        Logger.d(s);
        return s;
    }

    public static String getHearRateTime(String data ,int minute){
        String s = "";
        String month = data.substring(4,6);
        String day = data.substring(6,8);
        int hour = minute/60;
        int m = minute%60;
        s = month +"月"+day+"日 "+hour+":"+m;
        return s;
    }

    /**
     * 血压时间
     * @return
     */
    public static String getBloodPressTime(String data){
        String s = "";
        String month = data.substring(4,6);
        String day = data.substring(6,8);
        s = month +"月"+day+"日";
        return s;
    }


    public static List<Activity> activities;

    public static void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<Activity>();
        }
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }


    public static void clearActivity() {
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) {
            return false;
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null || !ni.isAvailable()) {
            return false;
        }
        return true;
    }


    /**
     * 获取版本号
     */
    public static int getVersionCode(Context context) {
        int version = 0;
        try {
            PackageInfo info;
            info = context.getPackageManager().getPackageInfo(

                    context.getPackageName(), 0);
            version = info.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取渠道名
     */
    public static String getChannel(Context context) {
        String channel = "";
        try {
            ApplicationInfo appInfo =
                    context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return channel == null ? "" : channel;
    }

    /**
     * 获取Exception的堆栈新息。用于显示出错来源时使用。
     *
     * @param e      Exception对象
     * @param length 需要的信息长度，如果 <=0,表示全部信息
     * @return String 返回该Exception的堆栈新息
     */
    public static String getErrorStack(Throwable e, int length) {
        String error = null;
        if (e != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                e.printStackTrace(ps);
                error = baos.toString();
                if (length > 0) {
                    if (length > error.length()) {
                        length = error.length();
                    }
                    error = error.substring(0, length);
                }
                baos.close();
                ps.close();
            } catch (Exception e1) {
                error = e.toString();
            }
        }

        return error == null ? "" : error;
    }

    /**
     * 获取设备串号
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context context) {
        String deviceId = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();
        } catch (Exception e) {
            System.out.println("");
        }
        if (StringUtils.isBlank(deviceId)) {
            deviceId = getAndroidId(context);
        }
        return StringUtils.isBlank(deviceId) ? "" : deviceId;
    }

    /**
     * 获取设备ID
     */
    public static String getAndroidId(Context context) {
        String android_id = "";
        try {
            android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        } catch (Exception e) {
            // TODO: handle exception
        }
        return android_id;
    }

    /**
     * 获取时间间隔
     *
     * @param now  当前时间
     * @param time 原先时间
     * @return
     */
    public static String getTimeInterval(long now, long time) {
        String timeInfo = "";
        long second = (now - time) / 1000;
        if (second >= 60 * 60 * 24) {
            timeInfo = second / (60 * 60 * 24) + "天前";
        } else if (second >= 60 * 60 && second < 60 * 60 * 24) {
            timeInfo = second / (60 * 60) + "小时前";
        } else if (second >= 60 && second < 60 * 60) {
            timeInfo = second / 60 + "分钟前";
        } else {
            timeInfo = "少于一分钟";
        }
        return timeInfo;
    }

    public static <T extends View> T findById(Activity context, int resId) {
        if (context != null) {
            return (T) context.findViewById(resId);
        }
        return null;
    }

    public static void setTextviewDrawable(Context context, int drawable, TextView textView) {
        Drawable top = context.getResources().getDrawable(
                drawable);

        textView.setCompoundDrawablesWithIntrinsicBounds(null,
                top, null, null);
    }

    public static Drawable getShapeDrawable(Context mContext, int radius, int color){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(DisplayUtils.dip2px(mContext,radius));
        drawable.setColor(mContext.getResources().getColor(color));
        return drawable;
    }

    /**
     * 设置Textview的边框和文字颜色
     *
     * @param mContext
     * @param color
     * @param textView
     */
    public static void setTextViewBg(Context mContext, long color, TextView textView, long backColor) {
        try {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(DisplayUtils.dip2px(mContext, 8));
            drawable.setColor(CommTool.exchangeRGBA(backColor));
            textView.setTextColor(CommTool.exchangeRGBA(color));
            textView.setBackground(drawable);
        } catch (Exception e) {

        }

    }

    /**
     * 设置Textview的边框和文字颜色
     *
     * @param color
     * @param textView
     */
    public static void setTextColor(long color, TextView textView) {
        try {
            textView.setTextColor(CommTool.exchangeRGBA(color));
        } catch (Exception e) {

        }

    }

    public static int exchangeRGBA(long color) {
        int r = (int) (color >> 24 & 0xff);
        int g = (int) ((color >> 16) & 0xff);
        int b = (int) ((color >> 8) & 0xff);
        int a = (int) ((color >> 0) & 0xff);
        return Color.argb(a, r, g, b);
    }

    public static void setStatusBarFontDark(boolean dark, Activity activity) {
        // 小米MIUI
        try {
            Window window = activity.getWindow();
            Class clazz = activity.getWindow().getClass();
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (dark) {    //状态栏亮色且黑色字体
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
            } else {       //清除黑色字体
                extraFlagField.invoke(window, 0, darkModeFlag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 魅族FlymeUI
        try {
            Window window = activity.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            window.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // android6.0+系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (dark) {
                activity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    public static void openKeyBord(final Context context, final EditText edittext) {
        edittext.setFocusable(true);
        edittext.setFocusableInTouchMode(true);
        edittext.requestFocus();
        Timer timer = new Timer(); // 设置定时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() { // 弹出软键盘的代码
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edittext, 0);
            }
        }, 300); // 设置300毫秒的时长
    }

    public static void closeKeyBord(Activity context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 得到InputMethodManager的实例

        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

    }


    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
    };
    private static String[] PERMISSIONS_AUDION = {
            Manifest.permission.RECORD_AUDIO
    };

    public static void checkAudioPermission(Activity context) {
//        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                    context,
//                    PERMISSIONS_AUDION,
//                    ActivityResultCode.ACTIVITY_PERMISSION_AUDION
//            );
//        }
    }

    public static String getModel() {
        try {

            return android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";

    }

    public static String getMobileVersion() {
        try {
            return android.os.Build.VERSION.RELEASE;
        } catch (Exception e) {
            // TODO: handle exception
            return "";
        }

    }

    public static void startLoad(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            context.startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static boolean isMIUI() {
        return !StringUtils.isBlank(getSystemProperty("ro.miui.ui.version.name"));
    }

    public static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }
    private static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }
}
