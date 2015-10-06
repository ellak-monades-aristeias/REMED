package com.remed1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyMedications extends AppCompatActivity

{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MEDICATIONS");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onButton3_1Click(View v) {
        if (v.getId() == R.id.b3_1) {
            Intent addmed = new Intent(MyMedications.this, MyAddmed.class);
            startActivity(addmed);
        }
    }
}
