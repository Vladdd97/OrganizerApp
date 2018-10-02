package com.example.vlad.organiserapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class ShowAllEventsActivity extends AppCompatActivity {

    public int textViewIncreaseIndex = 0;
    public int modifyButtonIncreaseIndex = 1000;
    public int deleteButtonIncreaseIndex = 2000;
    public View.OnClickListener modifyButtonOnClickListener;
    public View.OnClickListener deleteButtonOnClickListener;
    public LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_events);

        // create OnClickListener for modify button
        modifyButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId() - modifyButtonIncreaseIndex;

                Intent modifyEventIntent = new Intent(ShowAllEventsActivity.this,ModifyEventActivity.class);
                modifyEventIntent.putExtra("eventId",id);
                startActivityForResult(modifyEventIntent,RequestCodes.MODIFY_ACTIVITY_RESULT);

                Log.d("ShowAllEventsLogger", "id :" + id);
            }
        };

        // create OnClickListener for delete button
        deleteButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId() - deleteButtonIncreaseIndex;
                CustomEventXmlParser.deleteEvent(id);
                parentLinearLayout.removeAllViews();
                showAllEvents();
                Log.d("ShowAllEventsLogger", "id :" + id);
            }
        };


        showAllEvents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == RequestCodes.MODIFY_ACTIVITY_RESULT){
            parentLinearLayout.removeAllViews();
            showAllEvents();
        }

    }

    public void showAllEvents() {

        // get parent Layout
        parentLinearLayout = findViewById(R.id.eventsLinearLayout);
        ArrayList<CustomEvent> eventList;
        eventList = CustomEventXmlParser.getcustomEvents();
        for (int i = 0; i < eventList.size(); i++) {

            // create child LinearLayout for TextView
            LinearLayout childTextViewLinearLayout = new LinearLayout(ShowAllEventsActivity.this);

            // create new TextView
            TextView newTextView = new TextView(ShowAllEventsActivity.this);
            newTextView.setId(textViewIncreaseIndex + eventList.get(i).getId());
            Date eventDate = eventList.get(i).getDate();
            newTextView.setText("\n" + "" +
                    "ID : " + eventList.get(i).getId() + "\n" +
                    "Title : " + eventList.get(i).getTitle() + "\n" +
                    "Description : " + eventList.get(i).getDescription() + "\n" +
                    "Date : " + eventDate.getDate() + "/" + eventDate.getMonth() + eventDate.getYear() +"\n" +
                    "Time: " + eventDate.getHours() + ":" + eventDate.getMinutes());

            // create child LinearLayout for Buttons
            LinearLayout childButtonswLinearLayout = new LinearLayout(ShowAllEventsActivity.this);

            // create modify button
            Button modifyButton = new Button(ShowAllEventsActivity.this);
            modifyButton.setId(modifyButtonIncreaseIndex + eventList.get(i).getId());
            modifyButton.setText("Modify");
            // style of button
            modifyButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            modifyButton.setTextColor(getResources().getColor(android.R.color.white));
            // add OnClickListener to modify button
            modifyButton.setOnClickListener(modifyButtonOnClickListener);

            // create delete button
            Button deleteButton = new Button(ShowAllEventsActivity.this);
            deleteButton.setId(deleteButtonIncreaseIndex + eventList.get(i).getId());
            deleteButton.setText("Delete");
            // style of button
            deleteButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            deleteButton.setTextColor(getResources().getColor(android.R.color.white));
            // add OnClickListener to delete button
            deleteButton.setOnClickListener(deleteButtonOnClickListener);

            // add TextView to child LinearLayout for TextView
            childTextViewLinearLayout.addView(newTextView);

            // add Buttons to child LinearLayout for Buttons
            childButtonswLinearLayout.addView(modifyButton);
            childButtonswLinearLayout.addView(deleteButton);

            // add childLinearLayouts to parent LinearLayout
            parentLinearLayout.addView(childTextViewLinearLayout);
            parentLinearLayout.addView(childButtonswLinearLayout);
        }

    }

    public void onClick_backButton(View v){
        finish();
    }


}
