package com.remed1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyReports extends AppCompatActivity

{
    DatabaseHelper myDb;
    int pososto=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("REPORTS");
        actionBar.setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);
        TextView per = (TextView) findViewById(R.id.tv6);

        Cursor permed = myDb.getPercentageInfo();
        permed.moveToFirst();

        if(permed.getInt(3) != 0)
            pososto = (permed.getInt(2)*100) / permed.getInt(3);
        else
             pososto =0;

        per.setText(" " + String.valueOf(pososto) + "%");

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

    ///////////////////////////////
    public void onButton6_1Click(View view) {

        myDb.Missed_Pills(0, 0);
        myDb.Receive_Pills(0, 0);
        MainActivity.totalpills =0;
        MainActivity.recievepill=0;
        MainActivity.missedpill=0;

        finish();
        startActivity(getIntent());
    }
}
