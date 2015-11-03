package com.remed1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyProfile extends AppCompatActivity {

    public static TextView tv;
    DatabaseHelper myDb;
    public static String username = " ";
    public static String usergender = " ";
    public static int gender = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PROFILE");
        actionBar.setDisplayHomeAsUpEnabled(true);
        myDb = new DatabaseHelper(this);
        tv = (TextView)findViewById(R.id.tv);

        MyProfile.username = myDb.getUserName();
        if(MyProfile.username.equals(""))
            MyProfile.usergender = "Mr / Mrs";
        MyProfile.gender = myDb.getUserGender();
        if(MyProfile.gender == 1)
        { MyProfile.usergender = "Mr";}
        else if(MyProfile.gender ==2)
        { MyProfile.usergender = "Mrs";}
        else
        { MyProfile.usergender = "Mr / Mrs";}
        tv.setText("WELCOME " + MyProfile.usergender + "  " + MyProfile.username);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onb1_1Click(View v) {
        if (v.getId() == R.id.b1_1) {
            Intent userprofile = new Intent(MyProfile.this, MyMakeUserProfile.class);
            startActivity(userprofile);

        }
    }

    public void onb1_2Click(View v) {
        if (v.getId() == R.id.b1_2) {
            Cursor display = myDb.getUserInfo();
            display.moveToFirst();
            if(display .getCount()==0) {
                Toast.makeText(MyProfile.this,"NO DATA FOUND" , Toast.LENGTH_LONG).show();
            }
            else {
                StringBuffer user_info = new StringBuffer();
                user_info.append("Name :" + display.getString(0) + "\n");
                user_info.append("Surname :" + display.getString(1) + "\n");
                user_info.append("Age :" + display.getString(2) + "\n");
                user_info.append("Gender :" + display.getString(3) + "\n");
                showUserInfo("USER INFO", user_info.toString());
            }

        }
    }




public  void   showUserInfo(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
        }

}
