package com.example.fitbyte.fitbyte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class MyWorkout extends MenuNavigation {

    private NewWorkout newWorkout = new NewWorkout();
    private WorkoutMain ma = new WorkoutMain();

    TextView name;
    int i = 0; int l = 0;

    TextView exerciseName1;  TextView exerciseName5;
    TextView exerciseName2;  TextView exerciseName6;
    TextView exerciseName3;  TextView exerciseName7;
    TextView exerciseName4;  TextView exerciseName8;

    NumberPicker reps01;  NumberPicker weight01;
    NumberPicker reps02;  NumberPicker weight02;
    NumberPicker reps03;  NumberPicker weight03;
    NumberPicker reps04;  NumberPicker weight04;
    NumberPicker reps05;  NumberPicker weight05;

    int sets01; int sets02; int sets03; int sets04; int sets05;

    String[] reps = new String[]{"1 rep", "2 reps", "3 reps","4 reps","5 reps","6 reps","7 reps",
            "8 reps","9 reps","10 reps","11 reps","12 reps","13 reps","14 reps","15 reps","16 reps",
            "17 reps","18 reps","19 reps","20 reps","21 reps","22 reps","23 reps","24 reps","25 reps"};

    int getReps;

    String[] weights = new String[]{"5 lbs.", "10 lbs.","15 lbs.","20 lbs.","25 lbs.","30 lbs.",
            "35 lbs.","40 lbs.","45 lbs.","50 lbs.","55 lbs.","60 lbs.","65 lbs.","70 lbs.","75 lbs.",};

    int getWeight;

    TextView prev01;     TextView prev04;     TextView prev07;     TextView prev10;     TextView prev13;
    TextView prev02;     TextView prev05;     TextView prev08;     TextView prev11;     TextView prev14;
    TextView prev03;     TextView prev06;     TextView prev09;     TextView prev12;     TextView prev15;

    Button se01; Button se02; Button se03; Button se04; Button se05;

    LinearLayout layout01; LinearLayout layout02; LinearLayout layout03; LinearLayout layout04; LinearLayout layout05;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myworkout_layout);



        reps01 = (NumberPicker)findViewById(R.id.reps01);
        reps01.setMaxValue(1);
        reps01.setMaxValue(24);
        reps01.setDisplayedValues(reps);
        reps01.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getReps = newVal;
            }
        });

        weight01 = (NumberPicker)findViewById(R.id.weight01);
        weight01.setMinValue(1);
        weight01.setMaxValue(15);
        weight01.setDisplayedValues(weights);

        weight01.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getWeight = newVal;
            }
        });

        reps02 = (NumberPicker)findViewById(R.id.reps02);
        reps02.setMaxValue(1);
        reps02.setMaxValue(24);
        reps02.setDisplayedValues(reps);
        reps02.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getReps = newVal;
            }
        });

        weight02 = (NumberPicker)findViewById(R.id.weight02);
        weight02.setMinValue(1);
        weight02.setMaxValue(15);
        weight02.setDisplayedValues(weights);

        weight02.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getWeight = newVal;
            }
        });

        reps03 = (NumberPicker)findViewById(R.id.reps03);
        reps03.setMaxValue(1);
        reps03.setMaxValue(24);
        reps03.setDisplayedValues(reps);
        reps03.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getReps = newVal;
            }
        });

        weight03 = (NumberPicker)findViewById(R.id.weight03);
        weight03.setMinValue(1);
        weight03.setMaxValue(15);
        weight03.setDisplayedValues(weights);

        weight03.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getWeight = newVal;
            }
        });

        reps04 = (NumberPicker)findViewById(R.id.reps04);
        reps04.setMaxValue(1);
        reps04.setMaxValue(24);
        reps04.setDisplayedValues(reps);
        reps04.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getReps = newVal;
            }
        });

        weight04 = (NumberPicker)findViewById(R.id.weight04);
        weight04.setMinValue(1);
        weight04.setMaxValue(15);
        weight04.setDisplayedValues(weights);

        weight04.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getWeight = newVal;
            }
        });

        reps05 = (NumberPicker)findViewById(R.id.reps05);
        reps05.setMaxValue(1);
        reps05.setMaxValue(24);
        reps05.setDisplayedValues(reps);
        reps05.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getReps = newVal;
            }
        });

        weight05 = (NumberPicker)findViewById(R.id.weight05);
        weight05.setMinValue(1);
        weight05.setMaxValue(15);
        weight05.setDisplayedValues(weights);

        weight05.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getWeight = newVal;
            }
        });


        name = (TextView) findViewById(R.id.workoutName);
        name.setText(ma.n);

        exerciseName1 = (TextView) findViewById(R.id.tvNameWorkOut01);
        exerciseName2 = (TextView) findViewById(R.id.tvNameWorkOut02);
        exerciseName3 = (TextView) findViewById(R.id.tvNameWorkOut03);
        exerciseName4 = (TextView) findViewById(R.id.tvNameWorkOut04);
        exerciseName5 = (TextView) findViewById(R.id.tvNameWorkOut05);
        exerciseName6 = (TextView) findViewById(R.id.tvNameWorkOut06);
        exerciseName7 = (TextView) findViewById(R.id.tvNameWorkOut07);

        prev01 = (TextView)findViewById(R.id.prev01);
        prev02 = (TextView)findViewById(R.id.prev02);
        prev03 = (TextView)findViewById(R.id.prev03);
        prev04 = (TextView)findViewById(R.id.prev04);
        prev05 = (TextView)findViewById(R.id.prev05);
        prev06 = (TextView)findViewById(R.id.prev06);
        prev07 = (TextView)findViewById(R.id.prev07);
        prev08 = (TextView)findViewById(R.id.prev08);
        prev09 = (TextView)findViewById(R.id.prev09);
        prev10 = (TextView)findViewById(R.id.prev10);
        prev11 = (TextView)findViewById(R.id.prev11);
        prev12 = (TextView)findViewById(R.id.prev12);
        prev13 = (TextView)findViewById(R.id.prev13);
        prev14 = (TextView)findViewById(R.id.prev14);
        prev15 = (TextView)findViewById(R.id.prev15);

        se01 = (Button)findViewById(R.id.se01);
        se02 = (Button)findViewById(R.id.se02);
        se03 = (Button)findViewById(R.id.se03);
        se04 = (Button)findViewById(R.id.se04);
        se05 = (Button)findViewById(R.id.se05);

        layout01 = (LinearLayout)findViewById(R.id.layout01);
        layout02 = (LinearLayout)findViewById(R.id.layout02);
        layout03 = (LinearLayout)findViewById(R.id.layout03);
        layout04 = (LinearLayout)findViewById(R.id.layout04);
        layout05 = (LinearLayout)findViewById(R.id.layout05);


        switch (ma.k) {
            case 1:
                i = newWorkout.workoutList1.size();
                settingText(newWorkout.workoutList1);
                break;
            case 2:
                i = newWorkout.workoutList2.size();
                settingText(newWorkout.workoutList2);
                break;
            case 3:
                i = newWorkout.workoutList3.size();
                settingText(newWorkout.workoutList3);
                break;
            default:
                break;
        }
        l = ma.k;
    }

    protected void settingText(ArrayList list){
        switch (i) {
            case 1:
                exerciseName1.setText(list.get(0).toString());
                reps02.setVisibility(View.GONE);
                weight02.setVisibility(View.GONE);
                reps03.setVisibility(View.GONE);
                weight03.setVisibility(View.GONE);
                reps04.setVisibility(View.GONE);
                weight04.setVisibility(View.GONE);
                reps05.setVisibility(View.GONE);
                weight05.setVisibility(View.GONE);
                layout02.setVisibility(View.GONE);
                layout03.setVisibility(View.GONE);
                layout04.setVisibility(View.GONE);
                layout05.setVisibility(View.GONE);
                se02.setVisibility(View.GONE);
                se03.setVisibility(View.GONE);
                se04.setVisibility(View.GONE);
                se05.setVisibility(View.GONE);
                break;
            case 2:
                exerciseName1.setText(list.get(0).toString());
                exerciseName2.setText(list.get(1).toString());
                reps03.setVisibility(View.GONE);
                weight03.setVisibility(View.GONE);
                reps04.setVisibility(View.GONE);
                weight04.setVisibility(View.GONE);
                reps05.setVisibility(View.GONE);
                weight05.setVisibility(View.GONE);
                layout03.setVisibility(View.GONE);
                layout04.setVisibility(View.GONE);
                layout05.setVisibility(View.GONE);
                se03.setVisibility(View.GONE);
                se04.setVisibility(View.GONE);
                se05.setVisibility(View.GONE);
                break;
            case 3:
                exerciseName1.setText(list.get(0).toString());
                exerciseName2.setText(list.get(1).toString());
                exerciseName3.setText(list.get(2).toString());
                reps04.setVisibility(View.GONE);
                weight04.setVisibility(View.GONE);
                reps05.setVisibility(View.GONE);
                weight05.setVisibility(View.GONE);
                layout04.setVisibility(View.GONE);
                layout05.setVisibility(View.GONE);
                se04.setVisibility(View.GONE);
                se05.setVisibility(View.GONE);
                break;
            case 4:
                exerciseName1.setText(list.get(0).toString());
                exerciseName2.setText(list.get(1).toString());
                exerciseName3.setText(list.get(2).toString());
                exerciseName4.setText(list.get(3).toString());
                reps05.setVisibility(View.GONE);
                weight05.setVisibility(View.GONE);
                layout05.setVisibility(View.GONE);
                se05.setVisibility(View.GONE);
                break;
            case 5:
                exerciseName1.setText(list.get(0).toString());
                exerciseName2.setText(list.get(1).toString());
                exerciseName3.setText(list.get(2).toString());
                exerciseName4.setText(list.get(3).toString());
                exerciseName5.setText(list.get(4).toString());
                break;
            case 6:
                exerciseName1.setText(list.get(0).toString());
                exerciseName2.setText(list.get(1).toString());
                exerciseName3.setText(list.get(2).toString());
                exerciseName4.setText(list.get(3).toString());
                exerciseName5.setText(list.get(4).toString());
                exerciseName6.setText(list.get(5).toString());
                break;
            case 7:
                exerciseName1.setText(list.get(0).toString());
                exerciseName2.setText(list.get(1).toString());
                exerciseName3.setText(list.get(2).toString());
                exerciseName4.setText(list.get(3).toString());
                exerciseName5.setText(list.get(4).toString());
                exerciseName6.setText(list.get(5).toString());
                exerciseName7.setText(list.get(6).toString());
                break;
            default:
                break;
        }
    }

    public void finishWorkout(View view){
        Intent myIntent = new Intent(this, WorkoutMain.class);
        startActivity(myIntent);
    }

    public void saveEntry01(View view) {
        sets01++;
        switch (sets01) {
            case 1:
                prev01.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 2:
                prev02.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 3:
                prev03.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            default:
                break;
        }
    }
    public void saveEntry02(View view) {
        sets02++;
        switch (sets02) {
            case 1:
                prev04.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 2:
                prev05.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 3:
                prev06.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            default:
                break;
        }
    }
    public void saveEntry03(View view) {
        sets03++;
        switch (sets03) {
            case 1:
                prev07.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 2:
                prev08.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 3:
                prev09.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            default:
                break;
        }
    }
    public void saveEntry04(View view) {
        sets04++;
        switch (sets04) {
            case 1:
                prev10.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 2:
                prev11.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 3:
                prev12.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            default:
                break;
        }
    }
    public void saveEntry05(View view) {
        sets05++;
        switch (sets05) {
            case 1:
                prev13.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 2:
                prev14.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            case 3:
                prev15.setText(getReps+1 + " reps x " + getWeight * 5 + " lbs.");
                break;
            default:
                break;
        }
    }

}
