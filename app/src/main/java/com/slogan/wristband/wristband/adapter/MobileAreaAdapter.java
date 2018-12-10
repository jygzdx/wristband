package com.slogan.wristband.wristband.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.bean.MobileAreaEntity;

import java.util.List;

public class MobileAreaAdapter extends BaseQuickAdapter<MobileAreaEntity,BaseViewHolder> {
    public MobileAreaAdapter(@Nullable List<MobileAreaEntity> data) {
        super(R.layout.item_mobile_area, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MobileAreaEntity item) {
        helper.setText(R.id.tv_area,item.getArea())
                .setText(R.id.tv_area_code,"+"+item.getCode());
    }
}
