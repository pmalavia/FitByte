package com.example.fitbyte.fitbyte;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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

    NotificationCompat.Builder notifications;
    private static final int uniqueID = 123; //identity for notification



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

    public void onSave(View view) { //button onSave clicked
       // SharedPreferences pref =  getSharedPreferences("Reminders", MODE_PRIVATE) ;
       // SharedPreferences.Editor editor = pref.edit();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:ss");
        //boolean checked = ((RadioButton) view).isChecked();//checked t or f
        timePicker = new TimePicker(getApplicationContext());
        notifications = new NotificationCompat.Builder(this);
        notifications.setAutoCancel(true); //once clicked it dissapears
        notifications.setSmallIcon(R.drawable.ic_launcher); //set little logo at the very top


        notifications.setTicker("Time to add your meal!");
        notifications.setWhen(System.currentTimeMillis());
        notifications.setContentTitle("Diary Time!");








               if(breakfast.isChecked() ){
                    //editor.putInt("Breakfast", timePicker.getCurrentHour() + timePicker.getCurrentMinute());
                   notifications.setContentText("Breakfast");
                   Intent intent = new Intent(this, Diary.class);
                   PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                   notifications.setContentIntent(pendingIntent);
                   NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notifications.build());
                }


                if(lunch.isChecked()){
                    //editor.putInt("Lunch", timePicker.getCurrentHour() + timePicker.getCurrentMinute());
                    notifications.setContentText("Lunch");
                    Intent intent = new Intent(this, Diary.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    notifications.setContentIntent(pendingIntent);
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notifications.build());
                }



                if(dinner.isChecked()){
                    //editor.putInt("Dinner", timePicker.getCurrentHour() + timePicker.getCurrentMinute());
                    notifications.setContentText("Dinner");
                    Intent intent = new Intent(this, Diary.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    notifications.setContentIntent(pendingIntent);
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notifications.build());
                }

        }

     }



