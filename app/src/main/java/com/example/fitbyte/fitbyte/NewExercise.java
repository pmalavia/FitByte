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

        editMinutes = (EditText)findViewById(R.id.edit_minutes);

        Spinner spinner = (Spinner)findViewById(R.id.cardio_exercise_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cardio_exercise_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        SharedPreferences profileInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        weight = profileInfo.getInt("Userweight", 0);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if (parent.getItemAtPosition(pos).toString().equals("Badminton")) {
            met = 5.5;
            exerciseText = "Badminton";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Basketball")) {
            met = 6.5;
            exerciseText = "Basketball";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Bicycling (light)")) {
            met = 6.8;
            exerciseText = "Bicycling (light)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Bicycling (moderate)")) {
            met = 8.0;
            exerciseText = "Bicycling (moderate)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Bicycling (vigorous)")) {
            met = 10.0;
            exerciseText = "Bicycling (vigorous)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Boxing")) {
            met = 12.8;
            exerciseText = "Boxing";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Bowling")) {
            met = 3.8;
            exerciseText = "Bowling";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Calisthenics (light)")) {
            met = 2.8;
            exerciseText = "Calisthenics (light)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Calisthenics (moderate)")) {
            met = 3.8;
            exerciseText = "Calisthenics (moderate)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Calisthenics (vigorous)")) {
            met = 8.0;
            exerciseText = "Calisthenics (vigorous)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Fencing")) {
            met = 6.0;
            exerciseText = "Fencing";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Football")) {
            met = 8.0;
            exerciseText = "Football";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Golf")) {
            met = 4.8;
            exerciseText = "Golf";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Racquetball")) {
            met = 7.0;
            exerciseText = "Racquetball";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Rock Climbing (low-moderate)")) {
            met = 5.8;
            exerciseText = "Rock Climbing (low-moderate)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Rock Climbing (difficult)")) {
            met = 7.5;
            exerciseText = "Rock Climbing (difficult)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Running (10 min/mile)")) {
            met = 9.8;
            exerciseText = "Running (10 min/mile)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Running (8 min/mile)")) {
            met = 11.8;
            exerciseText = "Running (8 min/mile)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Running (6 min/mile)")) {
            met = 14.5;
            exerciseText = "Running (6 min/mile)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Running (5 min/mile)")) {
            met = 19.0;
            exerciseText = "Running (5 min/mile)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Running (4.3 min/mile)")) {
            met = 23.0;
            exerciseText = "Running (4.3 min/mile)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Skateboarding")) {
            met = 5.0;
            exerciseText = "Skateboarding";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Soccer (general)")) {
            met = 7.0;
            exerciseText = "Soccer (general)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Soccer (competitive)")) {
            met = 10.0;
            exerciseText = "Soccer (competitive)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Softball")) {
            met = 5.0;
            exerciseText = "Softball";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Swimming (light-moderate)")) {
            met = 5.8;
            exerciseText = "Swimming (light-moderate)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Swimming (competition)")) {
            met = 10.3;
            exerciseText = "Swimming (competition)";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Tennis")) {
            met = 7.3;
            exerciseText = "Tennis";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Volleyball")) {
            met = 6.0;
            exerciseText = "Volleyball";
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Walking")) {
            met = 3.5;
            exerciseText = "Walking";
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void onClick(View view) {

        boolean minutesEx = false;

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

