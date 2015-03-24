package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NewWorkout extends MenuNavigation implements View.OnClickListener {

    public int k = 0;
    public static EditText editText1;
    protected TextView tv2;
    protected Button selectChestButton;
    protected Button selectBackButton;
    protected Button selectShoulderButton;
    protected Button selectBicepButton;
    protected Button selectTricepButton;
    protected Button selectLegButton;
    protected Button selectAbButton;
    public static ArrayList<CharSequence> workoutList1 = new ArrayList<>();
    public static ArrayList<CharSequence> workoutList2 = new ArrayList<>();
    public static ArrayList<CharSequence> workoutList3 = new ArrayList<>();
    public static ArrayList<CharSequence> workoutList4 = new ArrayList<>();
    public static ArrayList<CharSequence> workoutList5 = new ArrayList<>();
    public static int numberOfWorkouts = 0;
    public static String workoutName01 = "Enter a New Workout";
    public static String workoutName02 = "Enter a New Workout";
    public static String workoutName03 = "Enter a New Workout";
    public static String workoutName04 = "Enter a New Workout";
    public static String workoutName05 = "Enter a New Workout";


    protected CharSequence[] chest = { "Barbell Bench Press", "Incline Dumbbell Press",
            "Incline Dumbbell Fly", "Decline Dumbbell Press" };

    protected CharSequence[] back = { "Deadlift", "Bent Over Row", "Pull Ups", "Good Mornings",
            "T-Bar Rows", "Seated Cable Rows" };

    protected CharSequence[] shoulders = { "Military Press", "Lateral Raises", "Upright Rows",
            "Shrugs"};

    protected CharSequence[] biceps = { "Barbell Curl", "Dumbbell Curl", "Hammer Curls",
            "EZ Bar Curl"};

    protected CharSequence[] triceps = { "Skullcrushers", "Rope Pushdowns", "Dips",
            "Close Grip Bench Press"};

    protected CharSequence[] legs = { "Barbell Squats", "Leg Press", "Standing Calf Raises",
            "Dumbbell Lunges"};

    protected CharSequence[] abs = { "Crunches", "Sit Ups", "Leg Raises", "Plank"};


    protected ArrayList<CharSequence> myList = new ArrayList<CharSequence>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newworkout_layout);



        editText1 = (EditText)findViewById(R.id.editText);
        tv2 = (TextView)findViewById(R.id.textView2);

        selectChestButton = (Button) findViewById(R.id.button_Chest);
        selectBackButton = (Button) findViewById(R.id.button_Back);
        selectShoulderButton = (Button) findViewById(R.id.button_Shoulder);
        selectBicepButton = (Button) findViewById(R.id.button_Bicep);
        selectTricepButton = (Button) findViewById(R.id.button_Tricep);
        selectLegButton = (Button) findViewById(R.id.button_Leg);
        selectAbButton = (Button) findViewById(R.id.button_Abs);


        selectChestButton.setOnClickListener(this);
        selectBackButton.setOnClickListener(this);
        selectShoulderButton.setOnClickListener(this);
        selectBicepButton.setOnClickListener(this);
        selectTricepButton.setOnClickListener(this);
        selectLegButton.setOnClickListener(this);
        selectAbButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button_Back:
                k = 1;
                showSelectDialog(back);
                break;
            case R.id.button_Shoulder:
                k = 2;
                showSelectDialog(shoulders);
                break;
            case R.id.button_Bicep:
                k = 3;
                showSelectDialog(biceps);
                break;
            case R.id.button_Tricep:
                k = 4;
                showSelectDialog(triceps);
                break;
            case R.id.button_Chest:
                k = 5;
                showSelectDialog(chest);
                break;
            case R.id.button_Leg:
                k = 6;
                showSelectDialog(legs);
                break;
            case R.id.button_Abs:
                k = 7;
                showSelectDialog(abs);
                break;
            default:
                break;
        }
    }

    protected void showSelectDialog(final CharSequence[] list) {
        boolean[] checkedExercises = new boolean[list.length];
        int count = list.length;
        for(int i = 0; i < count; i++)
            checkedExercises[i] = myList.contains(list[i]);
        DialogInterface.OnMultiChoiceClickListener dialogListener = new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                    myList.add(list[which]);
                else
                    myList.remove(list[which]);
                onChangeSelected();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to workout");
        if(k == 1){
            builder.setMultiChoiceItems(back, checkedExercises, dialogListener);
        }
        else if(k == 2){
            builder.setMultiChoiceItems(shoulders, checkedExercises, dialogListener);
        }
        else if(k == 3){
            builder.setMultiChoiceItems(biceps, checkedExercises, dialogListener);
        }
        else if(k == 4){
            builder.setMultiChoiceItems(triceps, checkedExercises, dialogListener);
        }
        else if(k == 5){
            builder.setMultiChoiceItems(chest, checkedExercises, dialogListener);
        }
        else if(k == 6){
            builder.setMultiChoiceItems(legs, checkedExercises, dialogListener);
        }
        else if(k == 7){
            builder.setMultiChoiceItems(abs, checkedExercises, dialogListener);
        }
        else{

        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void submitClicked(View view){
        numberOfWorkouts++;
        switch(numberOfWorkouts){
            case 1 :
                workoutName01 = editText1.getText().toString();
                break;
            case 2 :
                workoutName02 = editText1.getText().toString();
                break;
            case 3 :
                workoutName03 = editText1.getText().toString();
                break;
            case 4 :
                workoutName04 = editText1.getText().toString();
                break;
            case 5 :
                workoutName05 = editText1.getText().toString();
                break;
            default :
                break;
        }
        saveValues();
        Intent myIntent = new Intent(this,WorkoutMain.class);
        startActivity(myIntent);
    }

    protected void saveValues(){
        switch(numberOfWorkouts){
            case 1 :
                for(int i = 0; i < myList.size(); i++){
                    workoutList1.add(myList.get(i));
                }
                break;
            case 2 :
                for(int i = 0; i < myList.size(); i++){
                    workoutList2.add(myList.get(i));
                }
                break;
            case 3 :
                for(int i = 0; i < myList.size(); i++){
                    workoutList3.add(myList.get(i));
                }
                break;
            case 4 :
                for(int i = 0; i < myList.size(); i++){
                    workoutList4.add(myList.get(i));
                }
                break;
            case 5 :
                for(int i = 0; i < myList.size(); i++){
                    workoutList5.add(myList.get(i));
                }
                break;
            default :
                break;
        }
    }

    protected void onChangeSelected() {
        StringBuilder stringBuilder = new StringBuilder();
        for(CharSequence c : myList)
            stringBuilder.append(c + "\n");
        tv2.setText(stringBuilder.toString());
    }
}


