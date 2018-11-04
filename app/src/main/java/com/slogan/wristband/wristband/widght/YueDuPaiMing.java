package com.slogan.wristband.wristband.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.slogan.wristband.wristband.utils.DisplayUtils;

/**
 * Create by rssj on 2018-10-16
 */
public class YueDuPaiMing extends View {

    //分段颜色
    private static final int[] SECTION_COLORS = {0xffff6854, 0xff9847af, 0xff3D29FF};

    Context context;

    //控件的宽高
    private int w, h;

    //绘制的中点
    private int cx, cy;
    //圆弧半径
    private int r1, r2;

    private String ranking = "";
    private float percent = 100f;

    //外圈底色
    private Paint grayP1;
    //内圈底色
    private Paint grayP2;
    //外圈进度条
    private Paint cP1;
    //内圈进度条色
    private Paint cP2;
    //外圆点
    private Paint roundP1;
    //内圆点
    private Paint roundP2;
    //分割线
    private Paint line1P;
    //指示线
    private Paint line2P;
    //加号
    private Paint addP;
    //百分比
    private Paint percentP;
    //排名
    private Paint rankingP;

    public YueDuPaiMing(Context context) {
        this(context, null);
    }

    public YueDuPaiMing(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YueDuPaiMing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        grayP1 = new Paint();
        grayP1.setAntiAlias(true);
        grayP1.setColor(0xFFE4E4E4);
        grayP1.setStyle(Paint.Style.STROKE);
        grayP1.setStrokeWidth(DisplayUtils.dip2px(context, 1));

        grayP2 = new Paint();
        grayP2.setAntiAlias(true);
        grayP2.setColor(0xFFE2E2E2);
        grayP2.setStyle(Paint.Style.STROKE);
        grayP2.setStrokeWidth(DisplayUtils.dip2px(context, 3));


        cP1 = new Paint();
        cP1.setAntiAlias(true);
        cP1.setColor(0xFFFF8C7F);
        cP1.setStyle(Paint.Style.STROKE);
        cP1.setStrokeWidth(DisplayUtils.dip2px(context, 1));

        cP2 = new Paint();
        cP2.setAntiAlias(true);
        cP2.setColor(0xFFE2E2E2);
        cP2.setStyle(Paint.Style.STROKE);
        cP2.setStrokeWidth(DisplayUtils.dip2px(context, 3));

        addP = new Paint();
        addP.setAntiAlias(true);
        addP.setColor(0xffFEA800);
        addP.setStyle(Paint.Style.STROKE);
        addP.setStrokeWidth(DisplayUtils.dip2px(context, 1.2f));

        rankingP = new Paint();
        rankingP.setAntiAlias(true);
        rankingP.setColor(0xff999999);
        rankingP.setStyle(Paint.Style.STROKE);
        rankingP.setTextSize(DisplayUtils.sp2px(context, 12));
        rankingP.setTextAlign(Paint.Align.CENTER);

        percentP = new Paint();
        percentP.setAntiAlias(true);
        percentP.setColor(0xff0F0F0F);
        percentP.setStyle(Paint.Style.STROKE);
        percentP.setTextSize(DisplayUtils.sp2px(context, 30));
        percentP.setTextAlign(Paint.Align.CENTER);

        addP = new Paint();
        addP.setAntiAlias(true);
        addP.setColor(0xffFEA800);
        addP.setStyle(Paint.Style.STROKE);
        addP.setStrokeWidth(DisplayUtils.dip2px(context, 1));

        line1P = new Paint();
        line1P.setAntiAlias(true);
        line1P.setColor(0xFFFF8C7F);
        line1P.setStyle(Paint.Style.STROKE);
        line1P.setStrokeWidth(DisplayUtils.dip2px(context, 1));

        line2P = new Paint();
        line2P.setAntiAlias(true);
        line2P.setColor(0xFFBFBFBF);
        line2P.setStyle(Paint.Style.STROKE);
        line2P.setStrokeCap(Paint.Cap.ROUND); //键帽
        line2P.setStrokeWidth(DisplayUtils.dip2px(context, 3));

        roundP1 = new Paint();
        roundP1.setAntiAlias(true);
        roundP1.setColor(0x77FF0000);
        roundP1.setStyle(Paint.Style.STROKE);
        roundP1.setStrokeCap(Paint.Cap.ROUND);
        roundP1.setStrokeWidth(DisplayUtils.dip2px(context, 7));

        roundP2 = new Paint();
        roundP2.setAntiAlias(true);
        roundP2.setColor(0xFFFF0000);
        roundP2.setStyle(Paint.Style.STROKE);
        roundP2.setStrokeCap(Paint.Cap.ROUND);
        roundP2.setStrokeWidth(DisplayUtils.dip2px(context, 4));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        w = MeasureSpec.getSize(widthMeasureSpec);
        h = MeasureSpec.getSize(heightMeasureSpec);

        cx = w / 2;
        cy = h - 20;

        r1 = Math.min(cx - DisplayUtils.dip2px(context, 5), h - DisplayUtils.dip2px(context, 10));
        r2 = Math.min(cx - DisplayUtils.dip2px(context, 23), h - DisplayUtils.dip2px(context, 28));

        setMeasuredDimension(w, h);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(cx, cy);

        RectF oval1 = new RectF(-r1, -r1,
                r1, r1);
        canvas.drawArc(oval1, -180, 180, false, grayP1);
        canvas.drawArc(oval1, -180, 180 * percent / 100, false, cP1);

        float[] positions = new float[3];
        positions[0] = 0.5f;
        positions[1] = 0.75f;
        positions[2] = 1.0f;
        cP2.setShader(new SweepGradient(0, 0, SECTION_COLORS, positions));
        RectF oval2 = new RectF(-r2, -r2,
                r2, r2);
        canvas.drawArc(oval2, -180, 180, false, grayP2);
        canvas.drawArc(oval2, -180, 180 * percent / 100, false, cP2);

        canvas.drawLine(-DisplayUtils.dip2px(context, 4), 0, DisplayUtils.dip2px(context, 4), 0, addP);
        canvas.drawLine(0, -DisplayUtils.dip2px(context, 4), 0, DisplayUtils.dip2px(context, 4), addP);
        canvas.drawText(ranking, 0, -DisplayUtils.dip2px(context, 15), rankingP);
        canvas.drawText("" + percent + "%", 0, -DisplayUtils.dip2px(context, 35), percentP);
        canvas.save();

        for (int i = 0; i < 5; i++) {
            canvas.drawLine(-r2 + DisplayUtils.dip2px(context, 8), 0, -r2 + DisplayUtils.dip2px(context, 26), 0, line2P);
            canvas.rotate(45);
        }
        canvas.save();

        canvas.rotate(-225);//反旋转回初始状态
        canvas.rotate(180 * percent / 100);
        canvas.drawLine(-r1, 0, -r1 + DisplayUtils.dip2px(context, 30), 0, line1P);
        canvas.drawPoint(-r1, 0, roundP1);
        canvas.drawPoint(-r1, 0, roundP2);
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
