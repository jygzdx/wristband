package com.slogan.wristband.wristband.widght;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.slogan.wristband.wristband.R;

/**
 * 统一提示Toast
 * 
 * @author {user}
 * @version [版本号, 2016年2月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class CommToast
{
  private  Toast toast;

    private TextView textView;


    public void showToast(Context context, String text)
    {
        if (toast == null)
        {
            toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            textView = (TextView)view.findViewById(R.id.toast_text);
            toast.setView(view);
            
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        
        textView.setText(text);
        toast.show();
        
    }
    
    public void showToast(Context context, int text)
    {
        if (toast == null)
        {
            toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            textView = (TextView)view.findViewById(R.id.toast_text);
            toast.setView(view);
            
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        textView.setText(text);
        toast.show();
        
    }

}
