package com.slogan.wristband.wristband.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.adapter.CommonPagerAdapter;
import com.slogan.wristband.wristband.utils.ImageUtil;
import com.slogan.wristband.wristband.widght.PageControl;
import com.slogan.wristband.wristband.widght.user.BirthdayView;
import com.slogan.wristband.wristband.widght.user.HeadAndNameView;
import com.slogan.wristband.wristband.widght.user.HeightAndWeightView;
import com.slogan.wristband.wristband.widght.user.SexView;
import com.slogan.wristband.wristband.widght.user.SportStepView;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TImage;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserInfoActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

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
    public Context mContext;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getTakePhoto().onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_info);
        ButterKnife.bind(this);
        getTakePhoto().onCreate(savedInstanceState);
        mContext = this;
        initWidget();
    }
List<View> views = new ArrayList<>();

    public void initWidget() {
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
//    /**
//     * 从文件中获取图片（不裁剪）
//     */
//    void onPickFromDocuments();
//    /**
//     * 从相册中获取图片（不裁剪）
//     */
//    void onPickFromGallery();
//    /**
//     * 从相机获取图片(不裁剪)
//     * @param outPutUri 图片保存的路径
//     */
//    void onPickFromCapture(Uri outPutUri);
    public void choosePhoto(Uri outPutUri){
        getTakePhoto().onPickFromCapture(outPutUri);
    }

    public void choosePic(){
        getTakePhoto().onPickFromGallery();
    }

    @Override
    public void takeSuccess(TResult result) {
        Logger.d(new Gson().toJson(result));
        if(result.getImage().getFromType().name().equals("CAMERA")){
            String DOWNLOADPATH = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + "wristband" + File.separator + "head"
                    + File.separator+result.getImage().getOriginalPath()
                    .substring(result.getImage().getOriginalPath().lastIndexOf("/")+1);
            Logger.d(DOWNLOADPATH);
            headAndNameView.showHead(DOWNLOADPATH);
        }else{
            headAndNameView.showHead(result.getImage().getOriginalPath());
        }

    }

    @Override
    public void takeCancel() {
    }

    @Override
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }
}
