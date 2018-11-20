package com.slogan.wristband.wristband.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.adapter.CommonPagerAdapter;
import com.slogan.wristband.wristband.widght.PageControl;
import com.slogan.wristband.wristband.widght.user.BirthdayView;
import com.slogan.wristband.wristband.widght.user.HeadAndNameView;
import com.slogan.wristband.wristband.widght.user.HeightAndWeightView;
import com.slogan.wristband.wristband.widght.user.SexView;
import com.slogan.wristband.wristband.widght.user.SportStepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserInfoActivity extends BaseActivity {

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
    @BindView(R.id.vp_pager)
    ViewPager vpPager;
    @BindView(R.id.tv_up)
    TextView tvUp;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.myPageControlView)
    LinearLayout myPageControlView;
    private PageControl pageControl;
    private HeadAndNameView headAndNameView;
    private SexView sexView;
    private BirthdayView birthdayView;
    private HeightAndWeightView heightAndWeightView;
    private SportStepView sportStepView;
private int currentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_info);
        ButterKnife.bind(this);
        initHandler();
        initWidget();
    }
List<View> views = new ArrayList<>();
    @Override
    public void initWidget() {
        super.initWidget();
        pageControl = new PageControl(this, 5, PageControl.DEFAULT_POSITION, 1);
        headAndNameView = new HeadAndNameView(this);
        sexView = new SexView(this);
        birthdayView = new BirthdayView(this);
        heightAndWeightView = new HeightAndWeightView(this);
        sportStepView = new SportStepView(this);
        views.add(headAndNameView);
        views.add(sexView);
        views.add(birthdayView);
        views.add(heightAndWeightView);
        views.add(sportStepView);
        CommonPagerAdapter adapter = new CommonPagerAdapter(views);
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(currentPosition);
        pageControl.moveToPosition(currentPosition);
        myPageControlView.addView(pageControl);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                if(position == 0){
                    tvUp.setVisibility(View.GONE);
                    tvNext.setVisibility(View.VISIBLE);
                    tvSubmit.setVisibility(View.GONE);
                }else if(position == 4){
                    tvUp.setVisibility(View.VISIBLE);
                    tvNext.setVisibility(View.GONE);
                    tvSubmit.setVisibility(View.VISIBLE);
                }else{
                    tvUp.setVisibility(View.VISIBLE);
                    tvNext.setVisibility(View.VISIBLE);
                    tvSubmit.setVisibility(View.GONE);
                }
                pageControl.moveToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.tv_right_text, R.id.tv_up, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_text:
                break;
            case R.id.tv_up:
                pageControl.moveToPosition(currentPosition-1);
                vpPager.setCurrentItem(currentPosition-1);
                break;
            case R.id.tv_next:
                pageControl.moveToPosition(currentPosition+1);
                vpPager.setCurrentItem(currentPosition+1);
                break;
        }
    }
}
