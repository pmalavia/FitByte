package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class myCalendar extends MenuNavigation {

    CalendarView calendar;
    Notes notes = new Notes();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        super.onCreateDrawer();

        calendar = (CalendarView)findViewById(R.id.calendarView);
        final SharedPreferences myNotes = getSharedPreferences("myNotes", Context.MODE_PRIVATE);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if(dayOfMonth == notes.day) {
                    Toast.makeText(getApplicationContext(), myNotes.getString("note1", ""), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Note",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
