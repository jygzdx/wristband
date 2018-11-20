package com.slogan.wristband.wristband.widght.user;

import android.content.Context;
import android.widget.LinearLayout;

import com.slogan.wristband.wristband.activity.AddUserInfoActivity;

public class HeadAndNameView extends LinearLayout {
    private AddUserInfoActivity activity;

    public HeadAndNameView(AddUserInfoActivity activity) {
        super(activity.mContext);
        this.activity = activity;
    }
}
