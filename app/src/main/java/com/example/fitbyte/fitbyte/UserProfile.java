package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
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
    public static EditText editGender;
    public static EditText editActivityLevel;
    public static EditText editPounds;
    public static EditText editWeeks;
    public static EditText editGoal;


    private ImageView profilePic;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static Bitmap bitmap;
    static public String nameM, genderM, activityM, goalM, caloriesM;
    static public int poundsM, weeksM, weightM, heightM, ageM;


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
        boolean genderEx = false;
        boolean activityEx = false;
        boolean poundsEx = false;
        boolean weeksEx = false;
        boolean goalEx = false;

        editName = (EditText) findViewById(R.id.name);
        editAge = (EditText) findViewById(R.id.age);
        editWeight = (EditText) findViewById(R.id.weight);
        editHeight = (EditText) findViewById(R.id.height);
        editGender = (EditText) findViewById(R.id.gender);
        editActivityLevel = (EditText) findViewById(R.id.activityLevel);
        editPounds = (EditText) findViewById(R.id.poundsGoal);
        editWeeks = (EditText) findViewById(R.id.weeksGoal);
        editGoal = (EditText) findViewById(R.id.goal);

        //  CHECK AGE
        try {
            int age = Integer.parseInt(editAge.getText().toString());
            ageEx = true;
            if (age > 90 || age < 10) {
                editAge.setTypeface(null, Typeface.BOLD);
                editAge.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid age", Toast.LENGTH_LONG).show();
                ageEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid age", Toast.LENGTH_LONG).show();
        }

        // CHECK WEIGHT
        try {
            int weight = Integer.parseInt(editWeight.getText().toString());
            weightEx = true;
            if (weight > 400 || weight < 50) {
                editWeight.setTypeface(null, Typeface.BOLD);
                editWeight.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid weight", Toast.LENGTH_LONG).show();
                weightEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid weight", Toast.LENGTH_LONG).show();
        }

        // CHECK HEIGHT
        try {
            int height = Integer.parseInt(editHeight.getText().toString());
            heightEx = true;
            if (height > 90 || height < 24) {
                editHeight.setTypeface(null, Typeface.BOLD);
                editHeight.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid height", Toast.LENGTH_LONG).show();
                heightEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid height", Toast.LENGTH_LONG).show();
        }

        // CHECK GENDER
        try {
            String gender = editGender.getText().toString();
            genderEx = true;
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                // do nothing
            }
            else{
                editGender.setTypeface(null, Typeface.BOLD);
                editGender.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid gender (Male or Female)", Toast.LENGTH_LONG).show();
                genderEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid gender (Male or Female)", Toast.LENGTH_LONG).show();
        }

        // CHECK ACTIVITY LEVEL
        try {
            String activityLevel = editActivityLevel.getText().toString();
            activityEx = true;
            if (activityLevel.equalsIgnoreCase("S") || activityLevel.equalsIgnoreCase("LA") || activityLevel.equalsIgnoreCase("MA")
                    || activityLevel.equalsIgnoreCase("VA")) {
                //  do nothing
            }
            else{
                editActivityLevel.setTypeface(null, Typeface.BOLD);
                editActivityLevel.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid activity level (S,LA,MA,or VA)", Toast.LENGTH_LONG).show();
                activityEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid activity level (S,LA,MA,or VA)", Toast.LENGTH_LONG).show();
        }

        // CHECK Pounds
        try {
            int pounds = Integer.parseInt(editPounds.getText().toString());
            poundsEx = true;
            if (pounds > 400 || pounds < 1) {
                editPounds.setTypeface(null, Typeface.BOLD);
                editPounds.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid entry for pounds", Toast.LENGTH_LONG).show();
                poundsEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid entry for pounds", Toast.LENGTH_LONG).show();
        }

        // CHECK Weeks
        try {
            int weeks = Integer.parseInt(editWeeks.getText().toString());
            weeksEx = true;
            if (weeks > 400 || weeks < 1) {
                editWeeks.setTypeface(null, Typeface.BOLD);
                editWeeks.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid entry for weeks", Toast.LENGTH_LONG).show();
                weeksEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid entry for weeks", Toast.LENGTH_LONG).show();
        }

        // CHECK goal
        try {
            String goal = editGoal.getText().toString();
            goalEx = true;
            if (goal.equalsIgnoreCase("Lose") || goal.equalsIgnoreCase("Gain")) {
                //  do nothing
            }
            else{
                editGoal.setTypeface(null, Typeface.BOLD);
                editGoal.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Please enter a valid goal (Gain or Lose)", Toast.LENGTH_LONG).show();
                goalEx = false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a valid goal (gain or lose", Toast.LENGTH_LONG).show();
        }

        if (ageEx && heightEx && weightEx && genderEx && activityEx && poundsEx && weeksEx && goalEx) {
            save();
            Intent myIntent = new Intent(this, Homepage.class);
            startActivity(myIntent);
        }
    }

    public void save() {
        CalorieGoal calorieGoal = new CalorieGoal();
        SharedPreferences profileInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE); //save all username info to internal memory
        SharedPreferences.Editor editor = profileInfo.edit();

        editor.putString("Username", editName.getText().toString());
        editor.putInt("Userage", Integer.parseInt(editAge.getText().toString()));
        editor.putInt("Userweight", Integer.parseInt(editWeight.getText().toString()));
        editor.putInt("Userheight", Integer.parseInt(editHeight.getText().toString()));
        editor.putString("Usergender", editGender.getText().toString());
        editor.putInt("Usergoalpounds", Integer.parseInt(editPounds.getText().toString()));
        editor.putInt("Userweeks", Integer.parseInt(editWeeks.getText().toString()));
        editor.putString("Useractivitylevel", editActivityLevel.getText().toString());
        editor.putString("Usergoal", editGoal.getText().toString());
        editor.putString("calorieString",getStringCalorieGoal());
        editor.commit();

        nameM = profileInfo.getString("Username", "");
        ageM = profileInfo.getInt("Userage", 1);
        heightM = profileInfo.getInt("Userheight", 1);
        weightM = profileInfo.getInt("Userweight", 1);
        genderM = profileInfo.getString("Usergender", "");
        activityM = profileInfo.getString("Useractivitylevel", "");
        weeksM = profileInfo.getInt("Userweeks", 1);
        poundsM = profileInfo.getInt("Usergainorlose", 1);
        goalM = profileInfo.getString("Usergoal", "");
        caloriesM = profileInfo.getString("calorieString", "");

    }

    public String getName1() {
        return nameM;
    }
    public String getGender1() {
        return genderM;
    }
    public int getWeight1() {
        return weightM;
    }
    public int getAge1() {
        return ageM;
    }
    public int getGoalWeeks1() {
        return weeksM;
    }
    public int getGoalPounds1() {
        return poundsM;
    }
    public int getHeight1() {
        return heightM;
    }
    public String getActivity1() {
        return activityM;
    }
    public String getGoal1() {
        return goalM;
    }
    public String getName() {
        return editName.getText().toString();
    }

    public String getCaloriesDaily(){
        return caloriesM;
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


        ppw = (profileInfo.getInt("Userintgoal", 0))/(profileInfo.getInt("Userweeks", 0));
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