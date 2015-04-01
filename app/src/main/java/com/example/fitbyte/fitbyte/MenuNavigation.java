package com.example.fitbyte.fitbyte;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by user on 3/18/2015.
 */
public class MenuNavigation extends ActionBarActivity {

    DrawerLayout drawerLayout;
    ListView listView;
    String[] menu;
    ArrayAdapter<String> adapter;

    protected void onCreateDrawer() {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.menu_navigation);


        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        listView = (ListView)findViewById(R.id.list_layout);

        menu = new String[]{"Home", "Diary","Profile", "Exercises","Calendar","Reminders","Settings"};
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        listView.setAdapter(adapter);
        listView.setSelector(android.R.color.holo_blue_dark);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                displayView(position);
            }
        });
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
            default:break;
        }
    }
}
