package com.example.vlad.organiserapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowEventNotificationActivity extends AppCompatActivity {


    private CustomEvent customEvent;

    private TextView descriptionOfEvent;
    private TextView titleOfEvent;

    private Intent fromActivity;
    int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_eventa_notification);

        fromActivity = getIntent();
        eventId = fromActivity.getExtras().getInt("eventId");
        customEvent = CustomEventXmlParser.getEventById(eventId);

        // find all TextInputs
        descriptionOfEvent = findViewById(R.id.descriptionOfEvent);
        titleOfEvent = findViewById(R.id.titleOfEvent);

        titleOfEvent.setText(customEvent.getTitle());
        descriptionOfEvent.setText(customEvent.getDescription());
    }
}
