package com.slogan.wristband.wristband.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.bean.ItemTestDataModel;

import java.util.List;

/**
 * Created by sll on 2018/11/25.
 */
public class HeartRateAdapter extends BaseQuickAdapter<ItemTestDataModel,BaseViewHolder> {

    public HeartRateAdapter(int layoutResId, @Nullable List<ItemTestDataModel> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ItemTestDataModel item) {
        helper.setText(R.id.tv_time,item.time);
        helper.setText(R.id.tv_num,item.num);
    }
}
