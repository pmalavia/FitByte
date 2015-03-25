package com.example.fitbyte.fitbyte;

public class CalorieGoal {
    UserProfile u = new UserProfile();
    int calcBMR(){
        int bmr;
        String gender = u.getGender();
        int weight = u.getWeight();
        int age = u.getAge();
        int height = u.getHeight();
        if(gender.equals("Male")){
            bmr = (int) ((65 + (6.23*weight) + (12.7*height) - (6.8*age)) +0.5);
        }
        else{
            bmr = (int) ((655 + (4.35*weight) + (4.7*height) - (4.7*age)) +0.5);
        }
        return bmr;
    }

    public int getBMR(){

        return calcBMR();
    }

    int calcTDEE(){
        int tdee=0;
        int bmr = getBMR();
        String activity = u.getActivity();
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
        int weeks = u.getGoalWeek();
        int pounds = u.getGoal();
        double ppw;
        int dailyvarcals;
        String goal = u.getGainOrLose();
        int caloriegoal;


        ppw = pounds/weeks;
        dailyvarcals = (int)((ppw * 3500)/7);


        if(goal.equals("Gain")){
            caloriegoal = getTDEE() + dailyvarcals;
        }
        else{
            caloriegoal = getTDEE() - dailyvarcals;
        }

        return caloriegoal;

    }

    public int getCalorieGoal(){

        return calcCalorieGoal();
    }

    public String getStringCalorieGoal(){

        return Integer.toString(calcCalorieGoal());
    }

}

