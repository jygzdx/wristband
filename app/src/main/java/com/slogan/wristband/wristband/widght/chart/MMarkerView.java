package com.slogan.wristband.wristband.widght.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.slogan.wristband.wristband.R;

/**
 * Created by sll on 2018/11/28.
 */
public class MMarkerView extends MarkerView {

    private TextView tvValue;
    private TextView tvPoint;
    private View root;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvValue = findViewById(R.id.tv_value);
        tvPoint = findViewById(R.id.tv_point);
        root = findViewById(R.id.ll_root);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        tvValue.setText(e.getY()+"");
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        super.draw(canvas, posX-root.getWidth()/2, posY-root.getHeight()+tvPoint.getHeight()/2);
    }
}
