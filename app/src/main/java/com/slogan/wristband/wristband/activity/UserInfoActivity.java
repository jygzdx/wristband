package com.slogan.wristband.wristband.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.activity.base.BaseActivity;
import com.slogan.wristband.wristband.utils.ImageUtil;
import com.slogan.wristband.wristband.widght.ChangeNicknamePop;
import com.slogan.wristband.wristband.widght.ChoosePicPop;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

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
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.ll_nickname)
    LinearLayout llNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.ll_birthday)
    LinearLayout llBirthday;
    @BindView(R.id.tv_height)
    TextView tvHeight;
    @BindView(R.id.ll_height)
    LinearLayout llHeight;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.ll_weight)
    LinearLayout llWeight;
    private ChoosePicPop choosePicPop;
    private String DOWNLOADPATH;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private ChangeNicknamePop changeNickNamePop;

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
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        getTakePhoto().onCreate(savedInstanceState);
        initHandler();
        initWidget();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tvTitle.setText(R.string.title_user_info);
        ivRight.setImageDrawable(null);
    }

    @OnClick({R.id.ll_nickname, R.id.ll_sex, R.id.ll_birthday, R.id.ll_height, R.id.ll_weight,
        R.id.ll_head_title
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head_title:
                showAddPicPop();
                break;
            case R.id.ll_nickname:
                showNickNamePop();
                break;
            case R.id.ll_sex:
                break;
            case R.id.ll_birthday:
                break;
            case R.id.ll_height:
                break;
            case R.id.ll_weight:
                break;
        }
    }

    private void showNickNamePop() {
        if(changeNickNamePop == null){
           changeNickNamePop = new ChangeNicknamePop(this);
        }
        changeNickNamePop.showDialog();
        changeNickNamePop.setOnClickListener(new ChangeNicknamePop.OnClickListener() {
            @Override
            public void onEnsureClick(String text) {
                tvNickname.setText(text);
            }
        });
    }

    private void showAddPicPop() {
        if(choosePicPop == null){
            choosePicPop = new ChoosePicPop(this);
        }
        choosePicPop.showDialog();
        choosePicPop.setOnClickListener(new ChoosePicPop.OnClickListener() {
            @Override
            public void onCameraClick() {
                DOWNLOADPATH = Environment.getExternalStorageDirectory().getPath()
                        + File.separator + "wristband" + File.separator + "head"
                        + File.separator+System.currentTimeMillis()+".jpg";
                File file = new File(DOWNLOADPATH);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Uri imageUri = Uri.fromFile(file);
                choosePhoto(imageUri);
            }

            @Override
            public void onPicClick() {
                choosePic();
            }
        });
    }

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
            ImageUtil.setCircleImage(DOWNLOADPATH,ivHead);
        }else{
            ImageUtil.setCircleImage(result.getImage().getOriginalPath(),ivHead);
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
