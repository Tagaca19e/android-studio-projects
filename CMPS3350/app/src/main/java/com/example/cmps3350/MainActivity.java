package com.example.cmps3350;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Demo inital app";
    public static float start = 0;
    public static float end = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout myLayout = (ConstraintLayout)
                            findViewById(R.id.main_layout);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventType = event.getActionMasked();
                int height = myLayout.getHeight() / 2;

                switch (eventType) {
                    case MotionEvent.ACTION_DOWN:
                        start = event.getY();
                    case MotionEvent.ACTION_UP:
                        end = event.getY();
                }
                float diff = end - start;
                if (diff >= height) {
                    System.exit(0);
                }
                return true;
            }
        });

        Button btn = (Button) findViewById(R.id.btnprintname);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "By Eidmone Tagaca",
                                                    Toast.LENGTH_SHORT).show();

                final TextView mTextView = (TextView) findViewById(R.id.name);
                mTextView.setText("CS 3350 - Eidmone ");

                final Runnable r = new Runnable() {
                    public void run() {
                        final TextView mTextView = (TextView)
                                                        findViewById(R.id.name);
                        mTextView.setText("Hello World!");
                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(r, 2000);
            }
        });
    }
}