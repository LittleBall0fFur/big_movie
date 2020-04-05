package com.nhlstenden.bmdb.observerpattern;

public interface Observer {

    // Method to update the observer, used by subject
    public void update();

    // Attach with subject to observe
    public void setSubject(Subject sub);

    // Get name of observer
    public String getName();

    // Get message send by subject
    public String getMessage();
}
