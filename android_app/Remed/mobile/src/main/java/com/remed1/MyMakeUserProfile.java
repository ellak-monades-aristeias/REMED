package com.remed1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by ΜΗΤΣΟΣ on 7/10/2015.
 */
public class MyMakeUserProfile  extends AppCompatActivity implements View.OnClickListener {

    EditText nameView;
    EditText surnameView;
    EditText ageView;
    RadioGroup rbg_gender;
    RadioButton rb_gender;
    Button btn_save_profile;
    Button btn_edit_profile;
    Button btn_delete_profile;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_user_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MAKE YOUR PROFILE");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ///////////////////////////////////////
        myDb = new DatabaseHelper(this);
        nameView = (EditText)findViewById(R.id.et1_1_1);
        surnameView = (EditText)findViewById(R.id.et1_1_2);
        ageView = (EditText)findViewById(R.id.et1_1_3);
        rbg_gender = (RadioGroup) findViewById(R.id.rdgroup);
        btn_save_profile = (Button) findViewById(R.id.b1_1_1);
        btn_save_profile.setOnClickListener(this);
        btn_edit_profile = (Button) findViewById(R.id.b1_1_2);
        btn_edit_profile.setOnClickListener(this);
        btn_delete_profile = (Button) findViewById(R.id.b1_1_3);
        btn_delete_profile.setOnClickListener(this);
        if (myDb.IsEmpty() == 0) {
            btn_save_profile.setEnabled(true);
            btn_edit_profile.setEnabled(false);
            btn_delete_profile.setEnabled(false);
        }
        else
        {
            btn_save_profile.setEnabled(false);
            btn_edit_profile.setEnabled(true);
            btn_delete_profile.setEnabled(true);

        }


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

    public void saveUserProfile(){

        boolean isInserted = myDb.insertUserProfile(nameView.getText().toString(),
                surnameView.getText().toString(),
                ageView.getText().toString(),
                rb_gender.getText().toString()

        );
        if(isInserted)
            Toast.makeText(MyMakeUserProfile.this, "USER DATA INSERTED", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MyMakeUserProfile.this, "USER DATA NOT INSERTED", Toast.LENGTH_LONG).show();
    }

    public  void deleteUserProfile()
    {
        myDb.deleteAllData();
            Toast.makeText(MyMakeUserProfile.this, "USER PROFILE DELETED", Toast.LENGTH_LONG).show();

    }

    public void updateUserProfile(){

        boolean isUpdate = myDb.editUserProfile(nameView.getText().toString(),
                surnameView.getText().toString(),
                ageView.getText().toString(),
                rb_gender.getText().toString()

        );
        if(isUpdate)
            Toast.makeText(MyMakeUserProfile.this, "USER DATA UPDATED", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MyMakeUserProfile.this, "USER DATA NOT UPDATED", Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1_1_1:
                int selectedId = rbg_gender.getCheckedRadioButtonId();
                rb_gender = (RadioButton) findViewById(selectedId);
                MyMakeUserProfile.this.saveUserProfile();

                MyProfile.username = myDb.getUserName();
                MyProfile.gender = myDb.getUserGender();
                if(MyProfile.gender == 1)
                    { MyProfile.usergender = "Mr";}
                else
                    { MyProfile.usergender = "Mrs";}
                MyProfile.tv.setText("WELCOME " + MyProfile.usergender + " "+ MyProfile.username);
                MyMakeUserProfile.this.finish();

                break;
            case R.id.b1_1_3:
                MyMakeUserProfile.this.deleteUserProfile();
                MyProfile.tv.setText("WELCOME Mr / Mrs");
                MyMakeUserProfile.this.finish();
                break;
            case R.id.b1_1_2:
                int selectedId1 = rbg_gender.getCheckedRadioButtonId();
                rb_gender = (RadioButton) findViewById(selectedId1);
                MyMakeUserProfile.this.updateUserProfile();

                MyProfile.username = myDb.getUserName();
                MyProfile.gender = myDb.getUserGender();
                if(MyProfile.gender == 1)
                {MyProfile.usergender = "Mr";}
                else
                { MyProfile.usergender = "Mrs";}
                MyProfile.tv.setText("WELCOME " + MyProfile.usergender + " "+ MyProfile.username);
                MyMakeUserProfile.this.finish();
                break;
            default:
                break;
        }
    }



}
