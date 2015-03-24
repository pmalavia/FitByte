package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;


public class UserProfile extends Activity {

    public static EditText editName;
    public static EditText editAge;
    public static EditText editWeight;
    public static EditText editHeight;
    public static Switch gender;
    public static NumberPicker goal;
    public static NumberPicker weeks;
    public static RadioGroup activityLevel;
    public static RadioGroup gainGroup;
    public static RadioButton sedentary, lActive, mActive, vActive;
    public static RadioButton gain, lose;
    public static int goalInt;
    public static int activityInt;
    public static int genderInt;
    public static int selectedActivity;
    public static int gainInt;
    public static int gl;
    public static int weekInt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        goal = (NumberPicker)findViewById(R.id.numberPicker);
        goal.setMinValue(5);
        goal.setMaxValue(150);

        goal.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                goalInt = newVal;
            }
        });

        weeks = (NumberPicker)findViewById(R.id.numberPicker2);
        weeks.setMinValue(1);
        weeks.setMaxValue(52);

        weeks.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                weekInt = newVal;
            }
        });

    }

    public void submitClicked(View view){
        boolean ageEx = false;
        boolean heightEx = false;
        boolean weightEx = false;
        editName = (EditText)findViewById(R.id.name);
        editAge = (EditText)findViewById(R.id.age);
        editWeight = (EditText)findViewById(R.id.weight);
        editHeight = (EditText)findViewById(R.id.height);
        gender = (Switch)findViewById(R.id.switch1);
        activityLevel = (RadioGroup)findViewById(R.id.radioGroup);
        sedentary = (RadioButton)findViewById(R.id.rb01);
        lActive = (RadioButton)findViewById(R.id.rb02);
        mActive = (RadioButton)findViewById(R.id.rb03);
        vActive = (RadioButton)findViewById(R.id.rb04);
        gain = (RadioButton)findViewById(R.id.gain);
        lose = (RadioButton)findViewById(R.id.lose);
        gainGroup = (RadioGroup)findViewById(R.id.gainORlose);

        gainInt = gainGroup.getCheckedRadioButtonId();
        if(gainInt == gain.getId()){
            gl = 1;
        }
        else{
            gl = 2;
        }

        selectedActivity = activityLevel.getCheckedRadioButtonId();
        if(selectedActivity == sedentary.getId()){
            activityInt  = 1;
        }
        else if(selectedActivity == lActive.getId()){
            activityInt  = 2;
        }
        else if(selectedActivity == mActive.getId()){
            activityInt = 3;
        }
        else if(selectedActivity == vActive.getId()){
            activityInt = 4;
        }
        else{
            activityInt = 0;
        }

        if(gender.isChecked()){
            genderInt = 1;
        }
        else{
            genderInt = 2;
        }

        try{
            int age = Integer.parseInt(editAge.getText().toString());
            ageEx = true;
            if(age > 90 || age < 10){
                Toast.makeText(getApplicationContext(),"Please enter a valid age",Toast.LENGTH_LONG).show();
                ageEx = false;
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Please enter a valid age",Toast.LENGTH_LONG).show();
        }

        try{
            int weight = Integer.parseInt(editWeight.getText().toString());
            weightEx = true;
            if(weight > 400 || weight < 50){
                Toast.makeText(getApplicationContext(),"Please enter a valid weight",Toast.LENGTH_LONG).show();
                weightEx = false;
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Please enter a valid weight",Toast.LENGTH_LONG).show();
        }

        try{
            int height = Integer.parseInt(editHeight.getText().toString());
            heightEx = true;
            if(height > 90 || height < 24){
                Toast.makeText(getApplicationContext(),"Please enter a valid height",Toast.LENGTH_LONG).show();
                heightEx = false;
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Please enter a valid height",Toast.LENGTH_LONG).show();
        }

        if(activityInt == 0){
            Toast.makeText(getApplicationContext(),"Please select an activity level",Toast.LENGTH_LONG).show();
        }

        if(ageEx && heightEx && weightEx && activityInt > 0){
            Intent myIntent = new Intent(this, HomePage.class);
            startActivity(myIntent);
        }
    }

    public void setName(EditText name){
        editName = name;
    }

    public void setAge(EditText age){
        editAge = age;
    }

    public void setWeight(EditText weight){
        editWeight = weight;
    }

    public void setHeight(EditText height){
        editHeight = height;
    }

    public String getStringAge(){
        return editAge.getText().toString();
    }

    public String getStringWeight(){
        return editWeight.getText().toString();
    }

    public String getStringHeight(){
        return editHeight.getText().toString();
    }

    public String getStringGoal(){
        return Integer.toString(goalInt);
    }

    public String getName(){
        return editName.getText().toString();
    }

    public String getStringWeeks(){return Integer.toString(weekInt);}

    public String getGainOrLose(){
        if(gl == 1){
            return "Gain";
        }
        else{
            return "Lose";
        }
    }

    public int getAge(){
        return Integer.parseInt(editAge.getText().toString());
    }

    public int getHeight(){
        return Integer.parseInt(editHeight.getText().toString());
    }

    public int getWeight(){
        return Integer.parseInt(editWeight.getText().toString());
    }

    public int getGoal(){
        return goalInt;
    }

    public int getGoalWeek(){return weekInt;}

    public String getGender(){
        if(genderInt == 1){
            return "Male";
        }
        else{
            return "Female";
        }
    }

    public String getActivity(){
        if(activityInt == 1){
            return "Sedentary";
        }
        else if(activityInt == 2){
            return "Lightly Active";
        }
        else if(activityInt == 3){
            return "Moderately Active";
        }
        else{
            return "Very Active";
        }
    }
}
