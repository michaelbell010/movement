package com.dbs.movement;

public class EventData {

    private String eventName;
    private String eventDescription;
    private int eventImage;

    public EventData(String eventName, String eventDescription, int eventImage) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }
    public String getEventDescription() {
        return eventDescription;
    }
    public int getEventImage() {
        return eventImage;
    }
}

