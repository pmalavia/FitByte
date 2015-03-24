package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutMain extends MenuNavigation {

    NewWorkout workout;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    public static String n = "";
    public static int k=0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutmain_layout);
        super.onCreateDrawer();

        workout = new NewWorkout();
        bt1 = (Button)findViewById(R.id.workout1);
        bt2 = (Button)findViewById(R.id.workout2);
        bt3 = (Button)findViewById(R.id.workout3);
        bt4 = (Button)findViewById(R.id.workout4);
        bt5 = (Button)findViewById(R.id.workout5);

        bt1.setText(workout.workoutName01);
        bt2.setText(workout.workoutName02);
        bt3.setText(workout.workoutName03);
        bt4.setText(workout.workoutName04);
        bt5.setText(workout.workoutName05);

    }

    public void workoutButtonClicked(View view){
        Button b = (Button)view;
        n = b.getText().toString();

        if(n.equals("Enter a New Workout")){
            Intent myIntent = new Intent(this,NewWorkout.class);
            startActivity(myIntent);
            return;
        }

        switch(view.getId()){
            case R.id.workout1 :
                k = 1;
                break;
            case R.id.workout2 :
                k = 2;
                break;
            case R.id.workout3 :
                k = 3;
                break;
            case R.id.workout4 :
                k = 4;
                break;
            case R.id.workout5 :
                k = 5;
                break;
            default:
                break;
        }
        Intent myIntent = new Intent(this,MyWorkout.class);
        startActivity(myIntent);
    }
}
