package com.murach.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView dateConfirmation;
    private Button backBtn;
    private Button bookBtn;
    public static String haircutButton;
    public static String styleButton;
    public static String colorButton;
    public static String shaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_page);
        dateConfirmation = (TextView) findViewById(R.id.dateConfirmation);

        if (UserHomeActivity.isHaircutClicked == true) {
            haircutButton = "yes";
        }else haircutButton = "no";

        if (UserHomeActivity.isStyleClicked == true) {
            styleButton = "yes";
        }else styleButton = "no";

        if (UserHomeActivity.isColorClicked == true) {
            colorButton = "yes";
        }else colorButton = "no";

        if (UserHomeActivity.isShaveClicked == true) {
            shaveButton = "yes";
        }else shaveButton = "no";



        dateConfirmation.setText("Haircut: " + haircutButton + "\nStyle: " + styleButton + "\nColor: " + colorButton + "\nShave: " + shaveButton + "\nTime: " + CalendarActivity.time + "\nDate: " + CalendarActivity.date);




        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCalendar();
            }
        });

        bookBtn = (Button) findViewById(R.id.bookBtn);
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            AppointmentModel apptModel;

            try {
                apptModel = new AppointmentModel(-1, haircutButton.toString(), styleButton.toString(), colorButton.toString(), shaveButton.toString(), CalendarActivity.date.toString(), CalendarActivity.time.toString());
            }
            catch(Exception e){

                Toast.makeText(ConfirmationActivity.this, "Error Saving Appointment", Toast.LENGTH_SHORT).show();
                apptModel = new AppointmentModel(-1, "error", "error", "error", "error", "error", "error");
            }

                DataBaseHelper dbHelper = new DataBaseHelper(ConfirmationActivity.this);


                boolean success = dbHelper.addNewAppointment(apptModel);
                Toast.makeText(ConfirmationActivity.this, "Success111 = " + success, Toast.LENGTH_SHORT).show();

            GoHome();
            }
        });

    }

    public void OpenCalendar() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void GoHome() {
        Intent intent = new Intent(this, UserHomeActivity.class);
        startActivity(intent);
    }
}
