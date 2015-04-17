package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView profilePic;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static Bitmap bitmap;
    static public String nameM, ageM, heightM, weightM, genderM, activityM, weightGoalM, weekGoalM, gainOrloseM;
    static public int goalM;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        SharedPreferences visit = getSharedPreferences("Visit2", Context.MODE_PRIVATE);
         //this will make it so this page only appears once, the very first time
         SharedPreferences.Editor editor = visit.edit();

         if(visit.getBoolean("activity_executed", false)){
         Intent intent = new Intent(this, Homepage.class);
         startActivity(intent);
         finish();
         }
         else{

         editor.putBoolean("activity_executed", true);
         editor.commit();
         }

        profilePic = (ImageView) findViewById(R.id.profilePic);
        goal = (NumberPicker) findViewById(R.id.numberPicker); //weight goal
        goal.setMinValue(5);
        goal.setMaxValue(150);

        goal.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() { //weight goal
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                goalInt = newVal;
            }
        });

        weeks = (NumberPicker) findViewById(R.id.numberPicker2);
        weeks.setMinValue(1);
        weeks.setMaxValue(52);

        weeks.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                weekInt = newVal;
            }
        });


    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            profilePic.setImageBitmap(bitmap);
        }
    }

    public void submitClicked(View view) {
        boolean ageEx = false;
        boolean heightEx = false;
        boolean weightEx = false;
        editName = (EditText) findViewById(R.id.name);
        editAge = (EditText) findViewById(R.id.age);
        editWeight = (EditText) findViewById(R.id.weight);
        editHeight = (EditText) findViewById(R.id.height);
        gender = (Switch) findViewById(R.id.switch1);
        activityLevel = (RadioGroup) findViewById(R.id.radioGroup);
        sedentary = (RadioButton) findViewById(R.id.rb01);
        lActive = (RadioButton) findViewById(R.id.rb02);
        mActive = (RadioButton) findViewById(R.id.rb03);
        vActive = (RadioButton) findViewById(R.id.rb04);
        gain = (RadioButton) findViewById(R.id.gain);
        lose = (RadioButton) findViewById(R.id.lose);
        gainGroup = (RadioGroup) findViewById(R.id.gainORlose);


        gainInt = gainGroup.getCheckedRadioButtonId();
        if (gainInt == gain.getId()) {
            gl = 1;
        } else {
            gl = 2;
        }

        selectedActivity = activityLevel.getCheckedRadioButtonId();
        if (selectedActivity == sedentary.getId()) {
            activityInt = 1;
        } else if (selectedActivity == lActive.getId()) {
            activityInt = 2;
        } else if (selectedActivity == mActive.getId()) {
            activityInt = 3;
        } else if (selectedActivity == vActive.getId()) {
            activityInt = 4;
        } else {
            activityInt = 0;
        }

        if (gender.isChecked()) {
            genderInt = 1;
        } else {
            genderInt = 2;
        }

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

        if (activityInt == 0) {
            Toast.makeText(getApplicationContext(), "Please select an activity level", Toast.LENGTH_LONG).show();
        }


        if (ageEx && heightEx && weightEx && activityInt  > 0) {


            save();
            Intent myIntent = new Intent(this, Homepage.class);
            startActivity(myIntent);
        }


    }

    public void save() {

       SharedPreferences profileInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE); //save all username info to internal memory
        SharedPreferences.Editor editor = profileInfo.edit();

        editor.putString("Username", editName.getText().toString());
        editor.putString("Userage", editAge.getText().toString());
        editor.putString("Userweight", editWeight.getText().toString());
        editor.putString("Userheight", editHeight.getText().toString());
        editor.putString("Usergender", getGender());
        editor.putString("Usergoalweeks", getStringWeeks());
        editor.putString("Userweightgoal", getStringGoal());
        editor.putInt("Userweeks", getGoalWeek());
        editor.putString("Useractivitylevel", getActivity());
        editor.putString("Usergainorlose", getGainOrLose());
        editor.putInt("Userintgoal", getGoal());
        editor.putString("calorieString", getStringCalorieGoal());
        editor.putInt("calorieInt", getCalorieGoal());
        editor.commit();

        nameM = profileInfo.getString("Username", "");
        ageM = profileInfo.getString("Userage", "");
        heightM = profileInfo.getString("Userheight", "");
        weightM = profileInfo.getString("Userweight", "");
        genderM = profileInfo.getString("Usergender", "");
        activityM = profileInfo.getString("Useractivitylevel", "");
        weightGoalM = profileInfo.getString("Userweightgoal", "");
        weekGoalM = profileInfo.getString("Usergoalweeks", "");
        gainOrloseM = profileInfo.getString("Usergainorlose", "");
        goalM = profileInfo.getInt("Userintgoal", 0);

    }

    public String getName1() {
        return nameM;
    }

    public String getGender1() {
        return genderM;
    }

    public String getWeight1() {
        return weightM;
    }

    public String getAge1() {
        return ageM;
    }

    public String getGoalWeeks1() {
        return weekGoalM;
    }

    public String getGoalWeight1() {
        return weightGoalM;
    }

    public String getHeight1() {
        return heightM;
    }

    public String getActivity1() {
        return activityM;
    }

    public int getGoal1() {
        return goalM;
    }

    public String getGainOrLose1() {
        return gainOrloseM;
    }
    //-------------------------------------End of get memory variables ^^^^^^^^^^^^^^^^^^^^^^^

    public void setName(EditText name) {
        editName = name;
    }

    public void setAge(EditText age) {
        editAge = age;
    }

    public void setWeight(EditText weight) {
        editWeight = weight;
    }

    public void setHeight(EditText height) {
        editHeight = height;
    }

    public String getStringAge() {
        return editAge.getText().toString();
    }

    public String getStringWeight() {
        return editWeight.getText().toString();
    }

    public String getStringHeight() {
        return editHeight.getText().toString();
    }

    public String getStringGoal() {
        return Integer.toString(goalInt);
    }

    public String getName() {
        return editName.getText().toString();
    }

    public String getStringWeeks() {
        return Integer.toString(weekInt);
    }

    public String getGainOrLose() {
        if (gl == 1) {
            return "Gain";
        } else {
            return "Lose";
        }
    }

    public int getAge() {
        return Integer.parseInt(editAge.getText().toString());
    }

    public int getHeight() {
        return Integer.parseInt(editHeight.getText().toString());
    }

    public int getWeight() {
        return Integer.parseInt(editWeight.getText().toString());
    }

    public int getGoal() {
        return goalInt;
    }

    public int getGoalWeek() {
        return weekInt;
    }

    public String getGender() {
        if (genderInt == 1) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public String getActivity() {
        if (activityInt == 1) {
            return "Sedentary";
        } else if (activityInt == 2) {
            return "Lightly Active";
        } else if (activityInt == 3) {
            return "Moderately Active";
        } else {
            return "Very Active";
        }

    }
//-----------------------------------calorie methods ------------------------------------------------
    int calcBMR(){
        SharedPreferences profileInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        int bmr;
        //String gender = getGender1();

        //int weight = Integer.parseInt(getWeight1());
        //int weight = Integer.parseInt(userInfo1.getString("Userweight", "")); //convert string to int
        //int age = Integer.parseInt(getAge1());
        //int age = Integer.parseInt(userInfo1.getString("Userage", ""));
        //int height = Integer.parseInt(getHeight1());
        //int height = Integer.parseInt(userInfo1.getString("Userheight", ""));

        if(profileInfo.getString("Usergender", "").equals("Male")){
            bmr = (int) ((65 + (6.23*Integer.parseInt(profileInfo.getString("Userweight", ""))) + (12.7*Integer.parseInt(profileInfo.getString("Userheight", ""))) - (6.8*Integer.parseInt(profileInfo.getString("Userage", "")))) +0.5);
        }
        else{
            bmr = (int) ((655 + (4.35*Integer.parseInt(profileInfo.getString("Userweight", ""))) + (4.7*Integer.parseInt(profileInfo.getString("Userheight", ""))) - (4.7*Integer.parseInt(profileInfo.getString("Userage", "")))) +0.5);
        }
        return bmr;
    }

    public int getBMR(){

        return calcBMR();
    }

    int calcTDEE(){
        SharedPreferences userInfo1 = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        int tdee=0;
        int bmr = getBMR();
       // String activity = getActivity1();
        String activity = userInfo1.getString("Useractivitylevel", "");
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
        SharedPreferences profileInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        // int weeks = Integer.parseInt(getGoalWeeks1());
        //int weeks = userInfo1.getInt("Userweeks", 0);
        //int pounds = getGoal1();
        //int pounds = userInfo1.getInt("Userintgoal", 0);
        double ppw;
        int dailyvarcals;
       // String goal = getGainOrLose1();
        int caloriegoal;


        ppw = profileInfo.getInt("Userintgoal", 0)/profileInfo.getInt("Userweeks", 0);
        dailyvarcals = (int)((ppw * 3500)/7);


        if( profileInfo.getString("Usergainorlose", "").equals("Gain")){
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
