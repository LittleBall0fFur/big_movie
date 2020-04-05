package com.nhlstenden.bmdb;

public interface iObservable {
    void add(iObserver o);
    void remove(iObserver o);
    void notifier();
}
