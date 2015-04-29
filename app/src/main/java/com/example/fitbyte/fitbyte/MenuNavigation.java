package com.example.fitbyte.fitbyte;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;

/**
 * Created by user on 3/18/2015.
 */
public class MenuNavigation extends ActionBarActivity {

    DrawerLayout drawerLayout;
    ListView listView;
    String[] menu;
    ArrayAdapter<String> adapter;
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private ActionBarDrawerToggle mDrawerToggle;
    private static final String open = "open";
    private static final String close = "close";

    protected void onCreateDrawer() {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.menu_navigation);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        listView = (ListView)findViewById(R.id.list_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_logo,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            public void onDrawerClosed(View view){
            super.onDrawerClosed(view);

            }

            public void onDrawerOpened(View drawerView){
            super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(mDrawerToggle);



        menu = new String[]{"Home", "Diary","Profile", "Workouts","Calendar","Reminders"};
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        listView.setAdapter(adapter);
        listView.setSelector(android.R.color.holo_blue_dark);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                displayView(position);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_logo);



        //getActionBar().setDisplayHomeAsUpEnabled(true);
       // getActionBar().setHomeButtonEnabled(true);
    }

   /** protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    } */

    public boolean onCreateOptionsMenu(Menu menu){
    MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;


        } return super.onOptionsItemSelected(item);
    }

    private void displayView(int position){
        switch(position){
            case 0 :
                startActivity(new Intent(this,Homepage.class));
                break;
            case 1 :
                startActivity(new Intent(this,Diary.class));
                break;
            case 2 :
                startActivity(new Intent(this,EditProfile.class));
                break;
            case 3 :
                startActivity(new Intent(this, WorkoutMain.class));
                break;
            case 4 :
                startActivity(new Intent(this, Calendar.class));
                break;
            case 5:
                startActivity(new Intent(this, Reminders.class));
            default:break;
        }
    }
}
