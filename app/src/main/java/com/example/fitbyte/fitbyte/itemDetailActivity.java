package com.example.fitbyte.fitbyte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class itemDetailActivity extends ActionBarActivity {

    private foodItem selectedItem;
    TextView itemName;
    TextView brandName;
    TextView calories;
    TextView carbs;
    TextView fat;
    TextView protein;
    TextView srvSize;
    TextView srvUnit;
    TextView srvWeight;
    public static int calorieTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent intent = getIntent();
        selectedItem = intent.getParcelableExtra(searchListActivity.SELECTED_ITEM);

        itemName = (TextView) findViewById(R.id.itemName);
        brandName = (TextView) findViewById(R.id.foodBrandName);
        calories = (TextView) findViewById(R.id.foodCalories);
        carbs = (TextView) findViewById(R.id.foodCarbs);
        fat = (TextView) findViewById(R.id.foodFat);
        protein = (TextView) findViewById(R.id.foodProtein);
        srvSize = (TextView) findViewById(R.id.servingSize);
        srvUnit = (TextView) findViewById(R.id.servingUnit);
        srvWeight = (TextView) findViewById(R.id.servingWeight);

        itemName.setText(selectedItem.getItemName());
        brandName.setText(selectedItem.getBrandName());
        calories.setText(selectedItem.getCalories()+"");
        carbs.setText(selectedItem.getCarbs()+"");
        fat.setText(selectedItem.getFat()+"");
        protein.setText(selectedItem.getProtein()+"");
        srvSize.setText(selectedItem.getServingSize()+"");
        srvUnit.setText(selectedItem.getServingUnit());
        srvWeight.setText(selectedItem.getServingWeightinGrams()+"");

    }

    public void itemAdded(View view){
        Diary diary = new Diary();
        boolean breakfast = diary.getBreakfast();
        boolean lunch = diary.getLunch();
        boolean dinner = diary.getDinner();

        SharedPreferences foodLogs = getSharedPreferences("foodLogs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = foodLogs.edit();

        String food1;
        String food2;
        String food3;
        String food4;
        String food5;
        String food6;

        food1 = foodLogs.getString("item1", "");
        food2 = foodLogs.getString("item2", "");
        food3 = foodLogs.getString("item3", "");
        food4 = foodLogs.getString("item4", "");
        food5 = foodLogs.getString("item5", "");
        food6 = foodLogs.getString("item6", "");

        if(breakfast){
            if(food1.equals("")){
                calorieTotal += selectedItem.getCalories();
                editor.putInt("CaloriesConsumed",calorieTotal);
                editor.putString("item1", selectedItem.getItemName() + " gains " + selectedItem.getCalories() + " calories");
                editor.commit();
            }
            else if(food2.equals("")){
                calorieTotal += selectedItem.getCalories();
                editor.putInt("CaloriesConsumed",calorieTotal);
                editor.putString("item2", selectedItem.getItemName() + " gains " + selectedItem.getCalories() + " calories");
                editor.commit();
            }
        }
        else if(lunch){
            if(food3.equals("")){
                calorieTotal += selectedItem.getCalories();
                editor.putInt("CaloriesConsumed",calorieTotal);
                editor.putString("item3", selectedItem.getItemName() + " gains " + selectedItem.getCalories() + " calories");
                editor.commit();
            }
            else if(food4.equals("")){
                calorieTotal += selectedItem.getCalories();
                editor.putInt("CaloriesConsumed",calorieTotal);
                editor.putString("item4", selectedItem.getItemName() + " gains " + selectedItem.getCalories() + " calories");
                editor.commit();
            }
        }
        else if(dinner){
            if(food5.equals("")){
                calorieTotal += selectedItem.getCalories();
                editor.putInt("CaloriesConsumed",calorieTotal);
                editor.putString("item5", selectedItem.getItemName() + " gains " + selectedItem.getCalories() + " calories");
                editor.commit();
            }
            else if(food6.equals("")){
                calorieTotal += selectedItem.getCalories();
                editor.putInt("CaloriesConsumed",calorieTotal);
                editor.putString("item6", selectedItem.getItemName() + " gains " + selectedItem.getCalories() + " calories");
                editor.commit();
            }
        }

        Intent myIntent = new Intent(this,Diary.class);
        startActivity(myIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
