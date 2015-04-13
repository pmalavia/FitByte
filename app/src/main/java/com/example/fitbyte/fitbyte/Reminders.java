package com.example.fitbyte.fitbyte;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 4/12/2015.
 */
public class Reminders extends MenuNavigation {

    Button save;
    RadioButton breakfast, lunch, dinner;
    TimePicker timePicker;
    Date date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_layout);
        super.onCreateDrawer();

        save = (Button) findViewById(R.id.bSaveReminder);
        breakfast = (RadioButton) findViewById(R.id.rbBreakfast);
        lunch = (RadioButton) findViewById(R.id.rbLunch);
        dinner = (RadioButton) findViewById(R.id.rbDinner);



    }

    private void onSave(View view) { //button onSave clicked
        SharedPreferences pref =  getSharedPreferences("Reminders", MODE_PRIVATE) ;
        SharedPreferences.Editor editor = pref.edit();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:ss");


        boolean checked = ((RadioButton) view).isChecked();//checked t or f
        timePicker = new TimePicker(getApplicationContext());

        switch (view.getId()){
            case R.id.rbBreakfast:
                if(checked ){
                    editor.putString("Breakfast", timePicker.toString());

                }
                break;
            case R.id.rbLunch:
                if(checked){
                    editor.putString("Lunch", timePicker.toString());
                }
                break;

            case R.id.rbDinner:
                if(checked){
                    editor.putString("Dinner", timePicker.toString());
                }
                break;

        }

     }


}
