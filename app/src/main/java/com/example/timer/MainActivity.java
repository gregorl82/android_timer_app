package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long TIME_IN_MILLISECONDS = 600000;

    private CountDownTimer mCountDownTimer;
    private ImageButton mStartPauseButton;
    private Button mResetButton;
    private TextView mClockText;
    private Drawable mStartIcon;
    private Drawable mPauseIcon;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = TIME_IN_MILLISECONDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClockText = findViewById(R.id.timerTextView);
        mStartPauseButton = findViewById(R.id.startButton);
        mResetButton = findViewById(R.id.resetButton);

        mStartIcon = getDrawable(R.drawable.ic_play_arrow_black_24dp);
        mPauseIcon = getDrawable(R.drawable.ic_pause_black_24dp);

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

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
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
                mStartPauseButton.setImageDrawable(mStartIcon);
                updateCountdownText();
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
        mStartPauseButton.setImageDrawable(mPauseIcon);
        mResetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mStartPauseButton.setImageDrawable(mStartIcon);
        mResetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        mTimeLeftInMillis = TIME_IN_MILLISECONDS;
        updateCountdownText();
    }

    private void updateCountdownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String remainingTime = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mClockText.setText(remainingTime);
    }
}
