package com.example.fitbyte.fitbyte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
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

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }*/

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
