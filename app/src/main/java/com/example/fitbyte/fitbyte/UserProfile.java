package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    public static EditText editPounds;
    public static EditText editWeeks;

    public static String editGoal;
    public static String editActivityLevel;
    public static String editGender;


    public static double ppw;
    public static int dailyvarcals;
    public static int caloriegoal;
    public static int tdee=0;
    public static int bmr;

    public static RadioGroup activityLevel;
    public static RadioButton sedentary;
    public static RadioButton la;
    public static RadioButton ma;
    public static RadioButton va;

    public static RadioGroup gender;
    public static RadioButton male;
    public static RadioButton female;

    public static RadioGroup goal;
    public static RadioButton gain;
    public static RadioButton lose;

    private ImageView profilePic;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static Bitmap bitmap;
    static public String nameM, genderM, activityM, goalM, caloriesM;
    static public int poundsM, weeksM, weightM, heightM, ageM;
    private int weeks, pounds;


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

        activityLevel = (RadioGroup)findViewById(R.id.activityLevel);
        sedentary = (RadioButton)findViewById(R.id.sedentary);
        la = (RadioButton)findViewById(R.id.lightlyActive);
        ma = (RadioButton)findViewById(R.id.moderatelyActive);
        va = (RadioButton)findViewById(R.id.veryActive);

        gender = (RadioGroup)findViewById(R.id.gender);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);

        goal = (RadioGroup)findViewById(R.id.goal);
        gain = (RadioButton)findViewById(R.id.gain);
        lose = (RadioButton)findViewById(R.id.lose);

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
        editPounds = (EditText) findViewById(R.id.poundsGoal);
        editWeeks = (EditText) findViewById(R.id.weeksGoal);

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
        try{
            genderEx = true;
            if(male.isChecked()){
                editGender = "Male";
            }
            else if(female.isChecked()){
                editGender = "Female";
            }
            else{
                Toast.makeText(getApplicationContext(), "Please enter a gender", Toast.LENGTH_LONG).show();
                genderEx = false;
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a gender)", Toast.LENGTH_LONG).show();
        }

        // CHECK ACTIVITY LEVEL
        try{
            activityEx = true;
            if(sedentary.isChecked()){
                editActivityLevel = "S";
            }
            else if(la.isChecked()){
                editActivityLevel = "LA";
            }
            else if(ma.isChecked()){
                editActivityLevel = "MA";
            }
            else if(va.isChecked()){
                editActivityLevel = "VA";
            }
            else{
                Toast.makeText(getApplicationContext(), "Please enter an activity level", Toast.LENGTH_LONG).show();
                activityEx = false;
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter an activity level", Toast.LENGTH_LONG).show();
        }


        // CHECK Pounds
        try {
            pounds = Integer.parseInt(editPounds.getText().toString());
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
            weeks = Integer.parseInt(editWeeks.getText().toString());
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
        try{
            goalEx = true;
            if(gain.isChecked()){
                editGoal = "Gain";
            }
            else if(lose.isChecked()){
                editGoal = "Lose";
            }
            else{
                Toast.makeText(getApplicationContext(), "Please enter a goal", Toast.LENGTH_LONG).show();
                goalEx = false;
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter a goal", Toast.LENGTH_LONG).show();
        }

        if ((((double)pounds/weeks)>2)&&(lose.isChecked())){
            Toast.makeText(getApplicationContext(), "It is not recommended to lose more that 2 pounds a week. Please edit your goal",
                    Toast.LENGTH_LONG).show();
            poundsEx = false;
            weeksEx = false;
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
        editor.putString("Usergender", editGender);
        editor.putInt("Usergoalpounds", Integer.parseInt(editPounds.getText().toString()));
        editor.putInt("Userweeks", Integer.parseInt(editWeeks.getText().toString()));
        editor.putString("Useractivitylevel", editActivityLevel);
        editor.putString("Usergoal", editGoal);
        editor.putString("calorieString",getStringCalorieGoal());
        editor.commit();


        nameM = profileInfo.getString("Username", "");
        ageM = profileInfo.getInt("Userage", 1);
        heightM = profileInfo.getInt("Userheight", 1);
        weightM = profileInfo.getInt("Userweight", 1);
        genderM = profileInfo.getString("Usergender", "");
        activityM = profileInfo.getString("Useractivitylevel", "");
        weeksM = profileInfo.getInt("Userweeks", 1);
        poundsM = profileInfo.getInt("Usergoalpounds", 1);
        goalM = profileInfo.getString("Usergoal", "");
        caloriesM = profileInfo.getString("calorieString", "");



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

        ppw = (profileInfo.getInt("Usergoalpounds", 1))/(profileInfo.getInt("Userweeks", 1));
        dailyvarcals = (int)((ppw * 3500)/7);

        if( profileInfo.getString("Usergoal", "").equalsIgnoreCase("Gain")){
            caloriegoal = tdee + dailyvarcals;
        }
        else{
            caloriegoal = tdee - dailyvarcals;
        }

        //Shared Preferences for Daily Exercises---------------------------------------------------

        SharedPreferences dailyExercises = getSharedPreferences("dailyExercises", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = dailyExercises.edit();

        editor2.putString("exercise1", " ");
        editor2.putString("exercise2", " ");
        editor2.putString("exercise3", " ");
        editor.putInt("dailyExerciseValue", 0);
        editor.putInt("counter", 0);
        editor2.commit();


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

    public void showAlert(View view) {AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("How would you describe your daily activity level?\n\n" +
                "S = Sedentary\nSA = Slightly Active\nMA = Moderately Active\nVA = Very Active")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        myAlert.show();
    }
}