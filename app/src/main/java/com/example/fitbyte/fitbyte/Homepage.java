package com.example.fitbyte.fitbyte;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
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
        SharedPreferences userInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        UserProfile userInfo1 = new UserProfile(); //user information object

        CalorieGoal dailyGoal = new CalorieGoal();//daily goal


        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //set the date
        String currentDateandTime = sdf.format(new Date());

        //setting text
        date.setText(currentDateandTime);
        //name.setText(userInfo.getName());
        name.setText(userInfo.getString("Username", "").toString()); //from memory

        //fullWeightGoal.setText(userInfo.getGainOrLose() + " Weight");
        fullWeightGoal.setText(userInfo.getString("Usergainorlose", ""));

        //goalTime.setText(userInfo.getStringWeeks() + " Weeks");
        goalTime.setText(userInfo.getString("Usergoalweeks", ""));

        dailyCalGoal.setText( userInfo.getString("calorieString", ""));








        EditProfile editProfile = new EditProfile();
        if(editProfile.visited){
            profilePicture.setImageBitmap(editProfile.bitmap);
        }
        else {
            profilePicture.setImageBitmap(userInfo1.bitmap);
        }

    }
}









