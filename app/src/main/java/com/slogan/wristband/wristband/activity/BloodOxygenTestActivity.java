package com.slogan.wristband.wristband.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slogan.wristband.wristband.R;
import com.slogan.wristband.wristband.adapter.HeartRateAdapter;
import com.slogan.wristband.wristband.bean.ItemTestDataModel;
import com.slogan.wristband.wristband.widght.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BloodOxygenTestActivity extends AppCompatActivity {

    @BindView(R.id.cp_progress)
    CircularProgressBar cpProgress;
    @BindView(R.id.iv_anim)
    ImageView ivAnim;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_num)
    LinearLayout llNum;
    @BindView(R.id.tv_testing)
    TextView tvTesting;
    @BindView(R.id.tv_retry)
    TextView tvRetry;
    @BindView(R.id.rv)
    RecyclerView rv;

    private List<ItemTestDataModel> data = new ArrayList<>();
    private static final int UPDATE_PROGRESS = 1000;
    private int progress;
    private HeartRateAdapter adapter = new HeartRateAdapter(R.layout.item_blood_oxygen_test,data);
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_PROGRESS:
                    updateProgress();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_oxygen_test);
        ButterKnife.bind(this);
        initRV();
        startTest();
    }

    private void initRV() {
        data.add(new ItemTestDataModel("11月11日  上午10:54","112"));
        data.add(new ItemTestDataModel("11月11日  上午10:53","122"));
        data.add(new ItemTestDataModel("11月11日  上午10:52","132"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }

    @OnClick({R.id.iv_left, R.id.tv_retry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                onBackPressed();
                break;
            case R.id.tv_retry:
                startTest();
                break;
        }
    }

    private void startTest() {
        progress = 0;
        ivAnim.setVisibility(View.VISIBLE);
        llNum.setVisibility(View.GONE);
        tvTesting.setVisibility(View.VISIBLE);
        tvRetry.setVisibility(View.GONE);
        handler.sendEmptyMessage(UPDATE_PROGRESS);
    }

    private void updateProgress() {
        if (cpProgress==null){
            return;
        }
        progress+=10;
        if (progress<100){
            cpProgress.setProgress(progress);
            handler.sendEmptyMessageDelayed(UPDATE_PROGRESS,500);
        } else {
            cpProgress.setProgress(100);
            handler.removeMessages(UPDATE_PROGRESS);
            showResult();
        }
    }

    private void showResult() {
        ivAnim.setVisibility(View.GONE);
        llNum.setVisibility(View.VISIBLE);
        tvTesting.setVisibility(View.GONE);
        tvRetry.setVisibility(View.VISIBLE);

        tvNum.setText("99");
        data.add(0,new ItemTestDataModel("12月22日  上午10：00","99"));
        adapter.notifyDataSetChanged();
    }
}
