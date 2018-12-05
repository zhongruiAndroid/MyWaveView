package com.github.wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @createBy r-zhong
 * @time 2018-12-05 14:57
 */
public class WaveView extends View {

    private Bitmap bitmap;

    public void Log(String str){
        Log.i("===","==="+str);
    }
    public WaveView(Context context) {
        super(context);
        init(null);

    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        waveColor=Color.parseColor("#20D18C");

        if(attrs==null){
            return;
        }
    }


    private Paint wavePaint;

    private int waveColor;
    /*周期比例(宽度的百分之多少)*/
    private float period;
    private float periodScale=1f;

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
            float startY= (float) (90f*Math.sin((i*2f*Math.PI/period)));
            floats[i]=getHeight()-startY;
            canvas.drawLine(i,getHeight()-startY,i,getHeight(),wavePaint);
        }


        BitmapShader shader=new BitmapShader(bitmap,Shader.TileMode.REPEAT,Shader.TileMode.CLAMP);
        this.wavePaint.setShader(shader);
    }
    public Bitmap getBitmap(){
        return  bitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initPaint();

        period=getWidth()*periodScale;

        updateWave();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
Log("==="+(wavePaint.getShader()==null));
//        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,wavePaint);
        canvas.drawRect(new Rect(0,0,getWidth(),getHeight()),wavePaint);

        /*for (int i = 0; i < getWidth(); i++) {
            canvas.drawLine(i,floats[i]-100,i,getHeight(),wavePaint);
        }*/
    }
}
