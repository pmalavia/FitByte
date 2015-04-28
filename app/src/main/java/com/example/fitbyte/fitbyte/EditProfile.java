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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class EditProfile extends Activity {

    UserProfile up = new UserProfile();

    public static EditText editName;
    public static EditText editAge;
    public static EditText editWeight;
    public static EditText editHeight;
    public static EditText editPounds;
    public static EditText editWeeks;


    public static RadioButton sedentary;
    public static RadioButton la;
    public static RadioButton ma;
    public static RadioButton va;

    public static RadioButton gain;
    public static RadioButton lose;
    public static RadioButton male;
    public static RadioButton female;

    public static String editActivityLevel;
    public static String editGoal;
    public static String editGender;

    private ImageView profilePic;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static Bitmap bitmap;
    public static boolean visited = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_layout);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        SharedPreferences userInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        sedentary = (RadioButton)findViewById(R.id.sedentary);
        la = (RadioButton)findViewById(R.id.lightlyActive);
        ma = (RadioButton)findViewById(R.id.moderatelyActive);
        va = (RadioButton)findViewById(R.id.veryActive);

        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);

        gain = (RadioButton)findViewById(R.id.gain);
        lose = (RadioButton)findViewById(R.id.lose);

        if(visited){
            profilePic.setImageBitmap(bitmap);
        }
        else {
            profilePic.setImageBitmap(up.bitmap);
        }

        editWeeks = (EditText) findViewById(R.id.weeksGoal);
        editWeeks.setText(userInfo.getInt("Userweeks", 1) + "");

        editPounds = (EditText) findViewById(R.id.poundsGoal);
        editPounds.setText(userInfo.getInt("Usergoalpounds", 1) + "");

        editName = (EditText) findViewById(R.id.name);
        editName.setText(userInfo.getString("Username", ""));

        editAge = (EditText) findViewById(R.id.age);
        editAge.setText(userInfo.getInt("Userage", 1) + "");

        editWeight = (EditText) findViewById(R.id.weight);
        editWeight.setText(userInfo.getInt("Userweight", 1) + "");

        editHeight = (EditText) findViewById(R.id.height);
        editHeight.setText(userInfo.getInt("Userheight", 1) + "");

        switch(userInfo.getString("Usergoal", "")){
            case "Gain":
                gain.setChecked(true);
                break;
            default:
                lose.setChecked(true);
                break;
        }

        switch(userInfo.getString("Usergender", "")){
            case "Male":
                male.setChecked(true);
                break;
            default:
                female.setChecked(true);
                break;
        }

        switch(userInfo.getString("Useractivitylevel","")){
            case "S":
                sedentary.setChecked(true);
                break;
            case "LA":
                la.setChecked(true);
                break;
            case "MA":
                ma.setChecked(true);
                break;
            default:
                va.setChecked(true);
                break;
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

        if (ageEx && heightEx && weightEx && genderEx && activityEx && poundsEx && weeksEx && goalEx) {
            Intent myIntent = new Intent(this, Homepage.class);
            startActivity(myIntent);
        }

        SharedPreferences userInfo = getSharedPreferences("UserInfo", Context.CONTEXT_IGNORE_SECURITY);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("Username", editName.getText().toString());
        editor.putInt("Userage", Integer.parseInt(editAge.getText().toString()));
        editor.putInt("Userweight", Integer.parseInt(editWeight.getText().toString()));
        editor.putInt("Userheight", Integer.parseInt(editHeight.getText().toString()));
        editor.putString("Usergender", editGender);
        editor.putInt("Userweeks", Integer.parseInt(editWeeks.getText().toString()));
        editor.putString("Usergoal", editGoal);
        editor.putInt("Usergoalpounds", Integer.parseInt(editPounds.getText().toString()));
        editor.putString("Useractivitylevel", editActivityLevel);
       // editor.putString("calorieString",calorieGoal.getStringCalorieGoal());
        editor.commit();
    }

    public void dispatchTakePictureIntent(View view) {
        visited = true;
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

    public void showHelp(View view) {AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
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