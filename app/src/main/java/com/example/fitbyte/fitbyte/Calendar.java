package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.os.Bundle;

public class Calendar extends MenuNavigation {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        super.onCreateDrawer();

    }
}
