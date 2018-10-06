package com.example.vlad.organiserapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ModifyEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener , DatePickerDialog.OnDateSetListener {

    private TextView descriptionOfEvent;
    private TextView titleOfEvent;
    private TextView timeTextView;
    private TextView dateTextView;
    private CustomEvent customEvent;
    private Switch isAlarmSet;

    private Intent fromActivity;
    int eventId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event);

        // find all TextInputs
        descriptionOfEvent = findViewById(R.id.descriptionOfEvent);
        titleOfEvent = findViewById(R.id.titleOfEvent);
        timeTextView = findViewById(R.id.timeTextView);
        dateTextView = findViewById(R.id.dateTextView);

        isAlarmSet = findViewById(R.id.isAlarmSetTextView);

        // get eventById
        fromActivity = getIntent();
        eventId = fromActivity.getExtras().getInt("eventId");
        // find customEvent
        customEvent = CustomEventXmlParser.getEventById(eventId);
        Log.d("Modifyactivity","Passed customEventObject : " + customEvent.toString());

        // set all TextInputs
        titleOfEvent.setText(customEvent.getTitle());
        descriptionOfEvent.setText(customEvent.getDescription());
        timeTextView.setText(customEvent.getDate().getHours() + ":" + customEvent.getDate().getMinutes());
        dateTextView.setText(customEvent.getDate().getDate() + "/" + (customEvent.getDate().getMonth()+1) +
                "/" + customEvent.getDate().getYear());
        isAlarmSet.setChecked(customEvent.getIsAlarmSet() == 1 ? true : false);

    }

    public void onClick_chooseTimeButton(View v){
        TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyEventActivity.this,ModifyEventActivity.this,
                customEvent.getDate().getHours(),customEvent.getDate().getMinutes(),true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        customEvent.getDate().setHours(hourOfDay);
        customEvent.getDate().setMinutes(minute);
        customEvent.getDate().setSeconds(0);

        timeTextView.setText(hourOfDay+":"+minute);

    }

    public void onClick_chooseDateButton(View v){
        DatePickerDialog dialog = new DatePickerDialog(ModifyEventActivity.this, ModifyEventActivity.this,
                customEvent.getDate().getYear(),  customEvent.getDate().getMonth(),  customEvent.getDate().getDate());
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        customEvent.getDate().setDate(dayOfMonth);
        customEvent.getDate().setMonth(monthOfYear);
        customEvent.getDate().setYear(year);
        dateTextView.setText(customEvent.getDate().getDate() + "/" + (customEvent.getDate().getMonth()+1) +
                "/" + customEvent.getDate().getYear());
    }



    public void onClick_saveButton(View v){

        if (isAlarmSet.isChecked())
            customEvent.setIsAlarmSet(1);
        else
            customEvent.setIsAlarmSet(0);

        customEvent.setTitle(titleOfEvent.getText().toString());
        customEvent.setDescription(descriptionOfEvent.getText().toString());
        CustomEventXmlParser.modifyXml(customEvent);

        finish();
    }



    public void onClick_backButton(View v){
        finish();
    }
}
