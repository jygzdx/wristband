package com.slogan.wristband.wristband.widght.user;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.AddUserInfoActivity;
import com.slogan.wristband.wristband.adapter.NumericWheelAdapter;
import com.slogan.wristband.wristband.utils.DisplayUtils;
import com.slogan.wristband.wristband.widght.SportWheelView;
import com.slogan.wristband.wristband.widght.WheelView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportStepView extends LinearLayout {
    private final View view;
    @BindView(R.id.sport)
    SportWheelView sport;
    private AddUserInfoActivity activity;

    public SportStepView(AddUserInfoActivity activity) {
        super(activity.mContext);
        view = LayoutInflater.from(activity.mContext).inflate(R.layout.layout_sport_step, this);
        ButterKnife.bind(view);
        this.activity = activity;
        initWeight();
    }

    private void initWeight() {
        sport.setAdapter(new NumericWheelAdapter(1,20,"%s000",false));
        sport.setCyclic(true);
        sport.setCurrentItem(5);
        int textSize = DisplayUtils.sp2px(activity.mContext, 16);
        sport.TEXT_SIZE = textSize;
    }
}
