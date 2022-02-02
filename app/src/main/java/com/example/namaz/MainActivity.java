package com.example.namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnPrayerTimings , btnGoodDeeds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrayerTimings = findViewById(R.id.btnPrayerTimings);
        btnGoodDeeds = findViewById(R.id.btnGoodDeeds);
        btnPrayerTimings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrayerTimingsScreen.class);
                startActivity(intent);
            }
        });
        btnGoodDeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoodDeedsScreen.class);
                startActivity(intent);
            }
        });
    }

}