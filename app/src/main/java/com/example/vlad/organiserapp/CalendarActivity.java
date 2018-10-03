package com.example.vlad.organiserapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private String dateOfEvent;
    private CalendarView calendarView;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);

        // set fileName variable of CustomXml class
        // getFileDir() - Returns a File representing an internal directory for your app
        String path = getFilesDir() + File.separator + "OrganiserEvents.xml";
        CustomEventXmlParser.fileName  = path;

        date = new Date();
        dateOfEvent = date.getDate() + "/" + date.getMonth() + "/" + date.getYear();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getBaseContext(),"You have selected : " + dayOfMonth + "day ",Toast.LENGTH_SHORT).show();
                dateOfEvent = dayOfMonth + "/" + (month+1) + "/" + year;
            }
        });
    }


    public void onClick_addEventButton(View v){

        Intent addEventIntent = new Intent(CalendarActivity.this,AddEventActivity.class);
        addEventIntent.putExtra("dateOfEvent",dateOfEvent);
        startActivity(addEventIntent);
    }


    public void onClick_getEvents(View v){

        Intent getEventIntent = new Intent(CalendarActivity.this,ShowAllEventsActivity.class);
        startActivity(getEventIntent);


    }

    public void onClick_setNotificationButton(View v) {

        Calendar calendar = Calendar.getInstance();
        Intent setNotificationIntent = new Intent(CalendarActivity.this,NotificationReceiver.class);
        //setNotificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //need to be modified
        setNotificationIntent.putExtra("eventId",1);

        PendingIntent pedingIntent = PendingIntent.getBroadcast(getApplicationContext(),RequestCodes.NOTIFICATION_REQUEST_CODE,
                setNotificationIntent,PendingIntent.FLAG_UPDATE_CURRENT );

        AlarmManager alarManager =  (AlarmManager) getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
            alarManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+10000,pedingIntent);
        else
            alarManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+10000,pedingIntent);

    }


}
