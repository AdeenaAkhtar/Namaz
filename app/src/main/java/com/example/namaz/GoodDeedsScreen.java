package com.example.namaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class GoodDeedsScreen extends AppCompatActivity {
    FloatingActionButton btnAddGoodDeed;
    TextView txtGoodDeed;
    EditText edtGoodDeed;
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_deeds_screen);
        btnAddGoodDeed = findViewById(R.id.btnAddGoodDeed);
        edtGoodDeed = findViewById(R.id.edtGoodDeed);
        LinearLayout linearLayout =  findViewById(R.id.layoutGoodDeeds);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedpreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String goodDeed = entry.getValue().toString();
            txtGoodDeed = new TextView(getApplicationContext());
            txtGoodDeed.setText(goodDeed);
            txtGoodDeed.setId(count);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(40,20,40,20);
            txtGoodDeed.setLayoutParams(params);
            txtGoodDeed.setBackground(getDrawable(R.drawable.back));
            txtGoodDeed.setTextSize(25);
            txtGoodDeed.setTextColor(getResources().getColor(R.color.purple_700));
            txtGoodDeed.setPadding(40,20,40, 20);
            txtGoodDeed.setGravity(Gravity.LEFT | Gravity.CENTER);
            linearLayout.addView(txtGoodDeed);
            count= Integer.parseInt(entry.getKey())+1;
        }
        btnAddGoodDeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtGoodDeed.getText().toString() != null)
                {
                String goodDeed = "Good Deed "+count+": "+edtGoodDeed.getText().toString();
                txtGoodDeed = new TextView(getApplicationContext());
                txtGoodDeed.setText(goodDeed);
                txtGoodDeed.setId(count);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(40,20,40,20);
                txtGoodDeed.setLayoutParams(params);
                txtGoodDeed.setBackground(getDrawable(R.drawable.back));
                txtGoodDeed.setTextSize(25);
                txtGoodDeed.setTextColor(getResources().getColor(R.color.purple_700));
                txtGoodDeed.setPadding(40,20,40, 20);
                txtGoodDeed.setGravity(Gravity.LEFT | Gravity.CENTER);
                linearLayout.addView(txtGoodDeed);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(String.valueOf(count), goodDeed);
                editor.commit();
                count++;
                edtGoodDeed.setText("");
                }
            }
        });
    }
}