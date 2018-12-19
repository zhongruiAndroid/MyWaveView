package com.github.wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @createBy r-zhong
 * @time 2018-12-05 14:57
 */
public class WaveView2 extends View {


    private Bitmap bitmap;

    public void Log(String str){
        Log.i("===","==="+str);
    }
    public WaveView2(Context context) {
        super(context);
        init(null);

    }

    public WaveView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public WaveView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        waveColor=Color.parseColor("#20D18C");
        amplitude=dip2px(getContext(),30);

        if(attrs==null){
            return;
        }
    }


    private Paint wavePaint;

    private int waveColor;
    /*波长*/
    private float waveLength;
    /*波长比例(view width)*/
    private float waveScale=0.7f;
    /*振幅*/
    private float amplitude;
    /*进度*/
    private float progress=30;
    /*总进度*/
    private float max=100;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }

    private void initPaint() {
        wavePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        wavePaint.setStrokeWidth(1);
//        wavePaint.setColor(waveColor);

//        this.wavePaint.setShader(null);
    }
    float[]floats;

    private void updateWave(){
        bitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);

        Paint wavePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        wavePaint.setStrokeWidth(1);
        wavePaint.setColor(waveColor);
        floats=new float[getWidth()];
        for (int i = 0; i < getWidth(); i++) {
            float startY= (float) (amplitude*Math.sin((i*2f*Math.PI/ waveLength)));
            floats[i]=getHeight()-startY;
            canvas.drawLine(i,getHeight()-startY,i,getHeight(),wavePaint);
        }


        BitmapShader shader=new BitmapShader(bitmap,Shader.TileMode.REPEAT,Shader.TileMode.CLAMP);
        this.wavePaint.setShader(shader);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initPaint();

        waveLength =getWidth()*waveScale;

        updateWave();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,wavePaint);

    }


    private float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return   (pxValue / scale + 0.5f);
    }

    private float dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }
}
