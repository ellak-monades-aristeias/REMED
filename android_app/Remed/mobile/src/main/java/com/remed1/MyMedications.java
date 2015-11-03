package com.remed1;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyMedications extends AppCompatActivity

{
    public static Cursor find_id;
    public static int choise = 0;
    DatabaseHelper myDb;
    PopupWindow popupWindow;
   public static int pillid=0;
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
            if (display.getCount() == 0) {
                Toast.makeText(MyMedications.this, "NO DATA FOUND", Toast.LENGTH_LONG).show();
                return;
            } else {
                StringBuffer pills_data = new StringBuffer();
                while (display.moveToNext()) {
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


    //Deactivated Button
    public void onButton3_6Click(View view) {

        showPopupForDeactivate();
    }

    //Activated Button
    public void onButton3_5Click(View view) {

        showPopup();
    }


    public void showPopup() {


        LinearLayout llContainer = new LinearLayout(this);


        llContainer.setOrientation(LinearLayout.VERTICAL);


        LinearLayout llContainerInline = new LinearLayout(this);


        llContainerInline.setOrientation(LinearLayout.HORIZONTAL);


        final EditText etInput = new EditText(this);


        final TextView tvError = new TextView(this);


        Button bDone = new Button(this);
        Button bCancel = new Button(this);


        etInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tvError.setVisibility(View.GONE);
            }
        });


        etInput.setHint("Pill id:");


        etInput.setInputType(InputType.TYPE_CLASS_NUMBER);


        etInput.setGravity(Gravity.CENTER);


        tvError.setVisibility(View.GONE);

        bDone.setText("Done");
        bCancel.setText("Cancel");

        bDone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (etInput.getText().toString().equals("")) {
                    tvError.setText("Please enter a valid value id");
                    tvError.setVisibility(View.VISIBLE);
                    etInput.setText("");


                } else {
                    doneInput(etInput.getText().toString());
                    popupWindow.dismiss();
                }
            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    popupWindow.dismiss();
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.topMargin = 20;


        LinearLayout.LayoutParams layoutParamsForInlineContainer = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParamsForInlineContainer.topMargin = 30;


        LinearLayout.LayoutParams layoutParamsForInlineET = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        layoutParamsForInlineET.weight = 1;


        LinearLayout.LayoutParams layoutParamsForInlineButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParamsForInlineButton.weight = 0;


        llContainerInline.addView(etInput, layoutParamsForInlineET);


        llContainerInline.addView(bDone, layoutParamsForInlineButton);
        llContainerInline.addView(bCancel, layoutParamsForInlineButton);


        llContainer.addView(tvError, layoutParams);


        llContainer.addView(llContainerInline, layoutParamsForInlineContainer);


        llContainer.setGravity(Gravity.CENTER);


        llContainer.setBackgroundColor(0x95000000);


        popupWindow = new PopupWindow(llContainer,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        popupWindow.setFocusable(true);


        popupWindow.showAtLocation(llContainer, Gravity.CENTER, 0, 0);

    }

    public void doneInput(String input) {
        int bias = Integer.parseInt(input);



        find_id = myDb.setReminderToRow(input);
        find_id.moveToFirst();
       if (find_id.getCount() == 0) {
            Toast.makeText(MyMedications.this, "THERE IS NO PILL WITH ID: " + bias , Toast.LENGTH_LONG).show();
            return;
        } else {




            StringBuffer pl = new StringBuffer();

            pl.append("Id :" + find_id.getString(0) + "\n");
            pl.append("Pill_Name :" + find_id.getString(1) + "\n");
            pl.append("Starting_Date :" + find_id.getString(2) + "\n");
            pl.append("Starting_Time :" + find_id.getString(3) + "\n");
            pl.append("Schedule :" + find_id.getString(4) + "\n");
            pl.append("Shape_Color :" + find_id.getString(5) + "\n");
            pl.append("Dosage :" + find_id.getString(6) + "\n");
            pl.append("Instructions :" + find_id.getString(7) + "\n\n");


            showData12("SET REMINDER FOR THIS PILL", pl.toString());
        }
    }


    public void showData12(String title, String Message) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MyMedications.this, "THE REMINDER ACTIVATED", Toast.LENGTH_LONG).show();
                        setReminder(MyMedications.find_id);
                    }
                });
        builder1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MyMedications.this, "NO ACTION", Toast.LENGTH_LONG).show();

            }
        });
        builder1.setTitle(title);
        builder1.setMessage(Message);
        builder1.show();
    }

   public void setReminder (Cursor row) {
       Calendar cal = Calendar.getInstance();

    MyMedications.pillid = Integer.parseInt(row.getString(0));



       String delims = "[ ,:]+";
       String[] startTime = row.getString(3).split(delims);
       String[] startDate = row.getString(2).split(delims);
       String[] repeat = row.getString(4).split(delims);

        int month=0;
       switch(startDate[0]){
           case "Jan":
               month=0;
               break;
           case "Feb":
               month=1;
               break;
           case "Mar":
               month=2;
               break;
           case "Apr":
               month=3;
               break;
           case "May":
               month=4;
               break;
           case "Jun":
               month=5;
               break;
           case "Jul":
               month=6;
               break;
           case "Aug":
               month=7;
               break;
           case "Sep":
               month=8;
               break;
           case "Oct":
               month=9;
               break;
           case "Nov":
               month=10;
               break;
           case "Dec":
               month=11;
               break;
               default:
                   break;
       }

        int day = Integer.parseInt(startDate[1]);
       int year = Integer.parseInt(startDate[2]);
         int hour = Integer.parseInt(startTime[0]);
         int minute = Integer.parseInt(startTime[1]);
       int h = (24 / Integer.parseInt(repeat[0]));

       long time_repeating =  h *60 * 60 * 1000;



       cal.set(Calendar.HOUR_OF_DAY, hour);
       cal.set(Calendar.MINUTE, minute);
       cal.set(Calendar.SECOND, 0);
       cal.set(Calendar.MILLISECOND, 0);
       cal.set(Calendar.MONTH,month);
       cal.set(Calendar.YEAR, year);
       cal.set(Calendar.DAY_OF_MONTH, day);

       Intent intent = new Intent(this, AlertMsg.class);
       intent.putExtra("id",MyMedications.pillid);
       intent.putExtra("name",row.getString(1) );
       intent.putExtra("dosage",row.getString(6));
       intent.putExtra("instructions",row.getString(7));
       PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), MyMedications.pillid, intent, PendingIntent.FLAG_UPDATE_CURRENT);



       AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

       alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), time_repeating , pendingIntent);
   }


    public void showPopupForDeactivate() {


        LinearLayout llContainer = new LinearLayout(this);


        llContainer.setOrientation(LinearLayout.VERTICAL);


        LinearLayout llContainerInline = new LinearLayout(this);


        llContainerInline.setOrientation(LinearLayout.HORIZONTAL);


        final EditText etInput = new EditText(this);


        final TextView tvError = new TextView(this);


        Button bDone = new Button(this);
        Button bCancel = new Button(this);

        etInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tvError.setVisibility(View.GONE);
            }
        });


        etInput.setHint("Pill id:");


        etInput.setInputType(InputType.TYPE_CLASS_NUMBER);


        etInput.setGravity(Gravity.CENTER);


        tvError.setVisibility(View.GONE);

        bDone.setText("Done");
        bCancel.setText("Cancel");

        bDone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (etInput.getText().toString().equals("")) {
                    tvError.setText("Please enter a valid value id");
                    tvError.setVisibility(View.VISIBLE);
                    etInput.setText("");


                } else {

                    doneInput_deactivate(etInput.getText().toString());

                    popupWindow.dismiss();
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.topMargin = 20;


        LinearLayout.LayoutParams layoutParamsForInlineContainer = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParamsForInlineContainer.topMargin = 30;


        LinearLayout.LayoutParams layoutParamsForInlineET = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


        layoutParamsForInlineET.weight = 1;


        LinearLayout.LayoutParams layoutParamsForInlineButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParamsForInlineButton.weight = 0;


        llContainerInline.addView(etInput, layoutParamsForInlineET);


        llContainerInline.addView(bDone, layoutParamsForInlineButton);
        llContainerInline.addView(bCancel, layoutParamsForInlineButton);


        llContainer.addView(tvError, layoutParams);


        llContainer.addView(llContainerInline, layoutParamsForInlineContainer);


        llContainer.setGravity(Gravity.CENTER);


        llContainer.setBackgroundColor(0x95000000);


        popupWindow = new PopupWindow(llContainer,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        popupWindow.setFocusable(true);


        popupWindow.showAtLocation(llContainer, Gravity.CENTER, 0, 0);

    }


    public void doneInput_deactivate(String input) {
        int bias = Integer.parseInt(input);



        find_id = myDb.setReminderToRow(input);
        find_id.moveToFirst();
        if (find_id.getCount() == 0) {
            Toast.makeText(MyMedications.this, "THERE IS NO PILL WITH ID: " + bias , Toast.LENGTH_LONG).show();
            return;
        } else {




            StringBuffer pl = new StringBuffer();

            pl.append("Id :" + find_id.getString(0) + "\n");
            pl.append("Pill_Name :" + find_id.getString(1) + "\n");
            pl.append("Starting_Date :" + find_id.getString(2) + "\n");
            pl.append("Starting_Time :" + find_id.getString(3) + "\n");
            pl.append("Schedule :" + find_id.getString(4) + "\n");
            pl.append("Shape_Color :" + find_id.getString(5) + "\n");
            pl.append("Dosage :" + find_id.getString(6) + "\n");
            pl.append("Instructions :" + find_id.getString(7) + "\n\n");


            showData_deactivate("DEACTIVATE REMINDER FOR THIS PILL", pl.toString(), bias );
        }
    }


    public void showData_deactivate(String title, String Message, final int  id_d) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MyMedications.this, "THE RIMINDER DEACTIVATED", Toast.LENGTH_LONG).show();
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(id_d);

                Intent myIntent = new Intent(MyMedications.this, AlertMsg.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MyMedications.this, id_d,
                        myIntent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
        });
        builder1.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MyMedications.this, "NO ACTION", Toast.LENGTH_LONG).show();

            }
        });
        builder1.setTitle(title);
        builder1.setMessage(Message);
        builder1.show();
    }
















}