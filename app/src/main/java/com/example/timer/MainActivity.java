package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer mCountDownTimer;
    private Button mStartPauseButton;
    private TextView mClockText;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClockText = findViewById(R.id.timerTextView);
        mStartPauseButton = findViewById(R.id.startButton);

        mStartPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning){
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
    }

    private void startTimer(){
    }

    private void pauseTimer(){}
}
