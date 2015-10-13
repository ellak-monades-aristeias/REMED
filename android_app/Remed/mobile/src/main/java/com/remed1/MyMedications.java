package com.remed1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyMedications extends AppCompatActivity

{
public static int choise = 0;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MEDICATIONS");
        actionBar.setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent userprofile = new Intent(MyMedications.this, MainActivity.class);
                startActivity(userprofile);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onButton3_1Click(View v) {
        if (v.getId() == R.id.b3_1) {
            Intent addmed = new Intent(MyMedications.this, MyAddmed.class);
            startActivity(addmed);
            choise = 1;
        }
    }

    public void onButton3_3Click(View v) {
        if (v.getId() == R.id.b3_3) {
            Intent addmed = new Intent(MyMedications.this, MyAddmed.class);
            startActivity(addmed);
            choise = 2;
        }
    }

    public void onButton3_4Click(View v) {
        if (v.getId() == R.id.b3_4) {
            Intent addmed = new Intent(MyMedications.this, MyAddmed.class);
            startActivity(addmed);
            choise = 3;
        }
    }


    public void onButton3_2Click(View v) {
        if (v.getId() == R.id.b3_2) {
            Cursor display = myDb.viewMyMeds();
            if(display.getCount() == 0) {
                Toast.makeText(MyMedications.this, "NO DATA FOUND", Toast.LENGTH_LONG).show();
                return;
            }
             else
            {
                StringBuffer pills_data = new StringBuffer();
                while(display.moveToNext()){
                    pills_data.append("Id :" + display.getString(0) + "\n");
                    pills_data.append("Pill_Name :" + display.getString(1) + "\n");
                    pills_data.append("Starting_Date :" + display.getString(2) + "\n");
                    pills_data.append("Starting_Time :" + display.getString(3) + "\n");
                    pills_data.append("Schedule :" + display.getString(4) + "\n");
                    pills_data.append("Shape_Color :" + display.getString(5) + "\n");
                    pills_data.append("Dosage :" + display.getString(6) + "\n");
                    pills_data.append("Instructions :" + display.getString(7) + "\n\n");
                 }


                showData("PILLS INFO", pills_data.toString());

            }

        }

    }

    public   void   showData(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }




}
