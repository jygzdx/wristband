package com.slogan.wristband.wristband.widght;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.adapter.NumericWheelAdapter;
import com.slogan.wristband.wristband.utils.DisplayUtils;
import com.slogan.wristband.wristband.utils.StringUtils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017-04-25.
 */
public class DatePickDialog extends Dialog {
    private ViewGroup mViewGroup = null;
    private int START_YEAR = 1948, END_YEAR = 1996;
    WheelView wv_year;
    WheelView wv_month;
    WheelView wv_day;
    Button btn_finish;
    Button btn_dialog1;
    TextView dialog_text;
    Context context;
    private final View line;

    public DatePickDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        this.context = context;
        mViewGroup = (ViewGroup) ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.include_datepick_dialog, null);
        btn_finish = (Button) mViewGroup.findViewById(R.id.btn_finish);
        dialog_text = (TextView) mViewGroup.findViewById(R.id.dialog_text);
        line = mViewGroup.findViewById(R.id.line);

        btn_dialog1 = (Button) mViewGroup.findViewById(R.id.btn_dialog1);

        btn_dialog1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 24;
        START_YEAR = calendar.get(Calendar.YEAR) - 100;
        END_YEAR = calendar.get(Calendar.YEAR) - 17;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        initView(year, month, day);

    }

    private void initView(int year, int month, int day) {

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
        String[] months_little = { "4", "6", "9", "11" };

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);
        // 年
        wv_year = (WheelView) mViewGroup.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR, false));// 设置"年"的显示数据
        wv_year.setCyclic(true);// 可循环滚动
        wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

        // 月
        wv_month = (WheelView) mViewGroup.findViewById(R.id.month);
        wv_month.setAdapter(new NumericWheelAdapter(1, 12, false));
        wv_month.setCyclic(true);
        wv_month.setCurrentItem(month);

        // 日
        wv_day = (WheelView) mViewGroup.findViewById(R.id.day);
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
        int textSize = DisplayUtils.sp2px(context, 16);
        wv_day.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;
    }

    public void resetDate(int start_year, int end_year, String current_date) {
        START_YEAR = start_year;
        END_YEAR = end_year;
        try {
            int year = Integer.parseInt(current_date.substring(0, 4));
            int month = Integer.parseInt(current_date.substring(5, 7));
            int day = Integer.parseInt(current_date.substring(8, 10));
            initView(year, month - 1, day);
        } catch (Exception e) {
        }

    }

    public void showDialog(View.OnClickListener onClickListener,
                           String text) {
        btn_finish.setOnClickListener(onClickListener);
        if (StringUtils.isBlank(text)) {
            dialog_text.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }else{
            dialog_text.setText(text);
        }
        Window localWindow = getWindow();
        localWindow.setContentView(mViewGroup);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
    
        try {
            this.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dismiss(){
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDate(String date) {
        if (!StringUtils.isBlank(date)) {
            String[] dateStrings = date.split("-");
            if (dateStrings.length == 3) {
                wv_year.setCurrentItem(Integer.valueOf(dateStrings[0].trim())
                        - START_YEAR);// 初始化时显示的数据
                wv_month.setCurrentItem(Integer.valueOf(dateStrings[1].trim())-1);
                wv_day.setCurrentItem(Integer.valueOf(dateStrings[2].trim()) - 1);
            }
        }

    }

    public String getDate() {
        String parten = "00";
        DecimalFormat decimal = new DecimalFormat(parten);
        return (wv_year.getCurrentItem() + START_YEAR) + "-"
                + decimal.format((wv_month.getCurrentItem() + 1)) + "-"
                + decimal.format((wv_day.getCurrentItem() + 1));
    }
}
