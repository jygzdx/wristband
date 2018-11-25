package com.slogan.wristband.wristband.widght.heartrate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by sll on 2018/11/22.
 */
public class UnableSeekBar extends SeekBar {
    public UnableSeekBar(Context context) {
        super(context);
    }

    public UnableSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnableSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
