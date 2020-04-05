package com.nhlstenden.bmdb;

public class ConcreteObserver implements iObserver {
    private ConcreteObservable concreteObservable;

    public ConcreteObserver (ConcreteObservable concreteObservable){
        this.concreteObservable = concreteObservable;
        concreteObservable.add(this);
    }

    public void update(){
        System.out.print(this.concreteObservable.getValue());
    }
}
