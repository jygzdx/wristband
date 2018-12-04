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
import com.slogan.wristband.wristband.utils.DisplayUtils;
import com.slogan.wristband.wristband.utils.ImageUtil;
import com.slogan.wristband.wristband.widght.ChangeNicknamePop;
import com.slogan.wristband.wristband.widght.ChoosePicPop;
import com.slogan.wristband.wristband.widght.ChooseSexPop;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.util.ConvertUtils;

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
    private ChooseSexPop sexPop;
    private DatePicker picker;
    private DoublePicker weightPicker;
    private DoublePicker heightPicker;

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
        R.id.ll_head_title,R.id.iv_left
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_head_title:
                showAddPicPop();
                break;
            case R.id.ll_nickname:
                showNickNamePop();
                break;
            case R.id.ll_sex:
                showSexPop();
                break;
            case R.id.ll_birthday:
                showBirthdayDialog();
                break;
            case R.id.ll_height:
                showHeightDialog();
                break;
            case R.id.ll_weight:
                showWeightDialog();
                break;
        }
    }

    private void showBirthdayDialog(){
        if(picker == null){
            picker = new DatePicker(this);
            Calendar calendar = Calendar.getInstance();
            int start_year = calendar.get(Calendar.YEAR) - 100;
            int end_year = calendar.get(Calendar.YEAR);
            picker.setCanceledOnTouchOutside(true);
            picker.setUseWeight(true);
            picker.setTopPadding(30);
            picker.setRangeEnd(end_year, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            picker.setRangeStart(start_year, 1, 1);
            picker.setSelectedItem(1990, 6, 15);
            picker.setResetWhileWheel(false);
            picker.setCycleDisable(false);
            picker.setTopLineColor(getResources().getColor(R.color.line_bg));
            picker.setCancelTextColor(getResources().getColor(R.color.text_666));
            picker.setSubmitTextColor(getResources().getColor(R.color.main_color));
            picker.setTopHeight(55);
            picker.setContentPadding(40,20);
            picker.setDividerColor(getResources().getColor(R.color.main_color));
        }


        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                tvBirthday.setText(year+"年"+month+"月"+day+"日");
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText("");
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText("");
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText("");
            }
        });
        picker.show();
    }

    private void showHeightDialog(){
        final ArrayList<String> firstData = new ArrayList<>();
        for (int i = 140; i < 200; i++) {
            firstData.add(i+"");
        }
        final ArrayList<String> secondData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            secondData.add(i+"");
        }
        if(heightPicker == null){
            heightPicker = new DoublePicker(this,firstData,secondData);
            heightPicker.setCanceledOnTouchOutside(true);
            heightPicker.setSelectedIndex(0,0);
            heightPicker.setFirstLabel("", null);
            heightPicker.setSecondLabel("点", "厘米");
            heightPicker.setUseWeight(true);
            heightPicker.setTopPadding(30);
            heightPicker.setCycleDisable(false);
            heightPicker.setTopLineColor(getResources().getColor(R.color.line_bg));
            heightPicker.setCancelTextColor(getResources().getColor(R.color.text_666));
            heightPicker.setSubmitTextColor(getResources().getColor(R.color.main_color));
            heightPicker.setTopHeight(55);
            heightPicker.setContentPadding(40,20);
            heightPicker.setDividerColor(getResources().getColor(R.color.main_color));
        }


        heightPicker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                tvHeight.setText(firstData.get(selectedFirstIndex)+"."+secondData.get(selectedSecondIndex)+"cm");
            }
        });
        heightPicker.show();
    }

    private void showWeightDialog(){
        final ArrayList<String> firstData = new ArrayList<>();
        for (int i = 30; i < 100; i++) {
            firstData.add(i+"");
        }
        final ArrayList<String> secondData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            secondData.add(i+"");
        }
        if(weightPicker == null){
            weightPicker = new DoublePicker(this,firstData,secondData);
            weightPicker.setCanceledOnTouchOutside(true);
            weightPicker.setUseWeight(true);
            weightPicker.setTopPadding(30);
            weightPicker.setCycleDisable(false);
            weightPicker.setTopLineColor(getResources().getColor(R.color.line_bg));
            weightPicker.setCancelTextColor(getResources().getColor(R.color.text_666));
            weightPicker.setSubmitTextColor(getResources().getColor(R.color.main_color));
            weightPicker.setTopHeight(55);
            weightPicker.setContentPadding(40,20);
            weightPicker.setDividerColor(getResources().getColor(R.color.main_color));
            weightPicker.setSelectedIndex(20, 0);
            weightPicker.setFirstLabel(null, null);
            weightPicker.setSecondLabel("点", "千克");
        }


        weightPicker.setOnPickListener(new DoublePicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex) {
                tvWeight.setText(firstData.get(selectedFirstIndex)+"."+secondData.get(selectedSecondIndex)+"千克");
            }
        });
        weightPicker.show();
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

    private void showSexPop(){
        if(sexPop == null){
            sexPop = new ChooseSexPop(this);
        }
        sexPop.showDialog();
        sexPop.setOnClickListener(new ChooseSexPop.OnClickListener() {
            @Override
            public void onSex(int sex) {
                if(sex == 0){
                    tvSex.setText("男");
                }else{
                    tvSex.setText("女");
                }
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
