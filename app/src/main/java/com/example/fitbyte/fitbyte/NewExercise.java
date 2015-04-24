package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewExercise extends MenuNavigation implements AdapterView.OnItemSelectedListener {

    private double met = 0.0;
    public EditText editMinutes;
    public static int weight;
    public int caloriesBurned;
    public String caloriesBurnedText;
    public String minutesText;
    public String exerciseText;
    public String exercise1;
    public String exercise2;
    public String exercise3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newexercise_layout);
        super.onCreateDrawer();

        Spinner spinner = (Spinner)findViewById(R.id.cardio_exercise_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cardio_exercise_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if (parent.getItemAtPosition(pos).toString().equals("Bicycling")) {
            met = 7.5;
            exerciseText = "Bicycling";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Calisthenics")) {
            met = 8.0;
            exerciseText = "Calisthenics";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Resistance")) {
            met = 6;
            exerciseText = "Resistance";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Rowing")) {
            met = 4.8;
            exerciseText = "Rowing";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Jogging")) {
            met = 7.0;
            exerciseText = "Jogging";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Running")) {
            met = 10.0;
            exerciseText = "Running";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Basketball")) {
            met = 6.5;
            exerciseText = "Basketball";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Skateboarding")) {
            met = 5.0;
            exerciseText = "Skateboarding";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Tennis")) {
            met = 7.3;
            exerciseText = "Tennis";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Walking")) {
            met = 3.5;
            exerciseText = "Walking";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Swimming")) {
            met = 5.8;
            exerciseText = "Swimming";
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void onClick(View view) {
        SharedPreferences profileInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        boolean minutesEx = false;
        weight = profileInfo.getInt("Userweight", 1);
        editMinutes = (EditText)findViewById(R.id.edit_minutes);

        try {
            int minutes = Integer.parseInt(editMinutes.getText().toString());
            minutesEx = true;

            if (minutes > 0 && minutes < 1440) {
            }
            else{
                editMinutes.setTypeface(null, Typeface.BOLD);
                editMinutes.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid integer value for minutes between 0 and 1440", Toast.LENGTH_LONG).show();
                minutesEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid integer value for minutes between 0 and 1440", Toast.LENGTH_LONG).show();
        }

        if (minutesEx) {
            int minutes = Integer.parseInt(editMinutes.getText().toString());

            caloriesBurned = (int) (met * 3.5 * (weight / 2.2) / 200 * minutes);
            minutesText = String.valueOf(minutes);
            caloriesBurnedText = String.valueOf(caloriesBurned);

            SharedPreferences dailyExercises = getSharedPreferences("dailyExercises", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = dailyExercises.edit();

            exercise1 = dailyExercises.getString("exercise1", "");
            exercise2 = dailyExercises.getString("exercise2", "");
            exercise3 = dailyExercises.getString("exercise3", "");

            if (exercise1.equals(" ")) {
                editor.putString("exercise1", "1. " + exerciseText + " for " + minutesText + " minutes burned " + caloriesBurnedText + " calories.\n");
                editor.putInt("exerciseCaloriesValue1", caloriesBurned);
                editor.commit();
            } else if (exercise2.equals(" ")) {
                editor.putString("exercise2", "2. " + exerciseText + " for " + minutesText + " minutes burned " + caloriesBurnedText + " calories.\n");
                editor.putInt("exerciseCaloriesValue2", caloriesBurned);
                editor.commit();
            } else if (exercise3.equals(" ")) {
                editor.putString("exercise3", "3. " + exerciseText + " for " + minutesText + " minutes burned " + caloriesBurnedText + " calories.\n");
                editor.putInt("exerciseCaloriesValue3", caloriesBurned);
                editor.commit();
            } else {
                Toast.makeText(getApplicationContext(), "Maximum number of exercises for the day has been exceeded.", Toast.LENGTH_LONG).show();
            }

            Intent i = new Intent(this, Diary.class);
            startActivity(i);

        }
    }
}

