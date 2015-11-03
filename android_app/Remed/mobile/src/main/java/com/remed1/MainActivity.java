package com.remed1;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity

{

    public static int totalpills;
    public static int recievepill;
    public static int missedpill;

    DatabaseHelper myDb;
    Cursor c1;
    ArrayAdapter<String> listAdapter1;
    Cursor per_inf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////////////////////

        myDb = new DatabaseHelper(this);

        if(myDb.PercentageIsEmpty() == 0) {

            myDb.insertInitialNumbersPills();
            totalpills =0;
            recievepill=0;
            missedpill=0;
        }
        else {

            per_inf = myDb.getPercentageInfo();
            per_inf.moveToFirst();
            totalpills = per_inf.getInt(3);
            recievepill = per_inf.getInt(1);
            missedpill = per_inf.getInt(2);

        }


        c1 = myDb.getPillsInfo();
        final ListView mypills = (ListView) findViewById(R.id.lv);
        ArrayList<String> mArrayList = new ArrayList<>();
        for(c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext()) {

            mArrayList.add(c1.getString(c1.getColumnIndex("Pill_Name"))
                    + " " + c1.getString(c1.getColumnIndex("Starting_Date"))
                    + " " + c1.getString(c1.getColumnIndex("Schedule"))
                    + " " + c1.getString(c1.getColumnIndex("Instructions")));
        }

        listAdapter1 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, mArrayList);
        mypills.setAdapter(listAdapter1);
        c1.close();

        mypills.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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



                   showData1("PILLS INFO", pills_data.toString());
               }


        });


    }


    public   void   showData1(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_profile : case R.id.user_profile :
                Intent profile = new Intent(MainActivity.this, MyProfile.class);
                startActivity(profile);
                break;
            case R.id.action_pillbox:
                Intent pillbox = new Intent(MainActivity.this, MyPillbox.class);
                startActivity(pillbox);
                break;
            case R.id.action_medications :
                Intent medications = new Intent(MainActivity.this, MyMedications.class);
                startActivity(medications);
                break;
            case R.id.action_missed_med :  case R.id.mis_med :
                Intent missedmed1 = new Intent(MainActivity.this, MyMissedmed.class);
                startActivity(missedmed1);
                break;
            case R.id.action_reports :
                Intent reports = new Intent(MainActivity.this, MyReports.class);
                startActivity(reports);
                break;
            case R.id.action_settings :
                Intent settings = new Intent(MainActivity.this, MySettings.class);
                startActivity(settings);
                break;
            case R.id.action_help :
                Intent help = new Intent(MainActivity.this, MyHelp.class);
                startActivity(help);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    public void onmain_buttonClick(View v) {
        if (v.getId() == R.id.main_buttton) {
            Intent actions = new Intent(MainActivity.this, MyMedications.class);
            startActivity(actions);
        }
    }

}



