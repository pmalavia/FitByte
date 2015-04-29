package com.example.fitbyte.fitbyte;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diary extends MenuNavigation {

    public static int calorieGoal;
    public static int exerciseValue;
    public static int calorieStatus;

    public static boolean breakfast = false;
    public static boolean lunch = false;
    public static boolean dinner = false;


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

        SharedPreferences foodLogs = getSharedPreferences("foodLogs", Context.MODE_PRIVATE);
        SharedPreferences workouts = getSharedPreferences("workouts", Context.MODE_PRIVATE);

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

        diaryFoodValue.setText(Integer.toString(foodLogs.getInt("CaloriesConsumed",0)));

        diaryExerciseValue.setText("(" + Integer.toString(dailyExercises.getInt("dailyExerciseValue", 0)) + ")");

        TextView diaryCalorieStatus = (TextView) findViewById(R.id.diaryCalorieStatus);
        calorieStatus = foodLogs.getInt("CaloriesConsumed",0) + dailyExercises.getInt("dailyExerciseValue", 0) - calorieGoal;
        diaryCalorieStatus.setText(String.valueOf(calorieStatus));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        TextView date = (TextView) findViewById(R.id.date);
        date.setText(currentDateandTime);

        TextView textView4 = (TextView)findViewById(R.id.breakfastTV1);
        TextView textView5 = (TextView)findViewById(R.id.breakfastTV2);
        TextView textView6 = (TextView)findViewById(R.id.lunchTV1);
        TextView textView7 = (TextView)findViewById(R.id.lunchTV2);
        TextView textView8 = (TextView)findViewById(R.id.dinnerTV1);
        TextView textView9 = (TextView)findViewById(R.id.dinnerTV2);


        textView4.setText(foodLogs.getString("item1",""));
        textView5.setText(foodLogs.getString("item2",""));
        textView6.setText(foodLogs.getString("item3",""));
        textView7.setText(foodLogs.getString("item4",""));
        textView8.setText(foodLogs.getString("item5",""));
        textView9.setText(foodLogs.getString("item6",""));

        TextView textView1 = (TextView)findViewById(R.id.workoutTV1);
        TextView textView2 = (TextView)findViewById(R.id.workoutTV2);
        TextView textView3 = (TextView)findViewById(R.id.workoutTV3);
        textView1.setText(workouts.getString("workoutName1",""));
        textView2.setText(workouts.getString("workoutName2",""));
        textView3.setText(workouts.getString("workoutName3",""));
    }

    public void onClick(View view) {
        Intent i = new Intent(this, NewExercise.class);
        startActivity(i);
    }

    public void breakfastClicked(View view){
        breakfast = true;
        lunch = false;
        dinner = false;
        Intent i = new Intent(this, searchPage.class);
        startActivity(i);
    }
    public void lunchClicked(View view){
        breakfast = false;
        lunch = true;
        dinner = false;
        Intent i = new Intent(this, searchPage.class);
        startActivity(i);
    }
    public void dinnerClicked(View view){
        breakfast = false;
        lunch = false;
        dinner = true;
        Intent i = new Intent(this, searchPage.class);
        startActivity(i);
    }

    public void onClickWorkout(View view){
        Intent i = new Intent(this, WorkoutMain.class);
        startActivity(i);
    }


    public boolean getBreakfast(){
        return breakfast;
    }
    public boolean getLunch(){
        return lunch;
    }
    public boolean getDinner(){
        return dinner;
    }
}

