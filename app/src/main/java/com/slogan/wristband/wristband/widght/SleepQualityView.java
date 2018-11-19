package com.slogan.wristband.wristband.widght;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.veclink.bracelet.bean.DeviceSleepData;

import java.util.List;

public class SleepQualityView extends LinearLayout {
    private final Context mContext;

    public SleepQualityView(Context context) {
        this(context,null);
    }

    public SleepQualityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        SleepQualityView.this.setOrientation(HORIZONTAL);
    }

    public void refreshView(List<DeviceSleepData> sleepData){
        if(sleepData == null){
            return;
        }
        for (int i = 0; i < sleepData.size(); i++) {
            DeviceSleepData sleep = sleepData.get(i);
            TextView textView = new TextView(mContext);
            LayoutParams params = new LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = sleep.sleepDuration;
            textView.setLayoutParams(params);
            if (sleep.sleepState <= 1) {
                textView.setBackgroundColor(mContext.getResources().getColor(R.color.deep_sleep));
            } else if (sleep.sleepState > 1 && sleep.sleepState<= 3) {
                textView.setBackgroundColor(mContext.getResources().getColor(R.color.light_sleep));
            } else {
                textView.setBackgroundColor(mContext.getResources().getColor(R.color.clear_sleep));
            }
        }
    }
}
