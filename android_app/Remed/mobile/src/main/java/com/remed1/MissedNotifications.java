package com.remed1;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ΜΗΤΣΟΣ on 29/10/2015.
 */
public class MissedNotifications extends AppCompatActivity {

    DatabaseHelper myDb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_notifications);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PILL NOT TAKEN");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ////////////////////////////////////////////////
        myDb = new DatabaseHelper(this);

        ImageView iv2= (ImageView)findViewById(R.id.imv2);
        iv2.setImageResource(R.mipmap.ic_fail);

        MainActivity.missedpill =  MainActivity.missedpill +1;
        MainActivity.totalpills = MainActivity.totalpills + 1;

        myDb.Missed_Pills(MainActivity.missedpill, MainActivity.totalpills);



        saveMissedPills();


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(getIntent().getExtras().getInt("Number_of_intent"));



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

    /////////////////////////////////////////////

    public void saveMissedPills(){

        boolean isInserted = myDb.insertMissedPills(getIntent().getExtras().getString("name"),
                getIntent().getExtras().getString("time"),
                getIntent().getExtras().getString("missedid")

        );
        if(isInserted){
            Toast.makeText(MissedNotifications.this, "DATA FOR MISSED PILL INSERTED", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MissedNotifications.this, "DATA FOR MISSED PILL NOT INSERTED", Toast.LENGTH_LONG).show();

        }


    }


}
