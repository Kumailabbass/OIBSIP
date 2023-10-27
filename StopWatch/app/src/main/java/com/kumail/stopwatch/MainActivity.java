package com.kumail.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView stopwatchTextView;
    private Button startButton, stopButton, resetButton;
    private boolean running;
    private long elapsedMilliseconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatchTextView = findViewById(R.id.stopwatchTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void start() {
        if (!running) {
            running = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            handler.post(runTimer);
        }
    }

    private void stop() {
        running = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        handler.removeCallbacks(runTimer);
    }

    private void reset() {
        running = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        elapsedMilliseconds = 0;
        updateTextView(elapsedMilliseconds);
    }

    private void updateTextView(long milliseconds) {
        long seconds = milliseconds / 1000;
        long millis = milliseconds % 1000;
        stopwatchTextView.setText(String.format("%02d:%03d", seconds, millis));
    }

    private Runnable runTimer = new Runnable() {
        @Override
        public void run() {
            elapsedMilliseconds += 10; // Increment by 10 milliseconds (you can adjust the interval)
            updateTextView(elapsedMilliseconds);
            handler.postDelayed(this, 10); // Update every 10 milliseconds
        }
    };
}
