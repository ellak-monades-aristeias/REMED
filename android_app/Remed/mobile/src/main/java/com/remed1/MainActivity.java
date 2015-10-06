package com.remed1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater(); //from activity
        inflater.inflate(R.menu.my_menu, menu);


//It is important to return true to see the menu
        return true;
    }

  //  @Override
   // public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
  //      getMenuInflater().inflate(R.menu.menu_main, menu);
  //      return true;
  //  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //  if (id == R.id.action_settings) {
        //     return true;
        // }

        switch (id) {
            case R.id.action_profile :
             //   int tv =
             //   dothis(textView1);
             // TextView textView = new TextView(this);
             // textView.setText("Hey, one more TextView");
             //   Toast msgtoast1 = Toast.makeText(this.getBaseContext(), "1",
             //           Toast.LENGTH_LONG);
             //   msgtoast1.show();
                Intent profile = new Intent(MainActivity.this, MyProfile.class);
                startActivity(profile);
                break;

            case R.id.action_pillbox :
                Intent pillbox = new Intent(MainActivity.this, MyPillbox.class);
                startActivity(pillbox);
                break;
            case R.id.action_medications :
                Intent medications = new Intent(MainActivity.this, MyMedications.class);
                startActivity(medications);
                break;
            case R.id.action_missed_med :
                Intent missedmed = new Intent(MainActivity.this, MyMissedmed.class);
                startActivity(missedmed);
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

    /*private void dothis(){
        new AlertDialog.Builder(this)
                .setTitle("userprofile")
                .setMessage("geia")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }*/


   // public void dothis(View v)
   // {
   //     if(v.getId() == R.id.action_profile)
    //    {
    //        Intent i = new Intent(MainActivity.this, MyProfile.class);
    //        startActivity(i);


     //   }


  //  }


}



