package com.example.rajsaraogi.xenisis.holder;

public class Event {
    private String eventId, departmentId;
    public String eventName, eventDescription, eventPrice, coordinateName, coordinateMobile;
    public int imageName;

    public Event (String eventId, String departmentId, String eventName, String eventDescription, String eventPrice, String coordinateName, String coordinateMobile, int imageName){
        this.eventId= eventId;
        this.departmentId = departmentId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventPrice = eventPrice;
        this. coordinateName = coordinateName;
        this. coordinateMobile = coordinateMobile;
        this.imageName = imageName;
    }

    public String getEventId() {
        return eventId;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
