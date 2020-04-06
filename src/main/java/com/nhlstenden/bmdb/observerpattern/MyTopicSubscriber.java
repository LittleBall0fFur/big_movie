package com.nhlstenden.bmdb.observerpattern;

public class MyTopicSubscriber implements Observer {

    private String name;
    private Subject topic;
    public String message;

    public MyTopicSubscriber(String nm){
        this.name=nm;
    }

    /**
     * Updates message and notifys observers
     */
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: No new message");
        }else {
            this.message = msg;
        }
    }

    /**
     * Set subject on this observer
     * @param sub subject
     */
    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }

    /**
     * Get name from observer
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get message from observer
     * @return message
     */
    public String getMessage() {
        return this.message;
    }
}