package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.os.Bundle;

public class Diary extends MenuNavigation {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_layout);
        super.onCreateDrawer();
    }
}

