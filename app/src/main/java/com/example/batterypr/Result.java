//Author:    Mordy dabah

package com.example.batterypr;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    private TextView tvState;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // take over
        tvState = findViewById(R.id.tvState);
        image = findViewById(R.id.ivState);


        int btr = 0;
        Bundle extra = getIntent().getExtras();
        if (!extra.isEmpty()) {
            btr = (int) extra.get("btr");
        }

        if (btr <= 29) {
            image.setImageResource(R.drawable.low);
            tvState.setText("Low");
            tvState.setTextColor(getResources().getColor(R.color.red));
            tvState.setBackgroundColor(getResources().getColor(R.color.green));
        } else if (btr <= 74) {
            image.setImageResource(R.drawable.medium);
            tvState.setText("Medium");
            tvState.setTextColor(getResources().getColor(R.color.green));
            tvState.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else {
            image.setImageResource(R.drawable.high);
            tvState.setText("High");
            tvState.setTextColor(getResources().getColor(R.color.blue));
            tvState.setBackgroundColor(getResources().getColor(R.color.grey));
        }

    }
}
