package com.murach.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.zip.DataFormatException;

public class UserRegistration extends AppCompatActivity {

    private TextView loginTextView;
    private TextView goToRegisterTextView;
    private Button registerButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText usernameEditText;
    private EditText passwordRegisterEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sets layout to user registration page
        setContentView(R.layout.register_home);

        registerButton = findViewById(R.id.registerButton);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        usernameEditText = findViewById(R.id.usernameRegisterEditText);
        passwordRegisterEditText = findViewById(R.id.passwordRegisterEditText);



        addListenerGoToLogin();

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                UserModel userModel;

                try{
                    userModel = new UserModel(-1, firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), emailEditText.getText().toString(), phoneEditText.getText().toString(), usernameEditText.getText().toString(), DataBaseHelper.Encryption(passwordRegisterEditText.getText().toString()));
                }
                catch(Exception e){
                    Toast.makeText(UserRegistration.this, "Error Creating Account", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "error", "error", "error", "error", "error", "error");

                }

                DataBaseHelper dbHelper = new DataBaseHelper(UserRegistration.this);

                boolean success = dbHelper.addNewUser(userModel);
                Toast.makeText(UserRegistration.this, "Success = " + success, Toast.LENGTH_SHORT).show();

                openLogin();
            }
        });

    }

    public void addListenerGoToLogin() {
        // set listener for registration text view
        loginTextView = (TextView) findViewById(R.id.goToLoginTextView);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    // continues to login page
    public void openLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
