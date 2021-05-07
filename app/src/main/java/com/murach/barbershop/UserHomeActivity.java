package com.murach.barbershop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomeActivity extends AppCompatActivity {

    private Button apptBtn;
    private Button haircutBtn;
    private Button styleBtn;
    private Button shaveBtn;
    private Button colorBtn;
    public static Boolean isHaircutClicked = false;
    public static Boolean isStyleClicked = false;
    public static Boolean isShaveClicked = false;
    public static Boolean isColorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sets layout to user home page
        setContentView(R.layout.user_home);
        apptBtn = (Button) findViewById(R.id.apptBtn);

        haircutBtn = (Button) findViewById(R.id.haircutBtn);
        haircutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isHaircutClicked = !isHaircutClicked;
                if(isHaircutClicked) {
                    haircutBtn.setBackgroundColor(getResources().getColor(R.color.orange));
                } else {
                    haircutBtn.setBackgroundColor(getResources().getColor(R.color.tan));
                }
            }
        });

        styleBtn = (Button) findViewById(R.id.styleBtn);
        styleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStyleClicked = !isStyleClicked;
                if(isStyleClicked) {
                    styleBtn.setBackgroundColor(getResources().getColor(R.color.orange));
                } else {
                    styleBtn.setBackgroundColor(getResources().getColor(R.color.tan));
                }
            }

        });

        shaveBtn = (Button) findViewById(R.id.shaveBtn);
        shaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShaveClicked = !isShaveClicked;
                if(isShaveClicked) {
                    shaveBtn.setBackgroundColor(getResources().getColor(R.color.orange));
                } else {
                    shaveBtn.setBackgroundColor(getResources().getColor(R.color.tan));
                }
            }
        });

        colorBtn = (Button) findViewById(R.id.colorBtn);
        colorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isColorClicked = !isColorClicked;
                if(isColorClicked) {
                    colorBtn.setBackgroundColor(getResources().getColor(R.color.orange));
                } else {
                    colorBtn.setBackgroundColor(getResources().getColor(R.color.tan));
                }
            }
        });

        apptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendar();
            }
        });
    }

    // continues to calendar
    public void openCalendar() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
