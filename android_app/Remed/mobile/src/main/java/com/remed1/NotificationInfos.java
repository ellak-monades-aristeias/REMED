package com.remed1;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ΜΗΤΣΟΣ on 13/10/2015.
 */
public class NotificationInfos extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PILL TAKEN");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ////////////////////////////////////////////////
        myDb = new DatabaseHelper(this);

        ImageView iv1= (ImageView)findViewById(R.id.imv1);
        iv1.setImageResource(R.mipmap.ic_suc);



        MainActivity.recievepill =  MainActivity.recievepill +1;
        MainActivity.totalpills = MainActivity.totalpills + 1;

        myDb.Receive_Pills(MainActivity.recievepill, MainActivity.totalpills);

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


}
