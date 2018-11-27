package com.slogan.wristband.wristband.widght;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.slogan.wristband.wristband.R;

/**
 * 通用列表选择框
 */
@SuppressLint("InflateParams")
public class CommListViewDialog extends Dialog {
    private ViewGroup mViewGroup = null;

  private  TextView dialog_text;

    private  ListView listview;

    private Button btn_finish;

    private  Context context;

    private  String[] content;

    private TypeAdapter typeAdapter;

    private String currentSelect;

    public CommListViewDialog(Context context) {
        // TODO Auto-generated constructor stub
        super(context, R.style.MyDialogStyle);
        this.context = context;

        mViewGroup =
                (ViewGroup) LayoutInflater.from(context).inflate(R.layout.include_radio_list_dialog_new,
                        null);
        btn_finish =  mViewGroup.findViewById(R.id.btn_finish);
        listview =  mViewGroup.findViewById(R.id.list_view);
        dialog_text = mViewGroup.findViewById(R.id.dialog_text);
        btn_finish.setOnClickListener(dismissClickListener);
        setCanceledOnTouchOutside(true);

    }

   private  View.OnClickListener dismissClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            CommListViewDialog.this.dismiss();
        }
    };

    /**
     * <一句话功能简述> <功能详细描述>弹出列表选择对话框
     *
     * @param content 列表内容
     * @param tip     顶部提示文字
     * @see [类、类#方法、类#成员]
     */
    public void showDialogList(String[] content, int tip, String currentSelect) {
        this.currentSelect = currentSelect;
        btn_finish.setText(R.string.cancel);
        if (typeAdapter == null) {
            typeAdapter = new TypeAdapter(content);
            listview.setAdapter(typeAdapter);
        } else {
            typeAdapter.setItems(content);
            typeAdapter.notifyDataSetChanged();
        }
        dialog_text.setText(tip);
        Window localWindow = getWindow();
        localWindow.setContentView(mViewGroup);
        WindowManager.LayoutParams lp=localWindow.getAttributes();
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity= Gravity.BOTTOM;
        lp.windowAnimations=R.style.AnimationBottom;
        localWindow.getDecorView().setPadding(0,0,0,0);
        localWindow.setAttributes(lp);

        this.show();
    }

    public void setButtonText(String text) {
        btn_finish.setText(text);
    }

    public void updateData(String[] content, int tip) {
        if (typeAdapter == null) {
            typeAdapter = new TypeAdapter(content);
            listview.setAdapter(typeAdapter);
        } else {
            typeAdapter.setItems(content);
            typeAdapter.notifyDataSetChanged();
        }
        dialog_text.setText(tip);
    }

    public void setOnitemClick(OnItemClickListener onItemClickListener) {
        listview.setOnItemClickListener(onItemClickListener);
    }

    class TypeAdapter extends BaseAdapter {
        String[] items;

        public String[] getItems() {
            return items;
        }

        public void setItems(String[] items) {
            this.items = items;
        }

        public TypeAdapter(String[] items) {
            this.items = items;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return items.length;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_dialog_fly, null);
                holder = new ViewHolder();
                holder.name_tv = convertView.findViewById(R.id.name_tv);
                holder.divide_view = convertView.findViewById(R.id.divide_view);
                holder.selected_img = convertView.findViewById(R.id.selected_img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == items.length - 1) {
                holder.divide_view.setVisibility(View.GONE);

            } else {
                holder.divide_view.setVisibility(View.VISIBLE);
            }
            if (currentSelect == null) {
                holder.selected_img.setVisibility(View.GONE);
            } else {
                holder.selected_img.setVisibility(items[position].equals(currentSelect) ? View.VISIBLE : View.GONE);
            }
            convertView.setTag(R.string.app_name, items[position]);
            holder.name_tv.setText(Html.fromHtml(items[position]));

            return convertView;
        }

    }

    final static class ViewHolder {
        TextView name_tv;

        View divide_view;

        ImageView selected_img;
    }
}
