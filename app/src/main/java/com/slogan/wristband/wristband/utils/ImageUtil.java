package com.slogan.wristband.wristband.utils;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.slogan.wristband.wristband.app.WristbandApplication;

import java.io.File;

/**
 * Created by czb on 2018/8/8.
 */

public class ImageUtil {
    private static final String TAG = "ImageUtil";

    public static void setCircleImage(String url, ImageView img){
        RequestOptions options = new RequestOptions().circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate();
        Glide.with(WristbandApplication.getInstance())
                .load(url)
                .apply(options)
                .into(img);
    }


    public static void setCircleImage(String url, ImageView img,int defaultImg){
        RequestOptions options = new RequestOptions().circleCrop().placeholder(defaultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate();
        Glide.with(WristbandApplication.getInstance())
                .load(url)
                .apply(options)
                .into(img);
    }

    public static void seImageWithoutPlace(String url, ImageView img){
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(WristbandApplication.getInstance())
                .load(url)
                .apply(options)
                .into(img);
    }

    public static void seImageFileWithoutPlace(File file, ImageView img){
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(WristbandApplication.getInstance())
                .load(file)
                .apply(options)
                .into(img);
    }
    public static void setCommonImage(Context mContext, String url, ImageView img){
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(WristbandApplication.getInstance()).load(url)
                .apply(options)
                .into(img);
    }

    public static void setCommonImage( String url, ImageView img,int defaultImg){
        RequestOptions options = new RequestOptions().placeholder(defaultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(WristbandApplication.getInstance()).load(url)
                .apply(options)
                .into(img);
    }

    public static void setImageNoPlaceholder(Context mContext, String url, ImageView img){
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(WristbandApplication.getInstance()).load(url)
                .apply(options)
                .into(img);
    }

    public static void setResourceImg(int res, ImageView img){
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(WristbandApplication.getInstance()).load(res)
                .apply(options)
                .into(img);
    }

    public static void setBgWithBlur(Context context , String url, final View view){
//        RequestOptions options = new RequestOptions();
//        options.diskCacheStrategy(DiskCacheStrategy.ALL)
//                .transform(new BlurTransformation(50));
//        Glide.with(WristbandApplication.getInstance()).load(url)
//                .into(new SimpleTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
//                        view.setBackground(drawable);
//                    }
//                });
    }
}
