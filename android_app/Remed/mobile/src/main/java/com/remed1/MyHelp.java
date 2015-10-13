package com.remed1;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyHelp extends AppCompatActivity

    {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("HELP");
            actionBar.setDisplayHomeAsUpEnabled(true);

            Button OpenPDF = (Button) findViewById(R.id.b7_1);
            OpenPDF.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    File pdfFile = new File("storage/sdcard/REMED.pdf");
                    if (pdfFile.exists()) {
                        Uri path = Uri.fromFile(pdfFile);
                        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                        pdfIntent.setDataAndType(path, "application/pdf");
                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        try {
                            startActivity(pdfIntent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(MyHelp.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });




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




}
