package com.remed1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyMissedmed extends AppCompatActivity

{
      DatabaseHelper myDb;
     Cursor c2;
    public static Cursor del;
       ArrayAdapter<String> listAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missedmed);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MISSED MED");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ////////////////////////////////////////////////


        myDb = new DatabaseHelper(this);
        c2 = myDb.getmissedpills();

        final ListView missedpills = (ListView) findViewById(R.id.lv2);
        ArrayList<String> mArrayList = new ArrayList<>();
        for (c2.moveToFirst(); !c2.isAfterLast(); c2.moveToNext()) {
            mArrayList.add(c2.getString(c2.getColumnIndex("Id")) + " " + c2.getString(c2.getColumnIndex("Name_Missed_Pill")) + " " + c2.getString(c2.getColumnIndex("Date_Time_Missed_Pill")));
        }
        listAdapter2 = new ArrayAdapter<String>(MyMissedmed.this,
                android.R.layout.simple_list_item_1, mArrayList);
        missedpills.setAdapter(listAdapter2);
        c2.close();

        missedpills.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                del = myDb.getmissedid();
                del.moveToPosition(position);
                Cursor pillid = myDb.getmissedpillid();
                pillid.moveToPosition(position);
                String row = pillid.getString(pillid.getColumnIndex("Id_Missed_Pill"));

                Cursor display = myDb.setCursorToRow(row);
                display.moveToFirst();

                StringBuffer mpills_data = new StringBuffer();

                mpills_data.append("Id :" + display.getString(0) + "\n");
                mpills_data.append("Pill_Name :" + display.getString(1) + "\n");
                mpills_data.append("Starting_Date :" + display.getString(2) + "\n");
                mpills_data.append("Starting_Time :" + display.getString(3) + "\n");
               mpills_data.append("Schedule :" + display.getString(4) + "\n");
                mpills_data.append("Shape_Color :" + display.getString(5) + "\n");
                mpills_data.append("Dosage :" + display.getString(6) + "\n");
                mpills_data.append("Instructions :" + display.getString(7) + "\n\n");

                showMissedData("MISSED PILLS INFO", mpills_data.toString());

            }


        });
    }
    public   void   showMissedData(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Delete",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                deleteSingleMissedPill(del.getString(0));
                finish();
                startActivity(getIntent());
            }
        });
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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


    //////////////////////////////////////////
    public void onButton4_1Click(View v) {
        if (v.getId() == R.id.b4_1) {

            deleteMissedPills();
            finish();
            startActivity(getIntent());
        }
    }

    public  void deleteMissedPills()
    {
        myDb.deleteAllMissedPillsData();
        Toast.makeText(MyMissedmed.this, "ALL MISSED PILLS DELETED", Toast.LENGTH_LONG).show();

    }

    public  void deleteSingleMissedPill(String id)
    {
        myDb.removeSingleMissedPill(id);
        Toast.makeText(MyMissedmed.this, "SINGLE MISSED PILL DELETED", Toast.LENGTH_LONG).show();

    }

}
