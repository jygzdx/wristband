package com.slogan.wristband.wristband.widght;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.slogan.wristband.wristband.R;

/**
 * Created by free_boy on 2018/10/24.
 */

public class PasswordLoginView extends LinearLayout {
    private final Context mContext;
private View view ;
    public PasswordLoginView(Context context) {
        super(context);
        this.mContext = context;
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_password_view, this);
    }
}
