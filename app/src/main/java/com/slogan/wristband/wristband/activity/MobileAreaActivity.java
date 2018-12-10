package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.adapter.MobileAreaAdapter;
import com.slogan.wristband.wristband.app.ActivityResultCode;
import com.slogan.wristband.wristband.bean.MobileAreaEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobileAreaActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.ll_title_parent)
    LinearLayout llTitleParent;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private LinearLayoutManager manager;
    private MobileAreaAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_area);
        ButterKnife.bind(this);
initHandler();
initWidget();
    }

    private List<MobileAreaEntity> areaEntityList = new ArrayList<>();

    @Override
    public void initWidget() {
        super.initWidget();
            areaEntityList.add(new MobileAreaEntity("中国大陆","86",true));
        areaEntityList.add(new MobileAreaEntity("香港","852",false));
        areaEntityList.add(new MobileAreaEntity("澳门","853",false));
        areaEntityList.add(new MobileAreaEntity("台湾","886",false));
        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        adapter = new MobileAreaAdapter(areaEntityList);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MobileAreaEntity entity = (MobileAreaEntity) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra("area_code",entity.getCode());
                setResult(ActivityResultCode.ACTIVITY_MOBILE_AREA,intent);
                finish();
            }
        });
    }

    @OnClick(R.id.iv_left)
    public void onViewClicked() {
        finish();
    }
}
