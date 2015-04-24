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

    public static double ppw;
    public static int dailyvarcals;
    public static int caloriegoal;
    public static int tdee=0;
    public static int bmr;

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




        // CALORIE CALCULATIONS -------------------------------------------------------------------

        if(profileInfo.getString("Usergender", "").equalsIgnoreCase("Male")){
            bmr = (int) ((65 + (6.23*profileInfo.getInt("Userweight", 1)) + (12.7*profileInfo.getInt("Userheight", 1)) - (6.8*profileInfo.getInt("Userage", 1))) +0.5);
        }
        else{
            bmr = (int) ((655 + (4.35*profileInfo.getInt("Userweight", 1)) + (4.7*profileInfo.getInt("Userheight", 1)) - (4.7*profileInfo.getInt("Userage", 1))) +0.5);
        }

        String activity = profileInfo.getString("Useractivitylevel", "");
        switch(activity){
            case "S":
                tdee = (int)((bmr * 1.2) + 0.5);
                break;
            case "LA":
                tdee = (int)((bmr * 1.375) + 0.5);
                break;
            case "MA":
                tdee = (int)((bmr * 1.55) + 0.5);
                break;
            case "VA":
                tdee = (int)((bmr * 1.725) + 0.5);
                break;
        }

        ppw = (profileInfo.getInt("UserGoalPounds", 1))/(profileInfo.getInt("UserGoalWeeks", 1));
        dailyvarcals = (int)((ppw * 3500)/7);

        if( profileInfo.getString("UserGainOrLose", "").equalsIgnoreCase("Gain")){
            caloriegoal = tdee + dailyvarcals;
        }
        else{
            caloriegoal = tdee - dailyvarcals;
        }

        //Shared Preferences for Daily Exercises---------------------------------------------------

       /* SharedPreferences dailyExercises = getSharedPreferences("dailyExercises", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = dailyExercises.edit();

        editor2.putString("exercise1", " ");
        editor2.putString("exercise2", " ");
        editor2.putString("exercise3", " ");
        editor.putInt("dailyExerciseValue", 0);
        editor.putInt("counter", 0);
        editor2.commit();

*/
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

    public static String getCaloriesDaily(){
        return caloriesM;
    }


    public static int getCalorieGoal(){

        return caloriegoal;
    }

    public String getStringCalorieGoal(){

        return Integer.toString(getCalorieGoal());
    }

    public int getPoundsM(){
        return Integer.parseInt(editPounds.getText().toString());
    }

}