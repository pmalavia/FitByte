package com.example.fitbyte.fitbyte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class searchListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView lv;
    private ArrayList<foodItem> searchedItemList;
    public final static String SELECTED_ITEM = "com.example.amirsaifi.ITEM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        Intent intent = getIntent();
        //Copies "searchedItems" ArrayList from searchPage into "searchedItemList"
        //Used to populate ListView
        searchedItemList = intent.getParcelableArrayListExtra(searchPage.SEARCHED_ITEMS);

        //Default ArrayAdapter for class "foodItem"
        // uses "foodItem" toString() to populate text in each ListView cell
        ArrayAdapter<foodItem> arrlistAdapter = new ArrayAdapter<foodItem>(this, R.layout.list_items, searchedItemList);
        lv = (ListView) findViewById(R.id.foodItems);
        lv.setAdapter(arrlistAdapter);
        lv.setOnItemClickListener(this);

        /*
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPos = position;
                foodItem selectedItem = (foodItem) lv.getItemAtPosition(itemPos);

                Intent i = new Intent(searchListActivity.this, itemDetailActivity.class);
                i.putExtra("SELECTED_ITEM", selectedItem);
                startActivity(i);
            }
        });
        */


    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_list, menu);
        return true;
    }
*/
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int itemPos = position;
        foodItem selectedItem = (foodItem) lv.getItemAtPosition(itemPos);

        Intent i = new Intent(searchListActivity.this, itemDetailActivity.class);
        i.putExtra(SELECTED_ITEM, selectedItem);
        startActivity(i);


    }
}
