package com.nhlstenden.bmdb;

import java.util.ArrayList;
import java.util.List;

public class ConcreteObservable implements iObservable {
    List<iObserver> observers = new ArrayList<iObserver>();

    private int temp = 25;
    public void add(iObserver o){
        this.observers.add(o);
    }

    public void remove(iObserver o){
        this.observers.remove(o);
    }

    public void notifier(){
        for (iObserver o : this.observers) {
            o.update();
        }
    }

    public int getValue(){
        return this.temp;
    }
}
