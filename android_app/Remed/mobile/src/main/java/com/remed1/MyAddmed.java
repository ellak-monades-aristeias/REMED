package com.remed1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyAddmed extends AppCompatActivity implements View.OnClickListener {


    DateFormat formate = DateFormat.getDateInstance();
    Calendar calendar = Calendar.getInstance();
    TextView dateView;
    TextView timeView;
    EditText id;
    Button btn_set_date;
    Button btn_set_time;
    Button btn_set_days;
    DatabaseHelper myDb;
    EditText pillname;
    EditText dosage;
   Spinner how_many_times;
   Spinner instructions;
    Spinner shape_pill;
    Spinner color_pill;
    String[] strings;
   String[] strings1;

    protected CharSequence[] days = { "Every Day", "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday","Friday","Saturday" };

    protected ArrayList<CharSequence> selectedDays = new ArrayList<CharSequence>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        ////////////////////////////////////
        myDb = new DatabaseHelper(this);
        pillname = (EditText)findViewById(R.id.editText);
        id = (EditText)findViewById(R.id.editText3);
        dosage = (EditText)findViewById(R.id.editText2);
        //////////////////////////////////////
        ActionBar actionBar = getSupportActionBar();
        if(MyMedications.choise == 1) {
            actionBar.setTitle("ADD MED");
            actionBar.setDisplayHomeAsUpEnabled(true);
            TextView tv = (TextView)findViewById(R.id.tvid) ;
            tv.setVisibility(View.INVISIBLE);
            id.setVisibility(View.INVISIBLE);}
        if(MyMedications.choise == 2) {
            actionBar.setTitle("UPDATE MED");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if(MyMedications.choise == 3) {
            actionBar.setTitle("DELETE MED");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        dateView = (TextView) findViewById(R.id.tv3_1_5);
        btn_set_date = (Button) findViewById(R.id.b3_1_2);
        btn_set_date.setOnClickListener(this);
        updateDate();

        timeView = (TextView) findViewById(R.id.tv3_1_9);
        btn_set_time = (Button)findViewById(R.id.b3_1_6);
        btn_set_time.setOnClickListener(this);
        updateTime();

        btn_set_days = (Button) findViewById(R.id.b3_1_3);
        btn_set_days.setOnClickListener(this);
        how_many_times = (Spinner) findViewById(R.id.spinner3_1_1);
        instructions = (Spinner) findViewById(R.id.spinner3_1_3);
         strings = getResources().getStringArray(R.array.Shapes);
        strings1 = getResources().getStringArray(R.array.Color_Names);
        shape_pill = (Spinner)findViewById(R.id.spinner3_1_4);
        shape_pill.setAdapter(new MyAdapter(MyAddmed.this, R.layout.row, strings));
        color_pill = (Spinner)findViewById(R.id.spinner3_1_5);
        color_pill.setAdapter(new MyAdapter1(MyAddmed.this, R.layout.color, strings1));
    }



    public void updateDate(){

        dateView.setText(formate.format(calendar.getTime()));
    }

    public void updateTime(){
        timeView.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) );
    }

   public void setTime(){

        new TimePickerDialog(MyAddmed.this, t, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker v, int hour, int minute) {
            timeView.setText(hour + ":" + minute);

        }
    };

    public  void setDate(){

    new DatePickerDialog(MyAddmed.this,d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    if(MyMedications.choise ==1) {
        MenuItem save_item = menu.add(R.string.save_option);
        save_item.setShowAsAction(2);
        save_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                MyAddmed.this.saveData();
                return false;
            }
        });
    }
        if(MyMedications.choise ==2) {
            MenuItem edit_item = menu.add(R.string.edit_option);
            edit_item.setShowAsAction(2);
            edit_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    MyAddmed.this.updateData();
                    return false;
                }
            });

        }


        if(MyMedications.choise ==3) {
            MenuItem remove_item = menu.add(R.string.remove_option);
            remove_item.setShowAsAction(2);
            remove_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    MyAddmed.this.deleteData();

                    return false;
                }
            });

        }

        return true;
   }
/////////////////////////////////
    public  void deleteData()
    {
        Integer deleterows = myDb.removeData(id.getText().toString());
        if(deleterows>0)
            Toast.makeText(MyAddmed.this, "DATA DELETED", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MyAddmed.this, "DATA NOT DELETED", Toast.LENGTH_LONG).show();
    }

    //////////////////////////////
    public void saveData(){

       boolean isInserted = myDb.insertData(pillname.getText().toString(),
               dateView.getText().toString(),
               timeView.getText().toString(),
               how_many_times.getSelectedItem().toString(),
               shape_pill.getSelectedItem().toString() + " / "
                + color_pill.getSelectedItem().toString(),
               dosage.getText().toString(),
               instructions.getSelectedItem().toString()
       );
        if(isInserted)
            Toast.makeText(MyAddmed.this, "DATA INSERTED", Toast.LENGTH_LONG).show();
        else
           Toast.makeText(MyAddmed.this, "DATA NOT INSERTED", Toast.LENGTH_LONG).show();


    }
////////////////////////////////////////////////
public void updateData() {

    boolean isEdit = myDb.editData(id.getText().toString(),
            pillname.getText().toString(),
            dateView.getText().toString(),
            timeView.getText().toString(),
            how_many_times.getSelectedItem().toString(),
            shape_pill.getSelectedItem().toString() + " / "
                    + color_pill.getSelectedItem().toString(),
            dosage.getText().toString(),
            instructions.getSelectedItem().toString());
    if (isEdit)
        Toast.makeText(MyAddmed.this, "DATA UPDATE", Toast.LENGTH_LONG).show();
    else
        Toast.makeText(MyAddmed.this, "DATA NOT UPDATE", Toast.LENGTH_LONG).show();
}
    ////////////////////////////////

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

        @Override
        public  void onDateSet(DatePicker v, int year, int month, int day){
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateDate();

       }
    };
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

///////////////////////////////////////////////////////////////
    protected void showSelectDaysDialog() {

    final boolean[] checkedDays = new boolean[days.length];

    int  count = days.length;

    for(int i = 0; i < count; i++) {
        selectedDays.remove(days[i]);}

    for(int i = 0; i < count; i++) {
        checkedDays[i] = selectedDays.contains(days[i]);

    }
    DialogInterface.OnMultiChoiceClickListener daysDialogListener = new DialogInterface.OnMultiChoiceClickListener() {

        @Override

        public void onClick(DialogInterface dialog, int which, boolean isChecked) {


            if(days[which].toString().equals("Every Day")) {

                for(int i = 0; i < (days.length); i++) {
                    selectedDays.remove(days[i]);}
                for (int i = 0; i<=(days.length); i++) {

                    ((AlertDialog) dialog).getListView().setItemChecked(i, true);
                }
            }

            if(checkedDays[which] == false && days[which].toString().equals("Every Day")) {
                for (int i = 1; i <= (days.length); i++) {
                    ((AlertDialog) dialog).getListView().setItemChecked(i, false);
                }
            }

            if(isChecked)
               selectedDays.add(days[which]);
            else
                selectedDays.remove(days[which]);
            onChangeSelectedDays();

        }

    };

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setTitle("Select Days");

    builder.setMultiChoiceItems(days, checkedDays, daysDialogListener);
    builder.setPositiveButton("OK", null);
    AlertDialog dialog = builder.create();

    dialog.show();

     }

    protected void onChangeSelectedDays() {

        StringBuilder stringBuilder = new StringBuilder();

        for(CharSequence days : selectedDays)
            stringBuilder.append(days + " ");
        btn_set_days.setText(stringBuilder.toString());

    }
//////////////////////////////////////////////////////////////
            @Override
     public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.b3_1_3:
                        showSelectDaysDialog();
                        break;
                    case R.id.b3_1_2:
                        setDate();
                        break;
                    case R.id.b3_1_6:
                        setTime();
                        break;
                    default:
                        break;
                }
            }


    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int textViewResourceId,   String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.shape_pill);
            label.setText(strings[position]);
            label.setTextSize(18f);
            TypedArray arr_images = getResources().obtainTypedArray(R.array.Image_Shape);
            ImageView icon=(ImageView)row.findViewById(R.id.image);
            icon.setImageResource(arr_images.getResourceId(position,0));
            arr_images.recycle();
            return row;
        }
    }

    public class MyAdapter1 extends ArrayAdapter<String> {

        public MyAdapter1(Context context, int textViewResourceId,   String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View color=inflater.inflate(R.layout.color, parent, false);
            TextView label1=(TextView)color.findViewById(R.id.color_pill);
            label1.setText(strings1[position]);
            label1.setTextSize(18f);
            TypedArray clr = getResources().obtainTypedArray(R.array.androidcolors);
            label1.setBackgroundColor(clr.getColor(position,0));
            clr.recycle();

            return color;
        }
    }

}


