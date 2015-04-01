package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Diary extends MenuNavigation {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_layout);
        super.onCreateDrawer();
    }

    public void running(View view){
        Intent myIntent = new Intent(this, DistanceTracker.class);
        startActivity(myIntent);

    }
}

