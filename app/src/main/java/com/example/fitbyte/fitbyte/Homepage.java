package com.example.fitbyte.fitbyte;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends MenuNavigation {

    private TextView date;
    private TextView caloriesRemaining;
    private TextView name;
    private TextView goalTime;
    private TextView fullWeightGoal;
    private TextView dailyCalGoal;
    private TextView calBurned;
    private TextView net;
    private ImageView profilePic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);
        super.onCreateDrawer();

        date = (TextView) findViewById(R.id.tvDate);
        caloriesRemaining = (TextView) findViewById(R.id.tvCaloriesRemaining);
        name = (TextView) findViewById(R.id.tvName);
        goalTime = (TextView) findViewById(R.id.tvTimeLeft);
        fullWeightGoal = (TextView) findViewById(R.id.tvWeightGoal);
        dailyCalGoal = (TextView) findViewById(R.id.tvGoal);
        calBurned = (TextView) findViewById(R.id.tvCalBurned);
        net = (TextView) findViewById(R.id.tvNet);
        profilePic = (ImageView) findViewById(R.id.profilePic);

        setDisplay();//initialize
    }


    private void setDisplay (){
        UserProfile userInfo = new UserProfile(); //user information object

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //set the date
        String currentDateandTime = sdf.format(new Date());

        //setting text
        date.setText(currentDateandTime);
        name.setText(userInfo.getName());
        fullWeightGoal.setText(userInfo.getGainOrLose());
        goalTime.setText(userInfo.getStringWeeks());
    }

    private void displayView(int position){
        switch(position){
            case 0 :
                startActivity(new Intent(this,Diary.class));
                break;
            case 1 :
                startActivity(new Intent(this,EditProfile.class));
                break;
            case 2 :
                startActivity(new Intent(this, WorkoutMain.class));
                break;
            case 3 :
                startActivity(new Intent(this, Calendar.class));
                break;
            default:break;
        }
    }
}
