package com.murach.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {

    ListView apptListView;
    public Button backToLandingBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments_page);
        apptListView = findViewById(R.id.apptListView);

        ArrayAdapter appointmentArrayAdapter;
        DataBaseHelper dbHelper;
        List<AppointmentModel> displayAppointments;

        dbHelper = new DataBaseHelper(AppointmentsActivity.this);
        displayAppointments = dbHelper.getAllAppointments();
        appointmentArrayAdapter = new ArrayAdapter<AppointmentModel>(AppointmentsActivity.this, android.R.layout.simple_list_item_1, displayAppointments);

        apptListView.setAdapter(appointmentArrayAdapter);

        backToLandingBtn = (Button) findViewById(R.id.backToLandingBtn);
        backToLandingBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GoToLanding();
            }
        });
    }

    public void GoToLanding() {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }
}