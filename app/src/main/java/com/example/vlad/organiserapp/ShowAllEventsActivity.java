package com.example.vlad.organiserapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

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
                Log.d("ShowAllEventsLogger", "id :" + id);
            }
        };

        // create OnClickListener for delete button
        deleteButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId() - deleteButtonIncreaseIndex;
                CustomEventXml.deleteEvent(id);
                parentLinearLayout.removeAllViews();
                showAllEvents();
                Log.d("ShowAllEventsLogger", "id :" + id);
            }
        };


        showAllEvents();
    }

    public void showAllEvents() {

        // get parent Layout
        parentLinearLayout = findViewById(R.id.eventsLinearLayout);
        ArrayList<CustomEvent> eventList;
        eventList = CustomEventXml.getcustomEvents();
        for (int i = 0; i < eventList.size(); i++) {

            // create child LinearLayout for TextView
            LinearLayout childTextViewLinearLayout = new LinearLayout(ShowAllEventsActivity.this);

            // create new TextView
            TextView newTextView = new TextView(ShowAllEventsActivity.this);
            newTextView.setId(textViewIncreaseIndex + eventList.get(i).getId());
            newTextView.setText("\n" + "" +
                    "ID : " + eventList.get(i).getId() + "\n" +
                    "Title : " + eventList.get(i).getTitle() + "\n" +
                    "Description : " + eventList.get(i).getDescription() + "\n" +
                    "Date : " + eventList.get(i).getDate());

            // create child LinearLayout for Buttons
            LinearLayout childButtonswLinearLayout = new LinearLayout(ShowAllEventsActivity.this);

            // create modify button
            Button modifyButton = new Button(ShowAllEventsActivity.this);
            modifyButton.setId(modifyButtonIncreaseIndex + eventList.get(i).getId());
            modifyButton.setText("Modify");
            modifyButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
            // add OnClickListener to modify button
            modifyButton.setOnClickListener(modifyButtonOnClickListener);

            // create delete button
            Button deleteButton = new Button(ShowAllEventsActivity.this);
            deleteButton.setId(deleteButtonIncreaseIndex + eventList.get(i).getId());
            deleteButton.setText("Delete");
            deleteButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
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
