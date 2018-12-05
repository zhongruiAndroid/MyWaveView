package com.github.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @createBy r-zhong
 * @time 2018-12-05 14:57
 */
public class WaveView extends View {
    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private Paint wavePaint;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
