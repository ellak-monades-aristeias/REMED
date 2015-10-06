package com.remed1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ΜΗΤΣΟΣ on 1/10/2015.
 */
public class MyAddmed extends AppCompatActivity implements View.OnClickListener {


    DateFormat formate = DateFormat.getDateInstance();
    Calendar calendar = Calendar.getInstance();
    TextView dateView;
    Button btn_set_date;
    Button btn_set_days;
    private static final int SELECTED_PICTURE = 1;
    Button btn_set_shape;
    Button btn_set_color;
    ImageView shape;
    ImageView color;

    protected CharSequence[] days = { "Every Day", "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday","Friday","Saturday" };

    protected ArrayList<CharSequence> selectedDays = new ArrayList<CharSequence>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ADD MED");
        actionBar.setDisplayHomeAsUpEnabled(true);

        dateView = (TextView) findViewById(R.id.tv3_1_5);
        btn_set_date = (Button) findViewById(R.id.b3_1_2);
        btn_set_date.setOnClickListener(this);
        updateDate();

        btn_set_days = (Button) findViewById(R.id.b3_1_3);
        btn_set_days.setOnClickListener(this);
        btn_set_shape = (Button) findViewById(R.id.b3_1_4);
        btn_set_color = (Button) findViewById(R.id.b3_1_5);
        shape = (ImageView)findViewById(R.id.image3_1_1);
        color = (ImageView)findViewById(R.id.image3_1_2);
        btn_set_shape.setOnClickListener(this);
        btn_set_color.setOnClickListener(this);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



                if(requestCode==SELECTED_PICTURE && resultCode == RESULT_OK && data !=null){
                    Uri pickedImage = data.getData();
                    String[] filePath ={ MediaStore.Images.Media.DATA};

                    Cursor cursor=getContentResolver().query(pickedImage, filePath, null, null, null);
                    cursor.moveToFirst();

                    String imagePath=cursor.getString(cursor.getColumnIndex(filePath[0]));
                    shape.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    color.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    cursor.close();




        }
    }

    public void updateDate(){

        dateView.setText(formate.format(calendar.getTime()));
    }



    public  void setDate(){

    new DatePickerDialog(MyAddmed.this,d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

      MenuItem item = menu.add(R.string.save_option);
        item.setShowAsAction(2);
        return true;
    }
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
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

///////////////////////////////////////////////////////////////
protected void showSelectColoursDialog() {

    boolean[] checkedDays = new boolean[days.length];

    int count = days.length;

    for(int i = 0; i < count; i++)

        checkedDays[i] = selectedDays.contains(days[i]);

    DialogInterface.OnMultiChoiceClickListener daysDialogListener = new DialogInterface.OnMultiChoiceClickListener() {

        @Override

        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            if(isChecked)

                selectedDays.add(days[which]);

            else

                selectedDays.remove(days[which]);

            onChangeSelectedColours();

        }

    };

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setTitle("Select Days");

    builder.setMultiChoiceItems(days, checkedDays, daysDialogListener);
    builder.setPositiveButton("OK", null);
    AlertDialog dialog = builder.create();

    dialog.show();

}



    protected void onChangeSelectedColours() {

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

                        showSelectColoursDialog();

                        break;
                    case R.id.b3_1_2:
                        setDate();
                        break;
                    case R.id.b3_1_4:
                        Intent i1 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i1, SELECTED_PICTURE);
                        break;
                    case R.id.b3_1_5:
                        Intent i2 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i2, SELECTED_PICTURE);
                        break;
                    default:

                        break;
                }

            }



}
