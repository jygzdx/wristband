package com.slogan.wristband.wristband.widght.DateSlider.labeler;

import android.content.Context;

import com.slogan.wristband.wristband.widght.DateSlider.timeview.DayTimeLayoutView;
import com.slogan.wristband.wristband.widght.DateSlider.timeview.TimeView;


/**
 * A Labeler that displays days using DayTimeLayoutViews.
 */
public class DayDateLabeler extends DayLabeler {
    /**
     * The format string that specifies how to display the day. Since this class
     * uses a DayTimeLayoutView, the format string should consist of two strings
     * separated by a space.
     *
     * @param formatString
     */
    public DayDateLabeler(String formatString) {
        super(formatString);
    }

    @Override
    public TimeView createView(Context context, boolean isCenterView, boolean isCenterLeftView, boolean isCenterRightView) {
        return new DayTimeLayoutView(context, isCenterView, isCenterLeftView, isCenterRightView, 10, 10, 10, 1f);
    }

    @Override
    public TimeView createView(Context context, boolean isCenterView, boolean isCenterLeftView, boolean isCenterRightView,
                               int topTextSize, int centerTextSize, int bottomTextSize, int selectColor) {
        return new DayTimeLayoutView(context, isCenterView, isCenterLeftView, isCenterRightView, topTextSize, centerTextSize, bottomTextSize, 1f, selectColor);
    }
}