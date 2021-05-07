package com.murach.barbershop;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView calendarView;
    private TextView dateTextView;
    private TextView timeTextView;
    private Button nextBtn;
    public static String date;
    public static String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);

        // sets color of Month text
        ViewGroup vg = (ViewGroup) calendarView.getChildAt(0);
        View child = vg.getChildAt(0);
        if(child instanceof TextView) {
            ((TextView)child).setTextColor(getResources().getColor(R.color.orange));
        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = (i1 + 1) + "/" + i2 + "/" + i;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy: " + date);

                if (date != null) {
                    dateTextView.setText(date);
                }

            }
        });

        nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfirmationPage();
            }
        });
    }

    public void buttonClicked(View v) {
        int id = v.getId();
        switch (id){
            case R.id.time1:
                time = "8:00AM";
//                Toast.makeText(this,"8:00AM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time2:
                time = "9:00AM";
//                Toast.makeText(this,"9:00AM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time3:
                time = "10:00AM";
//                Toast.makeText(this,"10:00AM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time4:
                time = "11:00AM";
//                Toast.makeText(this,"11:00AM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time5:
                time = "1:00PM";
//                Toast.makeText(this,"1:00PM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time6:
                time = "2:00PM";
//                Toast.makeText(this,"2:00PM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time7:
                time = "3:00PM";
//                Toast.makeText(this,"3:00PM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time8:
                time = "4:00PM";
//                Toast.makeText(this,"4:00PM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time9:
                time = "5:00PM";
//                Toast.makeText(this,"5:00PM",Toast.LENGTH_SHORT).show();
                break;
            case R.id.time10:
                time = "6:00PM";
//                Toast.makeText(this,"6:00PM",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this,"No time selected",Toast.LENGTH_SHORT).show();
        }
        if (time != null) {
            timeTextView.setText(time);
        }
    }

    // continues to confirmation page
    public void openConfirmationPage() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        startActivity(intent);
    }
}
