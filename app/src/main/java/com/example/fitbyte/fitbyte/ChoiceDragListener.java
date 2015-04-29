package com.example.fitbyte.fitbyte;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class ChoiceDragListener extends Activity implements View.OnDragListener {
    Notes notes = new Notes();
    public boolean onDrag(View v, DragEvent event){
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                //no action necessary
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                //no action necessary
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //no action necessary
                break;
            case DragEvent.ACTION_DROP:

                View view = (View)event.getLocalState();
                final TextView dropTarget = (TextView)v;
                TextView dropped = (TextView)view;
                notes.addNote(dropped.getText().toString());
                dropped.setText("");
                dropTarget.setText("Note Saved!");
                dropTarget.setBackgroundColor(Color.GREEN);

                dropTarget.postDelayed(new Runnable(){
                    public void run(){
                        dropTarget.setBackgroundResource(R.drawable.textview);
                        dropTarget.setText("Drag Note Here");
                    }
                },3000);


                dropTarget.setGravity(1);
                dropTarget.setTypeface(Typeface.DEFAULT_BOLD);

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                //no action necessary
                break;
            default:
                break;
        }
        return true;
    }
}

