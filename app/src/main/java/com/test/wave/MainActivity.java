package com.test.wave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.wave.WaveView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        waveView = findViewById(R.id.wv);
        waveView.post(new Runnable() {
            @Override
            public void run() {
                iv.setImageBitmap(waveView.getBitmap());
            }
        });
    }
}
