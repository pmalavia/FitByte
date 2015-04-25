package com.example.fitbyte.fitbyte;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diary extends MenuNavigation {

    public static int calorieGoal;
    public static int foodValue;
    public static int exerciseValue;
    public static int calorieStatus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_layout);
        super.onCreateDrawer();
        LinearLayout layout = (LinearLayout) findViewById(R.id.diarylayout);
        TextView diaryExerciseValue = (TextView) findViewById(R.id.diaryExerciseValue);
        TextView diaryFoodValue = (TextView) findViewById(R.id.diaryFoodValue);
        TextView diaryGoal = (TextView) findViewById(R.id.diaryGoalValue);

        SharedPreferences dailyExercises = getSharedPreferences("dailyExercises", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = dailyExercises.edit();

        SharedPreferences userInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        int counter = dailyExercises.getInt("counter", 0);

        if (!dailyExercises.getString("exercise1", "").equals(" ") && counter == 0) {
            counter++;
            editor.putInt("counter", counter);
            TextView exerciseText1 = new TextView(this);
            exerciseText1.setText(dailyExercises.getString("exercise1", ""));
            int temp = dailyExercises.getInt("dailyExerciseValue", 0);
            temp = temp - dailyExercises.getInt("exerciseCaloriesValue1", 0);
            editor.putInt("dailyExerciseValue", temp);
            editor.commit();
            exerciseValue = dailyExercises.getInt("dailyExerciseValue", 0);
            layout.addView(exerciseText1);
        }
        else if (!dailyExercises.getString("exercise2", "").equals(" ") && counter == 1) {
            counter++;
            editor.putInt("counter", counter);
            TextView exerciseText1 = new TextView(this);
            exerciseText1.setText(dailyExercises.getString("exercise1", ""));
            TextView exerciseText2 = new TextView(this);
            exerciseText2.setText(dailyExercises.getString("exercise2", ""));
            int temp = dailyExercises.getInt("dailyExerciseValue", 0);
            temp = temp - dailyExercises.getInt("exerciseCaloriesValue2", 0);
            editor.putInt("dailyExerciseValue", temp);
            editor.commit();
            exerciseValue = dailyExercises.getInt("dailyExerciseValue", 0);
            layout.addView(exerciseText1);
            layout.addView(exerciseText2);
        }
        else if (!dailyExercises.getString("exercise3", "").equals(" ") && counter == 2) {
            counter++;
            editor.putInt("counter", counter);
            TextView exerciseText1 = new TextView(this);
            exerciseText1.setText(dailyExercises.getString("exercise1", ""));
            TextView exerciseText2 = new TextView(this);
            exerciseText2.setText(dailyExercises.getString("exercise2", ""));
            TextView exerciseText3 = new TextView(this);
            exerciseText3.setText(dailyExercises.getString("exercise3", ""));
            int temp = dailyExercises.getInt("dailyExerciseValue", 0);
            temp = temp - dailyExercises.getInt("exerciseCaloriesValue3", 0);
            editor.putInt("dailyExerciseValue", temp);
            editor.commit();
            exerciseValue = dailyExercises.getInt("dailyExerciseValue", 0);
            layout.addView(exerciseText1);
            layout.addView(exerciseText2);
            layout.addView(exerciseText3);
        }
        else {
            TextView exerciseText1 = new TextView(this);
            exerciseText1.setText(dailyExercises.getString("exercise1", ""));
            TextView exerciseText2 = new TextView(this);
            exerciseText2.setText(dailyExercises.getString("exercise2", ""));
            TextView exerciseText3 = new TextView(this);
            exerciseText3.setText(dailyExercises.getString("exercise3", ""));
            layout.addView(exerciseText1);
            layout.addView(exerciseText2);
            layout.addView(exerciseText3);
        }

        calorieGoal = (userInfo.getInt("Caloriegoal", 0));
        diaryGoal.setText(Integer.toString(calorieGoal));

        foodValue = 0;
        diaryFoodValue.setText(String.valueOf(foodValue));

        diaryExerciseValue.setText("(" + Integer.toString(dailyExercises.getInt("dailyExerciseValue", 0)) + ")");

        TextView diaryCalorieStatus = (TextView) findViewById(R.id.diaryCalorieStatus);
        calorieStatus = foodValue + dailyExercises.getInt("dailyExerciseValue", 0) - calorieGoal;
        diaryCalorieStatus.setText(String.valueOf(calorieStatus));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        TextView date = (TextView) findViewById(R.id.date);
        date.setText(currentDateandTime);
    }

    public void onClick(View view) {
        Intent i = new Intent(this, NewExercise.class);
        startActivity(i);
    }

    public void foodClicked(View view){
        Intent i = new Intent(this, searchPage.class);
        startActivity(i);
    }

    public void running(View view){
        Intent myIntent = new Intent(this, DistanceTracker.class);
        startActivity(myIntent);

    }
}

