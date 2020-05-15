//Author:    Mordy dabah

package com.example.batterypr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BroadcastReceiver broadCastBattery;
    private Button btnShare;
    private EditText etName, etPhone;
    private TextView tvbattery;
    private int bt_value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);

        // take over
        btnShare = findViewById(R.id.btnShare);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPH);
        tvbattery = findViewById(R.id.tvBattery);

        // battery BrodcastRecevier

        broadCastBattery = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int battery = intent.getIntExtra("level", 0);
                Toast.makeText(MainActivity.this, "onReceive " + battery, Toast.LENGTH_LONG).show();
                bt_value = battery;
                tvbattery.setText(battery + " %");
            }
        };

        btnShare.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
        registerReceiver(broadCastBattery,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadCastBattery);
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    public void finish() {
        super.finish();
        Toast.makeText(this, "finish", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        if (v == btnShare) {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            int flag = 0;
            if (!name.matches("[A-Z][a-z]+")) // רק שם פרטי, בלי רווחים חייב להתחיל באות גדולה וכל שאר האותיות באות קטנה
            {
                etName.setError("Name must have A-Z no spaces and start with capital letter");
                flag = 1;
            }
            if (!phone.matches("05[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) { // מספר טלופן חוקי בישראל, חייב להתחיל מ05 להכיל 10 ספרות בדיוק בלי רווחים
                etPhone.setError("Phone must begin with 05 and be 10 chars long");
                flag = 1;
            }
            if (flag == 0) {
                Intent intent = new Intent(this, Result.class);
                intent.putExtra("btr", bt_value);
                startActivity(intent);
            }
        }
    }
}

