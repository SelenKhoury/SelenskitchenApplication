package com.example.selenskitchenapplication;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an intent for the notification receiver
        Intent notificationIntent = new Intent(this, Receiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the alarm to trigger the notification every 3000 sec
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long interval = 3000;
        long triggerTime = System.currentTimeMillis() + interval;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime, interval, pendingIntent);
    }

    // Handle the button click to display a toast
    public void showToast(View view) {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // Show the exit dialog when the back button is pressed
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit Confirmation");
        dialog.setMessage("Are you sure you want to exit?");
        dialog.setNegativeButton("No", null);
        dialog.setPositiveButton("Yes", (dialogInterface, i) -> finish());
        dialog.setIcon(R.drawable.ic_baseline_emoji_people_24);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public void start(View view){
            startActivity(new Intent(this, WelcomeActivity.class));
    }
}



// the notes in עברית

/*
// OnCreate

    //load option menu from an activity (linking xml option menu with the java activity)
    /*
    this method loads the menu design into this activity
     */
    //load option menu from an activity (linking xml option menu with the java activity)
    /*
    this method loads the menu design into this activity
     */
