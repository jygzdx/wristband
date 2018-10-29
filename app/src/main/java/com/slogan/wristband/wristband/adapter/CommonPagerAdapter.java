package com.slogan.wristband.wristband.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by czb on 2018/8/2.
 */

public class CommonPagerAdapter extends PagerAdapter {

    private List<View> views;

    public CommonPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views == null?0:views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if(views==null){
            return;
        }
        container.removeView(views.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if(views==null){
            return new Object();
        }
        container.addView(views.get(position));
        return views.get(position);
    }
}
