package com.example.namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class PrayerTimingsScreen extends AppCompatActivity {
    private RequestQueue queue;
    private TextView txtFajr, txtDhuhr, txtAsr, txtMaghrib, txtIsha;
    Date date;
    Calendar alarm, now;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_timings_screen);
        txtFajr = findViewById(R.id.txtFajr);
        txtDhuhr = findViewById(R.id.txtDhuhr);
        txtAsr = findViewById(R.id.txtAsr);
        txtMaghrib = findViewById(R.id.txtMaghrib);
        txtIsha = findViewById(R.id.txtIsha);
        queue = Volley.newRequestQueue(this);
        String url = "https://api.aladhan.com/v1/timingsByCity?city=Karachi&country=Pakistan&method=1https://api.aladhan.com/v1/timingsByCity?city=Karachi&country=Pakistan&method=1";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("error", "Some error occurred!!" + volleyError);
            }
        });
        queue.add(request);
    }

    private void parseJsonData(String string) {
        try {
            JSONObject obj1 = new JSONObject(string);
            String str1 = obj1.getString("data");
            JSONObject obj2 = new JSONObject(str1);
            String str2 = obj2.getString("timings");
            JSONObject obj3 = new JSONObject(str2);
            String fajr = obj3.getString("Fajr");
            String dhuhr = obj3.getString("Dhuhr");
            String asr = obj3.getString("Asr");
            String maghrib = obj3.getString("Maghrib");
            String isha = obj3.getString("Isha");
            txtFajr.setText("FAJR: " + fajr);
            String[] fajrTime = fajr.split(":");
            setAlarm(Integer.parseInt(fajrTime[0]), Integer.parseInt(fajrTime[0]));
            txtDhuhr.setText("DHUHR: " + dhuhr);
            String[] dhuhrTime = dhuhr.split(":");
            setAlarm(Integer.parseInt(dhuhrTime[0]), Integer.parseInt(dhuhrTime[0]));
            txtAsr.setText("ASR: " + asr);
            String[] asrTime = asr.split(":");
            setAlarm(Integer.parseInt(asrTime[0]), Integer.parseInt(asrTime[0]));
            txtMaghrib.setText("MAGHRIB: " + maghrib);
            String[] maghribTime = maghrib.split(":");
            setAlarm(Integer.parseInt(maghribTime[0]), Integer.parseInt(maghribTime[0]));
            txtIsha.setText("ISHA: " + isha);
            String[] ishaTime = isha.split(":");
            setAlarm(Integer.parseInt(ishaTime[0]), Integer.parseInt(ishaTime[0]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setAlarm(int hour, int minute) {
        date = new Date();
        alarm = Calendar.getInstance();
        now = Calendar.getInstance();
        now.setTime(date);
        alarm.setTime(date);
        alarm.set(Calendar.HOUR_OF_DAY, hour);
        alarm.set(Calendar.MINUTE, minute);
        alarm.set(Calendar.SECOND, 0);
        if (alarm.before(now)) {
            alarm.add(Calendar.DATE, 1);
        }
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0010000, intent, 0);
        alarmManager.set(AlarmManager.RTC, alarm.getTimeInMillis(), pendingIntent);
    }

    public void stopAlarm(View view) {
        AlarmReceiver.mediaPlayer.stop();
    }
}

