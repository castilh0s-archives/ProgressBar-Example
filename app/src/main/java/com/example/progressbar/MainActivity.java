package com.example.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBarLinear;
    ProgressBar progressBarCircle;
    Integer progressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressValue = 0;
        progressBarLinear = findViewById(R.id.progressBarLinear);
        progressBarCircle = findViewById(R.id.progressBarCircle);

        progressBarCircle.setVisibility(View.GONE);
    }

    public void onClick(View view) {
        progressBarCircle.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i = 0; i <= progressBarLinear.getMax(); i++) {
                    final Integer progress = i;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (progress == 100) {
                                progressBarCircle.setVisibility(View.GONE);
                            }
                            progressBarLinear.setProgress(progress);
                        }
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }).start();
    }
}
