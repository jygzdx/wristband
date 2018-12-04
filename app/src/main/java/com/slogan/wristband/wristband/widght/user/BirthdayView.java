package com.slogan.wristband.wristband.widght.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.AddUserInfoActivity;
import com.slogan.wristband.wristband.adapter.NumericWheelAdapter;
import com.slogan.wristband.wristband.utils.DisplayUtils;
import com.slogan.wristband.wristband.widght.DatePickDialog;
import com.slogan.wristband.wristband.widght.WheelView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;

public class BirthdayView extends LinearLayout {
    private final View view;
    private AddUserInfoActivity activity;
    private DatePickDialog mDatePickDialog;
    private int START_YEAR = 1948, END_YEAR = 1996;
    WheelView wv_year;
    WheelView wv_month;
    WheelView wv_day;

    public BirthdayView(AddUserInfoActivity activity) {
        super(activity.mContext);
        view = LayoutInflater.from(activity.mContext).inflate(R.layout.layout_birthday, this);
        ButterKnife.bind(view);
        this.activity = activity;

        initWidght();
    }

    private void initWidght() {
        Calendar calendar = Calendar.getInstance();
        START_YEAR = calendar.get(Calendar.YEAR) - 100;
        END_YEAR = calendar.get(Calendar.YEAR);
        initView(1990, 5, 15);
    }

    private void initView(int year, int month, int day) {

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
        String[] months_little = { "4", "6", "9", "11" };

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);
        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR, false));// 设置"年"的显示数据
        wv_year.setCyclic(true);// 可循环滚动
        wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

        // 月
        wv_month = (WheelView) view.findViewById(R.id.month);
        wv_month.setAdapter(new NumericWheelAdapter(1, 12, false));
        wv_month.setCyclic(true);
        wv_month.setCurrentItem(month);

        // 日
        wv_day = (WheelView) view.findViewById(R.id.day);
        wv_day.setCyclic(true);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31, false));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30, false));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new NumericWheelAdapter(1, 29, false));
            else
                wv_day.setAdapter(new NumericWheelAdapter(1, 28, false));
        }
        wv_day.setCurrentItem(day - 1);

        // 添加"年"监听
        WheelView.OnWheelChangedListener wheelListener_year = new WheelView.OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big
                        .contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31, false));
                } else if (list_little.contains(String.valueOf(wv_month
                        .getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30, false));
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29, false));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28, false));
                }
            }
        };
        // 添加"月"监听
        WheelView.OnWheelChangedListener wheelListener_month = new WheelView.OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31, false));
                } else if (list_little.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30, false));
                } else {
                    if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
                            .getCurrentItem() + START_YEAR) % 100 != 0)
                            || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29, false));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28, false));
                }
            }
        };
        wv_year.addChangingListener(wheelListener_year);
        wv_month.addChangingListener(wheelListener_month);
        int textSize = DisplayUtils.sp2px(activity.mContext, 16);
        wv_day.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;
    }

//    private void popSelectBirthDayDialog() {
//        if (mDatePickDialog == null) {
//            mDatePickDialog = new DatePickDialog(this.getContext());
//        }
//
//        int year = Calendar.getInstance().get(Calendar.YEAR) - 9;
//        mDatePickDialog.resetDate(1917,year,"2018-12-04");
//        mDatePickDialog.showDialog(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String date = mDatePickDialog.getDate();
//                mDatePickDialog.dismiss();
//            }
//        }, null);
//    }
}
