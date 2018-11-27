package com.slogan.wristband.wristband.widght;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czb on 2018/8/23.
 */

public class ChoosePicPop extends Dialog implements View.OnClickListener {
    private final Context mContext;
    private View mViewGroup;
    private TextView tv_pic;
    private TextView tv_camera;
    private OnClickListener listener;

    public ChoosePicPop(Context context) {
        super(context, R.style.MyDialogStyle);
        this.mContext = context;
        mViewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.include_choose_pic_pop, null);
        initWeight();
        setCanceledOnTouchOutside(true);
    }

    private void initWeight() {
        tv_camera = mViewGroup.findViewById(R.id.tv_camera);
        tv_pic = mViewGroup.findViewById(R.id.tv_pic);
        tv_camera.setOnClickListener(this);
        tv_pic.setOnClickListener(this);
    }


    public void showDialog() {
        Window localWindow = getWindow();
        localWindow.setContentView(mViewGroup);
        WindowManager.LayoutParams lp = localWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.AnimationBottom;
        localWindow.getDecorView().setPadding(0, 0, 0, 0);
        localWindow.setAttributes(lp);
        this.show();
    }

    @Override
    public void onClick(View view) {
        dismiss();
        switch (view.getId()){
            case R.id.tv_camera:
                if(listener!=null){
                    listener.onCameraClick();
                }
                break;
            case R.id.tv_pic:
                if(listener!=null){
                    listener.onPicClick();
                }
                break;
        }
    }

    public interface OnClickListener{
        void onCameraClick();
        void onPicClick();
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }
}
