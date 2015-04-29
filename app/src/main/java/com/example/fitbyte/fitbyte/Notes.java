package com.example.fitbyte.fitbyte;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Notes extends MenuNavigation {

    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);

    EditText note1;

    TextView save;

    public static SharedPreferences myNotes;
    public static SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_layout);
        super.onCreateDrawer();

        note1 = (EditText)findViewById(R.id.note1);
        note1.setOnTouchListener(new ChoiceTouchListener());

        save = (TextView)findViewById(R.id.save);
        save.setOnDragListener(new ChoiceDragListener());

        myNotes = getSharedPreferences("myNotes", Context.MODE_PRIVATE);
        editor = myNotes.edit();
    }

    public void addNote(String s){
        myNotes.getString("note1","");
        myNotes.getInt("day1", 0);
        editor.putInt("day1",day);
        editor.putString("note1",s);
        editor.commit();
    }
}
