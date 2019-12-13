package com.dbs.movement;

import android.media.Image;

public class Event {


    private String Date;
    private String Location;
    private String Time;
    private String Type;
    private String EventID;
    private String Desc;
    private String Title;
    private String Organiser;
    private String Image;

    public Event() {
    }

    public Event(String date, String location, String time, String type,String desc, String title, String eventID, String organiser, String image) {

        this.Date = date;
        this.Location = location;
        this.Time = time;
        this.Type = type;
        this.Desc = desc;
        this.Title = title;
        this.EventID = eventID;
        this.Organiser = organiser;
        this.Image = image;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }


    public String getEventID() {
        return EventID;
    }

    public void setEventID(String eventID) {
        EventID = eventID;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOrganiser() {
        return Organiser;
    }

    public void setOrganiser(String organiser) {
        Organiser = organiser;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

}
