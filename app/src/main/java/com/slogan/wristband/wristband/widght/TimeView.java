package com.slogan.wristband.wristband.widght;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by free_boy on 2018/11/5.
 */

public class TimeView extends LinearLayout {
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_shi)
    TextView tvShi;
    @BindView(R.id.tv_fen)
    TextView tvFen;

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.include_time_show_view, this);
        ButterKnife.bind(view);
    }

    public void setTime(int hour, int second) {
        tvHour.setText(hour + "");
        tvSecond.setText(second + "");
    }

    public void setOneCount(int num,String unit){
        tvSecond.setVisibility(GONE);
        tvFen.setVisibility(GONE);
        tvHour.setText(num+"");
        tvShi.setText(unit);
    }
}
