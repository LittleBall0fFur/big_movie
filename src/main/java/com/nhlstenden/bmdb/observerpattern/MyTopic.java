package com.nhlstenden.bmdb.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class MyTopic implements Subject {

    public List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX= new Object();

    public MyTopic(){
        this.observers=new ArrayList<>();
    }

    /**
     * Registers observer to subject
     * @param obj observer
     */
    @Override
    public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if(!observers.contains(obj)) observers.add(obj);
        }
    }

    /**
     * Unregisteres observer from subject
     * @param obj observer
     */
    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    /**
     * Notifies all observers attached to subject
     */
    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;

        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }

    }

    @Override
    public Object getUpdate(Observer obj) {
        return this.message;
    }

    /**
     * Posts message to topic and notify observers
     * @param msg message
     */
    public void postMessage(String msg){
        System.out.println("Message Posted to Topic:"+msg);
        this.message=msg;
        this.changed=true;
        notifyObservers();
    }
}
