package com.slogan.wristband.wristband.widght.heartrate;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sll on 2018/11/22.
 */
public class ItemHeartRateView extends LinearLayout {



    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    private View mRootView;

    public ItemHeartRateView(Context context) {
        this(context, null);
    }

    public ItemHeartRateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemHeartRateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_heart_rate_item, this);
        ButterKnife.bind(mRootView);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ItemHeartRateView);
        String num = array.getString(R.styleable.ItemHeartRateView_ihr_num);
        int icon = array.getResourceId(R.styleable.ItemHeartRateView_ihr_icon,R.drawable.ic_heartrate_avg);
        String name = array.getString(R.styleable.ItemHeartRateView_ihr_name);
        String unit = array.getString(R.styleable.ItemHeartRateView_ihr_unit);

        tvNum.setText(num);
        ivIcon.setImageResource(icon);
        tvName.setText(name);
        tvUnit.setText(unit);

        array.recycle();
    }

    public void setTvNum(String num) {
        tvNum.setText(num);
    }

    public void setTvUnit(String unit) {
        tvUnit.setText(unit);
    }

    public void setIvIcon(int res) {
        ivIcon.setImageResource(res);
    }

    public void setTvName(String name) {
        tvName.setText(name);
    }

}
