package com.slogan.wristband.wristband.widght;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by czb on 2018/8/23.
 */

public class ChooseSexPop extends Dialog implements View.OnClickListener {
    private final Context mContext;
    ImageView ivMan;
    LinearLayout llMan;
    ImageView ivWomen;
    LinearLayout llWomen;
    private View mViewGroup;
    private OnClickListener listener;
    private int sex = 0;

    public ChooseSexPop(Context context) {
        super(context, R.style.MyDialogStyle);
        this.mContext = context;
        mViewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.include_choose_sex_pop, null);
        initWeight();
        setCanceledOnTouchOutside(true);
    }

    private void initWeight() {
        ivWomen = mViewGroup.findViewById(R.id.iv_women);
        ivMan = mViewGroup.findViewById(R.id.iv_man);
        llWomen = mViewGroup.findViewById(R.id.ll_women);
        llMan = mViewGroup.findViewById(R.id.ll_man);
        llWomen.setOnClickListener(this);
        llMan.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.ll_man:
                sex = 0;
                ivMan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_sex_selected));
                ivWomen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_sex_unselected));
                if(listener !=null){
                    listener.onSex(sex);
                }
                break;
            case R.id.ll_women:
                sex = 1;
                ivMan.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_sex_unselected));
                ivWomen.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_sex_selected));
                if(listener !=null){
                    listener.onSex(sex);
                }
                break;
        }
    }

    public interface OnClickListener {
        void onSex(int sex);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
