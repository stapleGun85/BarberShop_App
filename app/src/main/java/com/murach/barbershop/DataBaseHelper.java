package com.murach.barbershop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_FNAME = "USER_FNAME";
    public static final String COLUMN_USER_LNAME = "USER_LNAME";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PHONE = "USER_PHONE";
    public static final String COLUMN_USER_USERNAME = "USER_USERNAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_ID = "USER_ID";


    public static final String APPT_TABLE = "APPT_TABLE";
    public static final String COLUMN_APPT_ID = "APPT_ID";
    public static final String COLUMN_HAIRCUT = "HAIRCUT";
    public static final String COLUMN_STYLE = "STYLE";
    public static final String COLUMN_COLOR = "COLOR";
    public static final String COLUMN_SHAVE = "SHAVE";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_USER = "USER";

    public static final String createAppointmentTable = "CREATE TABLE " + APPT_TABLE + " (" + COLUMN_APPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_HAIRCUT + " TEXT, " + COLUMN_STYLE + " TEXT, " + COLUMN_COLOR + " TEXT, " + COLUMN_SHAVE + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_TIME + " TEXT, " + COLUMN_USER + " INTEGER, FOREIGN KEY("+ COLUMN_USER +") REFERENCES "+ USER_TABLE +" ("+COLUMN_USER_ID+"))";


    public static final String createUserTable = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_FNAME + " TEXT, " + COLUMN_USER_LNAME + " TEXT, " + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PHONE + " TEXT, " + COLUMN_USER_USERNAME + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT)";



    //Constructor
    public DataBaseHelper(Context context) {
        super(context, "BarberShop.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(createUserTable);
        db.execSQL("PRAGMA foreign_keys = ON;");
        db.execSQL(createAppointmentTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists " + USER_TABLE);
        db.execSQL("drop Table if exists " + APPT_TABLE);

        onCreate(db);
    }

    public boolean addNewUser(UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_FNAME, userModel.getfName());
        cv.put(COLUMN_USER_LNAME, userModel.getlName());
        cv.put(COLUMN_USER_EMAIL, userModel.getEmail());
        cv.put(COLUMN_USER_PHONE, userModel.getPhoneNumber());
        cv.put(COLUMN_USER_USERNAME, userModel.getUserName());
        cv.put(COLUMN_USER_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }


    //****************   NEW   ********************//
    public boolean addNewAppointment(AppointmentModel appointmentModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_HAIRCUT, appointmentModel.isHaircut());
        cv.put(COLUMN_STYLE, appointmentModel.isStyle());
        cv.put(COLUMN_COLOR, appointmentModel.isColor());
        cv.put(COLUMN_SHAVE, appointmentModel.isShave());
        cv.put(COLUMN_DATE, appointmentModel.getDate());
        cv.put(COLUMN_TIME, appointmentModel.getTime());
        cv.put(COLUMN_USER, MainActivity.userID);

        long insert = db.insert(APPT_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    //***************************************************

    public List<UserModel> getAllUsers() {

        List<UserModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {

            do {
                int userID = cursor.getInt(0);
                String userFName = cursor.getString(1);
                String userLName = cursor.getString(2);
                String userEmail = cursor.getString(3);
                String userPhoneNumber = cursor.getString(4);
                String userUsername = cursor.getString(5);
                String userPassword = cursor.getString(6);

                UserModel newUser = new UserModel(userID, userFName, userLName, userEmail, userPhoneNumber, userUsername, userPassword);
                returnList.add(newUser);

            } while (cursor.moveToNext());
        } else {
            //failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnList;
    }

//******************      NEW     ***************************************

    public List<AppointmentModel> getAllAppointments(){

        List<AppointmentModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + APPT_TABLE + " WHERE " + COLUMN_USER + " = " + MainActivity.userID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){

            do{
                int apptID = cursor.getInt(0);
                String haircut = cursor.getString(1);
                String style = cursor.getString(2);
                String color = cursor.getString(3);
                String shave = cursor.getString(4);
                String date = cursor.getString(5);
                String time = cursor.getString(6);


                AppointmentModel newAppointment = new AppointmentModel(apptID, haircut, style, color, shave, date, time);
                returnList.add(newAppointment);

            } while (cursor.moveToNext());
        }
        else{

        }

        cursor.close();
        db.close();

        return returnList;
    }

    //******************************************************************

    public boolean checkIfUsernameExists(String username) {

        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from USER_TABLE where USER_USERNAME = ?", new String[]{username});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkUsernameAndPassword(String username, String password) {

        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from USER_TABLE where USER_USERNAME = ? and USER_PASSWORD = ?", new String[]{username, Encryption(password)});

        if (cursor.getCount() > 0) {

            return true;

        } else {

            return false;
        }
    }

    public static String Encryption(String password){

        char[] chars = password.toCharArray();

        String newPassword = "";

        for(char c : chars) {
            c += 5;
            newPassword += c;
        }
        return newPassword;
    }
}
