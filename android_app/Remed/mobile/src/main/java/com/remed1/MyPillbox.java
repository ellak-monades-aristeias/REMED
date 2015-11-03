package com.remed1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyPillbox extends AppCompatActivity

{
    DatabaseHelper myDb;
    Cursor c;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pillbox);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PILLBOX");
        actionBar.setDisplayHomeAsUpEnabled(true);
        myDb = new DatabaseHelper(this);
        c = myDb.getpillnames();
        ListView lvItems = (ListView) findViewById(R.id.lv1);
        ArrayList<String> mArrayList = new ArrayList<>();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            mArrayList.add(c.getString(c.getColumnIndex("Pill_Name"))+ " " + c.getString(c.getColumnIndex("Dosage")) );
        }

        listAdapter = new ArrayAdapter<String>(MyPillbox.this,
                android.R.layout.simple_list_item_1, mArrayList);
        lvItems.setAdapter(listAdapter);
        c.close();

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Cursor display = myDb.viewMyMeds();
                display.moveToPosition(position);

                StringBuffer pills_data = new StringBuffer();

                pills_data.append("Id :" + display.getString(0) + "\n");
                pills_data.append("Pill_Name :" + display.getString(1) + "\n");
                pills_data.append("Starting_Date :" + display.getString(2) + "\n");
                pills_data.append("Starting_Time :" + display.getString(3) + "\n");
                pills_data.append("Schedule :" + display.getString(4) + "\n");
                pills_data.append("Shape_Color :" + display.getString(5) + "\n");
                pills_data.append("Dosage :" + display.getString(6) + "\n");
                pills_data.append("Instructions :" + display.getString(7) + "\n\n");

                showData2("PILLS INFO", pills_data.toString());
            }


        });
    }


    public   void   showData2(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
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

}

