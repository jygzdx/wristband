package com.slogan.wristband.wristband.widght;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.utils.DisplayUtils;

/**
 * Created by czb on 2018/8/23.
 */

public class ChangeNicknamePop extends Dialog implements View.OnClickListener {
    private final Context mContext;
    private View mViewGroup;
    private TextView tv_cancel,tv_ensure,tv_title;
    private EditText et_nickname;
    private OnClickListener listener;
    private ImageView iv_clear;

    public ChangeNicknamePop(Context context) {
        super(context, R.style.MyDialogStyle);
        this.mContext = context;
        mViewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.include_edit_nickname, null);
        initWeight();
        setCanceledOnTouchOutside(true);
    }

    private void initWeight() {
        tv_cancel = mViewGroup.findViewById(R.id.tv_cancel);
        tv_ensure = mViewGroup.findViewById(R.id.tv_ensure);
        tv_title = mViewGroup.findViewById(R.id.tv_title);
        et_nickname = mViewGroup.findViewById(R.id.et_nickname);
        iv_clear = mViewGroup.findViewById(R.id.iv_clear);
        iv_clear.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        tv_ensure.setOnClickListener(this);
    }


    public void showDialog() {
        Window localWindow = getWindow();
        localWindow.setContentView(mViewGroup);
        WindowManager.LayoutParams lp = localWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        View view = localWindow.getDecorView();
        view.setPadding(0,0,0,0);
        localWindow.setAttributes(lp);
        this.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_clear:
                et_nickname.setText("");
                break;
            case R.id.tv_cancel:
               dismiss();
                break;
            case R.id.tv_ensure:
                dismiss();
                if(listener != null){
                    listener.onEnsureClick(et_nickname.getText().toString().trim());
                }
                break;
        }
    }

    public interface OnClickListener{
        void onEnsureClick(String text);
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }
}
