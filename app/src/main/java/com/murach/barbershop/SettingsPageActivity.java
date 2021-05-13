package com.murach.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsPageActivity extends AppCompatActivity {

    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);


        backBtn = (Button) findViewById(R.id.back_bt);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLandingPage();
            }
        });
    }

    // back to landingPage
    public void openLandingPage() {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }
}
