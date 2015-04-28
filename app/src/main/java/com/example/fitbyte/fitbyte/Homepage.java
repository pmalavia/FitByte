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
    private int caloriegoal;

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
//----------------------------- calorie goal ----------------------------------------------------
        SharedPreferences profileInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = profileInfo.edit();
        double bmr;
        double tdee =0;
        double ppw;
        double dailyvarcals;
        if(profileInfo.getString("Usergender", "").equalsIgnoreCase("Male")){
            bmr =  ((65 + (6.23*profileInfo.getInt("Userweight", 0)) + (12.7*profileInfo.getInt("Userheight", 0)) - (6.8*profileInfo.getInt("Userage", 0))) +0.5);
        }
        else{
            bmr =  ((655 + (4.35*profileInfo.getInt("Userweight", 0)) + (4.7*profileInfo.getInt("Userheight", 0)) - (4.7*profileInfo.getInt("Userage", 0))) +0.5);
        }

        String x = profileInfo.getString("Useractivitylevel", "");
        switch(x){
            case "S":
                tdee = (bmr * 1.2) + 0.5;
                break;
            case "LA":
                tdee = (bmr * 1.375) + 0.5;
                break;
            case "MA":
                tdee = (bmr * 1.55) + 0.5;
                break;
            case "VA":
                tdee = (bmr * 1.725) + 0.5;
                break;
        }

        ppw = (double)(profileInfo.getInt("Usergoalpounds", 0))/(profileInfo.getInt("Userweeks", 0));
        dailyvarcals = (ppw * 3500)/7;

        if( profileInfo.getString("Usergoal", "").equalsIgnoreCase("Gain")){
            caloriegoal = (int)(tdee + dailyvarcals);
        }
        else{
            caloriegoal = (int)(tdee - dailyvarcals);
        }
        int t = (int) bmr;
        editor.putInt("Caloriegoal", caloriegoal); //Saving caloriegoal for other classes, use this
        //in order to get the result
        editor.commit();

        setDisplay();//initialize
    }

    private void setDisplay (){
        SharedPreferences userInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences dailyExercises = getSharedPreferences("dailyExercises", Context.MODE_PRIVATE);

        UserProfile userInfo1 = new UserProfile(); //user information object



        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //set the date
        String currentDateandTime = sdf.format(new Date());

        //setting text
        date.setText(currentDateandTime);
        //name.setText(userInfo.getName());
        name.setText(userInfo.getString("Username", "").toString()); //from memory


        //fullWeightGoal.setText(userInfo.getGainOrLose() + " Weight");
        fullWeightGoal.setText(userInfo.getString("Usergoal","") + " " + Integer.toString(userInfo.getInt("Usergoalpounds", 0)) + " pounds" );

        goalTime.setText("in " + Integer.toString(userInfo.getInt("Userweeks", 0)) + " weeks");
        //goalTime.setText(userInfo1.getGoalWeeks1()+"");

        dailyCalGoal.setText( Integer.toString(userInfo.getInt("Caloriegoal", 1))); //tvGoal

        caloriesRemaining.setText(Integer.toString(userInfo.getInt("Caloriegoal", 0) - userInfo.getInt("IntakeValue", 0) - dailyExercises.getInt("dailyExerciseValue", 0)));
        calBurned.setText(Integer.toString(dailyExercises.getInt("dailyExerciseValue", 0)));

        net.setText(Integer.toString(userInfo.getInt("IntakeValue", 0) + dailyExercises.getInt("dailyExerciseValue", 0)));
        //IntakeValue for the calorie intake, not yet working.

        EditProfile editProfile = new EditProfile();
        if(editProfile.visited){
            profilePicture.setImageBitmap(editProfile.bitmap);
        }
        else {
            profilePicture.setImageBitmap(userInfo1.bitmap);
        }

    }


}








