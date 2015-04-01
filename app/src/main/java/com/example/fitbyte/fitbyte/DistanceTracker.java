package com.example.fitbyte.fitbyte;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class DistanceTracker extends MenuNavigation implements LocationListener{

    Button start;
    Button stop;
    Button reset;
    Button finish;
    TextView timer;
    TextView time;
    TextView distance;
    private boolean stopped = false;
    private long startTime;
    private long elapsedTime;
    private Handler mHandler;
    private final int REFRESH_RATE = 100;
    private String hours,minutes,seconds;
    private long secs,mins,hrs;
    double latitude_one;
    double longitude_one;
    double latitude_two;
    double longitude_two;
    LocationManager lm;
    private boolean visited = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distancetracker_layout);
        super.onCreateDrawer();

        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        reset = (Button)findViewById(R.id.reset);
        finish = (Button)findViewById(R.id.finish);
        timer = (TextView)findViewById(R.id.chronometer);

        time = (TextView)findViewById(R.id.time);
        distance = (TextView)findViewById(R.id.distance);

        mHandler = new Handler();

        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        findLocation(location);
    }

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (double) (earthRadius * c);
        return dist;
    }

    public void onLocationChanged(Location location){
        findLocation(location);
    }
    public void onProviderDisabled(String arg0) {}
    public void onProviderEnabled(String arg0) {}
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}


    private void findLocation(Location location){
        if(!visited){
            if(location == null){
                latitude_one = 0.0;
                longitude_one = 0.0;
            }
            else{
                latitude_one = location.getLatitude();
                longitude_one = location.getLongitude();
            }
            visited = true;
        }
        else{
            if(location == null){
                latitude_two = 0.0;
                longitude_two = 0.0;
            }
            else{
                latitude_two = location.getLatitude();
                longitude_two = location.getLongitude();
            }
        }
    }

    public void startClick(View view){
        showStopButton();
        if(stopped){
            startTime = System.currentTimeMillis() - elapsedTime;
        }
        else{
            startTime = System.currentTimeMillis();
        }
        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer,0);
    }

    public void stopClick(View view){
        hideStopButton();
        mHandler.removeCallbacks(startTimer);
        stopped = true;
    }

    public void resetClick(View view){
        stopped = false;
        timer.setText("00:00:00");
    }

    public void finishClick(View view){
        double dist = distFrom(latitude_one,longitude_one,latitude_two,longitude_two);
        time.setText(hours + " hours, " + minutes + " minutes, " + seconds + " seconds.");
        distance.setText(dist +"");
    }


    private void showStopButton(){
        start.setVisibility(View.GONE);
        reset.setVisibility(View.GONE);
        finish.setVisibility(View.GONE);
        stop.setVisibility(View.VISIBLE);
    }

    private void hideStopButton(){
        start.setVisibility(View.VISIBLE);
        reset.setVisibility(View.VISIBLE);
        finish.setVisibility(View.VISIBLE);
        stop.setVisibility(View.GONE);
    }

    private Runnable startTimer = new Runnable() {
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this,REFRESH_RATE);
        }
    };

    private void updateTimer(float time){
        secs = (long)(time/1000);
        mins = (long)((time/1000)/60);
        hrs = (long)(((time/1000)/60)/60);

        secs = secs % 60;
        seconds = String.valueOf(secs);
        if(secs == 0){
            seconds = "00";
        }
        if(secs < 10 && secs > 0){
            seconds = "0"+seconds;
        }

        mins = mins % 60;
        minutes = String.valueOf(mins);
        if(mins == 0){
            minutes = "00";
        }
        if(mins < 10 && mins > 0){
            minutes = "0"+minutes;
        }

        hours = String.valueOf(hrs);
        if(hrs == 0){
            hours = "00";
        }
        if(hrs < 10 && hrs > 0){
            hours = "0"+ hours;
        }

        timer.setText(hours + ":" + minutes + ":" + seconds);
    }

}
