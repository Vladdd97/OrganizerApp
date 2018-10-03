package com.example.vlad.organiserapp;

import java.util.Date;


public class CustomEvent {

    private int id;
    private String title;
    private String description;
    private int isAlarmSet;
    private Date date;

    public CustomEvent() {
    }

    public CustomEvent(int id, String title, String description,int isAlarmSet, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isAlarmSet = isAlarmSet;
        this.date = date;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsAlarmSet() {
        return isAlarmSet;
    }

    public void setIsAlarmSet(int isAlarmSet) {
        this.isAlarmSet = isAlarmSet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "CustomEvent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isAlarmSet='" + isAlarmSet + '\'' +
                ", date=" + date +
                '}';
    }
}
