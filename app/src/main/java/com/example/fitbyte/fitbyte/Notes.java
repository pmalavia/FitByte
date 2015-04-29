package com.example.fitbyte.fitbyte;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Notes extends MenuNavigation {

    Calendar cal = Calendar.getInstance();
    static int day;

    EditText note1;
    TextView save;

    public static SharedPreferences myNotes;
    public static SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_layout);
        super.onCreateDrawer();

        day = cal.get(Calendar.DAY_OF_MONTH);
        note1 = (EditText)findViewById(R.id.note1);
        note1.setOnTouchListener(new ChoiceTouchListener());

        save = (TextView)findViewById(R.id.save);
        save.setOnDragListener(new ChoiceDragListener());

        myNotes = getSharedPreferences("myNotes", Context.MODE_PRIVATE);
    }

    public void addNote(String s){
        editor = myNotes.edit();

        myNotes.getInt("day1", 0);
        editor.putInt("day1", day);

        myNotes.getString("note1","");
        editor.putString("note1", "Note 1 : " + s);
        editor.commit();

    }


}
