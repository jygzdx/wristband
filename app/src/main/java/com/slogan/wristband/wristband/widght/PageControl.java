package com.slogan.wristband.wristband.widght;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.utils.DisplayUtils;


public class PageControl extends LinearLayout
{
    /**
     * 初始化PageControl时圆点默认位置
     * */
    public static final int DEFAULT_POSITION = -1;
    
    private Context context;
    
    private int current;
    
    private int count;
    
    private OnPageControlListener onPageControlListener;
    
    private int icon_focus = R.drawable.shape_add_user_page_pressed;
    
    private int icon_normal = R.drawable.shape_add_user_page_normal;

    private OnClickListener onClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (onPageControlListener != null)
            {
                onPageControlListener.onPageControl(v.getTag());
            }
        }
    };
    
    private int martin;
    private int icon;
    public PageControl(Context context, int count, int current, int icon) {
        super(context);
        this.context = context;
        this.count = count;
        this.current = current;
        this.icon = icon;
        martin = DisplayUtils.dip2px(context, 2);

        resetIntroduceIcon(icon);

        moveToPosition(current);
    }

    private void resetIntroduceIcon(int icon) {
        if (icon == 1) {
            icon_normal = R.drawable.shape_add_user_page_normal;
            icon_focus = R.drawable.shape_add_user_page_pressed;
            martin = DisplayUtils.dip2px(context, 14);
        }
    }

    public void setOnPageControlListener(OnPageControlListener onPageControlListener) {
        this.onPageControlListener = onPageControlListener;
    }
    
    public void moveToPosition(int position) {
        if (current == position && position != DEFAULT_POSITION) {
            return;
        }
        removeAllViews();
        current = position == DEFAULT_POSITION ? 0 : position;
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(martin,0,martin,0);
        ImageView imageView;
        for (int i = 0; i < count; i++) {
            imageView = new ImageView(context);
            imageView.setBackgroundResource(current == i ? icon_focus : icon_normal);
            imageView.setTag(i);
            imageView.setOnClickListener(onClickListener);
            addView(imageView, params);
        }
    }
    
    public interface OnPageControlListener {
        void onPageControl(Object tag);
    }
}