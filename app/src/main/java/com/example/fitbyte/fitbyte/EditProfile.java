package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EditProfile extends MenuNavigation {

    UserProfile up = new UserProfile();
    EditText editName;
    EditText editAge;
    EditText editWeight;
    EditText editHeight;
    TextView goal;
    TextView gender;
    TextView gainLose;
    TextView weeks;
    TextView activityLevel;
    boolean heightEx, weightEx, ageEx;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_layout);

        weeks = (TextView) findViewById(R.id.weeks);
        weeks.setText(up.getStringWeeks());

        editName = (EditText) findViewById(R.id.name);
        editName.setText(up.getName());

        editAge = (EditText) findViewById(R.id.age);
        editAge.setText(up.getStringAge());

        editWeight = (EditText) findViewById(R.id.weight);
        editWeight.setText(up.getStringWeight());

        editHeight = (EditText) findViewById(R.id.height);
        editHeight.setText(up.getStringHeight());

        gainLose = (TextView) findViewById(R.id.gainLose);
        gainLose.setText(up.getGainOrLose());

        goal = (TextView) findViewById(R.id.goal);
        goal.setText(up.getStringGoal());

        gender = (TextView) findViewById(R.id.gender);
        gender.setText(up.getGender());

        activityLevel = (TextView) findViewById(R.id.activityLevel);
        activityLevel.setText(up.getActivity());
    }

    public void submitClicked(View view) {
        up.setName(editName);
        up.setAge(editAge);
        up.setWeight(editWeight);
        up.setHeight(editHeight);


        try {
            int age = Integer.parseInt(editAge.getText().toString());
            ageEx = true;
            if (age > 90 || age < 10) {
                Toast.makeText(getApplicationContext(), "Please enter a valid age", Toast.LENGTH_LONG).show();
                ageEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid age", Toast.LENGTH_LONG).show();
        }

        try {
            int weight = Integer.parseInt(editWeight.getText().toString());
            weightEx = true;
            if (weight > 400 || weight < 50) {
                Toast.makeText(getApplicationContext(), "Please enter a valid weight", Toast.LENGTH_LONG).show();
                weightEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid weight", Toast.LENGTH_LONG).show();
        }

        try {
            int height = Integer.parseInt(editHeight.getText().toString());
            heightEx = true;
            if (height > 90 || height < 24) {
                Toast.makeText(getApplicationContext(), "Please enter a valid height", Toast.LENGTH_LONG).show();
                heightEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid height", Toast.LENGTH_LONG).show();
        }

        if (ageEx && heightEx && weightEx) {
            Intent myIntent = new Intent(this, HomePage.class);
            startActivity(myIntent);
        }

        up.setName(editName);
        up.setAge(editAge);
        up.setWeight(editWeight);
        up.setHeight(editHeight);

    }
}
