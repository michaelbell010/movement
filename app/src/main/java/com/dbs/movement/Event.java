package com.dbs.movement;

public class Event {

    private String Date;
    private String Location;
    private String Time;
    private String Type;

    public Event() {
    }

    public Event(String date, String location, String time, String type) {
        Date = date;
        Location = location;
        Time = time;
        Type = type;
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


}
