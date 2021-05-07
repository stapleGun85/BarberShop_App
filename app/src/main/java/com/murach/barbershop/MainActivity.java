package com.murach.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.murach.barbershop.DataBaseHelper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView goToRegisterTextView;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView errorMessage;

    public static int userID;
    public static String userFName;
    public static String userLName;

    DataBaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        errorMessage = (TextView) findViewById(R.id.errorMessage);

        myDB = new DataBaseHelper(this);


        addListenerOnButton();
        addListenerForRegistration();
    }

    public void addListenerOnButton() {

        // set listener for login button
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();


            if(username.equals("") || password.equals("")){
                Toast.makeText(MainActivity.this, "Please Fill In All Fields", Toast.LENGTH_SHORT).show();
            }
            else{
                Boolean loginCheckResult = myDB.checkUsernameAndPassword(username,password);
                if(loginCheckResult == true){
                    errorMessage.setText("");

                    Cursor cursor = myDB.getReadableDatabase().query("USER_TABLE", null, "USER_USERNAME = ? and USER_PASSWORD = ?", new String[]{username, myDB.Encryption(password)}, null,null,null);
                    while (cursor.moveToNext())
                    {
                        userID = cursor.getInt(0);
                        userFName = "user first name: "+cursor.getString(1);
                        userLName = "user last name: "+cursor.getString(2);
                    }

                    openLandingPage();
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    errorMessage.setText("Error with username or password.");

                }
            }

            }
        });
    }

    public void addListenerForRegistration() {
        // set listener for registration text view
        goToRegisterTextView = (TextView) findViewById(R.id.goToRegisterTextView);
        goToRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openRegistration();
            }
        });
    }

    // continues to landing page
    public void openLandingPage() {
        Intent intent = new Intent(this, LandingPageActivity.class);
        startActivity(intent);
    }

    // continues to registration page
    public void openRegistration() {


        Intent intent = new Intent(this, UserRegistration.class);
        startActivity(intent);
    }

    // continues to login page
    public void openLoginPage() {


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

