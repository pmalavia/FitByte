package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class CalorieGoal extends MenuNavigation{
    UserProfile u = new UserProfile();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    calcCalorieGoal();
        calcTDEE();
        calcBMR();
    }

    int calcBMR(){
        //SharedPreferences userInfo1 = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        int bmr;
        String gender = u.getGender1();
        //String gender = userInfo1.getString("Usergender", "");
        int weight = Integer.parseInt(u.getWeight1());
        //int weight = Integer.parseInt(userInfo1.getString("Userweight", "")); //convert string to int
        int age = Integer.parseInt(u.getAge1());
        //int age = Integer.parseInt(userInfo1.getString("Userage", ""));
        int height = Integer.parseInt(u.getHeight1());
        //int height = Integer.parseInt(userInfo1.getString("Userheight", ""));

        if(gender.equals("Male")){
            bmr = (int) ((65 + (6.23*weight) + (12.7*height) - (6.8*age)) +0.5);
        }
        else{
            bmr = (int) ((655 + (4.35*weight) + (4.7*height) - (4.7*age)) +0.5);
        }
        return bmr;
    }

    public int getBMR(){

        return calcBMR();
    }

    int calcTDEE(){
        //SharedPreferences userInfo1 = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int tdee=0;
        int bmr = getBMR();
        String activity = u.getActivity1();
        //String activity = userInfo1.getString("Useractivitylevel", "");
        switch(activity){
            case "Sedentary":
                tdee = (int)((bmr * 1.2) + 0.5);
                break;
            case "Lightly Active":
                tdee = (int)((bmr * 1.375) + 0.5);
                break;
            case "Moderately Active":
                tdee = (int)((bmr * 1.55) + 0.5);
                break;
            case "Very Active":
                tdee = (int)((bmr * 1.725) + 0.5);
                break;
        }
        return tdee;
    }

    public int getTDEE(){

        return calcTDEE();
    }

    int calcCalorieGoal(){
      //SharedPreferences  userInfo1 = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        int weeks = Integer.parseInt(u.getGoalWeeks1());
        //int weeks = userInfo1.getInt("Userweeks", 0);
        int pounds = u.getGoal1();
        //int pounds = userInfo1.getInt("Userintgoal", 0);
        double ppw;
        int dailyvarcals;
        String goal = u.getGainOrLose1();
        int caloriegoal;


        ppw = pounds/weeks;
        dailyvarcals = (int)((ppw * 3500)/7);


        if(goal.equals("Gain")){
            caloriegoal = getTDEE() + dailyvarcals;
        }
        else{
            caloriegoal = getTDEE() - dailyvarcals;
        }

        return caloriegoal; //returns an int

    }


    public int getCalorieGoal(){

        return calcCalorieGoal();
    }

    public String getStringCalorieGoal(){

        return Integer.toString(calcCalorieGoal());
    }

}

