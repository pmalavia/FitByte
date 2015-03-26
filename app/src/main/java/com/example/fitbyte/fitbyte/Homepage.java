package com.example.fitbyte.fitbyte;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Homepage extends MenuNavigation {

    private TextView date;
    private TextView caloriesRemaining;
    private TextView name;
    private TextView goalTime;
    private TextView fullWeightGoal;
    private TextView dailyCalGoal;
    private TextView calBurned;
    private TextView net;
    private ImageView profilePicture;



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
        profilePicture = (ImageView) findViewById(R.id.profilePicture);

        setDisplay();//initialize

    }

    private void setDisplay (){
        UserProfile userInfo = new UserProfile(); //user information object

        CalorieGoal dailyGoal = new CalorieGoal();//daily goal

        CalorieGoal calGoal = new CalorieGoal();


        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //set the date
        String currentDateandTime = sdf.format(new Date());

        //setting text
        date.setText(currentDateandTime);
        name.setText(userInfo.getName());

        fullWeightGoal.setText(userInfo.getGainOrLose() + " Weight");
        goalTime.setText(userInfo.getStringWeeks() + " Weeks");
        dailyCalGoal.setText( dailyGoal.getStringCalorieGoal());


        fullWeightGoal.setText(userInfo.getGainOrLose() + " Weight");
        goalTime.setText(userInfo.getStringWeeks() + " Weeks");
        dailyCalGoal.setText(calGoal.getStringCalorieGoal());



        EditProfile editProfile = new EditProfile();
        if(editProfile.visited){
            profilePicture.setImageBitmap(editProfile.bitmap);
        }
        else {
            profilePicture.setImageBitmap(userInfo.bitmap);
        }
    }
    }









