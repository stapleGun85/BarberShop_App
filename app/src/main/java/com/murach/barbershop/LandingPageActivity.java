package com.murach.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {

    private Button makeApptBtn;
    private Button viewApptBtn;
    private Button logOutApptBtn;
    private Button settingAppBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        addListenerOnMakeApptBtn();
        addListenerOnViewApptBtn();
        addListenerOnSettingBtn(); //go to setting page
        addListenerOnLogOutButton(); //log out and back to log in page
    }

    public void addListenerOnMakeApptBtn() {
        makeApptBtn = (Button) findViewById(R.id.makeApptBtn);
        makeApptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
    }

    public void addListenerOnViewApptBtn() {
        viewApptBtn = (Button) findViewById(R.id.viewApptBtn);
        viewApptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAppts();
            }
        });
    }

    public void addListenerOnSettingBtn() {
        settingAppBtn = (Button) findViewById(R.id.SettingBtn);
        settingAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingPage();
            }
        });
    }

    public void addListenerOnLogOutButton() {
        logOutApptBtn = (Button) findViewById(R.id.LogOutBtn);
        logOutApptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    // continues to home page
    public void openHome() {
        Intent intent = new Intent(this, UserHomeActivity.class);
        startActivity(intent);
    }

    // continues to appointments page
    public void openAppts() {
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    // go to setting page
    public void openSettingPage() {
        Intent intent = new Intent(this, SettingsPageActivity.class);
        startActivity(intent);
    }

    // log out so return to log in page
    public void logOut() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
