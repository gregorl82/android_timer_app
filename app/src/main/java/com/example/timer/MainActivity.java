package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long TIME_IN_MILLIS = 600000;

    private CountDownTimer mCountDownTimer;
    private Button mStartPauseButton;
    private TextView mClockText;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = TIME_IN_MILLIS;

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
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                mStartPauseButton.setText("start");
                updateCountdownText();
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
        mStartPauseButton.setText("pause");
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mStartPauseButton.setText("start");
    }

    private void updateCountdownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String remainingTime = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mClockText.setText(remainingTime);
    }
}
