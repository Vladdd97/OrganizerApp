package com.example.vlad.organiserapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowAllEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_events);


        showAllEvents();
    }

    public void showAllEvents(){

        LinearLayout eventsLinearLayout = findViewById(R.id.eventsLinearLayout);
        ArrayList<CustomEvent> eventList;
        eventList = CustomEventXml.getcustomEvents();
        for( int i = 0 ; i < eventList.size(); i++){

            TextView newTextView = new TextView(ShowAllEventsActivity.this);
            newTextView.setId( eventList.get(i).getId() );
            newTextView.setText("\n" + "" +
                    "ID : " + eventList.get(i).getId() + "\n" +
                    "Title : " + eventList.get(i).getTitle() + "\n" +
                    "Description : " + eventList.get(i).getDescription() + "\n" +
                    "Date : " + eventList.get(i).getDate());
            eventsLinearLayout.addView(newTextView);
        }

    }




    //try something , need to be deleted
    int count = 0;
    public void onClick_addTextViewButton(View v){

        count ++;
        LinearLayout eventsLinearLayout = findViewById(R.id.eventsLinearLayout);

        TextView txtName = new TextView(ShowAllEventsActivity.this);
        txtName.setId(count);
        txtName.setText("new text" + "\n" + "dadada");

        eventsLinearLayout.addView(txtName);
    }
}
