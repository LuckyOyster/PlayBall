package com.gabriel.playball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BallPerformance ballSettings=(BallPerformance)getIntent().getSerializableExtra("performance");


    }
}
